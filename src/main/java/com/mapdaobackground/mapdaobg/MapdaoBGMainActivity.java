package com.mapdaobackground.mapdaobg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MapdaoBGMainActivity extends MapdaoBGActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapdao_bg);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mapdao_bg, menu);
        return true;
    }

    private void Logout() {
        AVService.logout();
        Intent loginIntent = new Intent(activity, MapdaoBGLoginActivity.class);
        startActivity(loginIntent);
        activity.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_logout:
                new AlertDialog.Builder(activity)
                        .setTitle(
                                activity.getResources().getString(
                                        R.string.dialog_message_title))
                        .setMessage(
                                activity.getResources().getString(
                                        R.string.action_logout_alert_message))
                        .setNegativeButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                        Logout();
                                    }
                                })
                        .setPositiveButton(android.R.string.cancel,
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
