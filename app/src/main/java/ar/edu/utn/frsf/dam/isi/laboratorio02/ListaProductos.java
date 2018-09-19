package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;


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
    private EditText edtCantidad;
    private Button aceptar;
    private Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_lista_productos);
        spinner =  findViewById(R.id.spinnerCategoria);
        edtCantidad =  findViewById(R.id.edtCantidad);
        aceptar =  findViewById(R.id.btnAceptar);
        adapterCategoria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, product.getCategorias());
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCategoria);
        listaProductos = findViewById(R.id.listaProductos);
        tvProducto = findViewById(R.id.productos);
        adapterProductos = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,product.getLista());
        listaProductos.setAdapter(adapterProductos);
        this.aceptar.setOnClickListener(listenerBtnAceptar);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Categoria cat = (Categoria) adapterView.getItemAtPosition(i);
                adapterProductos = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice, product.buscarPorCategoria(cat));
                listaProductos.setAdapter(adapterProductos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 producto = (Producto) adapterView.getItemAtPosition(i);
            }
        });
    }

    private View.OnClickListener listenerBtnAceptar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listaProductos.getSelectedItem();
            Intent intentResultado = new Intent();
            intentResultado.putExtra("cantidad",Integer.valueOf(edtCantidad.getText().toString()));
            Log.d("TEST","Eleccion: "+producto);
            intentResultado.putExtra("producto",producto.getId());
            setResult(Activity.RESULT_OK,intentResultado);
            finish();
        }
    };
}