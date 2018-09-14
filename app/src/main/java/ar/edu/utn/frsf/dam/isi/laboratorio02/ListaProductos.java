package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.ProductoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Producto;

public class ListaProductos extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<Categoria> adapterCategoria;
    private ProductoRepository product;
    private ArrayAdapter<Producto> peliculaAdapter;
    private TextView tvPelicula;
    private ListView listaPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        spinner = (Spinner) findViewById(R.id.spinnerCategoria);
        adapterCategoria = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, product.getCategorias());
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCategoria);

        listaPeliculas = (ListView) findViewById(R.id.listaProductos);
        tvPelicula =(TextView) findViewById(R.id.productos);
        peliculaAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,product.getLista());
        listaPeliculas.setAdapter(peliculaAdapter);
    }
}
