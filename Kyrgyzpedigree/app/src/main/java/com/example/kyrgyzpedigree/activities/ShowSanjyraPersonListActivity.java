package com.example.kyrgyzpedigree.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.models.PersonDto;
import com.example.kyrgyzpedigree.service.DBService;
import com.example.kyrgyzpedigree.util.GlobalVariables;

import java.util.List;
import java.util.stream.Collectors;

public class ShowSanjyraPersonListActivity extends AppCompatActivity {

    TextView textViewTop;
    ListView listResult;
    DBService db;
    Intent intentToMenu1, intent;
    Button btnHome;
    String podrodName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sanjyra_person_list);

        textViewTop = findViewById(R.id.textTop);
        listResult = findViewById(R.id.listResult);
        btnHome = findViewById(R.id.btnHomeResult);
        db = DBService.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        podrodName = getIntent().getStringExtra("selectedValue");
        if (podrodName==null){
            podrodName = GlobalVariables.getInstance().getPodrodNameForPreviousActivityInShowSanjyraTreeActivity();
        }
        textViewTop.setText("Список участников из : <" + podrodName + ">");
        List<PersonDto> personDtoList = db.getPersonListByPodrodId(db.getPodrodNameToPodrodMap().get(podrodName).getId()).stream().map(PersonDto::new).collect(Collectors.toList());
        ArrayAdapter<PersonDto> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personDtoList);

        listResult.setAdapter(itemsAdapter);
        if (itemsAdapter.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "В подроде <" + podrodName + "> пусто", Toast.LENGTH_SHORT);
            toast.show();
        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ShowSanjyraPersonListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
                        "Removal Window", Toast.LENGTH_SHORT);
                toast1.show();
                GlobalVariables.getInstance().setPreviousActivityForDeletePersonActivity(this.getClass());
                GlobalVariables.getInstance().setPodrodNameForPreviousActivityInShowSanjyraTreeActivity(podrodName);
                intentToMenu1 = new Intent(ShowSanjyraPersonListActivity.this, DeletePersonActivity.class);
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
