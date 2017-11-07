package com.glite.popeyes.view.menu.our_menu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.glite.popeyes.R;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Item;
import com.glite.popeyes.util.CheckerUtil;
import com.glite.popeyes.util.Constants;
import com.glite.popeyes.util.Logger;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.menu.our_menu.model.Category;
import com.glite.popeyes.view.menu.our_menu.model.SubMenu;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Brian
 * @date: 19/09/2016
 */

public class CategoryExpandableAdapter extends ExpandableRecyclerAdapter<CategoryViewHolder, SubMenuViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private OnSelectedClickListener onSelectedClickListener;

    public CategoryExpandableAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CategoryViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View categoryView = mInflater.inflate(R.layout.item_delivery_menu_parent, parentViewGroup, false);
        return new CategoryViewHolder(categoryView);
    }

    @Override
    public SubMenuViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View subMenu = mInflater.inflate(R.layout.item_delivery_menu_sub_1, childViewGroup, false);
        return new SubMenuViewHolder(subMenu);
    }

    @Override
    public void onBindParentViewHolder(CategoryViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Category category = (Category) parentListItem;
        if (category != null) {
            parentViewHolder.mCategoryName.setText(CheckerUtil.checkIfNull(category.getCategoryName()));
            parentViewHolder.mDescription.setText(CheckerUtil.checkIfNull(category.getDescription()));
//            parentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ToastUtil.showSingleToast(mContext, "Click");
//                }
//            });
        } else {
            Logger.debug("null");
        }
    }

    @Override
    public void onBindChildViewHolder(SubMenuViewHolder childViewHolder, int position, Object childListItem) {
        Item subMenu = (Item) childListItem;
        if (subMenu != null) {
            childViewHolder.mTitle.setText(subMenu.getMenuName());
            childViewHolder.mDescription.setText(subMenu.getDescription());

            if (CheckerUtil.checkIfNull(subMenu.getImageSource()) != Constants.EMPTY) {
                Picasso.with(mContext).load(subMenu.getImageSource().trim()).fit().into(childViewHolder.mSubLogo);
            }


            childViewHolder.mSelectButton.setOnClickListener(view -> {
                if (onSelectedClickListener != null) {
                    onSelectedClickListener.onSelectedClick(subMenu);
                }
            });

        } else {
            Logger.debug("sub menu null");
        }
    }

    public interface OnSelectedClickListener {
        void onSelectedClick(Item subItem);
    }

    public void setOnSelectedClickListener(OnSelectedClickListener onSelectedClickListener) {
        this.onSelectedClickListener = onSelectedClickListener;
    }
}
