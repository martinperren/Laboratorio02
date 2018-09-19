package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.ProductoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Producto;

public class AltaPedidos extends AppCompatActivity {

    private EditText edtMail;
    private EditText edtDirEnvio;
    private EditText edtHora;
    private RadioButton optLocal;
    private RadioButton optDomicilio;
    private ListView listaProductos;
    private ArrayAdapter<Producto> adapterProductos;
    private ProductoRepository product = new ProductoRepository();
    private Button btnAgregarProducto;
    private Button btnQuitarProducto;
    private Button btnHacerPedido;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pedidos);

        edtMail =  findViewById(R.id.edtMail);
        edtDirEnvio =  findViewById(R.id.edtDirEnvio);
        edtHora =  findViewById(R.id.edtHora);
        optLocal= (RadioButton) findViewById(R.id.optLocal);
        optDomicilio= (RadioButton) findViewById(R.id.optDomicilio);
        listaProductos = findViewById(R.id.listaProductos);
        adapterProductos = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice,product.getLista());
        listaProductos.setAdapter(adapterProductos);

        btnAgregarProducto =  findViewById(R.id.btnAgregarProducto);
        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AltaPedidos.this,ListaProductos.class);
                startActivity(i);

            }
        });

        btnVolver =  findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AltaPedidos.this,MainActivity.class);
                startActivity(i);
            }
        });


    }

    }

