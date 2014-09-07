package krishan.dhancha.api.helper;

import android.widget.Toast;

import krishan.dhancha.BaseApp;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ANIRUDH on 07-Sep-14.
 */
public class CustomErrorHandler implements ErrorHandler {


    @Override
    public Throwable handleError(RetrofitError retrofitError) {
        Response r = retrofitError.getResponse();
        if (r != null && r.getStatus() == 408) {
            Toast.makeText(BaseApp.getContext(),"Connection timed out",Toast.LENGTH_SHORT).show();
        }
        return retrofitError;
    }
}
