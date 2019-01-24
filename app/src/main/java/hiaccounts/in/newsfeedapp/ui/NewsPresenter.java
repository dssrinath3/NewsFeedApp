package hiaccounts.in.newsfeedapp.ui;

import android.util.Log;

import hiaccounts.in.newsfeedapp.models.Newsfeed;
import hiaccounts.in.newsfeedapp.network.NetworkClient;
import hiaccounts.in.newsfeedapp.network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter implements NewsPresenterInterface {
    private NewsViewInterface newsViewInterface;
    private String TAG = "NewsPresenter";

    public NewsPresenter(NewsViewInterface newsViewInterface) {
        this.newsViewInterface = newsViewInterface;
    }

    @Override
    public void getNews() {
        if (newsViewInterface!=null){
            newsViewInterface.showProgressBar();
            getObservable().subscribeWith(getObserver());
        }

    }

    public Observable<Newsfeed> getObservable() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public DisposableObserver<Newsfeed> getObserver(){
        return new DisposableObserver<Newsfeed>() {

            @Override
            public void onNext(@NonNull Newsfeed movieResponse) {
                Log.d(TAG,"OnNext"+movieResponse.getRows());
                if (newsViewInterface!=null) {
                    newsViewInterface.displayNews(movieResponse);
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                newsViewInterface.displayError("Error fetching news Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                newsViewInterface.hideProgressBar();
            }
        };
    }
}
