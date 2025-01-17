package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //declare variables
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    private int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Button button = findViewById(R.id.add_button);
//        cityList.SetOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//                public void OnItemClick(AdapterView<?> parent, View view, int postion, long id){
//
//            }
//
//
//        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //very important
        EdgeToEdge.enable(this);

        cityList = findViewById(R.id.city_list);
        EditText cityInput = findViewById(R.id.input_city);
        Button addButton = findViewById(R.id.add_button);
        Button confirmButton = findViewById(R.id.confirm_button);
        Button deleteButton = findViewById(R.id.delete_button);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Fort Saskatchewan", "Calgary", "Nanaimo", "Medicine Hat"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        cityList.setOnItemClickListener((parent, view, position, id) -> selectedPosition = position);

        // Add city
        addButton.setOnClickListener(v -> {
            cityInput.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.VISIBLE);
        });


        confirmButton.setOnClickListener(v -> {
            String cityName = cityInput.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                cityInput.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
            } else {
                Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
            }
        });

        //delete
        deleteButton.setOnClickListener(v -> {
            if (selectedPosition >= 0 && selectedPosition < dataList.size()) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1; //
            } else {
                Toast.makeText(MainActivity.this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
            }
        });





    }
}