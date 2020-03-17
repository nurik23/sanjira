package com.example.kyrgyzpedigree.viewbinder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.bean.NodeItemType;
import com.example.kyrgyzpedigree.bean.NodeItem;
import com.example.kyrgyzpedigree.recyclertreeview_lib.TreeNode;
import com.example.kyrgyzpedigree.recyclertreeview_lib.TreeViewBinder;

public class PersonNodeBinder extends TreeViewBinder<PersonNodeBinder.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder provideViewHolder(View itemView) {
        return new PersonNodeBinder.ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node) {
        holder.ivArrow.setRotation(0);
        holder.ivArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_black_18dp);
        int rotateDegree = node.isExpand() ? 90 : 0;
        holder.ivArrow.setRotation(rotateDegree);
        NodeItem nodeItem = (NodeItem) node.getContent();
        NodeItemType nodeItemType = nodeItem.getNodeItemType();
        switch (nodeItemType) {
            case ROD:
                holder.tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rod_icon, 0, 0, 0);
                break;
            case PODROD:
                holder.tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.podrod_icon, 0, 0, 0);
                break;
            case PERSON:
                holder.tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.person_icon, 0, 0, 0);
                break;
            case EMPTY:
                holder.tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.empty_icon, 0, 0, 0);
                break;
            case PROPERTY:
                holder.tvName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.person_property_icon, 0, 0, 0);
                break;
        }
        holder.tvName.setText(nodeItem.getContentText());
        if (node.isLeaf())
            holder.ivArrow.setVisibility(View.INVISIBLE);
        else holder.ivArrow.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node;
    }

    @Override
    public NodeItemType getNodeItemType() {
        return NodeItemType.PERSON;
    }

    public static class ViewHolder extends TreeViewBinder.ViewHolder {
        private ImageView ivArrow;
        private TextView tvName;

        public ViewHolder(View rootView) {
            super(rootView);
            this.ivArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }

        public ImageView getIvArrow() {
            return ivArrow;
        }

        public TextView getTvName() {
            return tvName;
        }
    }
}
