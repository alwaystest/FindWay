package eric.tyut.findway.base;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by eric on 16/3/28.
 */
@Module
public class AppContext extends Application {
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Provides
    public static Context getContext() {
        return context;
    }
}
