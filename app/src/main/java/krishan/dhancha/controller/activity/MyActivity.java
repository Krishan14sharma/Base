package krishan.dhancha.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import krishan.dhancha.R;
import krishan.dhancha.controller.base.NetworkActivity;


public class MyActivity extends NetworkActivity {

    @NotEmpty(messageId = R.string.not_empty,order = 1)
    @RegExp(value = RegExp.EMAIL, messageId = R.string.validation_valid_email,order = 2)
    @InjectView(R.id.edt_email)
    EditText mEdtEmail;

    @MinLength(value = 3, messageId = R.string.validation_name_length, order = 3)
    @NotEmpty(messageId = R.string.not_empty)
    @InjectView(R.id.edt_password)
    EditText mEdtPassword;

    @MinLength(value = 3, messageId = R.string.validation_name_length, order = 4)
    @NotEmpty(messageId = R.string.not_empty)
    @InjectView(R.id.edt_confirm)
    EditText mEdtConfirm;

    @NotEmpty(messageId = R.string.not_empty)
    @InjectView(R.id.edt_phone)
    EditText mEdtPhone;

    @NotEmpty(messageId = R.string.not_empty)
    @InjectView(R.id.spinner)
    Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.inject(this);
        FormValidator.startLiveValidation(this, mEdtEmail, new SimpleErrorPopupCallback(this, false));
        FormValidator.startLiveValidation(this, mEdtConfirm, new SimpleErrorPopupCallback(this, false));

    }

    @OnClick(R.id.button)
    void pressed() {
        mEdtConfirm.setError(null);
        boolean isvalid = FormValidator.validate(this, new SimpleErrorPopupCallback(this));
        if (isvalid) {
            if(mEdtConfirm.getText().toString().equals(mEdtPassword.getText().toString())) {
                Intent in = new Intent(MyActivity.this, HomeActivity.class);
                startActivity(in);
            }else
            {
                mEdtConfirm.setError("Passwrd do not match");
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        FormValidator.stopLiveValidation(this);
    }

}
