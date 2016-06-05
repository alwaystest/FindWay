package eric.tyut.findway.show;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import eric.tyut.findway.R;
import eric.tyut.findway.base.BaseActivity;
import eric.tyut.findway.di.AppComponent;
import eric.tyut.findway.di.DaggerShowComponent;
import eric.tyut.findway.di.ShowModule;
import eric.tyut.findway.model.RouteItem;

public class ResultActivity extends BaseActivity implements IViewShow {
    @Inject
    IPresenter mPresenter;
    @Inject
    ResultAdapter adapter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recycler_view)
    RecyclerView resultList;

    AppComponent appComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        DaggerShowComponent.builder().appComponent(appComponent).showModule(new ShowModule(this)).build().inject(this);

        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");
        String fromStationName = getIntent().getStringExtra("fromStationName");
        String toStationName = getIntent().getStringExtra("toStationName");

        resultList.setAdapter(adapter);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.calculate(from, to, fromStationName, toStationName);
    }

    @Override
    public void updateList(final List<RouteItem> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void setupAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
