package krishan.dhancha.controller.base;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.devspark.appmsg.AppMsg;

import de.greenrobot.event.EventBus;
import krishan.dhancha.api.ApiClient;
import krishan.dhancha.controller.receiver.NetworkStateReceiver;
import krishan.dhancha.event.NetworkStateChanged;
import krishan.dhancha.helper.NetworkUtil;

/**
 * Created by ANIRUDH on 16-Aug-14.
 */

public class NetworkActivity extends ActionBarActivity {

    public boolean isInternetconnected() {
        return isInternetconnected;
    }
    private boolean isInternetconnected=true;
    AppMsg msg;
    protected ApiClient.ApiInterface server;

    @Override
    protected void onStart() {
        super.onStart();
        server=ApiClient.getApiClient();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this); // register EventBus
        msg=AppMsg.makeText(this,"No Internet connection!", AppMsg.STYLE_ALERT).setAnimation(android.support.v7.appcompat.R.anim.abc_slide_in_top, android.support.v7.appcompat.R.anim.abc_slide_out_top);
        msg.setDuration(AppMsg.LENGTH_STICKY);
        msg.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.cancel();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(NetworkUtil.getConnectivityStatus(getApplicationContext())==0)
        {
            onEventMainThread(new NetworkStateChanged(false,NetworkUtil.TYPE_NOT_CONNECTED));
        }else
        {
            onEventMainThread(new NetworkStateChanged(true,NetworkUtil.getConnectivityStatus(getApplicationContext())));
        }
        PackageManager pm = getPackageManager();
        ComponentName compName = new ComponentName(getApplicationContext(),NetworkStateReceiver.class);
        pm.setComponentEnabledSetting(compName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PackageManager pm = getPackageManager();
        ComponentName compName = new ComponentName(getApplicationContext(),NetworkStateReceiver.class);
        pm.setComponentEnabledSetting(compName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); // unregister EventBus
        AppMsg.cancelAll(this);
    }

    public void onEventMainThread(NetworkStateChanged event) {
        if (isInternetconnected != event.isInternetConnected()) {
            if (!event.isInternetConnected()) {
                Toast.makeText(this, "No Internet connection!", Toast.LENGTH_SHORT).show();
                isInternetconnected = false;
                msg.show();
            } else {
                Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
                isInternetconnected = true;
                msg.cancel();
            }
        }
    }
}
