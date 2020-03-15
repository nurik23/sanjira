
package com.example.kyrgyzpedigree;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kyrgyzpedigree.bean.Dir;
import com.example.kyrgyzpedigree.bean.File;
import com.example.kyrgyzpedigree.models.Rod;
import com.example.kyrgyzpedigree.recyclertreeview_lib.TreeNode;
import com.example.kyrgyzpedigree.recyclertreeview_lib.TreeViewAdapter;
import com.example.kyrgyzpedigree.viewbinder.DirectoryNodeBinder;
import com.example.kyrgyzpedigree.viewbinder.FileNodeBinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShowSanjyraTree extends AppCompatActivity {


    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private RecyclerView rv;
    private TreeViewAdapter adapter;
    private List<Rod> rodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sanjyra_tree);
        initView();
        initData();
    }

    private void initData() {
        rodList = databaseHelper.getRodList();
        List<TreeNode> nodes = new ArrayList<>();
        for (Rod rod : rodList) {
            TreeNode<Dir> rodNode = new TreeNode<>(new Dir(rod.getName()));
            nodes.add(rodNode);
        }

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeViewAdapter(nodes, Arrays.asList(new FileNodeBinder(), new DirectoryNodeBinder()));
        // whether collapse child nodes when their parent node was close.
//        adapter.ifCollapseChildWhileCollapseParent(true);
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    //Update and toggle the node.
                    onToggle(!node.isExpand(), holder);
//                    if (!node.isExpand())
//                        adapter.collapseBrotherNode(node);
                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                DirectoryNodeBinder.ViewHolder dirViewHolder = (DirectoryNodeBinder.ViewHolder) holder;
                final ImageView ivArrow = dirViewHolder.getIvArrow();
                int rotateDegree = isExpand ? 90 : -90;
                ivArrow.animate().rotationBy(rotateDegree)
                        .start();
            }
        });
        rv.setAdapter(adapter);
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.id_action_close_all:
                adapter.collapseAll();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}