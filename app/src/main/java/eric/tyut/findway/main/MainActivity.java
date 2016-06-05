package eric.tyut.findway.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eric.tyut.findway.R;
import eric.tyut.findway.base.BaseActivity;
import eric.tyut.findway.di.AppComponent;
import eric.tyut.findway.di.DaggerMainComponent;
import eric.tyut.findway.di.MainModule;
import eric.tyut.findway.show.ResultActivity;

public class MainActivity extends BaseActivity implements IViewMain {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.txt_from)
    EditText from;

    @Bind(R.id.txt_to)
    EditText to;

    @Inject
    IPresenter presenterMain;

    AppComponent appComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        DaggerMainComponent.builder().appComponent(appComponent).mainModule(new MainModule(this)).build().inject(this);
//        presenterMain = new PresenterMain(this);

    }

    @OnClick({R.id.btn_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                presenterMain.onClickSearch();
                break;
        }
    }

    @Override
    public String getFrom() {
        return from.getText().toString();
    }

    @Override
    public String getTo() {
        return to.getText().toString();
    }

    @Override
    public void showResult(String fromResponse, String toResponse) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("from", fromResponse);
        intent.putExtra("to", toResponse);
        intent.putExtra("fromStationName",getFrom());
        intent.putExtra("toStationName",getTo());
        startActivity(intent);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void setupAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
