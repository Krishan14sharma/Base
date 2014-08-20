package krishan.dhancha.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import krishan.dhancha.R;
import krishan.dhancha.controller.base.NetworkActivity;
import krishan.dhancha.view.FloatLabeledEditText;
import timber.log.Timber;


public class MyActivity extends NetworkActivity implements Validator.ValidationListener {

    @InjectView(R.id.edt_username)
    FloatLabeledEditText mEdtUsername;
    @InjectView(R.id.edt_password)
    FloatLabeledEditText mEdtPassword;
    @InjectView(R.id.edt_cnfrm)
    FloatLabeledEditText mEdtCnfrm;
    @InjectView(R.id.edt_email)
    FloatLabeledEditText mEdtEmail;
    @InjectView(R.id.btn_signup)
    Button mBtnSignup;

    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        Timber.d("activity Created");

        mBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.i("A button with ID %s was clicked to say '%s'.", mBtnSignup.getId(), mBtnSignup.getText());
                Intent in = new Intent(MyActivity.this, HomeActivity.class);
                startActivity(in);
            }
        });
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(View view, Rule<?> rule) {

    }
}
