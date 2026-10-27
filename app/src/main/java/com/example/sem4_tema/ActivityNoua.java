package com.example.sem4_tema;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityNoua extends AppCompatActivity {

    private EditText etCreatorName;
    private EditText etCategory;
    private EditText etTargetAudience;
    private CheckBox cbMonetized;
    private CheckBox cbPublic;
    private RatingBar rbRating;
    private Button btnSave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noua);

        etCreatorName = findViewById(R.id.etCreatorName);
        etCategory = findViewById(R.id.etCategory);
        etTargetAudience = findViewById(R.id.etTargetAudience);
        cbMonetized = findViewById(R.id.cbMonetized);
        cbPublic = findViewById(R.id.cbPublic);
        rbRating = findViewById(R.id.rbRating);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String creatorName = etCreatorName.getText().toString().trim();
            String category = etCategory.getText().toString().trim();
            String targetAudience = etTargetAudience.getText().toString().trim();
            boolean isMonetized = cbMonetized.isChecked();
            boolean isPublic = cbPublic.isChecked();
            float rating = rbRating.getRating();

            if (creatorName.isEmpty() || category.isEmpty() || targetAudience.isEmpty()) {
                Toast.makeText(ActivityNoua.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            TikTok newTikTok = new TikTok(creatorName, category, isMonetized, targetAudience, rating, isPublic);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("NEW_TIKTOK", newTikTok);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}