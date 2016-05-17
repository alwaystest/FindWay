package eric.tyut.findway.show;

import java.util.List;

import eric.tyut.findway.base.IView;
import eric.tyut.findway.model.Route;

/**
 * Created by Mzz on 2016/5/17.
 */
public interface IViewShow extends IView {
    public void updateList(List<Route> list);
}
