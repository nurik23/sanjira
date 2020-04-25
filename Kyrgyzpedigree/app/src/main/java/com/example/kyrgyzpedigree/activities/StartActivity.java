package com.example.kyrgyzpedigree.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kyrgyzpedigree.R;

public class StartActivity extends AppCompatActivity {

    Button btnAddData, btnShowSanjyra;
    Intent intent;
    Intent intentToMenu1, intentToMenu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnAddData = findViewById(R.id.btnAddData);
        btnShowSanjyra = findViewById(R.id.btnShowSanjyra);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            intent = new Intent(StartActivity.this, AdminAuthenticationActivity.class);
            startActivity(intent);
            }
        });

        btnShowSanjyra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
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
