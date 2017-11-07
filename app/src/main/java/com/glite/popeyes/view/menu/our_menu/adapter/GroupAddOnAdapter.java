package com.glite.popeyes.view.menu.our_menu.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.Addon;
import com.glite.popeyes.data.remote.reponse.order_menu.sub_menu.GroupInfo;
import com.glite.popeyes.font.FontManager;
import com.glite.popeyes.view.menu.our_menu.model.AddOnItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by QUOC CUONG on 11/10/2016.
 */
public class GroupAddOnAdapter extends BaseExpandableListAdapter {

    /***
     * Show type
     */
    private static final String ONE = "1";

    private static final Integer VALUE_0 = 0;
    private static final Integer VALUE_1 = 1;

    private Context mContext;
    private List<GroupInfo> mGroupInfos = new ArrayList<>();
    private HashMap<GroupInfo, List<AddOnItem>> mData = new HashMap<>();

    private ArrayList<Integer> mValueSpinnerList = new ArrayList<>();

    public GroupAddOnAdapter(Context context, List<GroupInfo> groupInfos, HashMap<GroupInfo, List<AddOnItem>> data) {
        this.mContext = context;
        this.mGroupInfos = groupInfos;
        this.mData = data;
        addValueSpinner();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return this.mGroupInfos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mData.get(this.mGroupInfos.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mGroupInfos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.mData.get(this.mGroupInfos.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupInfo groupInfo = (GroupInfo) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expanded_list_header, null);
            convertView.setPadding(32, 10, 32, 16);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.label_group);
        lblListHeader.setTextSize(14);
        lblListHeader.setTypeface(FontManager.getFuturaStdBoldCondensedFont(this.mContext));
        lblListHeader.setText(groupInfo.getGroupName());

        TextView iconExpand = (TextView) convertView.findViewById(R.id.icon_expand);

        if (isExpanded) {
            lblListHeader.setBackgroundResource(R.drawable.rectangle_446_copy);
            iconExpand.setBackgroundResource(R.drawable.iconexpand);
        } else {
            lblListHeader.setBackgroundResource(R.drawable.rectangle_446);
            iconExpand.setBackgroundResource(R.drawable.iconcollapsed);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        GroupInfo groupInfo = (GroupInfo) getGroup(groupPosition);
        int maximum = Integer.parseInt(groupInfo.getMaximum());
        int minumum = Integer.parseInt(groupInfo.getMinimum());

        List<AddOnItem> items = new ArrayList<>();

        // get item at group and child position
        AddOnItem item = (AddOnItem) getChild(groupPosition, childPosition);
        View view = convertView;

        items.add(item);

        // get showType of item
        String showType = item.getShowType();

        if (showType.equals(ONE)) {
            if (view == null || (view.getId() != R.id.grid_view_spinner)) {
                view = LayoutInflater.from(mContext).inflate(R.layout.expanded_group_spinner, parent, false);

                GroupViewHolder holder = new GroupViewHolder();
//                holder.horizontalListView = (RecyclerView) view.findViewById(R.id.recycle_spinner);
                holder.gridView = (GridView) view.findViewById(R.id.grid_view_spinner);

                view.setTag(holder);
            }

            GroupViewHolder holder = (GroupViewHolder) view.getTag();

            SpinnerAddOnAdapter adapter = new SpinnerAddOnAdapter(mContext, items);
            adapter.addValueSpinner(minumum, maximum);
            holder.gridView.setAdapter(adapter);

//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//            holder.horizontalListView.setLayoutManager(layoutManager);
//            holder.horizontalListView.setHasFixedSize(true);

//            holder.horizontalListView.setLayoutManager(new GridLayoutManager(mContext, 2));
//            ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(mContext, R.dimen.item_offset);
//            holder.horizontalListView.addItemDecoration(itemDecoration);
//
//            SpinnerAdapter horizontalAdapter = new SpinnerAdapter(mContext, childItems);
//            holder.horizontalListView.setAdapter(horizontalAdapter);

        } else {
            if (view == null || (view.getId() != R.id.layout_text_addons)) {
                view = LayoutInflater.from(mContext).inflate(R.layout.expanded_text_item, parent, false);
                view.setPadding(16, -5, 0, 10);

                TextViewHolder holder = new TextViewHolder();
                holder.txtAddOnName = (TextView) view.findViewById(R.id.text_add_on_name) ;
                holder.imgCheck = (ImageView) view.findViewById(R.id.img_check_add_on);

                view.setTag(holder);
            }

            TextViewHolder holder = (TextViewHolder) view.getTag();

            holder.txtAddOnName.setText(item.getTextAddOnName());
            holder.imgCheck.setImageResource(item.getImgCheck());

            if (item.isChecked()) {
                holder.imgCheck.setVisibility(View.VISIBLE);
            } else {
                holder.imgCheck.setVisibility(View.GONE);
            }
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {
    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }

    private void addValueSpinner() {
        mValueSpinnerList.add(VALUE_0);
        mValueSpinnerList.add(VALUE_1);
    }

    /**
     * Holder for spinner group view
     */
    class GroupViewHolder {
        RecyclerView horizontalListView;
        GridView gridView;
    }

    /***
     * Holder for text
     */
    class TextViewHolder {
        TextView txtAddOnName;
        ImageView imgCheck;
    }
}
