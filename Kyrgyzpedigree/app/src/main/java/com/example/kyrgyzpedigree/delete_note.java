package com.example.kyrgyzpedigree;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class delete_note extends AppCompatActivity {

    DatabaseHelper db;
    EditText editText;
    Button button;
    Person person;
    List<Person> allPerson;
    int idResult;
    Intent intentToShowActivity;
    Toast toast1, toast2, toast3;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        editText = findViewById(R.id.edit);
        button = findViewById(R.id.btn);
        text = findViewById(R.id.textViewDeleteNote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //кнопка назад
        db = new DatabaseHelper(this);
        person = new Person();
        allPerson = new ArrayList<>();
        allPerson = db.getAllPersons();
        toast1 = Toast.makeText(getApplicationContext(),
                "Enter <ID>", Toast.LENGTH_SHORT);
        toast2 = Toast.makeText(getApplicationContext(),
                "Successfully deleted", Toast.LENGTH_SHORT);
        toast3 = Toast.makeText(getApplicationContext(),
                "No such <ID> found", Toast.LENGTH_SHORT);

        button.setOnClickListener(v -> {
            String idToDelete = editText.getText().toString();
            if ((!idToDelete.isEmpty())) {
                db.deletePerson(Integer.parseInt(idToDelete));
                intentToShowActivity = new Intent(delete_note.this, showSanjyra.class);
                startActivity(intentToShowActivity);
                toast2.show();
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
