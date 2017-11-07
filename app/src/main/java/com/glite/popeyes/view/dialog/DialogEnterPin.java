package com.glite.popeyes.view.dialog;

import android.graphics.Point;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.glite.popeyes.R;
import com.glite.popeyes.view.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC on 10/14/2016.
 */

public class DialogEnterPin extends BaseDialogFragment {

    @BindView(R.id.button_enter_pin)
    Button mBtnEnterPin;

    private OnEnterPinClickListener onEnterPinClickListener;

    public DialogEnterPin() {
    }

    public static DialogEnterPin newInstance() {
        return new DialogEnterPin();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_enter_pin;
    }

    @Override
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();

        Point size = new Point();

        // Store dimensions of the screen in `size`
        if (window != null) {
            Display display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);

            // Set the width of the dialog proportional to 75% of the screen width
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
        // Call super onResume after sizing
        super.onResume();
    }

    @OnClick(R.id.button_enter_pin)
    public void onEnterPin() {
        onEnterPinClickListener.onEnterPinClicked();
    }

    public interface OnEnterPinClickListener {
        void onEnterPinClicked();
    }

    public void setOnEnterPinClickListener(OnEnterPinClickListener onEnterPinClicked) {
        this.onEnterPinClickListener = onEnterPinClicked;
    }
}
