package com.example.kyrgyzpedigree.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kyrgyzpedigree.util.GlobalVariables;
import com.example.kyrgyzpedigree.util.PersonStaticFields;
import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.models.Person;
import com.example.kyrgyzpedigree.models.Podrod;
import com.example.kyrgyzpedigree.models.Rod;
import com.example.kyrgyzpedigree.service.DBService;

import org.springframework.web.client.ResourceAccessException;

import java.util.Calendar;
import java.util.List;

public class SavePersonActivity extends AppCompatActivity {

    EditText editTextName, editTextEmail, editTextMestojitelstva, editTextNamedad, editTextNamemom;

    Spinner spinnerListRod, spinnerListPodrod;
    Button btnSave, btnOtmena, btnMaps;
    DBService db;
    TextView text;
    TextView editTextGodrojdeniya;
    Intent intent;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_person);
        db = DBService.getInstance();
        List<Rod> rodList = null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            rodList = db.getRodList();
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            Toast toast2 = Toast.makeText(getApplicationContext(),
                    "Проблемы с интернетом", Toast.LENGTH_SHORT);
            toast2.show();
            intent = new Intent(SavePersonActivity.this, MainActivity.class);
            startActivity(intent);
            return;
        }
        editTextGodrojdeniya = findViewById(R.id.selectGod);
        editTextName = findViewById(R.id.editName);
        editTextEmail = findViewById(R.id.editEmail);
        editTextMestojitelstva = findViewById(R.id.editAdress);
        editTextNamedad = findViewById(R.id.editDadName);
        editTextNamemom = findViewById(R.id.editMomName);
        text = findViewById(R.id.textForResult);

        editTextGodrojdeniya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SavePersonActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
