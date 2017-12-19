package com.example.balvinder.newsapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.widget.Toast;

import com.example.balvinder.newsapplication.NewsApp;
import com.example.balvinder.newsapplication.R;
import com.example.balvinder.newsapplication.interfaces.DialogInterface;

/**
 * Created by balvinder on 18/12/17.
 */

public class Utility {

    private SharedPreferences preferences;

    public static ProgressDialog getProgressDialog(final Context context, final String message, final boolean isCancelable) {
        ProgressDialog progressDialog = null;
        try {
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(isCancelable);
            progressDialog.setMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressDialog;
    }

    public void showNetworkError(Context context) {
        Toast toast = Toast.makeText(context, context.getString(R.string.net_msg), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void getDialogMessage(Context context, String message) {
        MessageDialog messageDialog = new MessageDialog(context, message);
        messageDialog.setDialogInterface(new DialogInterface() {
            @Override
            public void getIndex(int index) {
            }
        });
        messageDialog.show();
    }



    public int getVisibleFragment(Context mContext) {
        preferences = NewsApp.getInstance().getSharedPreferences();
        return preferences.getInt(mContext.getString(R.string.visible_fragment), -1);
    }


    public void putVisibleFragment(Context mContext, int visibleFragment) {
        preferences = NewsApp.getInstance().getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(mContext.getString(R.string.visible_fragment), visibleFragment);
        editor.commit();
    }

}
