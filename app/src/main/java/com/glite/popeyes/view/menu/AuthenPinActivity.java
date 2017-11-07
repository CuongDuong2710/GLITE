package com.glite.popeyes.view.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.ToolBarBaseActivity;
import com.glite.popeyes.view.menu.checkout.MyTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthenPinActivity extends AppCompatActivity {

    @BindView(R.id.text_num1)
    EditText edit_number1;
    @BindView(R.id.text_num2)
    EditText edit_number2;
    @BindView(R.id.text_num3)
    EditText edit_number3;
    @BindView(R.id.text_num4)
    EditText edit_number4;
    @BindView(R.id.text_num5)
    EditText edit_number5;
    @BindView(R.id.text_num6)
    EditText edit_number6;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, AuthenPinActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_authen_pin);

        ButterKnife.bind(this);

        onHideSoftInput();
    }

    public void ClickHandle(View v) {
        if (v.getId() != R.id.tvDel & v.getId() != R.id.tvStar) {
            TextView tvNum = (TextView) v;
            String value = tvNum.getText().toString();
            ToastUtil.showSingleToast(getApplicationContext(), value);
            beforeTextChanged(value);
        }
    }

    private void onHideSoftInput() {
        edit_number1.setShowSoftInputOnFocus(false);
        edit_number2.setShowSoftInputOnFocus(false);
        edit_number3.setShowSoftInputOnFocus(false);
        edit_number4.setShowSoftInputOnFocus(false);
        edit_number5.setShowSoftInputOnFocus(false);
        edit_number6.setShowSoftInputOnFocus(false);
    }

    private void beforeTextChanged(String value) {

        int colorState = getResources().getColor(R.color.orange);

        String text = edit_number1.getText().toString();
        if (text.isEmpty()) {
            edit_number1.setText(value);
            edit_number1.setBackgroundColor(edit_number1.hasSelection() ? Color.GRAY : colorState);
            edit_number2.requestFocus();
        }
    }

    private void onEditTextChanged(String value) {

        int colorState = getResources().getColor(R.color.orange);

        edit_number1.addTextChangedListener(new MyTextWatcher(edit_number1, edit_number2, colorState, value));
        edit_number2.addTextChangedListener(new MyTextWatcher(edit_number2, edit_number3, colorState, value));
        edit_number3.addTextChangedListener(new MyTextWatcher(edit_number3, edit_number4, colorState, value));
        edit_number4.addTextChangedListener(new MyTextWatcher(edit_number4, edit_number5, colorState, value));
        edit_number5.addTextChangedListener(new MyTextWatcher(edit_number5, edit_number6, colorState, value));
        edit_number6.addTextChangedListener(new MyTextWatcher(edit_number6, null, colorState, value));
    }
}
