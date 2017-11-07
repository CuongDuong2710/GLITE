package com.glite.popeyes.view.menu.our_menu.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.glite.popeyes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Brian
 * @date: 19/09/2016
 */

public class CategoryViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 90f;

    @BindView(R.id.img_arrow)
    ImageView mArrowExpandable;
    @BindView(R.id.menu_title)
    TextView mCategoryName;
    @BindView(R.id.menu_description)
    TextView mDescription;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (expanded) {
            mArrowExpandable.setRotation(ROTATED_POSITION);
            mCategoryName.setTextColor(Color.parseColor("#c41230"));
        } else {
            mArrowExpandable.setRotation(INITIAL_POSITION);
            mCategoryName.setTextColor(Color.parseColor("#003a63"));
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        RotateAnimation rotateAnimation;
        if (expanded) {     // rotate clockwise
            rotateAnimation = new RotateAnimation(ROTATED_POSITION, INITIAL_POSITION,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        } else {    //
            rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION, INITIAL_POSITION,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        }

        rotateAnimation.setDuration(200);
        rotateAnimation.setFillAfter(true);
        mArrowExpandable.startAnimation(rotateAnimation);
    }
}
