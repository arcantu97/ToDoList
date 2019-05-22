package com.example.superlista;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    ListView listView;
    List<String> productos;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.fab);
        final ArrayList<String> recibidos = (ArrayList<String>) getIntent().getSerializableExtra("lista");
        if (recibidos.isEmpty()){
            Toast.makeText(ListaActivity.this, "No se agrego ningún elemento!", Toast.LENGTH_SHORT).show();
        }
        productos = new ArrayList<>();
        for (String i: recibidos){
            productos.add(i);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, productos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              listView.getCheckedItemPositions();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recibidos.isEmpty()){}
                else{
                    productos.clear();
                    Toast.makeText(ListaActivity.this, "Lista de productos limpiada", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ListaActivity.this, "Volviendo a selección...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ListaActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

    }
}
