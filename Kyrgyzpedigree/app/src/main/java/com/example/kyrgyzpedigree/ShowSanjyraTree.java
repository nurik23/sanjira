
package com.example.kyrgyzpedigree;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kyrgyzpedigree.bean.NodeItemType;
import com.example.kyrgyzpedigree.bean.NodeItem;
import com.example.kyrgyzpedigree.models.Podrod;
import com.example.kyrgyzpedigree.models.Rod;
import com.example.kyrgyzpedigree.recyclertreeview_lib.TreeNode;
import com.example.kyrgyzpedigree.recyclertreeview_lib.TreeViewAdapter;
import com.example.kyrgyzpedigree.viewbinder.PersonNodeBinder;

import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ShowSanjyraTree extends AppCompatActivity {


    Dao dao = Dao.getInstance();
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
        try {
            rodList = dao.getRodList();
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            Toast toast2 = Toast.makeText(getApplicationContext(),
                    "Problem with network", Toast.LENGTH_SHORT);
            toast2.show();
            Intent intent = new Intent(ShowSanjyraTree.this, EnterActivity.class);
            startActivity(intent);
            return;
        }
        List<TreeNode> nodes = new ArrayList<>();
        for (Rod rod : rodList) {
            TreeNode<NodeItem> rodNode = new TreeNode<>(new NodeItem(rod.getName(), NodeItemType.ROD));
            if (rod.getPodrodList().isEmpty()) {
                rodNode.addChild(new TreeNode<>(new NodeItem("empty", NodeItemType.EMPTY)));
            } else {
                for (Podrod podrod : rod.getPodrodList()) {
                    rodNode.addChild(new TreeNode<>(new NodeItem(podrod.getName(), NodeItemType.PODROD)).addChild(new TreeNode<>(new NodeItem("proxy", NodeItemType.PROXY))));
                }
            }
            nodes.add(rodNode);
        }
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeViewAdapter(nodes, Collections.singletonList(new PersonNodeBinder()));
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    NodeItemType nodeItemType = node.getContent().getNodeItemType();
                    if (nodeItemType.equals(NodeItemType.PODROD) && (((TreeNode) node.getChildList().get(0)).getContent()).getNodeItemType().equals(NodeItemType.PROXY)) {
                        node.setChildList(new ArrayList<>());
                        String podrodName = ((NodeItem) node.getContent()).getContentText();
                        List<Person> personListByPodrodId = dao.getPersonListByPodrodId(dao.getPodrodNameToPodrodMap().get(podrodName).getId());
                        if (personListByPodrodId.isEmpty()) {
                            node.addChild(new TreeNode<>(new NodeItem("empty", NodeItemType.EMPTY)));
                        } else {
                            for (Person person : personListByPodrodId) {
                                node.addChild(new TreeNode<>(new NodeItem(person.getName(), NodeItemType.PERSON))
                                        .addChild(new TreeNode<>(new NodeItem("Name : " + person.getName(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("Email : " + person.getEmail(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("Birth date : " + person.getGodrojdeniya(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("Address : " + person.getMestojitelstva(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("Father : " + person.getNamedad(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("Mother : " + person.getName(), NodeItemType.PROPERTY))));
                            }
                        }
                    }
                }
                onToggle(!node.isExpand(), holder);
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                PersonNodeBinder.ViewHolder personViewHolder = (PersonNodeBinder.ViewHolder) holder;
                ImageView ivArrow = personViewHolder.getIvArrow();
                int rotateDegree = isExpand ? 90 : -90;
                if (ivArrow != null) {
                    ivArrow.animate().rotationBy(rotateDegree)
                            .start();
                }
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