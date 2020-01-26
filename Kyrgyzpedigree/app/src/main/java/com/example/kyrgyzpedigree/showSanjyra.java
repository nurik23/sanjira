package com.example.kyrgyzpedigree;

import android.app.ActionBar;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class showSanjyra extends AppCompatActivity {

    Button button;
    Intent intent;
    Spinner spinnerSanjyra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sanjyra);
        spinnerSanjyra = findViewById(R.id.spinnerSanjyra);



        button = findViewById(R.id.btnSelect);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedValueOfSanjyra = spinnerSanjyra.getSelectedItem().toString();
                intent = new Intent(showSanjyra.this, resultActivity.class);
                intent.putExtra("selectedValue", selectedValueOfSanjyra);
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Выбрали <"+selectedValueOfSanjyra+">", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
