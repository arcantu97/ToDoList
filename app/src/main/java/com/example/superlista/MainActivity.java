package com.example.superlista;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] nombreProducto;
    private TypedArray dataFoto;
    private ProductoAdapter adapter;
    private ArrayList<Producto> productos;
    ArrayList<String> canasta = new ArrayList<>();
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ListView y ArrayList
        adapter = new ProductoAdapter(this);
        ListView listView = findViewById(R.id.listView);
        button = findViewById(R.id.fab);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                //Comprobando que el item no se encuentre en la lista
                if(!canasta.contains(productos.get(i).getNombre())){
                    canasta.add(productos.get(i).getNombre());
                    Toast.makeText(MainActivity.this,  "Se añadió " + productos.get(i).getNombre() + " a la lista!", Toast.LENGTH_SHORT).show();
                    System.out.println(canasta);
                }

                //Si el item ya existe, sólo se notifica
                else {
                    Toast.makeText(MainActivity.this, productos.get(i).getNombre() + " ya está en la lista!", Toast.LENGTH_SHORT).show();
                }
            }


        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                //Si se deja una pulsación larga, se remueve el item de la lista
                Toast.makeText(MainActivity.this, "Se removió " + productos.get(i).getNombre() + " de la lista!", Toast.LENGTH_SHORT).show();
                if (canasta.contains(productos.get(i).getNombre())) {
                    canasta.remove(productos.get(i).getNombre());
                    System.out.println(canasta);
                }
                return true;
            }
        });

        //Métodos para añadir los elementos a la vista
        recursos();
        añadeItem();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Abriendo lista final...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                intent.putStringArrayListExtra("lista", canasta);
                startActivity(intent);
            }
        });
    }


    private void recursos(){
        nombreProducto = getResources().getStringArray(R.array.nombre);
        dataFoto = getResources().obtainTypedArray(R.array.foto);
    }

    private void añadeItem() {
        productos = new ArrayList<>();
        for (int i = 0; i < nombreProducto.length; i++){
            Producto producto = new Producto();
            producto.setImagen(dataFoto.getResourceId(i, -1));
            producto.setNombre(nombreProducto[i]);
            productos.add(producto);
        }

        adapter.setProductos(productos);
    }


}
