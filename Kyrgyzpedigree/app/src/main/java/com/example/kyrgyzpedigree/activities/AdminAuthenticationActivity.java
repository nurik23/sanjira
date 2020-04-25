package com.example.kyrgyzpedigree.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kyrgyzpedigree.util.GlobalVariables;
import com.example.kyrgyzpedigree.R;

public class AdminAuthenticationActivity extends AppCompatActivity {

    EditText editText1, editText2;
    Button button, buttonHome;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_authentication);

        editText1 = findViewById(R.id.edittextLogin);
        editText2 = findViewById(R.id.edittextPassword);
        button = findViewById(R.id.btnEnter);
        buttonHome = findViewById(R.id.btnBackToHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString().equals("admin")&&editText2.getText().toString().equals("admin")){
                    GlobalVariables.getInstance().setAdmin(true);
                    intent = new Intent(AdminAuthenticationActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast toast2 = Toast.makeText(getApplicationContext(),
                            "Данные не верные! ", Toast.LENGTH_SHORT);
                    toast2.show();
                }
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    intent = new Intent(AdminAuthenticationActivity.this, StartActivity.class);
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
