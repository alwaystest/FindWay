package eric.tyut.findway.main;

import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import javax.inject.Inject;

import eric.tyut.findway.base.AppContext;
import eric.tyut.findway.util.LogUtil;

/**
 * Created by Mzz on 2016/5/17.
 */
public class PresenterMain implements IPresenter {
    private static String TAG = "PresenterMain";
    IViewMain mView;
    ModelMain modelMain;

    String fromResponse = null;
    String toResponse = null;

    @Inject
    public PresenterMain(IViewMain view, ModelMain modelMain) {
        mView = view;
        this.modelMain = modelMain;
    }

    @Override
    public void onClickSearch() {
        String from = mView.getFrom();
        String to = mView.getTo();
        LogUtil.d(TAG, from + "\t" + to);
        if (TextUtils.isEmpty(from) || TextUtils.isEmpty(to)) {
            mView.showError("输入不能为空");
            return;
        }
        mView.onLoadStart();
        modelMain.loadFrom(from, new Response.Listener<String>() {
            @Override
            public void onResponse(final String FromResponse) {
                fromResponse = FromResponse;
                ifAllComplete();
            }
        }, new ErrorListener());
        modelMain.loadTo(to, new Response.Listener<String>() {
            @Override
            public void onResponse(String ToResponse) {
                toResponse = ToResponse;
                ifAllComplete();
            }
        },  new ErrorListener());
    }

    public void ifAllComplete() {
        if (TextUtils.isEmpty(fromResponse) || TextUtils.isEmpty(toResponse)) return;
        mView.onLoadComplete();
        mView.showResult(fromResponse, toResponse);
    }

    class ErrorListener implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            fromResponse = null;
            toResponse = null;
            mView.onLoadComplete();
            mView.showError("网络故障");
        }
    }
}
