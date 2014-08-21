package krishan.dhancha.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.devspark.appmsg.AppMsg;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import krishan.dhancha.R;
import krishan.dhancha.api.ApiClient;
import krishan.dhancha.controller.adapter.MovieAdapter;
import krishan.dhancha.controller.base.NetworkActivity;
import krishan.dhancha.model.Movie;
import krishan.dhancha.view.superlistview.SuperListview;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class HomeActivity extends NetworkActivity {

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
    public static class PlaceholderFragment extends Fragment {


        @InjectView(R.id.list)
        SuperListview list_movie;

        public PlaceholderFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            ButterKnife.inject(this,rootView);
            ApiClient.getTwitchTvApiClient().getStreams(10, 0, new Callback<List<Movie>>() {
                @Override
                public void success(List<Movie> justinTvStreamDatas, Response response) {
                    AppMsg msg = AppMsg.makeText(getActivity(), justinTvStreamDatas.get(0).getMovieTitle(), AppMsg.STYLE_CONFIRM)
                            .setAnimation(android.support.v7.appcompat.R.anim.abc_slide_in_top, android.support.v7.appcompat.R.anim.abc_slide_out_top);
                    msg.setPriority(AppMsg.PRIORITY_HIGH);
                    msg.setDuration(AppMsg.LENGTH_SHORT);
                    msg.show();
                    MovieAdapter adapter=new MovieAdapter(getActivity(),justinTvStreamDatas);
                    list_movie.setAdapter(adapter);
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    AppMsg msg = AppMsg.makeText(getActivity(), "Failed to Fetch Data!", AppMsg.STYLE_ALERT)
                            .setAnimation(android.support.v7.appcompat.R.anim.abc_slide_in_top, android.support.v7.appcompat.R.anim.abc_slide_out_top);
                    msg.setPriority(AppMsg.PRIORITY_HIGH);
                    msg.setDuration(AppMsg.LENGTH_SHORT);
                    msg.show();

                }
            });
            return rootView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
