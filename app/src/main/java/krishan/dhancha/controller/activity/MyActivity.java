package krishan.dhancha.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import krishan.dhancha.R;
import krishan.dhancha.controller.base.NetworkActivity;
import timber.log.Timber;


public class MyActivity extends NetworkActivity {

    @InjectView(R.id.button)
    Button btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
        Timber.d("activity Created");

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.i("A button with ID %s was clicked to say '%s'.", btn_test.getId(), btn_test.getText());
                Intent in =new Intent(MyActivity.this,HomeActivity.class);
                startActivity(in);
            }
        });
    }

}
