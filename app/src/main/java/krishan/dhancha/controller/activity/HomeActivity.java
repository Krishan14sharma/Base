package krishan.dhancha.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import krishan.dhancha.R;
import krishan.dhancha.api.ApiClient;
import krishan.dhancha.api.helper.CancelableCallback;
import krishan.dhancha.controller.adapter.MovieAdapter;
import krishan.dhancha.controller.base.NetworkActivity;
import krishan.dhancha.controller.base.ServerFragment;
import krishan.dhancha.model.Movie;
import krishan.dhancha.view.superlistview.OnMoreListener;
import krishan.dhancha.view.superlistview.SuperListview;
import krishan.dhancha.view.superlistview.SwipeDismissListViewTouchListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class HomeActivity extends NetworkActivity {

    public static final int NO_OF_ITEMS=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends ServerFragment implements SwipeRefreshLayout.OnRefreshListener,OnMoreListener, SwipeDismissListViewTouchListener.DismissCallbacks {


        @InjectView(R.id.list)
        SuperListview list_movie;

        CancelableCallback<List<Movie>> callback;
        List<Movie> movies;
        MovieAdapter adapter;
        int set=1;  //initially

        public PlaceholderFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
            movies=new ArrayList<Movie>();
            adapter=new MovieAdapter(getActivity(),movies);

            Callback callback1=new Callback<List<Movie>>() {
                @Override
                public void success(List<Movie> movie, Response response) {
                    movies.addAll(movie);
                    set=set+1;
                    adapter.notifyDataSetChanged();
                    list_movie.hideMoreProgress();
                    list_movie.hideProgress();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();
                }
            };

            callback=new CancelableCallback<List<Movie>>(callback1);

            server.getStreams(NO_OF_ITEMS, set,callback);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            ButterKnife.inject(this,rootView);
            list_movie.setAdapter(adapter);
            list_movie.setRefreshingColor(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
            list_movie.setRefreshListener(this);
            list_movie.setupMoreListener(this,1);
            list_movie.setupSwipeToDismiss(this,true);
            return rootView;
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            callback.cancel();
            callback=null;
        }


        @Override
        public void onRefresh() {
//            list_movie.showProgress();
//            set=1;
//            ApiClient.getApiClient().getStreams(NO_OF_ITEMS, set,callback);
//            Toast.makeText(getActivity(),"refresh",Toast.LENGTH_SHORT).show();
            throw new RuntimeException("This is a crash");

        }

        @Override
        public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {
            ApiClient.getApiClient().getStreams(NO_OF_ITEMS,set,callback);
        }

        @Override
        public boolean canDismiss(int position) {
            return true;
        }

        @Override
        public void onDismiss(ListView listView, int[] reverseSortedPositions) {
        }
    }


}
