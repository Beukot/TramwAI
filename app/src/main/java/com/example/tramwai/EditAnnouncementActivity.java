package com.example.tramwai;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class EditAnnouncementActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button saveButton;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_announcement);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        saveButton = findViewById(R.id.saveButton);

        // Get data from intent if editing existing announcement
        Intent intent = getIntent();
        if (intent.hasExtra("title")) {
            titleEditText.setText(intent.getStringExtra("title"));
            descriptionEditText.setText(intent.getStringExtra("description"));
            position = intent.getIntExtra("position", -1);
        }

        saveButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", titleEditText.getText().toString());
            resultIntent.putExtra("description", descriptionEditText.getText().toString());
            resultIntent.putExtra("position", position);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}

