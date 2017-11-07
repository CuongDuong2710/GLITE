package com.glite.popeyes.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.font.FontManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Tran Huy Phuc
 * @since 7/12/16
 */
public class CustomButtonIcon extends LinearLayout {

    @BindView(R.id.rll_root)
    LinearLayout lnlRoot;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;

    public CustomButtonIcon(Context context) {
        super(context);

        initView(context, null);
    }

    public CustomButtonIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CustomButtonIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    /**
     * Initialize view: set color, text, background...
     *
     * @param context
     */
    private void initView(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }

        inflate(context, R.layout.custom_button, this);
        ButterKnife.bind(this);

        /**
         * Setup attributes
         */
        if (attrs != null) {
            /**
             * Get style attributes
             */
            int default_height = (int)getResources().getDimension(R.dimen.button_height_normal);
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButtonIcon);
            int button_height = typedArray.getResourceId(R.styleable.CustomButtonIcon_btn_height, default_height);

            // set button height
            LayoutParams layoutParams = (LayoutParams) lnlRoot.getLayoutParams();

            layoutParams.height = button_height;
            layoutParams.weight = LayoutParams.MATCH_PARENT;

            int backgroundResourceId = typedArray.getResourceId(R.styleable.CustomButtonIcon_btn_background, 0);
            Drawable iconDrawable = typedArray.getDrawable(R.styleable.CustomButtonIcon_btn_icon);
            int textColor = typedArray.getColor(R.styleable.CustomButtonIcon_btn_textColor, ContextCompat.getColor(context, R.color.white));
            float textSize = typedArray.getDimension(R.styleable.CustomButtonIcon_btn_text_size, context.getResources().getDimension(R.dimen.button_text_size_17sp));
            String text = typedArray.getString(R.styleable.CustomButtonIcon_btn_text);
            boolean isCapital = typedArray.getBoolean(R.styleable.CustomButtonIcon_btn_text_capital, false);
            if (isCapital) {
                tvName.setAllCaps(true);
            }

            /**
             * Render views with attributes
             */
            lnlRoot.setBackgroundResource(backgroundResourceId);
            ivIcon.setImageDrawable(iconDrawable);
            tvName.setText(text);
            tvName.setTextColor(textColor);
            tvName.setTypeface(FontManager.getFuturaStdBoldCondensedFont(context));
            tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

            typedArray.recycle();

        }
    }

    /**
     * Set background
     *
     * @param backgroundResourceId
     */
    public void setBackground(int backgroundResourceId) {
        lnlRoot.setBackgroundResource(backgroundResourceId);
    }
}
