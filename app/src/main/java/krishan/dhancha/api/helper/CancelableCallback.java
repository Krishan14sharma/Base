package krishan.dhancha.api.helper;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CancelableCallback implements Callback {

    private final Callback callback;

    private boolean canceled;

    public CancelableCallback(Callback callback) {
        this.callback = callback;
        canceled = false;
    }

    public void cancel() {
        canceled = true;
    }

    @Override
    public void success(Object o, Response response) {
        if (!canceled) {
            callback.success(o, response);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        if (!canceled) {
            callback.failure(error);
        }
    }
}