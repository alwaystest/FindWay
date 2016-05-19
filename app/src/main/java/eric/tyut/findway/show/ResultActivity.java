package eric.tyut.findway.show;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import eric.tyut.findway.R;
import eric.tyut.findway.base.BaseActivity;
import eric.tyut.findway.model.Route;

public class ResultActivity extends BaseActivity implements IViewShow{
    IPresenter mPresenter;
    ResultAdapter adapter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recycler_view)
    RecyclerView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mPresenter = new PresenterShow(this);

        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");

        adapter = new ResultAdapter(new ArrayList<Route>());
        resultList.setAdapter(adapter);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.calculate(from, to);
    }

    @Override
    public void updateList(final List<Route> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.clear();
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
