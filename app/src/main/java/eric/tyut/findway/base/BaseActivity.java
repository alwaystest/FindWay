package eric.tyut.findway.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import eric.tyut.findway.R;

/**
 * Created by eric on 16/3/28.
 */
public class BaseActivity extends AppCompatActivity implements IView {
    protected String TAG = getClass().getSimpleName();

    ProgressDialog pd;

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
