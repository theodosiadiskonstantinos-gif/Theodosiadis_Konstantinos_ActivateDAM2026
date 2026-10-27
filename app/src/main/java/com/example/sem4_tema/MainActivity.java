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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<TikTok> tiktokList = new ArrayList<>();
    private TextView tvDisplayResult;
    private ActivityResultLauncher<Intent> addActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    TikTok receivedTikTok = result.getData().getParcelableExtra("NEW_TIKTOK");

                    if (receivedTikTok != null) {
                        tiktokList.add(receivedTikTok);
                        Toast.makeText(this, "TikTok added! Total in list: " + tiktokList.size(), Toast.LENGTH_SHORT).show();
                        tvDisplayResult.setText(receivedTikTok.toString());
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAddTikTok = findViewById(R.id.btnAddTikTok);
        tvDisplayResult = findViewById(R.id.tvDisplayResult);

        btnAddTikTok.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityNoua.class);
            addActivityLauncher.launch(intent);
        });
        Button btnViewList = findViewById(R.id.btnViewList);

        btnViewList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ActivityLista.class);
            intent.putParcelableArrayListExtra("object_list", tiktokList);
            startActivity(intent);
        });
    }
}