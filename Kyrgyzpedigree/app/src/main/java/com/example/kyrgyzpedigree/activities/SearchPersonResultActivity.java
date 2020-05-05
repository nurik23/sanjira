package com.example.kyrgyzpedigree.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.models.PersonDto;
import com.example.kyrgyzpedigree.service.DBService;
import com.example.kyrgyzpedigree.util.GlobalVariables;

import java.util.List;

public class SearchPersonResultActivity extends AppCompatActivity {

    private TextView textViewTop;
    private ListView listResult;
    private DBService db;
    private Intent intentToMenu1, intent;
    private Button btnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_person_result);

        textViewTop = findViewById(R.id.textTop);
        listResult = findViewById(R.id.listResult);
        btnHome = findViewById(R.id.btnHomeResult);
        db = DBService.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String personNameRegexFromSearch = GlobalVariables.getInstance().getPersonNameRegexFromSearch();
        textViewTop.setText("Результаты запроса по : <" + personNameRegexFromSearch + ">");
        List<PersonDto> allPersonsByNameLike = db.getAllPersonsByNameLike(personNameRegexFromSearch);
        ArrayAdapter<PersonDto> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allPersonsByNameLike);

        listResult.setAdapter(itemsAdapter);
        if (itemsAdapter.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "По вашему запросу : <" + personNameRegexFromSearch + "> ничего не найдено", Toast.LENGTH_SHORT).show();
        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchPersonResultActivity.this, MainActivity.class));
            }
        });
    }
}
