package com.mapdaobackground.mapdaobg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;


public class MapdaoBGLoginActivity extends MapdaoBGActivity {

    Button loginButton;
    Button registerButton;
    Button forgetPasswordButton;
    EditText userNameEditText;
    EditText userPasswordEditText;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapdao_bglogin);

        loginButton = (Button) findViewById(R.id.button_login);
        registerButton = (Button) findViewById(R.id.button_register);
        forgetPasswordButton = (Button) findViewById(R.id.button_forget_password);
        userNameEditText = (EditText) findViewById(R.id.editText_userName);
        userPasswordEditText = (EditText) findViewById(R.id.editText_userPassword);

        if (getUserId() != null) {
            Intent mainIntent = new Intent(activity, MapdaoBGMainActivity.class);
            startActivity(mainIntent);
            activity.finish();
        }

            loginButton.setOnClickListener(loginListener);
     //   registerButton.setOnClickListener(registerListener);
     //   forgetPasswordButton.setOnClickListener(forgetPasswordListener);
    }

    private String password() {
        return userPasswordEditText.getText().toString();
    }


View.OnClickListener loginListener = new View.OnClickListener() {

    @SuppressLint("NewApi")
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void onClick(View arg0) {
        String username = userNameEditText.getText().toString();
        if (username.isEmpty())
            {
                 showUserNameEmptyError();
               return;
            }
        if (password().isEmpty())
            {
            showUserPasswordEmptyError();
            return;
            }
                progressDialogShow();
                AVUser.logInInBackground(username,
                password(),
                new LogInCallback() {
                    public void done(AVUser user, AVException e) {
                        if (user != null) {
                            progressDialogDismiss();
                            Intent mainIntent = new Intent(activity,
                                    MapdaoBGMainActivity.class);
                            startActivity(mainIntent);
                            activity.finish();
                        } else {
                            progressDialogDismiss();
                            showLoginError();
                        }
                    }
                });
    }
};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mapdao_bglogin, menu);
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

    private void progressDialogDismiss() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    private void progressDialogShow() {
        progressDialog = ProgressDialog
                .show(activity,
                        activity.getResources().getText(
                                R.string.dialog_message_title),
                        activity.getResources().getText(
                                R.string.dialog_text_wait), true, false);
    }

    private void showLoginError() {
        new AlertDialog.Builder(activity)
                .setTitle(
                        activity.getResources().getString(
                                R.string.dialog_error_title))
                .setMessage(
                        activity.getResources().getString(
                                R.string.error_login_error))
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }

    private void showUserPasswordEmptyError() {
        new AlertDialog.Builder(activity)
                .setTitle(
                        activity.getResources().getString(
                                R.string.dialog_error_title))
                .setMessage(
                        activity.getResources().getString(
                                R.string.error_register_password_null))
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }

    private void showUserNameEmptyError() {
        new AlertDialog.Builder(activity)
                .setTitle(
                        activity.getResources().getString(
                                R.string.dialog_error_title))
                .setMessage(
                        activity.getResources().getString(
                                R.string.error_register_user_name_null))
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }
}


