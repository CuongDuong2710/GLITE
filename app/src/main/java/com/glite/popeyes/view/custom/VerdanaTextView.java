package com.glite.popeyes.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.glite.popeyes.font.FontManager;

/**
 * @author Tran Huy Phuc
 * @since 7/14/16
 */
public class VerdanaTextView extends TextView {

    public VerdanaTextView(Context context) {
        super(context);

        setupView(context);
    }

    public VerdanaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupView(context);
    }

    public VerdanaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setupView(context);
    }

    private void setupView(Context context) {
        if (isInEditMode()) {
            return;
        }
        setTypeface(FontManager.getVerdanaFont(context));
    }
}
