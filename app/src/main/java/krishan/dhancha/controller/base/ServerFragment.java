package krishan.dhancha.controller.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import krishan.dhancha.api.ApiClient;

/**
 * Created by ANIRUDH on 31-Aug-14.
 */
public class ServerFragment extends Fragment {

    protected ApiClient.ApiInterface server;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        server=ApiClient.getApiClient();
    }



}
