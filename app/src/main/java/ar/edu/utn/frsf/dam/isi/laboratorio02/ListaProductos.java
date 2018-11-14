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
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.BaseDatos;
import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.CategoriaDAO;
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
    private TextView tvCantidad;
    private Button aceptar;
    private Producto producto;
    private BaseDatos bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        bd = new BaseDatos(getApplicationContext());
        setContentView(R.layout.activity_lista_productos);
        spinner = findViewById(R.id.spinnerCategoria);
        edtCantidad = findViewById(R.id.edtCantidad);
        aceptar = findViewById(R.id.btnAceptar);
        tvCantidad = findViewById(R.id.tvCantidad);
        listaProductos = findViewById(R.id.listaProductos);
        tvProducto = findViewById(R.id.productos);
        adapterProductos = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, bd.getProducto());
        adapterCategoria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bd.getCategoria());
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCategoria);


        this.aceptar.setOnClickListener(listenerBtnAceptar);

        if (this.getIntent().getStringExtra("requestCode").equals("2")) {
            aceptar.setVisibility(View.INVISIBLE);
            tvCantidad.setVisibility(View.INVISIBLE);
            edtCantidad.setVisibility(View.INVISIBLE);
        }


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterProductos.clear();
                Categoria cat = (Categoria) adapterView.getItemAtPosition(i);
                List<Producto> lp = bd.getProducto();

                List<Producto> lista = new ArrayList<>();
                for (Producto producto : lp){
                    if(producto.getCategoria().getId() == cat.getId()){
                        lista.add(producto);
                    }
                }

                adapterProductos.addAll(lista);
                listaProductos.setAdapter(adapterProductos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                producto = (Producto) adapterView.getItemAtPosition(i);
            }
        });



      /*  Runnable r = new Runnable() {
            @Override
            public void run() {
                CategoriaRest catRest = new CategoriaRest();
                final Categoria[] cats = catRest.listarTodas().toArray(new Categoria[0]);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        adapterCategoria = new ArrayAdapter<Categoria>(ListaProductos.this, android.R.layout.simple_spinner_dropdown_item, cats);


                        spinner.setAdapter(adapterCategoria);
                        spinner.setSelection(0);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int
                                    position, long id) {

                                adapterProductos.clear();

                                adapterProductos.addAll(product.buscarPorCategoria((Categoria) parent.getItemAtPosition(position))
                                );

                                adapterProductos.notifyDataSetChanged();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        })
                        ;

                        listaProductos.setAdapter(adapterProductos);
                    }
                });
            }
        };
        Thread hiloCargarCombo = new Thread(r);
        hiloCargarCombo.start();
*/
    }

    private View.OnClickListener listenerBtnAceptar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intentResultado = new Intent();
            if (edtCantidad.getText().toString().equals("0")) {
                Toast.makeText(ListaProductos.this,
                        "La cantidad ingresada (" + edtCantidad.getText().toString() + ") es incorrecta",
                        Toast.LENGTH_LONG).show();
                return;
            }
            intentResultado.putExtra("cantidad", edtCantidad.getText().toString());
            Log.d("TEST", "Eleccion: " + producto);
            intentResultado.putExtra("producto", (String) producto.getId().toString());
            setResult(Activity.RESULT_OK, intentResultado);
            finish();
        }
    };


}