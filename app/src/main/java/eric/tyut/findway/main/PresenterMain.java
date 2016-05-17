package eric.tyut.findway.main;

import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Response;

import eric.tyut.findway.base.AppContext;
import eric.tyut.findway.util.LogUtil;

/**
 * Created by Mzz on 2016/5/17.
 */
public class PresenterMain implements IPresenter {
    private static String TAG = "PresenterMain";
    IViewMain mView;
    ModelMain modelMain;

    public PresenterMain(IViewMain view) {
        mView = view;
        modelMain = new ModelMain();
    }

    @Override
    public void onClickSearch() {
        String from = mView.getFrom();
        final String to = mView.getTo();
        LogUtil.d(TAG, from + "\t" + to);
        if (TextUtils.isEmpty(from) || TextUtils.isEmpty(to)) {
            Toast.makeText(AppContext.getContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mView.onLoadStart();
        modelMain.loadFrom(from, new Response.Listener<String>() {
            @Override
            public void onResponse(final String FromResponse) {
                modelMain.loadTo(to, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ToResponse) {
                        mView.onLoadComplete();
                        mView.showResult(FromResponse,ToResponse);
                    }
                }, null);
            }
        }, null);
    }
}
