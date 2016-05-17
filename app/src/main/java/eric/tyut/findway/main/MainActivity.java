package eric.tyut.findway.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eric.tyut.findway.R;
import eric.tyut.findway.base.BaseActivity;
import eric.tyut.findway.show.ResultActivity;
import eric.tyut.findway.util.LogUtil;

public class MainActivity extends BaseActivity implements IViewMain {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.txt_from)
    EditText from;

    @Bind(R.id.txt_to)
    EditText to;

    IPresenter presenterMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        presenterMain = new PresenterMain(this);

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
        startActivity(intent);
    }
}
