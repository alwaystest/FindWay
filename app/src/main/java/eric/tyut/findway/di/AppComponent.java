package eric.tyut.findway.di;

import android.content.Context;

import dagger.Component;
import eric.tyut.findway.base.BaseActivity;

/**
 * Created by Mzz on 2016/5/20.
 */
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(BaseActivity activity);
    Context getContext();
}
