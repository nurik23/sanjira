package com.example.kyrgyzpedigree.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.service.DBService;
import com.example.kyrgyzpedigree.util.GlobalVariables;

public class SearchPersonActivity extends AppCompatActivity {

    private DBService db = DBService.getInstance();
    private EditText searchEditText;
    private Button searchButton;
    Toast toast1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_person);

        searchButton = findViewById(R.id.btnSearchPerson);
        searchEditText = findViewById(R.id.editTextSearchPerson);
        toast1 = Toast.makeText(getApplicationContext(),
                "Введите ФИО ", Toast.LENGTH_SHORT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchButton.setOnClickListener(v -> {
            String nameRegex = searchEditText.getText().toString();
            if ((!nameRegex.isEmpty())) {
            GlobalVariables.getInstance().setPersonListFromSearch(db.getAllPersonsByNameLike(nameRegex));
            GlobalVariables.getInstance().setPersonNameRegexFromSearch(nameRegex);
            startActivity(new Intent(SearchPersonActivity.this, SearchPersonResultActivity.class));
            } else {
                toast1.show();
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
