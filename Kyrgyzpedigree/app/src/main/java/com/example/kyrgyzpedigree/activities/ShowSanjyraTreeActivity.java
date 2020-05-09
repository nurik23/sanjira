
package com.example.kyrgyzpedigree.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.models.Person;
import com.example.kyrgyzpedigree.models.Podrod;
import com.example.kyrgyzpedigree.models.Rod;
import com.example.kyrgyzpedigree.service.DBService;
import com.example.kyrgyzpedigree.util.GlobalVariables;
import com.example.kyrgyzpedigree.util.beans.NodeItem;
import com.example.kyrgyzpedigree.util.beans.NodeItemType;
import com.example.kyrgyzpedigree.util.recyclertreeview_lib.TreeNode;
import com.example.kyrgyzpedigree.util.recyclertreeview_lib.TreeViewAdapter;
import com.example.kyrgyzpedigree.util.viewbinder.NodeItemBinder;

import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ShowSanjyraTreeActivity extends AppCompatActivity {

    DBService dbService = DBService.getInstance();
    private RecyclerView rv;
    private TreeViewAdapter adapter;
    private List<Rod> rodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sanjyra_tree);
        initView();
        initData();
        Button btnHome = findViewById(R.id.btnHomeSanjyraTree);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnHome.setOnClickListener(v -> startActivity(new Intent(ShowSanjyraTreeActivity.this, MainActivity.class)));
    }

    private void initData() {
        try {
            rodList = dbService.getRodList();
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            Toast toast2 = Toast.makeText(getApplicationContext(),
                    "Проблема с интернетом", Toast.LENGTH_SHORT);
            toast2.show();
            Intent intent = new Intent(ShowSanjyraTreeActivity.this, MainActivity.class);
            startActivity(intent);
            return;
        }
        List<TreeNode> nodes = new ArrayList<>();
        for (Rod rod : rodList) {
            TreeNode<NodeItem> rodNode = new TreeNode<>(new NodeItem(rod.getName(), NodeItemType.ROD));
            if (rod.getPodrodList().isEmpty()) {
                rodNode.addChild(new TreeNode<>(new NodeItem("Пусто", NodeItemType.EMPTY)));
            } else {
                for (Podrod podrod : rod.getPodrodList()) {
                    rodNode.addChild(new TreeNode<>(new NodeItem(podrod.getName(), NodeItemType.PODROD)).addChild(new TreeNode<>(new NodeItem("proxy", NodeItemType.PROXY))));
                }
            }
            nodes.add(rodNode);
        }
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeViewAdapter(nodes, Collections.singletonList(new NodeItemBinder()));
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    NodeItemType nodeItemType = node.getContent().getNodeItemType();
                    if (nodeItemType.equals(NodeItemType.PODROD) && (((TreeNode) node.getChildList().get(0)).getContent()).getNodeItemType().equals(NodeItemType.PROXY)) {
                        node.setChildList(new ArrayList<>());
                        String podrodName = ((NodeItem) node.getContent()).getContentText();
                        List<Person> personListByPodrodId = dbService.getPersonListByPodrodId(dbService.getPodrodNameToPodrodMap().get(podrodName).getId());
                        if (personListByPodrodId.isEmpty()) {
                            node.addChild(new TreeNode<>(new NodeItem("пусто", NodeItemType.EMPTY)));
                        } else {
                            for (Person person : personListByPodrodId) {
                                TreeNode<NodeItem> personTreeNode = new TreeNode<>(new NodeItem(person.getName(), NodeItemType.PERSON));
                                personTreeNode
                                        .addChild(new TreeNode<>(new NodeItem("Id : " + person.getId(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("ФИО : " + person.getName(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("Почта : " + person.getEmail(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("Дата рождения : " + person.getGodrojdeniya(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("Место проживания : " + person.getMestojitelstva(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("ФИО отца : " + person.getNamedad(), NodeItemType.PROPERTY)))
                                        .addChild(new TreeNode<>(new NodeItem("ФИО матери : " + person.getNamemom(), NodeItemType.PROPERTY)));
                                node.addChild(personTreeNode);
                            }
                        }
                    }
                }
                onToggle(!node.isExpand(), holder);
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                NodeItemBinder.ViewHolder personViewHolder = (NodeItemBinder.ViewHolder) holder;
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
        rv = findViewById(R.id.rv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (GlobalVariables.getInstance().isAdmin()) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.result_menu, menu);
            return true;
        } else return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_note:
                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Окно удаления", Toast.LENGTH_SHORT);
                toast1.show();
                GlobalVariables.getInstance().setPreviousActivityForDeletePersonActivity(this.getClass());
                Intent intentToMenu1 = new Intent(ShowSanjyraTreeActivity.this, DeletePersonActivity.class);
                startActivity(intentToMenu1);
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}