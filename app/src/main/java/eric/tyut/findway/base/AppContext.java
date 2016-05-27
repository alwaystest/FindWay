package eric.tyut.findway.base;

import android.app.Application;
import android.content.Context;

import eric.tyut.findway.di.AppComponent;
import eric.tyut.findway.di.AppModule;
import eric.tyut.findway.di.DaggerAppComponent;

/**
 * Created by eric on 16/3/28.
 */
public class AppContext extends Application {
    private AppComponent appComponent;

    public static AppContext get(Context context){
        return (AppContext) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
