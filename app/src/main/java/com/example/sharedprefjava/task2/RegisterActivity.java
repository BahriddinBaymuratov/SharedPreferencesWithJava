package com.example.sharedprefjava.task2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharedprefjava.R;
import com.example.sharedprefjava.task2.model.User;
import com.example.sharedprefjava.task2.shared_pref.MySharedPreferences;
import com.example.sharedprefjava.task2.singleton.MyGson;
import com.google.android.material.button.MaterialButton;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText fullName, userName, password, confPass, phoneNum, email;
    private MaterialButton btnRegister;

    private MySharedPreferences mySharedPreferences;
//    private SharedPreferences sharedPreferences;
//    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findUi();
        mySharedPreferences = MySharedPreferences.getInstance(this);
//        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
//        editor = sharedPreferences.edit();

        btnRegister.setOnClickListener(view -> {
            if (isValid()) {
                String fullName = this.fullName.getText().toString();
                String email = this.email.getText().toString();
                String phoneNum = this.phoneNum.getText().toString();
                String userName = this.userName.getText().toString();
                String password = this.password.getText().toString();
                User u1 = new User(fullName, email, phoneNum, userName, password);

//                String userJsonString = sharedPreferences.getString("users", "");
                String userJsonString = mySharedPreferences.getUser();
                List<User> userList;
                if (userJsonString.equals("")) {
                    userList = new ArrayList<>();
                } else {
                    Type type = new TypeToken<List<User>>() {
                    }.getType();
                    userList = MyGson.getInstance().getGson().fromJson(userJsonString, type);
                }
                userList.add(u1);
                String jsonString = MyGson.getInstance().getGson().toJson(userList);
//                editor.putString("users", jsonString).commit();

//                if (editor.commit()) {
                if (mySharedPreferences.setUser(jsonString)) {
                    Intent intent = new Intent();
                    intent.putExtra("username", userName);
                    intent.putExtra("password", password);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private boolean isValid() {
        if (fullName.getText().toString().isEmpty()) {
            Toast.makeText(this, "FIO kiriting", Toast.LENGTH_SHORT).show();
            return false;
        } else if (email.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email kiriting", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phoneNum.getText().toString().isEmpty()) {
            Toast.makeText(this, "Telefon raqam kiriting", Toast.LENGTH_SHORT).show();
            return false;
        } else if (userName.getText().toString().isEmpty()) {
            Toast.makeText(this, "UserName kiriting", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Parol kiriting", Toast.LENGTH_SHORT).show();
            return false;
        } else if (confPass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Parolni qayta kiriting", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.getText().toString().equals(confPass.getText().toString())) {
            Toast.makeText(this, "Parollar bir xil emas !", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void findUi() {
        fullName = findViewById(R.id.editFullName);
        email = findViewById(R.id.editEmail);
        phoneNum = findViewById(R.id.editPhone);
        userName = findViewById(R.id.editUserName);
        password = findViewById(R.id.editPass);
        confPass = findViewById(R.id.retryPass);
        btnRegister = findViewById(R.id.btnRegister);
    }

}