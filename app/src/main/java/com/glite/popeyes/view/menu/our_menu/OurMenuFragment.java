package com.glite.popeyes.view.menu.our_menu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.glite.popeyes.R;
import com.glite.popeyes.data.remote.reponse.order_menu.category.CategoryItem;
import com.glite.popeyes.data.remote.reponse.order_menu.category.CategoryList;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Item;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Menu;
import com.glite.popeyes.data.remote.request.menu.CategoryIDRequest;
import com.glite.popeyes.data.remote.request.menu.ClientIDRequest;
import com.glite.popeyes.exception.ErrorMessageFactory;
import com.glite.popeyes.injector.components.fragment.DaggerOurMenuComponent;
import com.glite.popeyes.injector.modules.fragment.OurMenuModule;
import com.glite.popeyes.presenter.OurMenuPresenter;
import com.glite.popeyes.util.CheckerUtil;
import com.glite.popeyes.util.Config;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.base.BaseFragment;
import com.glite.popeyes.view.menu.our_menu.adapter.CategoryExpandableAdapter;
import com.glite.popeyes.view.menu.our_menu.model.Category;
import com.glitellp.libs.custom.LoadingDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OurMenuFragment extends BaseFragment implements OurMenuContract.View, CategoryExpandableAdapter.OnSelectedClickListener {

    public static final String FRAGMENT_TAG = "OurMenuFragment";

    @BindView(R.id.recycle_our_menu)
    RecyclerView mRecycleView;

    @BindView(R.id.emptyView)
    TextView mEmptyView;

    @BindView(R.id.our_menu_container)
    FrameLayout mOurMenuContainer;

    @Inject
    OurMenuPresenter mOurMenuPresenter;

    private CategoryExpandableAdapter mAdapter;
    private List<Category> mCategories;

    private LoadingDialog mLoadingDialog;

    private int lastExpandedPosition = -1;
    private int positionCategoryClicked = 0;

    public OurMenuFragment() {
        // Required empty public constructor
    }

    public static OurMenuFragment newInstance() {
        return new OurMenuFragment();
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_our_menu;
    }

    @Override
    public void injectComponent() {
        DaggerOurMenuComponent.builder().appComponent(getAppComponent())
                .ourMenuModule(new OurMenuModule(this)).build().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showEmptyView();

//        setUpRecycleView();

        mLoadingDialog = new LoadingDialog(getActivity(), getString(R.string.loading));

        // request api
        mOurMenuPresenter.requestCategories(new ClientIDRequest(Config.CLIENT_ID));

    }

    @Override
    public void setUpRecycleView() {
//        mCategories = new ArrayList<>();

        mAdapter = new CategoryExpandableAdapter(getActivity(), mCategories);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                if (lastExpandedPosition != -1
                        && position != lastExpandedPosition) {
                    mAdapter.collapseParent(lastExpandedPosition);
                }
                lastExpandedPosition = position;

                Category category = mCategories.get(position);
                positionCategoryClicked = position;
                if (category != null) {
                    mOurMenuPresenter.onItemClick(new CategoryIDRequest(category.getCategoryID()));
                }
            }

            @Override
            public void onListItemCollapsed(int position) {
                Category category = mCategories.get(position);
                if (category != null) {

                }
            }
        });

        mAdapter.setOnSelectedClickListener(this);

        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setHasFixedSize(true);
    }

    @Override
    public void showLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.showDialog();
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.hideDialog();
        }
    }

    @Override
    public void populateCategory(CategoryList categoryList) {
        if (categoryList == null || CheckerUtil.isEmptyList(categoryList.getItems())) {
            return;
        }
        List<CategoryItem> categoryItems = categoryList.getItems();
        if (!CheckerUtil.isEmptyList(categoryItems)) {
            mCategories = new ArrayList<>();
            for (CategoryItem categoryItem : categoryItems) {
                if (categoryItem != null) {
                    Category category = new Category(categoryItem, null);
                    mCategories.add(category);
                }
            }
        }

        // Sort list categories by sequence
        Collections.sort(mCategories);

        // attach to adapter
        setUpRecycleView();

        // hide empty view if has data
        hideEmptyView();
    }

    @Override
    public void populateSubCategory(Menu menu) {
        if (menu == null) {
            return;
        }
        List<Item> items = menu.getItems();
        if (items == null || CheckerUtil.isEmptyList(items)){
            return;
        }
        //get category
        Category category = mCategories.get(positionCategoryClicked);
        int parentPosition = mCategories.indexOf(category);
        //add item into category
        for (Item i : items) {
            category.getmSubMenu().add(i);
            mAdapter.notifyChildItemInserted(parentPosition, items.indexOf(i));
        }
    }

    @Override
    public void showResponseError(String msg) {
        ToastUtil.showSingleToast(getActivity(), msg);
    }

    @Override
    public void showMessageError(Throwable cause) {
        final String error = ErrorMessageFactory.create(getActivity(), cause);
        ToastUtil.showSingleToast(getActivity(), error);
    }

    @Override
    public void showEmptyView() {
        if (CheckerUtil.isEmptyList(mCategories)) {
            mEmptyView.setVisibility(View.VISIBLE);
            mRecycleView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideEmptyView() {
        if (!CheckerUtil.isEmptyList(mCategories)) {
            mEmptyView.setVisibility(View.GONE);
            mRecycleView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSelectedClick(Item subItem) {
        ToastUtil.showSingleToast(getActivity(), "selected item: " + subItem.getMenuName());

        // save data to preference
        if (subItem != null) {
            getAppComponent().orderManager().putItem(subItem);
        }

        Fragment fragment = getFragmentManager().findFragmentById(R.id.order_delivery_container);
        if (fragment != null) {
            getFragmentManager().beginTransaction().hide(fragment).commit();
        }
        GroupAddOnFragment addOnFragment = GroupAddOnFragment.newInstance();
        replaceFragment(R.id.order_delivery_container, addOnFragment, GroupAddOnFragment.FRAGMENT_TAG);
    }
}
