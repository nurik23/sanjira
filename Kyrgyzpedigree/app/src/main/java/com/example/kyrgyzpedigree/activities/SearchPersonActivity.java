package com.example.kyrgyzpedigree.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.service.DBService;
import com.example.kyrgyzpedigree.util.GlobalVariables;

public class SearchPersonActivity extends AppCompatActivity {

    private DBService db = DBService.getInstance();
    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_person);

        searchButton = findViewById(R.id.btnSearchPerson);
        searchEditText = findViewById(R.id.editTextSearchPerson);

        searchButton.setOnClickListener(v -> {
            String nameRegex = searchEditText.getText().toString();
            GlobalVariables.getInstance().setPersonListFromSearch(db.getAllPersonsByNameLike(nameRegex));
            GlobalVariables.getInstance().setPersonNameRegexFromSearch(nameRegex);
            startActivity(new Intent(SearchPersonActivity.this, SearchPersonResultActivity.class));
        });
    }
}
