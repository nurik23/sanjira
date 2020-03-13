
package com.example.kyrgyzpedigree;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ShowSanjyraTree extends AppCompatActivity {


    Button selectButton;
    Spinner spinnerSanjyra;
    String lastSelectedRod = "nothing";
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sanjyra_tree);


        final String div1[] = {"Ачакей", "Чокон", "Ажыбек", "Тен-Терт"};
        final String div2[] = {"Коткар", "Кушчу2"};
        final String div3[] = {"Карабагыш1", "Карабагыш2", "Карабагыш3"};
        final String div4[] = {"1", "2", "3"};
        final String div5[] = {"1", "2", "3"};
        final String div6[] = {"1", "2", "3"};
        final String div7[] = {"1", "2", "3"};
        final String div8[] = {"1", "2", "3"};
        final String div9[] = {"1", "2", "3"};
        final String div10[] = {"1", "2", "3"};
        final String div11[] = {"1", "2", "3"};
        final String div12[] = {"1", "2", "3"};
        final String div13[] = {"1", "2", "3"};
        final String div14[] = {"1", "2", "3"};
        final String div15[] = {"1", "2", "3"};
        final String div16[] = {"1", "2", "3"};
        final String div17[] = {"1", "2", "3"};
        final String div18[] = {"1", "2", "3"};
        final String div19[] = {"1", "2", "3"};
        final String div20[] = {"1", "2", "3"};
        final String div21[] = {"1", "2", "3"};
        final String div22[] = {"1", "2", "3"};
        final String div23[] = {"1", "2", "3"};
        final String div24[] = {"1", "2", "3"};
        final String div25[] = {"1", "2", "3"};
        final String div26[] = {"1", "2", "3"};
        final String div27[] = {"1", "2", "3"};
        final String div28[] = {"1", "2", "3"};
        final String div29[] = {"1", "2", "3"};
        final String div30[] = {"1", "2", "3"};
        final String div31[] = {"1", "2", "3"};
        List<String[]> prodrodList = Arrays.asList(div1, div2, div3, div4, div5, div6, div7,
                div8, div9, div10, div11, div12, div13, div14, div15, div16, div17, div18,
                div19, div20, div21, div22, div23, div24, div25, div26, div27, div28, div29,
                div30, div31);
        List<String> rodList = Arrays.asList("Саруу", "Кушчу", "Карабагыш", "Мундуз", "Чонбагыш", "Кытай", "Жетиген", "Басыз"
                , "Карачоро", "Саяк", "Монолдор", "Сарыбагыш", "Солто"
                , "Бугу", "Азык", "Монгуш", "Черик", "Багыш", "Адыгине", "Жедигер", "Ават", "Бостон", "Кыдыршаа", "Доолос"
                , "Найман", "Канды", "Кесек", "Жоокесек", "Каратейит"
                , "Нойгут", "Кыпчак");
        spinnerSanjyra = findViewById(R.id.spinnerListRod);
        selectButton = findViewById(R.id.btnSelect);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, rodList);
        spinnerSanjyra.setAdapter(adapter);

        selectButton.setOnClickListener(v -> {
            String selectedValueOfSanjyra = spinnerSanjyra.getSelectedItem().toString();
            if (lastSelectedRod.equals(selectedValueOfSanjyra)) {
                return;
            }
            lastSelectedRod = selectedValueOfSanjyra;
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Выбрали " + selectedValueOfSanjyra + "", Toast.LENGTH_SHORT);
            toast.show();
            int selectedRodIndex = 0;
            for (int i = 0; i < rodList.size(); i++) {
                if (rodList.get(i).equals(selectedValueOfSanjyra)) {
                    selectedRodIndex = i;
                    i = rodList.size();
                }
            }
            String[] podrodArray = prodrodList.get(selectedRodIndex);
            for (String podrod : podrodArray) {
                List<Person> allPersonsInPodrod = databaseHelper.getAllPersonsSanjyra(podrod);
                System.out.println("podrod : "+podrod);
                for (Person person : allPersonsInPodrod) {
                    System.out.println("person name :"+ person.getName());
                }

            }
        });
    }
}