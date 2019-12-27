package com.example.MemoryMatchingGame;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExitDialog extends AlertDialog {

    private Button yes;
    private Button no;
    private TextView titleTv;
    private TextView messageTv;
    private String titleStr;
    private String messageStr;
    private String yesStr, noStr;
    private ExitDialog.onNoOnclickListener noOnclickListener;
    private ExitDialog.onYesOnclickListener yesOnclickListener;

    public void setNoOnclickListener(String str, ExitDialog.onNoOnclickListener onNoOnclickListener) {

        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    public void setYesOnclickListener(String str, ExitDialog.onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    public ExitDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exitdialog_layout);
        setCanceledOnTouchOutside(false);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        yes.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }

        });
    }

    private void initData() {
        if (titleStr != null) {
            titleTv.setText(titleStr);
        }

        if (messageStr != null) {
            messageTv.setText(messageStr);
        }


        if (yesStr != null) {
            yes.setText(yesStr);
        }

        if (noStr != null) {
            no.setText(noStr);
        }
    }

    private void initView() {
        yes = (Button) findViewById(R.id.yes);
        titleTv = (TextView) findViewById(R.id.title);
        messageTv = (TextView) findViewById(R.id.message);
    }

    public void setTitle(String title) {
        titleStr = title;
    }

    public void setMessage(String message) {
        messageStr = message;
    }

    public interface onYesOnclickListener {
        void onYesClick();
    }

    public interface onNoOnclickListener {
        void onNoClick();
    }
}
