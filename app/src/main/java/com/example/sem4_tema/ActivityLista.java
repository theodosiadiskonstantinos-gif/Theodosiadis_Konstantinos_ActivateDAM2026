package com.example.sem4_tema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {

    private ArrayList<TikTok> receivedList;
    private ArrayAdapter<TikTok> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ListView lvTikToks = findViewById(R.id.lvTikToks);

        Intent intent = getIntent();
        receivedList = intent.getParcelableArrayListExtra("object_list");

        if (receivedList == null) {
            receivedList = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, receivedList);
        lvTikToks.setAdapter(adapter);

        lvTikToks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TikTok selectedItem = receivedList.get(position);
                Toast.makeText(ActivityLista.this, selectedItem.toString(), Toast.LENGTH_LONG).show();
            }
        });

        lvTikToks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                receivedList.remove(position);
                adapter.notifyDataSetChanged();

                Toast.makeText(ActivityLista.this, "Video deleted!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}