package eric.tyut.findway.show;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import eric.tyut.findway.R;
import eric.tyut.findway.base.API;
import eric.tyut.findway.base.AppContext;
import eric.tyut.findway.model.Route;

/**
 * Created by Mzz on 2016/5/18.
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    RequestQueue queue = Volley.newRequestQueue(AppContext.getContext());
    HashMap<String, String> cache = new HashMap<>();

    private List<Route> mData;

    public ResultAdapter(List<Route> list) {
        mData = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_routes, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(cache.get(mData.get(position).getFromStation()) == null) {
            holder.station.setText("Loading");
            StringRequest req = new StringRequest(Request.Method.POST, API.GET_STATION_NAME_BY_ID, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject json = new JSONObject(response.substring(1, response.length()));
                        String from = json.getString("name");
                        cache.put(mData.get(position).getFromStation(), from);
                        holder.station.setText(from);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, null) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("id", mData.get(position).getFromStation());
                    return map;
                }
            };
            queue.add(req);
        }else{
            holder.station.setText(cache.get(mData.get(position).getFromStation()));
        }
        holder.lineNo.setText(mData.get(position).getTrainNo());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
        }
    }

    public void addAll(List<Route> list){
        if(mData != null){
            mData.addAll(list);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView station;
        TextView lineNo;

        public ViewHolder(View itemView) {
            super(itemView);
            station = (TextView) itemView.findViewById(R.id.tv_station);
            lineNo = (TextView) itemView.findViewById(R.id.tv_line_no);
        }
    }
}
