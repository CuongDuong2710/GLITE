package com.glite.popeyes.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.font.FontManager;

/**
 * @author Tran Huy Phuc
 * @since 7/14/16
 */
public class FuturaTextView extends TextView {

    public FuturaTextView(Context context) {
        super(context);

        setupView(context, null);
    }

    public FuturaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupView(context, attrs);
    }

    public FuturaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setupView(context, attrs);
    }

    private void setupView(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        setTypeface(FontManager.getFuturaStdBoldCondensedFont(context));

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FuturaTextView);
        boolean underline = typedArray.getBoolean(R.styleable.FuturaTextView_underline, false);

        if (underline) {
            setPaintFlags(this.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }

        typedArray.recycle();
    }
}
