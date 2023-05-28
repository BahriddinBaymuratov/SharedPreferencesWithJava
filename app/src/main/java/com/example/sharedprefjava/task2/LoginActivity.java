package com.example.sharedprefjava.task2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
import java.util.List;

public class LoginActivity extends AppCompatActivity {
//    private SharedPreferences sharedPreferences;
//    private SharedPreferences.Editor editor;

    private EditText editText1, editText2;
    private MaterialButton btnLogin;
    private TextView textView;
    private MySharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findUi();
        mySharedPreferences = MySharedPreferences.getInstance(this);
//        sharedPreferences = getSharedPreferences("Login",MODE_PRIVATE);
//        editor = sharedPreferences.edit();

        btnLogin.setOnClickListener(view -> {
            // todo: Check user
//            String userJsonString = sharedPreferences.getString("users", "");
            String userJsonString = mySharedPreferences.getUser();
            if (userJsonString.equals("")){
                Toast.makeText(this, "Ro'yhat bo'sh", Toast.LENGTH_SHORT).show();
            }else {
                Type type = new TypeToken<List<User>>() {}.getType();
                List<User> userList = MyGson.getInstance().getGson().fromJson(userJsonString,type);

                String userName = editText1.getText().toString();
                String password = editText2.getText().toString();
                boolean isHave = false;
                for (User user : userList) {
                    if (user.getUserName().equals(userName) && user.getPassword().equals(password)){
                        isHave = true;
                        break;
                    }
                }
                if (isHave){
                    Intent intent = new Intent(this, DetailActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "UserName yoki Parol Xato !! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
//            startActivity(intent);
            activityResultLauncher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    String username = data.getStringExtra("username");
                    String password = data.getStringExtra("password");
                    editText1.setText(username);
                    editText2.setText(password);
                }
            }
    );

    private void findUi() {
        editText1 = findViewById(R.id.editName);
        editText2 = findViewById(R.id.editPass);
        btnLogin = findViewById(R.id.btnLogin);
        textView = findViewById(R.id.register);
    }
}