package com.example.sem4_tema;

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

public class ActivityNoua extends AppCompatActivity {

    private EditText etCreator;
    private Spinner spCategory;
    private CheckBox cbMonetized;
    private RadioGroup rgAudience;
    private RatingBar rbRating;
    private SwitchCompat swPublic;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noua);

        etCreator = findViewById(R.id.etCreator);
        spCategory = findViewById(R.id.spCategory);
        cbMonetized = findViewById(R.id.cbMonetized);
        rgAudience = findViewById(R.id.rgAudience);
        rbRating = findViewById(R.id.rbRating);
        swPublic = findViewById(R.id.swPublic);
        btnSave = findViewById(R.id.btnSaveTikTok);

        String[] categorii = {"Gaming", "Comedy", "Education", "Vlog", "Tech"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categorii);
        spCategory.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String creator = etCreator.getText().toString().trim();
            if (creator.isEmpty()) {
                creator = "Anonim";
            }
            String category = spCategory.getSelectedItem().toString();
            boolean isMonetized = cbMonetized.isChecked();

            int selectedRadioId = rgAudience.getCheckedRadioButtonId();
            RadioButton selectedRb = findViewById(selectedRadioId);
            String audience = selectedRb != null ? selectedRb.getText().toString() : "General";

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