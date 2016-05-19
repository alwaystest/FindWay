package eric.tyut.findway.di;

import dagger.Module;
import dagger.Provides;
import eric.tyut.findway.main.IPresenter;
import eric.tyut.findway.main.IViewMain;
import eric.tyut.findway.main.ModelMain;
import eric.tyut.findway.main.PresenterMain;

/**
 * Created by Mzz on 2016/5/20.
 */
@Module
public class MainModule {
    private IViewMain mView;
    public MainModule(IViewMain viewMain) {
        mView = viewMain;
    }

    @Provides
    IViewMain provideViewMain(){
        return mView;
    }

    @Provides
    IPresenter providePresenterMain(IViewMain view, ModelMain modelMain) {
        return new PresenterMain(view, modelMain);
    }
}
