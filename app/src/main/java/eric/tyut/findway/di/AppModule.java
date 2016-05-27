package eric.tyut.findway.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mzz on 2016/5/27.
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context){
        mContext = context;
    }

    @Provides
    Context provideContext(){
        return mContext;
    }
}
