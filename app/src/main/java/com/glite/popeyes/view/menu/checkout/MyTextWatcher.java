package com.glite.popeyes.view.menu.checkout;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.glite.popeyes.util.CheckerUtil;

/**
 * Created by QUOC CUONG on 13/10/2016.
 */
public class MyTextWatcher implements TextWatcher {

    private EditText mEditTextOne;
    private EditText mEditTextTwo;
    private int mColorFocus;
    private String value;

    public MyTextWatcher(EditText mEditTextOne, EditText mEditTextTwo, int colorFocus, String value) {
        this.mEditTextOne = mEditTextOne;
        this.mEditTextTwo = mEditTextTwo;
        this.mColorFocus = colorFocus;
        this.value = value;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = mEditTextOne.getText().toString();
        if (text.isEmpty()) {
            mEditTextOne.setText(value);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mEditTextOne.getText().toString().length() == 1) {
            if (mEditTextTwo != null) {
                mEditTextTwo.requestFocus();
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        mEditTextOne.setBackgroundColor(mEditTextOne.hasSelection() ? Color.GRAY : mColorFocus);
    }
}
