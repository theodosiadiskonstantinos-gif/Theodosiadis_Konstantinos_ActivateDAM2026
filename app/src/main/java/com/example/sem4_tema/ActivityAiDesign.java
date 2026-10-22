package com.example.sem4_tema;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.materialswitch.MaterialSwitch;

public class ActivityAiDesign extends AppCompatActivity {

    private EditText etCreator;
    private Spinner spCategory;
    private CheckBox cbMonetized;
    private RadioGroup rgAudience;
    private RatingBar rbRating;
    private MaterialSwitch swPublic;
    private Button btnSave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_design);

        etCreator = findViewById(R.id.etAiCreator);
        spCategory = findViewById(R.id.spAiCategory);
        cbMonetized = findViewById(R.id.cbAiMonetized);
        rgAudience = findViewById(R.id.rgAiAudience);
        rbRating = findViewById(R.id.rbAiRating);
        swPublic = findViewById(R.id.swAiPublic);
        btnSave = findViewById(R.id.btnAiSaveTikTok);

        String[] categories = {"Tech & Coding", "Comedy", "Gaming", "Vlog", "Education"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spCategory.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String creator = etCreator.getText().toString().trim();
            if (creator.isEmpty()) {
                creator = "AI Creator";
            }
            String category = spCategory.getSelectedItem().toString();
            boolean isMonetized = cbMonetized.isChecked();

            int selectedRadioId = rgAudience.getCheckedRadioButtonId();
            RadioButton selectedRb = findViewById(selectedRadioId);
            String audience = selectedRb != null ? selectedRb.getText().toString() : "All Ages";

            float rating = rbRating.getRating();
            boolean isPublic = swPublic.isChecked();

            TikTok tikTokItem = new TikTok(creator, category, isMonetized, audience, rating, isPublic);

            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("TIKTOK_OBJECT", tikTokItem);
            resultIntent.putExtras(bundle);

            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });
    }
}