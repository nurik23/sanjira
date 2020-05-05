package com.example.kyrgyzpedigree.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kyrgyzpedigree.R;
import com.example.kyrgyzpedigree.models.Podrod;
import com.example.kyrgyzpedigree.models.Rod;
import com.example.kyrgyzpedigree.service.DBService;

import org.springframework.web.client.ResourceAccessException;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class ShowSanjyraActivity extends AppCompatActivity {

    Button button;
    Intent intent;
    //Spinner spinnerSanjyra , spinnerSanjyra2;
    Spinner spinnerListRod, spinnerListPodrod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sanjyra);
        // spinnerSanjyra = findViewById(R.id.spinnerSanjyra);  //Rod
        //  spinnerSanjyra2 = findViewById(R.id.spinnerSanjyra2); //Podrod
        button = findViewById(R.id.btnSelect);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerListRod = findViewById(R.id.spinnerListRod);
        spinnerListPodrod = findViewById(R.id.spinnerListPodrod);

        DBService db = DBService.getInstance();
        List<Rod> rodList;
        try {
            rodList = db.getRodList();
        } catch (ResourceAccessException e) {
            e.printStackTrace();
            Toast toast2 = Toast.makeText(getApplicationContext(),
                    "Problem with network", Toast.LENGTH_SHORT);
            toast2.show();
            return;
        }
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
                Toast.makeText(ShowSanjyraActivity.this, "Select Item : " + itemSelect, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div1);
                    spinnerListPodrod.setAdapter(adapter1);
                }
                if (position == 1) {
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div2);
                    spinnerListPodrod.setAdapter(adapter2);

                }
                if (position == 2) {
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div3);
                    spinnerListPodrod.setAdapter(adapter3);

                }
                if (position == 3) {
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div4);
                    spinnerListPodrod.setAdapter(adapter4);

                }
                if (position == 4) {
                    ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div5);
                    spinnerListPodrod.setAdapter(adapter5);

                }
                if (position == 5) {
                    ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div6);
                    spinnerListPodrod.setAdapter(adapter6);

                }
                if (position == 6) {
                    ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div7);
                    spinnerListPodrod.setAdapter(adapter7);

                }
                if (position == 7) {
                    ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div8);
                    spinnerListPodrod.setAdapter(adapter8);

                }
                if (position == 8) {
                    ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div9);
                    spinnerListPodrod.setAdapter(adapter9);

                }
                if (position == 9) {
                    ArrayAdapter<String> adapter10 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div10);
                    spinnerListPodrod.setAdapter(adapter10);

                }
                if (position == 10) {
                    ArrayAdapter<String> adapter11 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div11);
                    spinnerListPodrod.setAdapter(adapter11);

                }
                if (position == 11) {
                    ArrayAdapter<String> adapter12 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div12);
                    spinnerListPodrod.setAdapter(adapter12);

                }
                if (position == 12) {
                    ArrayAdapter<String> adapter13 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div13);
                    spinnerListPodrod.setAdapter(adapter13);

                }
                if (position == 13) {
                    ArrayAdapter<String> adapter14 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div14);
                    spinnerListPodrod.setAdapter(adapter14);

                }
                if (position == 14) {
                    ArrayAdapter<String> adapter15 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div15);
                    spinnerListPodrod.setAdapter(adapter15);

                }
                if (position == 15) {
                    ArrayAdapter<String> adapter16 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div16);
                    spinnerListPodrod.setAdapter(adapter16);

                }
                if (position == 16) {
                    ArrayAdapter<String> adapter17 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div17);
                    spinnerListPodrod.setAdapter(adapter17);

                }
                if (position == 17) {
                    ArrayAdapter<String> adapter18 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div18);
                    spinnerListPodrod.setAdapter(adapter18);

                }
                if (position == 18) {
                    ArrayAdapter<String> adapter19 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div19);
                    spinnerListPodrod.setAdapter(adapter19);

                }
                if (position == 19) {
                    ArrayAdapter<String> adapter20 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div20);
                    spinnerListPodrod.setAdapter(adapter20);

                }
                if (position == 20) {
                    ArrayAdapter<String> adapter21 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div21);
                    spinnerListPodrod.setAdapter(adapter21);

                }
                if (position == 21) {
                    ArrayAdapter<String> adapter22 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div22);
                    spinnerListPodrod.setAdapter(adapter22);

                }
                if (position == 22) {
                    ArrayAdapter<String> adapter23 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div23);
                    spinnerListPodrod.setAdapter(adapter23);

                }
                if (position == 23) {
                    ArrayAdapter<String> adapter24 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div24);
                    spinnerListPodrod.setAdapter(adapter24);

                }
                if (position == 24) {
                    ArrayAdapter<String> adapter25 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div25);
                    spinnerListPodrod.setAdapter(adapter25);

                }
                if (position == 25) {
                    ArrayAdapter<String> adapter26 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div26);
                    spinnerListPodrod.setAdapter(adapter26);

                }
                if (position == 26) {
                    ArrayAdapter<String> adapter27 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div27);
                    spinnerListPodrod.setAdapter(adapter27);

                }
                if (position == 27) {
                    ArrayAdapter<String> adapter28 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div28);
                    spinnerListPodrod.setAdapter(adapter28);

                }
                if (position == 28) {
                    ArrayAdapter<String> adapter29 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div29);
                    spinnerListPodrod.setAdapter(adapter29);

                }
                if (position == 29) {
                    ArrayAdapter<String> adapter30 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div30);
                    spinnerListPodrod.setAdapter(adapter30);

                }
                if (position == 30) {
                    ArrayAdapter<String> adapter31 = new ArrayAdapter<String>(ShowSanjyraActivity.this, android.R.layout.simple_spinner_dropdown_item, div31);
                    spinnerListPodrod.setAdapter(adapter31);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String selectedValueOfSanjyra = spinnerSanjyra.getSelectedItem().toString();
                String selectedValueOfSanjyra = spinnerListPodrod.getSelectedItem().toString();
                intent = new Intent(ShowSanjyraActivity.this, ShowSanjyraPersonListActivity.class);
                intent.putExtra("selectedValue", selectedValueOfSanjyra);
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Выбрано : <" + selectedValueOfSanjyra + ">", Toast.LENGTH_SHORT);
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