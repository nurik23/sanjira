package com.example.kyrgyzpedigree.viewbinder;

import android.view.View;
import android.widget.TextView;

import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.bean.File;
import com.example.kyrgyzpedigree.recyclertreeview_lib.TreeNode;
import com.example.kyrgyzpedigree.recyclertreeview_lib.TreeViewBinder;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by tlh on 2016/10/1 :)
 */

public class FileNodeBinder extends TreeViewBinder<FileNodeBinder.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node) {
        File fileNode = (File) node.getContent();
        holder.tvName.setText(fileNode.fileName);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_file;
    }

    public class ViewHolder extends TreeViewBinder.ViewHolder {
        public TextView tvName;

        public ViewHolder(View rootView) {
            super(rootView);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }

    }
}
