package krishan.dhancha.api;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import krishan.dhancha.BaseApp;
import krishan.dhancha.api.helper.CustomErrorHandler;
import krishan.dhancha.model.Movie;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;


public class ApiClient {

    private static ApiInterface sTwitchTvService;

    public static ApiInterface getApiClient() {
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
            RestAdapter restAdapter = new RestAdapter.Builder().setErrorHandler(new CustomErrorHandler())
                    .setEndpoint("https://yts.re/api").setLogLevel(RestAdapter.LogLevel.HEADERS).setClient(new OkClient(okHttpClient))
//                  .setRequestInterceptor(new RequestInterceptor() {
//                @Override
//                public void intercept(RequestInterceptor.RequestFacade request) {
//                    request.addHeader("Accept", "application/json;versions=1");
//                    if (false) {
//                        int maxAge = 60; // read from cache for 1 minute
//                        request.addHeader("Cache-Control", "public, max-age=" + maxAge);
//                    } else {
//                        int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
//                        request.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
//                    }
//                }})
                    .build();



                sTwitchTvService=restAdapter.create(ApiInterface.class);
            }
            return sTwitchTvService;
    }



    public interface ApiInterface {

        @Headers("Cache-Control: public, max-age=64000,max-stale=24000")
        @GET("/upcoming.json")
//        void getStreams(@Query("limit") int limit, @Query("set") int offset, Callback<List<Movie>> callback);
        Observable<List<Movie>> getStreams(@Query("limit") int limit, @Query("set") int offset);

    }
    //retrofit: no caching with post
}