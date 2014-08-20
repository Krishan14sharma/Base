package krishan.dhancha.api;

import java.util.List;

import krishan.dhancha.model.Movie;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;


public class ApiClient {

    private static TwitchTvApiInterface sTwitchTvService;
    public static TwitchTvApiInterface getTwitchTvApiClient() {
        if (sTwitchTvService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://yts.re/api").setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
            sTwitchTvService = restAdapter.create(TwitchTvApiInterface.class);
        }
        return sTwitchTvService;
    }
    public interface TwitchTvApiInterface {
        @GET("/upcoming.json")
        void getStreams(@Query("limit") int limit, @Query("set") int offset, Callback<List<Movie>> callback);
    }
}