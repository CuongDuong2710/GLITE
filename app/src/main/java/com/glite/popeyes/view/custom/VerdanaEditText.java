package com.glite.popeyes.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.glite.popeyes.font.FontManager;

/**
 * @author Tran Huy Phuc
 * @since 7/14/16
 */
public class VerdanaEditText extends EditText {
    public VerdanaEditText(Context context) {
        super(context);

        setupView(context, null);
    }

    public VerdanaEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupView(context, attrs);
    }

    public VerdanaEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setupView(context, attrs);
    }

    private void setupView(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        setTypeface(FontManager.getVerdanaFont(context));
    }
}
