package eric.tyut.findway.show;

import android.support.v4.util.ArrayMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import eric.tyut.findway.model.Route;
import eric.tyut.findway.model.RouteItem;
import eric.tyut.findway.util.LogUtil;

/**
 * Created by Mzz on 2016/5/17.
 */
public class PresenterShow implements IPresenter {
    private static final String TAG = "PresenterShow";
    IViewShow mView;

    @Inject
    public PresenterShow(IViewShow view) {
        mView = view;
    }

    @Override
    public void calculate(final String from, final String to, final String fromStationName, final String toStationName) {
        mView.onLoadStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Route> listFrom = JSON.parseObject(from, new TypeReference<List<Route>>() {
                });
                List<Route> listTo = JSON.parseObject(to, new TypeReference<List<Route>>() {
                });
                LogUtil.d(TAG, listFrom.size() + "listFrom.size()");
                LogUtil.d(TAG, listTo.size() + "listTo.size()");
                ArrayMap<String, LinkedList<Route>> arrayMap = new ArrayMap<>();
                for (Route item : listFrom) {
                    if (arrayMap.get(item.getToStation()) == null) {
                        arrayMap.put(item.getToStation(), new LinkedList<Route>());
                    }
                    arrayMap.get(item.getToStation()).add(item);
                }
                String fromStationId = arrayMap.valueAt(0).getFirst().getFromStation();
                ArrayList<RouteItem> routes = new ArrayList<>();
                for (Route item : listTo) {
                    if (fromStationId.equals(item.getFromStation())) {
                        RouteItem routeItem = new RouteItem(RouteItem.TYPE.STRAIGHT);
                        routeItem.setFromStation(fromStationName);
                        routeItem.setToStation(toStationName);
                        routeItem.setTrainNo1(item.getTrainNo());
                        routes.add(routeItem);
                        continue;
                    }
                    LinkedList<Route> temp = arrayMap.get(item.getFromStation());
                    if (temp != null) {
                        for (Route aim : temp) {
                            RouteItem routeItem = new RouteItem(RouteItem.TYPE.TRANSPORT);
                            routeItem.setFromStation(fromStationName);
                            routeItem.setTransStationId(item.getFromStation());
                            routeItem.setToStation(toStationName);
                            routeItem.setTrainNo1(aim.getTrainNo());
                            routeItem.setTrainNo2(item.getTrainNo());
                            routes.add(routeItem);
                        }
                    }
                }
                LogUtil.d(TAG, routes.size() + "routes.size()");
                Collections.sort(routes);
                mView.onLoadComplete();
                mView.updateList(routes);
            }
        }).start();
    }
}
