package eric.tyut.findway.di;

import dagger.Component;
import eric.tyut.findway.show.IPresenter;
import eric.tyut.findway.show.ResultActivity;

/**
 * Created by Mzz on 2016/5/20.
 */
@Component(modules = {ShowModule.class})
public interface ShowComponent {
    void inject(ResultActivity resultActivity);
    IPresenter getPresenter();
}
