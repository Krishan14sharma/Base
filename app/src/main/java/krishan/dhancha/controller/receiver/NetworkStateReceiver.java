package krishan.dhancha.controller.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import krishan.dhancha.event.NetworkStateChanged;
import krishan.dhancha.helper.NetworkUtil;

public class NetworkStateReceiver extends BroadcastReceiver
{

    // post event if there is no Internet connection
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"haha",Toast.LENGTH_SHORT).show();
        if (intent.getExtras() != null) {
            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                // there is Internet connection
                EventBus.getDefault().post(new NetworkStateChanged(true, NetworkUtil.getConnectivityStatus(context)));
            } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                // no Internet connection, send network state changed
                EventBus.getDefault().post(new NetworkStateChanged(false, NetworkUtil.TYPE_NOT_CONNECTED));
            }
        }
    }}