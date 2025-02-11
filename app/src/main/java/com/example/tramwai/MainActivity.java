package com.example.tramwai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String[] LOGINS = {"a"};
    private static final String[] PASSWORDS = {"1"};

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (checkCredentials(username, password)) {
                    Intent intent = new Intent(MainActivity.this, AnnoucementsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Nieprawidłowy login lub hasło",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkCredentials(String username, String password) {
        for (int i = 0; i < LOGINS.length; i++) {
            if (LOGINS[i].equals(username) && PASSWORDS[i].equals(password)) {
                return true;
            }
        }
        return false;
    }
}

