package com.glite.popeyes.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.glite.popeyes.font.FontManager;

/**
 * @author: Brian
 * date: 17/09/2016.
 */
public class FuturaButton extends Button {

    public FuturaButton(Context context) {
        super(context);

        setUpView(context, null);
    }

    public FuturaButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setUpView(context, attrs);
    }

    public FuturaButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        setUpView(context, attrs);
    }

    private void setUpView(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

        this.setTypeface(FontManager.getFuturaStdBoldCondensedFont(context));
    }
}
