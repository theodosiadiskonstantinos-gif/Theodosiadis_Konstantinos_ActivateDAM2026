package com.example.sem4_tema;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplayResult;

    private final ActivityResultLauncher<Intent> addLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Bundle bundle = result.getData().getExtras();
                    if (bundle != null) {
                        ActivityNoua tikTok = bundle.getParcelable("TIKTOK_OBJECT");
                        if (tikTok != null) {
                            // Display via Toast
                            Toast.makeText(this, "Obiect adaugat cu succes!", Toast.LENGTH_SHORT).show();

                            // Display via TextView
                            tvDisplayResult.setText(tikTok.toString());
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = findViewById(R.id.btnAddTikTok);
        tvDisplayResult = findViewById(R.id.tvDisplayResult);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityNoua.class);
            addLauncher.launch(intent);
        });
    }
}