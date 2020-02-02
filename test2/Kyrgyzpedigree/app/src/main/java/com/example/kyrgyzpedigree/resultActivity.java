package com.example.kyrgyzpedigree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class resultActivity extends AppCompatActivity {

    TextView textViewTop;
    ListView listResult;
    DatabaseHelper db;
    Intent intentToMenu1, intent;
    Button btnHome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewTop = findViewById(R.id.textTop);
        listResult = findViewById(R.id.listResult);
        btnHome = findViewById(R.id.btnHomeResult);
        db = new DatabaseHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String textIntent = getIntent().getStringExtra("selectedValue");
        textViewTop.setText("List of people in the tribe : <"+textIntent+">");

        ArrayAdapter<Person> itemsAdapter =
                new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, db.getAllPersonsSanjyra(textIntent));

        listResult.setAdapter(itemsAdapter);
        if (itemsAdapter.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "In the tribe <"+textIntent+"> no entries", Toast.LENGTH_SHORT);
            toast.show();
        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(resultActivity.this, EnterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (Globalvar.isAdmin==true) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.result_menu, menu);
            return true;
        }
        else return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.delete_note:
                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Removal Window", Toast.LENGTH_SHORT);
                toast1.show();
                intentToMenu1 = new Intent(resultActivity.this, delete_note.class);
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
