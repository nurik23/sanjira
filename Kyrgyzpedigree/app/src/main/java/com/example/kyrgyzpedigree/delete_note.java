package com.example.kyrgyzpedigree;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class delete_note extends AppCompatActivity {

    DatabaseHelper db;
    EditText editText;
    Button button;
    Person person;
    ArrayList<Person> allPerson;
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
        allPerson = new ArrayList<Person>();
        allPerson = db.getAllPersons();
        toast1 = Toast.makeText(getApplicationContext(),
                "Введите <ID>", Toast.LENGTH_SHORT);
        toast2 = Toast.makeText(getApplicationContext(),
                "Успешно удалено", Toast.LENGTH_SHORT);
        toast3 = Toast.makeText(getApplicationContext(),
                "Такое <ID> не найдено", Toast.LENGTH_SHORT);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idToDelete = editText.getText().toString();
                if (idToDelete != null && !idToDelete.isEmpty()){
                    idResult = Integer.parseInt(idToDelete);
                    for (int i = 0; i < allPerson.size(); i++) {
                        if (idResult == allPerson.get(i).getId()) {
                            db.deletePerson(allPerson.get(i));
                            intentToShowActivity = new Intent(delete_note.this, showSanjyra.class);
                            startActivity(intentToShowActivity);
                            toast2.show();
                            break;
                        } else text.setText("Такого <ID> не найдено, введите существующий!");
                    }
                } else toast1.show();
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
