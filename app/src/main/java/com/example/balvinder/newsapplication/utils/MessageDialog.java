package com.example.balvinder.newsapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.balvinder.newsapplication.R;
import com.example.balvinder.newsapplication.interfaces.DialogInterface;

/**
 * Created by balvinder on 18/12/17.
 */

public class MessageDialog extends Dialog implements View.OnClickListener {
    Context context;
    DialogInterface dialogInterface;
    private Button okBtn;
    private TextView messageTextView;
    String messgae;
    boolean isOpenDialog = false;

    public MessageDialog(Context context, String messageText) {
        super(context);

        setCanceledOnTouchOutside(false);
        this.messgae = messageText;
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.message_dialog);
        isOpenDialog = true;
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        init();

    }

    private void init() {
        messageTextView = (TextView) findViewById(R.id.message_text);
        messageTextView.setText(messgae);
        okBtn = (Button) findViewById(R.id.okBtn);
        okBtn.setOnClickListener(this);
    }

    public DialogInterface getDialogInterface() {
        return dialogInterface;
    }

    public void setDialogInterface(DialogInterface dialogInterface) {
        this.dialogInterface = dialogInterface;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//
            case R.id.okBtn:
                dismiss();
                if (dialogInterface != null) {
                    dialogInterface.getIndex(0);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {

        if (!isOpenDialog) {
            isOpenDialog = false;
            dismiss();
            if (dialogInterface != null) {
                dialogInterface.getIndex(1);
            }
            super.onBackPressed();

        }
    }
}

