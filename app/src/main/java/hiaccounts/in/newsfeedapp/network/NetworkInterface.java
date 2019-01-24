package hiaccounts.in.newsfeedapp.network;

import hiaccounts.in.newsfeedapp.models.Newsfeed;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkInterface {

    //get news feed
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Observable<Newsfeed> getNews();


}
