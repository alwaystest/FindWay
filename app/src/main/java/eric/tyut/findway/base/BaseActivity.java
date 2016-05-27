package eric.tyut.findway.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import eric.tyut.findway.R;
import eric.tyut.findway.di.AppComponent;

/**
 * Created by eric on 16/3/28.
 */
public abstract class BaseActivity extends AppCompatActivity implements IView {
    protected String TAG = getClass().getSimpleName();

    ProgressDialog pd;
    AppComponent appComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAppComponent(AppContext.get(this).getAppComponent());
    }

    protected abstract void setupAppComponent(AppComponent appComponent);

    @Override
    public void onLoadStart() {
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage(getResources().getString(R.string.loading));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pd.show();
            }
        });
    }

    @Override
    public void onLoadComplete() {
        if (pd != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pd.dismiss();
                    pd = null;
                }
            });
        }
    }
}
