package com.example.setgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.setgame.Server.Registration;

import java.io.IOException;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity {
    EditText user_name;
    TextView mes;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name = findViewById(R.id.name);
        mes = findViewById(R.id.message);
        preferences = getSharedPreferences("token_names", Context.MODE_PRIVATE);
    }

    public void onClick(View v) throws IOException {
        final String name = user_name.getText().toString();
        Registration serv = new Registration();
        String regist = "{\"action\": \"register\", \"nickname\": \"" + name + "\"}";
        serv.execute(new Pair<String, Function<Response, Void>>(regist, new Function<Response, Void>() {
            @Override
            public Void apply(Response response) {
                RegisterResponse reg = (RegisterResponse) response;
                if (reg.status.equals("error")) {
                    int token = preferences.getInt("token", -1);
                    if (token == -1) {
                        mes.setText("This name is already exists");
                    }
                    else {
                        Intent i = new Intent(MainActivity.this, GameActivity.class);
                        i.putExtra("token", token);
                        startActivity(i);
                    }

                } else if (reg.status.equals("ok")) {
                    preferences.edit().putInt("token", reg.token).apply();
                    Intent i = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(i);
                } else {
                    mes.setText("Unknown error");
                }
                return null;
            }
        }));
    }
}
