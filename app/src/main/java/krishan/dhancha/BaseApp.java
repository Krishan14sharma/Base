package krishan.dhancha;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import com.crashlytics.android.Crashlytics;

import krishan.dhancha.controller.receiver.NetworkStateReceiver;
import timber.log.Timber;

/**
 * Created by ANIRUDH on 16-Aug-14.
 */
public class BaseApp extends Application {
    private static BaseApp instance;


    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Crashlytics.start(this);
        instance=this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private static class CrashReportingTree extends Timber.HollowTree {
        @Override public void i(String message, Object... args) {
            Crashlytics.log(String.format(message, args));
        }

        @Override public void i(Throwable t, String message, Object... args) {
            i(message, args);
        }

        @Override public void e(String message, Object... args) {
            i("ERROR: " + message, args);
        }

        @Override public void e(Throwable t, String message, Object... args) {
            e(message, args);
            Crashlytics.logException(t);
        }
    }
}