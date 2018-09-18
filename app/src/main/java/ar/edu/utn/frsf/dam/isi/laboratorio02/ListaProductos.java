package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.ProductoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Producto;

public class ListaProductos extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<Categoria> adapterCategoria;
    private ProductoRepository product = new ProductoRepository();
    private ArrayAdapter<Producto> adapterProductos;
    private TextView tvProducto;
    private ListView listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_lista_productos);
        spinner =  findViewById(R.id.spinnerCategoria);
        adapterCategoria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, product.getCategorias());
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCategoria);
        listaProductos = findViewById(R.id.listaProductos);
        tvProducto = findViewById(R.id.productos);
        adapterProductos = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,product.getLista());
        //adapterProductos = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,product.buscarPorCategoria(product.getCategorias().get(1)));
        listaProductos.setAdapter(adapterProductos);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Categoria cat = (Categoria) parent.getItemAtPosition(position);
                adapterProductos = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, product.buscarPorCategoria(cat));
                listaProductos.setAdapter(adapterProductos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto producto = (Producto) adapterView.getItemAtPosition(i);
            }
        });

    }



}