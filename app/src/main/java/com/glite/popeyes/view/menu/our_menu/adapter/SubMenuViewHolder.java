package com.glite.popeyes.view.menu.our_menu.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.glite.popeyes.R;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.custom.CustomButtonIcon;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Brian
 * @date: 19/09/2016
 */

public class SubMenuViewHolder extends ChildViewHolder {

    @BindView(R.id.sub_menu_title)
    TextView mTitle;
    @BindView(R.id.sub_menu_content)
    TextView mDescription;
    @BindView(R.id.sub_menu_logo)
    ImageView mSubLogo;
    @BindView(R.id.button_select)
    CustomButtonIcon mSelectButton;

    public SubMenuViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
