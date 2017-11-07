package com.glitellp.libs.custom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.glitellp.libs.R;

public class LoadingDialog {

    private Dialog dialog;
    private Activity mActivity;

    @SuppressLint("InflateParams")
    public LoadingDialog(Activity cxt, String mess) {
        mActivity = cxt;
        dialog = new Dialog(cxt, R.style.LoadingTheme);
        View view = LayoutInflater.from(cxt).inflate(
                R.layout.custom_progress_dialog, null);
        TextView tvContextMess = (TextView) view
                .findViewById(R.id.tv_content_mess);
        tvContextMess.setText(mess);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.getAttributes().dimAmount = 0.5f;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
    }

    public void showDialog() {
        try {
            if (!mActivity.isFinishing()) {
                dialog.show();
            }

        } catch (Exception e) {
        }
    }

    public void setDimAmount(float dim){
        Window window = dialog.getWindow();
        window.getAttributes().dimAmount = dim;
    }

    public boolean isShowing() {
        if (dialog != null) {
            return dialog.isShowing();
        }
        return false;
    }

    public void hideDialog() {
        try {
            if (dialog != null) {
                dialog.dismiss();
            }
        } catch (Exception e) {
        }
    }

    public void cancelDialog() {
        if (dialog != null && dialog.isShowing()) {
            try {
                dialog.cancel();
            } catch (Exception e) {
            }
        }
    }
}
