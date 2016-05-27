package eric.tyut.findway.di;

import android.content.Context;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import eric.tyut.findway.show.IPresenter;
import eric.tyut.findway.model.Route;
import eric.tyut.findway.show.IViewShow;
import eric.tyut.findway.show.PresenterShow;
import eric.tyut.findway.show.ResultAdapter;

/**
 * Created by Mzz on 2016/5/20.
 */
@Module
public class ShowModule {
    IViewShow mView;

    public ShowModule(IViewShow view) {
        mView = view;
    }

    @Provides
    IViewShow provideView() {
        return mView;
    }

    @Provides
    IPresenter provideIPresenter(IViewShow view) {
        return new PresenterShow(view);
    }

    @Provides
    ResultAdapter provideAdapter(Context context) {
        return new ResultAdapter(context, new ArrayList<Route>());
    }
}