//                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                editTextGodrojdeniya.setText(date);
            }
        };


        if (PersonStaticFields.name != null) {
            editTextName.setText(PersonStaticFields.name);
        }
        if (PersonStaticFields.email != null) {
            editTextEmail.setText(PersonStaticFields.email);
        }
        if (PersonStaticFields.godrojdeniya != null) {
            editTextGodrojdeniya.setText(PersonStaticFields.godrojdeniya);
        }
        if (PersonStaticFields.mestojitelstva != null) {
            editTextMestojitelstva.setText(PersonStaticFields.mestojitelstva);
        }
        if (PersonStaticFields.namedad != null) {
            editTextNamedad.setText(PersonStaticFields.namedad);
        }
        if (PersonStaticFields.namemom != null) {
            editTextNamemom.setText(PersonStaticFields.namemom);
        }
        String selectedPlaceFromMapActivity = PersonStaticFields.mestojitelstva;
        if (selectedPlaceFromMapActivity != null) {
            PersonStaticFields.mestojitelstva = null;
            editTextMestojitelstva.setText(selectedPlaceFromMapActivity);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSave = findViewById(R.id.buttonSave);
        btnOtmena = findViewById(R.id.buttonOtmena);
        btnMaps = findViewById(R.id.buttonMaps);
        spinnerListRod = findViewById(R.id.spinnerListRod);
        spinnerListPodrod = findViewById(R.id.spinnerListPodrod);

        String[] division = rodList.stream().map(Rod::getName).toArray(String[]::new);
        final String[] div1 = rodList.get(0).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div2 = rodList.get(1).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div3 = rodList.get(2).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div4 = rodList.get(3).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div5 = rodList.get(4).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div6 = rodList.get(5).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div7 = rodList.get(6).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div8 = rodList.get(7).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div9 = rodList.get(8).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div10 = rodList.get(9).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div11 = rodList.get(10).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div12 = rodList.get(11).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div13 = rodList.get(12).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div14 = rodList.get(13).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div15 = rodList.get(14).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div16 = rodList.get(15).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div17 = rodList.get(16).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div18 = rodList.get(17).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div19 = rodList.get(18).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div20 = rodList.get(19).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div21 = rodList.get(20).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div22 = rodList.get(21).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div23 = rodList.get(22).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div24 = rodList.get(23).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div25 = rodList.get(24).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div26 = rodList.get(25).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div27 = rodList.get(26).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div28 = rodList.get(27).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div29 = rodList.get(28).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div30 = rodList.get(29).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);
        final String[] div31 = rodList.get(30).getPodrodList().stream().map(Podrod::getName).toArray(String[]::new);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, division);
        spinnerListRod.setAdapter(adapter);

        spinnerListRod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelect = division[position];
                Toast.makeText(SavePersonActivity.this, "Выбрано : " + itemSelect, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div1);
                    spinnerListPodrod.setAdapter(adapter1);
                }
                if (position == 1) {
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div2);
                    spinnerListPodrod.setAdapter(adapter2);

                }
                if (position == 2) {
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div3);
                    spinnerListPodrod.setAdapter(adapter3);

                }
                if (position == 3) {
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div4);
                    spinnerListPodrod.setAdapter(adapter4);

                }
                if (position == 4) {
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div5);
                    spinnerListPodrod.setAdapter(adapter5);

                }
                if (position == 5) {
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div6);
                    spinnerListPodrod.setAdapter(adapter6);

                }
                if (position == 6) {
                    ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div7);
                    spinnerListPodrod.setAdapter(adapter7);

                }
                if (position == 7) {
                    ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div8);
                    spinnerListPodrod.setAdapter(adapter8);

                }
                if (position == 8) {
                    ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div9);
                    spinnerListPodrod.setAdapter(adapter9);

                }
                if (position == 9) {
                    ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div10);
                    spinnerListPodrod.setAdapter(adapter10);

                }
                if (position == 10) {
                    ArrayAdapter<String> adapter11 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div11);
                    spinnerListPodrod.setAdapter(adapter11);

                }
                if (position == 11) {
                    ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div12);
                    spinnerListPodrod.setAdapter(adapter12);

                }
                if (position == 12) {
                    ArrayAdapter<String> adapter13 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div13);
                    spinnerListPodrod.setAdapter(adapter13);

                }
                if (position == 13) {
                    ArrayAdapter<String> adapter14 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div14);
                    spinnerListPodrod.setAdapter(adapter14);

                }
                if (position == 14) {
                    ArrayAdapter<String> adapter15 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div15);
                    spinnerListPodrod.setAdapter(adapter15);

                }
                if (position == 15) {
                    ArrayAdapter<String> adapter16 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div16);
                    spinnerListPodrod.setAdapter(adapter16);

                }
                if (position == 16) {
                    ArrayAdapter<String> adapter17 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div17);
                    spinnerListPodrod.setAdapter(adapter17);

                }
                if (position == 17) {
                    ArrayAdapter<String> adapter18 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div18);
                    spinnerListPodrod.setAdapter(adapter18);

                }
                if (position == 18) {
                    ArrayAdapter<String> adapter19 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div19);
                    spinnerListPodrod.setAdapter(adapter19);

                }
                if (position == 19) {
                    ArrayAdapter<String> adapter20 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div20);
                    spinnerListPodrod.setAdapter(adapter20);

                }
                if (position == 20) {
                    ArrayAdapter<String> adapter21 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div21);
                    spinnerListPodrod.setAdapter(adapter21);

                }
                if (position == 21) {
                    ArrayAdapter<String> adapter22 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div22);
                    spinnerListPodrod.setAdapter(adapter22);

                }
                if (position == 22) {
                    ArrayAdapter<String> adapter23 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div23);
                    spinnerListPodrod.setAdapter(adapter23);

                }
                if (position == 23) {
                    ArrayAdapter<String> adapter24 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div24);
                    spinnerListPodrod.setAdapter(adapter24);

                }
                if (position == 24) {
                    ArrayAdapter<String> adapter25 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div25);
                    spinnerListPodrod.setAdapter(adapter25);

                }
                if (position == 25) {
                    ArrayAdapter<String> adapter26 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div26);
                    spinnerListPodrod.setAdapter(adapter26);

                }
                if (position == 26) {
                    ArrayAdapter<String> adapter27 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div27);
                    spinnerListPodrod.setAdapter(adapter27);

                }
                if (position == 27) {
                    ArrayAdapter<String> adapter28 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div28);
                    spinnerListPodrod.setAdapter(adapter28);

                }
                if (position == 28) {
                    ArrayAdapter<String> adapter29 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div29);
                    spinnerListPodrod.setAdapter(adapter29);

                }
                if (position == 29) {
                    ArrayAdapter<String> adapter30 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div30);
                    spinnerListPodrod.setAdapter(adapter30);

                }
                if (position == 30) {
                    ArrayAdapter<String> adapter31 = new ArrayAdapter<String>(SavePersonActivity.this, android.R.layout.simple_spinner_dropdown_item, div31);
                    spinnerListPodrod.setAdapter(adapter31);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnOtmena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SavePersonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonStaticFields.name = editTextName.getText().toString();
                PersonStaticFields.email = editTextEmail.getText().toString();
                PersonStaticFields.mestojitelstva = editTextMestojitelstva.getText().toString();
                PersonStaticFields.godrojdeniya = editTextGodrojdeniya.getText().toString();
                PersonStaticFields.namedad = editTextNamedad.getText().toString();
                PersonStaticFields.namemom = editTextNamemom.getText().toString();
                PersonStaticFields.rod = spinnerListRod.getSelectedItem().toString();
                PersonStaticFields.podrod = spinnerListPodrod.getSelectedItem().toString();
                intent = new Intent(SavePersonActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String mestojitelstva = editTextMestojitelstva.getText().toString();
                String godrojdeniya = editTextGodrojdeniya.getText().toString();
                String namedad = editTextNamedad.getText().toString();
                String namemom = editTextNamemom.getText().toString();
                String selectedRod = spinnerListRod.getSelectedItem().toString();
                String selectedPodrod = spinnerListPodrod.getSelectedItem().toString();

                Person person = new Person();

                Toast toastName = Toast.makeText(getApplicationContext(),
                        "Введите ФИО", Toast.LENGTH_SHORT);
                Toast toastEmail = Toast.makeText(getApplicationContext(),
                        "Введите электронную почту", Toast.LENGTH_SHORT);
                Toast toastMestojitelstva = Toast.makeText(getApplicationContext(),
                        "Введите место проживания", Toast.LENGTH_SHORT);
                Toast toastGodrojdeniya = Toast.makeText(getApplicationContext(),
                        "Выберите дату рождения", Toast.LENGTH_SHORT);
                Toast toastNamedad = Toast.makeText(getApplicationContext(),
                        "Введите ФИО отца", Toast.LENGTH_SHORT);
                Toast toastNamemom = Toast.makeText(getApplicationContext(),
                        "Введите ФИО матери", Toast.LENGTH_SHORT);


                if (name != null && !name.isEmpty()) {
                    person.setName(name);
                    if (email != null && !email.isEmpty()) {
                        person.setEmail(email);
                        if (godrojdeniya != null && !godrojdeniya.isEmpty()) {
                            person.setGodrojdeniya(godrojdeniya);
                            if (mestojitelstva != null && !mestojitelstva.isEmpty()) {
                                person.setMestojitelstva(mestojitelstva);
                                if (namedad != null && !namedad.isEmpty()) {
                                    person.setNamedad(namedad);
                                    if (namemom != null && !namemom.isEmpty()) {
                                        person.setNamemom(namemom);
                                        person.setPodrod(selectedRod);
                                        db.savePerson(person, selectedPodrod);
                                        finish();
                                        PersonStaticFields.resetFields();
                                        Toast toast2 = Toast.makeText(getApplicationContext(),
                                                "Данные успешно добавлены в базу данных.", Toast.LENGTH_SHORT);
                                        toast2.show();
                                        intent = new Intent(SavePersonActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else toastNamemom.show();
                                } else toastNamedad.show();
                            } else toastMestojitelstva.show();
                        } else toastGodrojdeniya.show();
                    } else toastEmail.show();
                } else toastName.show();

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
