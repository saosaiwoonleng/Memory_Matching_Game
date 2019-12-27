package com.example.MemoryMatchingGame;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.TextView;

public class SelfDialog extends AlertDialog {

    private Button yes;
    private TextView titleTv;
    private TextView messageTv;
    private String titleStr;
    private String messageStr;
    private String yesStr, noStr;
    private onYesOnclickListener yesOnclickListener;

    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {

        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    public SelfDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_exercise_sure_dialog_layout);
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


}