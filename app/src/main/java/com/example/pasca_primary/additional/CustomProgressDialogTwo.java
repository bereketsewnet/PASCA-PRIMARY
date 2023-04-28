package com.example.pasca_primary.additional;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.pasca_primary.R;

public class CustomProgressDialogTwo extends Dialog {

    public CustomProgressDialogTwo(@NonNull Context context) {
        super(context);

        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(params);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view = LayoutInflater.from(context).inflate(R.layout.loading_layout2,null);
        setContentView(view);
    }
}
