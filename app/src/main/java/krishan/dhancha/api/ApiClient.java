package krishan.dhancha.api;

import android.net.http.HttpResponseCache;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import krishan.dhancha.BaseApp;
import krishan.dhancha.model.Movie;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;


public class ApiClient {

    private static TwitchTvApiInterface sTwitchTvService;
    public static TwitchTvApiInterface getTwitchTvApiClient() {
        if (sTwitchTvService == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            File cacheDir = new File(BaseApp.getContext().getCacheDir(),"response");
            Cache httpResponseCache = null;
            try {
                httpResponseCache = new Cache(cacheDir, 10 * 1024 * 1024);
            } catch (IOException e) {
                e.printStackTrace();
            }
            okHttpClient.setCache(httpResponseCache);
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://yts.re/api").setLogLevel(RestAdapter.LogLevel.BASIC).setClient(new OkClient(okHttpClient))
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