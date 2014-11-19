package com.mapdaobackground.mapdaobg;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.RequestPasswordResetCallback;


public class MapdaoBGForgetPasswordActivity extends MapdaoBGActivity {

    EditText emailText;
    Button findPasswordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapdao_bgforget_password);
        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        emailText = (EditText) findViewById(R.id.editText_forget_password_email);
        findPasswordButton = (Button) findViewById(R.id.button_find_password);
        findPasswordButton.setOnClickListener(findPasswordListener);


    }
View.OnClickListener findPasswordListener = new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        String email = emailText.getText()
                .toString();
        if (email != null) {
            RequestPasswordResetCallback callback=new RequestPasswordResetCallback() {
                public void done(AVException e) {
                    if (e == null) {
                        Toast.makeText(activity,
                                R.string.forget_password_send_email,
                                Toast.LENGTH_LONG).show();
                        Intent LoginIntent = new Intent(activity,
                                MapdaoBGLoginActivity.class);
                        startActivity(LoginIntent);
                        finish();
                    } else {
                        showError(activity
                                .getString(R.string.forget_password_email_error));
                    }
                }
            };
            AVService.requestPasswordReset(email, callback);
        } else {
            showError(activity.getResources().getString(
                    R.string.error_register_email_address_null));
        }
    }
};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mapdao_bgforget_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
