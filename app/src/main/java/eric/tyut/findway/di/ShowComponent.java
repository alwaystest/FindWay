package eric.tyut.findway.di;

import dagger.Component;
import eric.tyut.findway.show.IPresenter;
import eric.tyut.findway.show.ResultActivity;
import eric.tyut.findway.show.ResultAdapter;

/**
 * Created by Mzz on 2016/5/20.
 */
@Component(modules = {ShowModule.class},dependencies = AppComponent.class)
public interface ShowComponent {
    void inject(ResultActivity resultActivity);
    IPresenter getPresenter();
    ResultAdapter getResultAdapter();
}
