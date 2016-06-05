package eric.tyut.findway.show;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eric.tyut.findway.R;
import eric.tyut.findway.base.API;
import eric.tyut.findway.model.RouteItem;

/**
 * Created by Mzz on 2016/5/18.
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    RequestQueue queue;
    HashMap<String, String> cache;
    Context mContext;

    private List<RouteItem> mData;

    public ResultAdapter(Context context, List<RouteItem> list) {
        mContext = context;
        mData = list;
        queue = Volley.newRequestQueue(context);
        cache = new HashMap<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_routes, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mData.get(position).getmType() == RouteItem.TYPE.TRANSPORT) {
            onBindTransViewHolder(holder, position);
        } else {
            onBindStraightViewHolder(holder, position);
        }
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

    public void addAll(List<RouteItem> list) {
        if (mData != null) {
            mData.addAll(list);
        }
    }

    private void onBindTransViewHolder(final ViewHolder holder, final int position) {
        if (cache.get(mData.get(position).getTransStationId()) == null) {
            holder.transStation.setText(R.string.loading);
            StringRequest req = new StringRequest(Request.Method.POST, API.GET_STATION_BY_ID, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject json = new JSONObject(response.substring(1, response.length()));
                        String stationName = json.getString("name");
                        cache.put(mData.get(position).getTransStationId(), stationName);
                        if (holder.transStation.getText().equals(mContext.getString(R.string.loading))) {
                            holder.transStation.setText(stationName);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, null) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("id", mData.get(position).getTransStationId());
                    return map;
                }
            };
            queue.add(req);
        } else {
            holder.transStation.setText(cache.get(mData.get(position).getTransStationId()));
        }
        holder.type.setText(mContext.getString(R.string.Transport));
        holder.type.setBackgroundColor(mContext.getResources().getColor(R.color.colorBlue));
        holder.fromStation.setText(mData.get(position).getFromStation());
        holder.transStation.setVisibility(View.VISIBLE);
        holder.toStation.setText(mData.get(position).getToStation());
        holder.trainNo1.setText(mData.get(position).getTrainNo1());
        holder.trainNo2.setText(mData.get(position).getTrainNo2());
        holder.trainNo2.setVisibility(View.VISIBLE);
    }

    private void onBindStraightViewHolder(final ViewHolder holder, final int position) {
        holder.type.setText(mContext.getString(R.string.Straight));
        holder.type.setBackgroundColor(mContext.getResources().getColor(R.color.colorGreen));
        holder.fromStation.setText(mData.get(position).getFromStation());
        holder.transStation.setVisibility(View.GONE);
        holder.toStation.setText(mData.get(position).getToStation());
        holder.trainNo1.setText(mData.get(position).getTrainNo1());
        holder.trainNo2.setVisibility(View.GONE);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView type;
        TextView fromStation;
        TextView transStation;
        TextView toStation;
        TextView trainNo1;
        TextView trainNo2;

        public ViewHolder(View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.txt_type);
            fromStation = (TextView) itemView.findViewById(R.id.txt_from_station);
            toStation = (TextView) itemView.findViewById(R.id.txt_term_station);
            transStation = (TextView) itemView.findViewById(R.id.txt_trans_station);
            trainNo1 = (TextView) itemView.findViewById(R.id.txt_line_no_1);
            trainNo2 = (TextView) itemView.findViewById(R.id.txt_line_no_2);
        }
    }
}
