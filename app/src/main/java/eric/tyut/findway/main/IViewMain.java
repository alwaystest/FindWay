package eric.tyut.findway.main;

import eric.tyut.findway.base.IView;

/**
 * Created by Mzz on 2016/5/17.
 */
public interface IViewMain extends IView {
    public String getFrom();
    public String getTo();
    public void showResult(String fromResponse,String toResponse);
}
