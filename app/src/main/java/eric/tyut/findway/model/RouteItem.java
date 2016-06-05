package eric.tyut.findway.model;

import android.support.annotation.NonNull;

/**
 * Created by Mzz on 2016/6/5.
 */
public class RouteItem implements Comparable<RouteItem> {

    public enum TYPE {STRAIGHT, TRANSPORT}

    private TYPE mType;
    private String fromStation;
    private String transStationId;
    private String toStation;
    private String trainNo1;
    private String trainNo2;

    public RouteItem(TYPE mType) {
        this.mType = mType;
    }

    public RouteItem(TYPE mType, String fromStation, String transStationId, String toStation, String trainNo1, String trainNo2) {
        this.mType = mType;
        this.fromStation = fromStation;
        this.transStationId = transStationId;
        this.toStation = toStation;
        this.trainNo1 = trainNo1;
        this.trainNo2 = trainNo2;
    }

    @Override
    public int compareTo(@NonNull RouteItem another) {
        if(this.mType == another.getmType()) return 0;
        if(this.mType == TYPE.STRAIGHT) return -1;
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteItem routeItem = (RouteItem) o;

        if (mType != routeItem.mType) return false;
        if (fromStation != null ? !fromStation.equals(routeItem.fromStation) : routeItem.fromStation != null)
            return false;
        if (transStationId != null ? !transStationId.equals(routeItem.transStationId) : routeItem.transStationId != null)
            return false;
        if (toStation != null ? !toStation.equals(routeItem.toStation) : routeItem.toStation != null)
            return false;
        if (trainNo1 != null ? !trainNo1.equals(routeItem.trainNo1) : routeItem.trainNo1 != null)
            return false;
        return !(trainNo2 != null ? !trainNo2.equals(routeItem.trainNo2) : routeItem.trainNo2 != null);

    }

    @Override
    public int hashCode() {
        int result = mType != null ? mType.hashCode() : 0;
        result = 31 * result + (fromStation != null ? fromStation.hashCode() : 0);
        result = 31 * result + (transStationId != null ? transStationId.hashCode() : 0);
        result = 31 * result + (toStation != null ? toStation.hashCode() : 0);
        result = 31 * result + (trainNo1 != null ? trainNo1.hashCode() : 0);
        result = 31 * result + (trainNo2 != null ? trainNo2.hashCode() : 0);
        return result;
    }

    public TYPE getmType() {
        return mType;
    }

    public void setmType(TYPE mType) {
        this.mType = mType;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getTransStationId() {
        return transStationId;
    }

    public void setTransStationId(String transStationId) {
        this.transStationId = transStationId;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getTrainNo1() {
        return trainNo1;
    }

    public void setTrainNo1(String trainNo1) {
        this.trainNo1 = trainNo1;
    }

    public String getTrainNo2() {
        return trainNo2;
    }

    public void setTrainNo2(String trainNo2) {
        this.trainNo2 = trainNo2;
    }
}
