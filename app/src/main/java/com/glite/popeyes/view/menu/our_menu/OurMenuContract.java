package com.glite.popeyes.view.menu.our_menu;

import com.glite.popeyes.data.remote.reponse.order_menu.category.CategoryList;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Menu;
import com.glite.popeyes.data.remote.request.menu.CategoryIDRequest;
import com.glite.popeyes.data.remote.request.menu.ClientIDRequest;
import com.glite.popeyes.view.base.BaseMvpView;
import com.glite.popeyes.view.menu.our_menu.model.Category;

/**
 * @author Brian
 * @date: 16/09/2016
 */

public interface OurMenuContract {

    interface View extends BaseMvpView {

        void showLoadingDialog();

        void hideLoadingDialog();

        void populateCategory(CategoryList categoryList);

        void populateSubCategory(Menu menu);

        void showResponseError(String msg);

        void showEmptyView();

        void hideEmptyView();

        void setUpRecycleView();

    }

    interface Presenter {

        void requestCategories(ClientIDRequest clientIDRequest);
        void clickLoadMoreCategory();
        void onItemClick(CategoryIDRequest categoryIDRequest);
    }
}
