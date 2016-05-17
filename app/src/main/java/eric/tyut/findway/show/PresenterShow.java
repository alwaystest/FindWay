package eric.tyut.findway.show;

import android.support.v4.util.ArrayMap;
import android.util.ArraySet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import eric.tyut.findway.model.Route;
import eric.tyut.findway.util.LogUtil;

/**
 * Created by Mzz on 2016/5/17.
 */
public class PresenterShow implements IPresenter {
    private static final String TAG = "PresenterShow";
    IViewShow mView;

    public PresenterShow(IViewShow view) {
        mView = view;
    }

    @Override
    public void calculate(final String from, final String to) {
        mView.onLoadStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Route> listFrom = JSON.parseObject(from, new TypeReference<List<Route>>() {
                });
                List<Route> listTo = JSON.parseObject(to, new TypeReference<List<Route>>() {
                });
                LogUtil.d(TAG, listFrom.size() + "");
                LogUtil.d(TAG, listTo.size() + "");
                ArrayMap<String, Route> arrayMap = new ArrayMap<>();
                for (Route item : listFrom) {
                    arrayMap.put(item.getToStation(), item);
                }
                ArrayList<Route> routes = new ArrayList<Route>();
                for (Route item : listTo) {
                    if (arrayMap.get(item.getFromStation()) != null) {
                        routes.add(item);
                    }
                }
                LogUtil.d(TAG, routes.size() + "");
                mView.onLoadComplete();
                mView.updateList(routes);
            }
        }).start();
    }
}
