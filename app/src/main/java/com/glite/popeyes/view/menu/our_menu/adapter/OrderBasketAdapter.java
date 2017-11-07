package com.glite.popeyes.view.menu.our_menu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.font.FontManager;
import com.glite.popeyes.util.ToastUtil;
import com.glite.popeyes.view.custom.FuturaButton;
import com.glite.popeyes.view.custom.FuturaTextView;
import com.glite.popeyes.view.custom.VerdanaTextView;
import com.glite.popeyes.view.menu.our_menu.GroupAddOnFragment;
import com.glite.popeyes.view.menu.our_menu.model.ItemNotFoundException;
import com.glite.popeyes.view.menu.our_menu.model.OrderItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QUOC CUONG on 22/09/2016.
 */
public class OrderBasketAdapter extends RecyclerView.Adapter<OrderBasketAdapter.ViewHolder> {

    private List<OrderItem> mOrderItemsList = new ArrayList<>();
    private Context mContext;
    private OnButtonClickListener onButtonClickListener;

    public OrderBasketAdapter(List<OrderItem> orderItemsList, Context context) {
        this.mOrderItemsList = orderItemsList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_basket_recycle, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItemName.setText(mOrderItemsList.get(position).getName());
        holder.mImageItem.setImageBitmap(mOrderItemsList.get(position).getImageSource());
        holder.mTextItemDetails.setText(mOrderItemsList.get(position).getDetails());
        holder.mTextNumberItems.setText(String.valueOf(mOrderItemsList.get(position).getQuantity()));
        holder.mTextPriceTotals.setText("$" + mOrderItemsList.get(position).getPrice());
    }

    public void updateOrderItemsList(List<OrderItem> data) {
        mOrderItemsList = data;
        notifyDataSetChanged();
    }

    public void addOrderItem(int position, OrderItem basketItem) {
        mOrderItemsList.add(position, basketItem);
        notifyItemInserted(position);
    }

    public void removeOrderItem(int position) {
        mOrderItemsList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mOrderItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final int MIN_VALUE = 1;

        @BindView(R.id.text_order_item_name)
        FuturaTextView mItemName;
        @BindView(R.id.img_order_item)
        ImageView mImageItem;
        @BindView(R.id.text_order_item_details)
        VerdanaTextView mTextItemDetails;
        @BindView(R.id.btn_add_an_item)
        Button mBtnAdd;
        @BindView(R.id.btn_minus_an_item)
        Button mBtnMinus;
        @BindView(R.id.text_view_price_value)
        TextView mTextPriceTotals;
        @BindView(R.id.btn_remove_this_item)
        FuturaButton mBtnRemove;
        @BindView(R.id.text_number_order_item)
        TextView mTextNumberItems;

        private Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);

            this.context = context;

            // setup all widgets
            ButterKnife.bind(this, itemView);

            //set typeface
            setTypeFace();

            //set on listener for buttons
            mBtnAdd.setOnClickListener(this);
            mBtnMinus.setOnClickListener(this);
            mBtnRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int quantity = Integer.parseInt(mTextNumberItems.getText().toString());
            int position = getAdapterPosition();
            OrderItem item = mOrderItemsList.get(position);

            switch (view.getId()) {
                // increase quantity
                case R.id.btn_add_an_item:
                    onAddButtonClicked(quantity, item);
                    break;
                // minus quantity
                case R.id.btn_minus_an_item:
                    onMinusButtonClicked(quantity, item);
                    break;
                // remove item
                case R.id.btn_remove_this_item:
                    onRemoveButtonClicked(quantity, item, position);
                    break;

                default:
                    break;
            }
        }

        private void onAddButtonClicked(int quantity, OrderItem item) {

            DecimalFormat df = new DecimalFormat("#.00");

            quantity++;
            mTextNumberItems.setText(quantity + "");
            mTextPriceTotals.setText("$" + df.format(quantity * item.getPrice()));
            onButtonClickListener.onAddButtonClick(item.getPrice());
        }

        private void onMinusButtonClicked(int quantity, OrderItem item) {

            DecimalFormat df = new DecimalFormat("#.00");

            if (quantity > MIN_VALUE) {
                quantity--;
                mTextNumberItems.setText(quantity + "");
                mTextPriceTotals.setText("$" + df.format(quantity * item.getPrice()));
                onButtonClickListener.onMinusButtonClick(item.getPrice());
            }
        }

        private void onRemoveButtonClicked(int quantity, OrderItem item, int position) {
            try {
                ToastUtil.showSingleToast(this.context, "Remove: " + position);

                // remove in list
                removeOrderItem(position);

                // remove in basket map
                GroupAddOnFragment.basket.removeItem(item.getId());

                GroupAddOnFragment.basket.getCartPrice();

            } catch (ItemNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void setTypeFace() {
            mItemName.setTypeface(FontManager.getFuturaStdBoldCondensedFont(this.context));
            mTextItemDetails.setTypeface(FontManager.getVerdanaFont(this.context));
            mTextPriceTotals.setTypeface(FontManager.getFuturaStdBoldCondensedFont(this.context));
        }
    }

    public interface OnButtonClickListener {
        void onAddButtonClick(double itemTotal);
        void onMinusButtonClick(double itemTotal);
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

}
