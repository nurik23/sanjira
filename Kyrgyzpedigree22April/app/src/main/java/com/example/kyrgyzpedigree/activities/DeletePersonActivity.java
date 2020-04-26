package com.example.kyrgyzpedigree.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kyrgyzpedigree.models.Person;
import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.service.DBService;
import com.example.kyrgyzpedigree.util.GlobalVariables;

import org.springframework.web.client.HttpServerErrorException;

public class DeletePersonActivity extends AppCompatActivity {

    DBService db;
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
        setContentView(R.layout.activity_delete_person);

        editText = findViewById(R.id.edit);
        button = findViewById(R.id.btn);
        text = findViewById(R.id.textViewDeleteNote);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //кнопка назад
        db = DBService.getInstance();
        person = new Person();
        allPerson = new ArrayList<>();
        allPerson = db.getAllPersons();
        toast1 = Toast.makeText(getApplicationContext(),
                "Введите <ID>", Toast.LENGTH_SHORT);
        toast2 = Toast.makeText(getApplicationContext(),
                "Удалено успешно", Toast.LENGTH_SHORT);
        toast3 = Toast.makeText(getApplicationContext(),
                "Участник с указанным <ID> не найден", Toast.LENGTH_SHORT);

        button.setOnClickListener(v -> {
            String idToDelete = editText.getText().toString();
            if ((!idToDelete.isEmpty())) {
                try {
                    db.deletePerson(Integer.parseInt(idToDelete));
                } catch (HttpServerErrorException e) {
                    toast3.show();
                }
                toast2.show();
                startActivity(new Intent(DeletePersonActivity.this, GlobalVariables.getInstance().getPreviousActivityForDeletePersonActivity()));
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
