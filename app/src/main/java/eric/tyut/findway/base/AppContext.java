package eric.tyut.findway.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by eric on 16/3/28.
 */
public class AppContext extends Application {
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
