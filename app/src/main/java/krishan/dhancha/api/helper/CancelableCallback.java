package krishan.dhancha.api.helper;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CancelableCallback<T> implements Callback<T> {

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
    public void success(T t, Response response) {
        if (!canceled) {
            callback.success(t, response);
        }



    }

    @Override
    public void failure(RetrofitError retrofitError)
    {  if (!canceled) {
        callback.failure(retrofitError);
    }
    }
}