package com.example.sharedprefjava.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sharedprefjava.R;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button saveBtn, showBtn;
    private TextView textView;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findUi();
        sharedPreferences = getSharedPreferences("My_shared", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        saveBtn.setOnClickListener(view -> {
            String str = editText.getText().toString();
            // cashga malumotlarni saqlemiz
            editor.putString("key1", str);
            editor.commit(); // commit da true yoki false qaytaradi
            editor.apply(); // apply bir qancha malumotlarni parallel saqlasa boladi

        });

        showBtn.setOnClickListener(view -> {
            String str = sharedPreferences.getString("key1", "Xatolik !!");
            textView.setText(str);
            // cashdagi malumotlarni oqib olamiz
        });
    }

    private void findUi() {
        saveBtn = findViewById(R.id.btnSave);
        showBtn = findViewById(R.id.btnShow);
        editText = findViewById(R.id.edit);
        textView = findViewById(R.id.text);

    }
}