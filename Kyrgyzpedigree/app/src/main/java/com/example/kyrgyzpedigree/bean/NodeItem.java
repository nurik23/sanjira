package com.example.kyrgyzpedigree.bean;

import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.recyclertreeview_lib.LayoutItemType;

public class NodeItem implements LayoutItemType {
    private String contentText;
    private NodeItemType nodeItemType;

    public NodeItem(String contentText) {
        this.contentText = contentText;
    }

    public NodeItem(String contentText, NodeItemType nodeItemType) {
        this.contentText = contentText;
        this.nodeItemType = nodeItemType;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentText() {
        return contentText;
    }

    public NodeItemType getNodeItemType() {
        return nodeItemType;
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_node;
    }
}