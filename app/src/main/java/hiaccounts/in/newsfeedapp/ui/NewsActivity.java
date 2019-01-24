package hiaccounts.in.newsfeedapp.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import hiaccounts.in.newsfeedapp.R;
import hiaccounts.in.newsfeedapp.adapters.NewsAdapter;
import hiaccounts.in.newsfeedapp.models.Newsfeed;
import hiaccounts.in.newsfeedapp.models.Row;

public class NewsActivity extends AppCompatActivity implements NewsViewInterface {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerNews)
    RecyclerView recyclerNews;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeToRefresh;
    private String TAG = "NewsActivity";

    private NewsPresenter newsPresenter;
    private NewsAdapter newsAdapter;
    private List<Row> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setupMVP();
        setupViews();
        getNewsList();

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffle();
            }
        });

        swipeToRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shuffle();
                swipeToRefresh.setRefreshing(false);
            }
        });
    }

    private void shuffle() {
        setupMVP();
        getNewsList();

    }


    private void setupMVP() {
        newsPresenter = new NewsPresenter(this);
    }

    private void setupViews() {
        setSupportActionBar(toolbar);
        recyclerNews.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getNewsList() {
        newsPresenter.getNews();
    }


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayNews(Newsfeed newsResponse) {
        if (newsResponse != null) {
            newsList = newsResponse.getRows();
            Log.v(TAG, newsResponse.getTitle());

            if (newsList!=null && newsList.size()>0){
                Collections.shuffle(newsList, new Random(System.currentTimeMillis()));
                newsAdapter = new NewsAdapter(newsList, NewsActivity.this);
                recyclerNews.setAdapter(newsAdapter);
            }

        } else {
            Log.d(TAG, "News response null");
        }
    }

    @Override
    public void displayError(String s) {
        Toast.makeText(this, "server failed.", Toast.LENGTH_SHORT).show();
    }
}
