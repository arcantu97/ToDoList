package com.example.superlista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {


    Context context;
    ArrayList<Producto> productos;

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ProductoAdapter(Context context){
        this.context = context;
        productos = new ArrayList<>();
    }

    @Override
    public int getCount() { return productos.size(); }

    @Override
    public Object getItem(int i) {
        return productos.get(i);
    }

    @Override
    public long getItemId(int i){ return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if ( view == null){
            view = LayoutInflater.from(context).inflate(R.layout.producto_lyt, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);
        Producto producto = (Producto) getItem(i);
        viewHolder.bind(producto);
        return view;
    }

    private class ViewHolder {
        private TextView txtName;
         private ImageView imgPhoto;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.titulo_producto);
            imgPhoto = view.findViewById(R.id.img);
        }

        void bind(Producto producto) {
            txtName.setText(producto.getNombre());
            imgPhoto.setImageResource(producto.getImagen());
        }
    }




}
