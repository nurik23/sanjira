package com.example.kyrgyzpedigree;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.*;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.ArrayList;
import java.util.Map;

public class resultActivity extends AppCompatActivity {

    TextView textViewTop;
    ListView listResult;
    DatabaseHelper db;
    Intent intentToMenu1, intentToMenu2;
    DatabaseReference ref;
    ArrayList<String> mUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewTop = findViewById(R.id.textTop);
        listResult = findViewById(R.id.listResult);
        //db = new DatabaseHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String textIntent = getIntent().getStringExtra("selectedValue");
        textViewTop.setText("Список людей в роду <"+textIntent+">");

        /*ArrayAdapter<Person> itemsAdapter =
                new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, db.getAllPersonsSanjyra(textIntent));
        listResult.setAdapter(itemsAdapter);
        */

        //ref = new DatabaseReference ("https://kyrgyzpedigree.firebaseio.com/Person/1");
        ref = FirebaseDatabase.getInstance().getReference().child("Person").child("2");
        listResult = (ListView)findViewById(R.id.listResult);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mUser );

        listResult.setAdapter(arrayAdapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String value = dataSnapshot.getValue(String.class);
                /*Map<String, String> value = dataSnapshot.getValue(Map.class);
                String email = value.get("email");
                String godrojdeniya = value.get("godrojdeniya");
                String id = value.get("id");
                String mestojitelstva = value.get("mestojitelstva");
                String name = value.get("name");
                String podrod = value.get("podrod");
                String rod = value.get("rod");
*/
                mUser.add(value);
                arrayAdapter.notifyDataSetChanged();
                /*Log.v("E_VALUE", "email :" + email );
                Log.v("E_VALUE", "godrojdeniya :" + godrojdeniya );
                Log.v("E_VALUE", "id :" + id );
                Log.v("E_VALUE", "mestojitelstva :" + mestojitelstva );
                Log.v("E_VALUE", "Name :" + name );
                Log.v("E_VALUE", "podrod :" + podrod );
                Log.v("E_VALUE", "rod :" + rod );
*/


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        /*
        if (arrayAdapter.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "В роду <"+textIntent+"> нету записей", Toast.LENGTH_SHORT);
            toast.show();
        }*/

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.result_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.delete_note:
                Toast toast1 = Toast.makeText(getApplicationContext(),
                        "Окно для удаления", Toast.LENGTH_SHORT);
                toast1.show();
                intentToMenu1 = new Intent(resultActivity.this, delete_note.class);
                startActivity(intentToMenu1);
                return true;
            case R.id.help:
                Toast toast2 = Toast.makeText(getApplicationContext(),
                        "О приложении", Toast.LENGTH_SHORT);
                toast2.show();
                intentToMenu2 = new Intent(resultActivity.this, infoActivity.class);
                startActivity(intentToMenu2);
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
