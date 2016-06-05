package eric.tyut.findway.main;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import eric.tyut.findway.base.API;
import eric.tyut.findway.util.LogUtil;

/**
 * Created by Mzz on 2016/5/17.
 */
public class ModelMain {
    private static String TAG = "ModelMain";
    private RequestQueue mQueue;

    @Inject
    public ModelMain(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    /**
     * 获取与出发车站直接相连的车站
     *
     * @param fromStation
     */
    public void loadFrom(final String fromStation, final Response.Listener<String> onSuccess, final Response.ErrorListener onFailed) {
        if (fromStation == null) {
            return;
        }
        StringRequest req = new StringRequest(Request.Method.POST, API.GET_FROM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    LogUtil.d(TAG, response);
                    String list = new JSONObject(response).get("stations").toString();
                    if (onSuccess != null) {
                        onSuccess.onResponse(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (onFailed != null) {
                        onFailed.onErrorResponse(new VolleyError(e));
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if (onFailed != null) {
                    onFailed.onErrorResponse(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("from", fromStation);
                return map;
            }
        };
        mQueue.add(req);
    }

    /**
     * 获取与目的车站直接连接的车站
     *
     * @param toStation
     */
    public void loadTo(final String toStation, final Response.Listener<String> onSuccess, final Response.ErrorListener onFailed) {
        StringRequest req = new StringRequest(Request.Method.POST, API.GET_TO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    LogUtil.d(TAG, response);
                    String list = new JSONObject(response).get("stations").toString();
                    if (onSuccess != null) {
                        onSuccess.onResponse(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (onFailed != null) {
                        onFailed.onErrorResponse(new VolleyError(e));
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                if (onFailed != null) {
                    onFailed.onErrorResponse(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("to", toStation);
                return map;
            }
        };
        mQueue.add(req);
    }
}
