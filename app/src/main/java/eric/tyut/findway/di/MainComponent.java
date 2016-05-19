package eric.tyut.findway.di;

import dagger.Component;
import eric.tyut.findway.main.MainActivity;

/**
 * Created by Mzz on 2016/5/20.
 */
@Component(modules = {MainModule.class},dependencies = {AppComponent.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
