package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.BaseDatos;
import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.ProductoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.ProductoRetrofit;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Producto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GestionProductoActivity extends AppCompatActivity {
    private Button btnMenu;
    private Button btnGuardar;
    private Button btnVolver;
    private Spinner comboCategorias;
    private EditText nombreProducto;
    private EditText descProducto;
    private EditText precioProducto;
    private ToggleButton opcionNuevoBusqueda;
    private EditText idProductoBuscar;
    private Button btnBuscar;
    private Button btnBorrar;
    private Boolean flagActualizacion;
    private ArrayAdapter<Categoria> adapterCategoria;
    private Categoria c;
    private int idProductoAct;
    private BaseDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = new BaseDatos(getApplicationContext());
        setContentView(R.layout.activity_gestion_producto);

        opcionNuevoBusqueda = (ToggleButton)
                findViewById(R.id.abmProductoAltaNuevo);
        idProductoBuscar = (EditText)
                findViewById(R.id.abmProductoIdBuscar);
        nombreProducto = (EditText)
                findViewById(R.id.abmProductoNombre);
        descProducto = (EditText)
                findViewById(R.id.abmProductoDescripcion);
        precioProducto = (EditText)
                findViewById(R.id.abmProductoPrecio);
        comboCategorias = (Spinner)
                findViewById(R.id.abmProductoCategoria);
        btnMenu = (Button) findViewById(R.id.btnAbmProductoVolver);
        btnGuardar = (Button)
                findViewById(R.id.btnAbmProductoCrear);
        btnBuscar = (Button)
                findViewById(R.id.btnAbmProductoBuscar);
        btnBorrar = (Button)
                findViewById(R.id.btnAbmProductoBorrar);
        btnVolver = findViewById(R.id.btnAbmProductoVolver);

        adapterCategoria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bd.getCategoria());
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboCategorias.setAdapter(adapterCategoria);
        flagActualizacion = false;
        opcionNuevoBusqueda.setChecked(false);
        btnBuscar.setEnabled(false);
        btnBorrar.setEnabled(false);
        idProductoBuscar.setEnabled(false);

/*
        Runnable r = new Runnable() {
            @Override
            public void run() {
                CategoriaRest catRest = new CategoriaRest();
                final Categoria[] cats = catRest.listarTodas().toArray(new Categoria[0]);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterCategoria = new ArrayAdapter<>(GestionProductoActivity.this, android.R.layout.simple_spinner_dropdown_item, cats);

                        comboCategorias.setAdapter(adapterCategoria);


                    }
                });
            }
        };

        Thread hiloCargarCombo = new Thread(r);
        hiloCargarCombo.start();
*/

        opcionNuevoBusqueda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                flagActualizacion = isChecked;
                btnBuscar.setEnabled(isChecked);
                btnBorrar.setEnabled(isChecked);
                idProductoBuscar.setEnabled(isChecked);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GestionProductoActivity.this, MainActivity.class);
                startActivity(i);

            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Producto p = new Producto();
                if(opcionNuevoBusqueda.isChecked()){
                    flagActualizacion = true;
                    p=bd.getProductoById(idProductoBuscar.getText().toString()).get(0);
                    p.setNombre(nombreProducto.getText().toString());

                    p.setCategoria(c);


                    p.setDescripcion(descProducto.getText().toString());

                    p.setPrecio(Double.parseDouble(precioProducto.getText().toString()));


                    bd.updateProducto(p);

                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Actualizado correctamente", Toast.LENGTH_SHORT);
                    toast1.show();

                    /*ProductoRetrofit clienteRest =
                            RestClient.getInstance()
                                    .getRetrofit()
                                    .create(ProductoRetrofit.class);
                    Call<Producto> altaCall = clienteRest.actualizarProducto(idProductoAct,p);
                    altaCall.enqueue(new Callback<Producto>() {
                        @Override
                        public void onResponse(Call<Producto> call,
                                               Response<Producto> resp) {
                            switch (resp.code()) {
                                case 200:
                                    Toast toast1 =
                                                Toast.makeText(getApplicationContext(),
                                                        "Actualizado correctamente", Toast.LENGTH_SHORT);
                                        toast1.show();
                                    break;
                                case 401:
                                case 403:
                                case 500:
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void onFailure(Call<Producto> call, Throwable t) {
                        }
                    });*/
                }else{
                    flagActualizacion = false;

                    p.setNombre(nombreProducto.getText().toString());

                    p.setCategoria(c);


                    p.setDescripcion(descProducto.getText().toString());

                    p.setPrecio(Double.parseDouble(precioProducto.getText().toString()));

                    bd.insertProducto(p);


                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Creado correctamente", Toast.LENGTH_SHORT);
                    toast1.show();
                    /*
                    ProductoRetrofit clienteRest = RestClient.getInstance().getRetrofit().create(ProductoRetrofit.class);
                    Call<Producto> altaCall = clienteRest.crearProducto(p);
                    altaCall.enqueue(new Callback<Producto>() {
                    @Override
                    public void onResponse(Call<Producto> call,
                                           Response<Producto> resp) {
                        switch (resp.code()) {
                            case 201:

                                    Toast toast1 =
                                            Toast.makeText(getApplicationContext(),
                                                    "Creado correctamente", Toast.LENGTH_SHORT);
                                    toast1.show();
                                break;
                            case 401:
                            case 403:
                            case 500:
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Producto> call, Throwable t) {
                    }
                }


                );
                */


                }
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Producto p=bd.getProductoById(idProductoBuscar.getText().toString()).get(0);
                nombreProducto.setText(p.getNombre());

                comboCategorias.setSelection(adapterCategoria.getPosition(p.getCategoria()));

                descProducto.setText(p.getDescripcion());

                precioProducto.setText(p.getPrecio().toString());

                idProductoAct = p.getId();

               /* ProductoRetrofit clienteRest =
                        RestClient.getInstance()
                                .getRetrofit()
                                .create(ProductoRetrofit.class);
                Call<Producto> altaCall = clienteRest.buscarProductoPorId(Integer.parseInt(idProductoBuscar.getText().toString()));

                altaCall.enqueue(new Callback<Producto>() {
                    @Override
                    public void onResponse(Call<Producto> call,
                                           Response<Producto> resp) {

                        switch (resp.code()) {
                            case 200:
                                Producto p = new Producto();
                                p=(Producto) resp.body();
                                nombreProducto.setText(p.getNombre());

                                comboCategorias.setSelection(adapterCategoria.getPosition(p.getCategoria()));

                                descProducto.setText(p.getDescripcion());

                                precioProducto.setText(p.getPrecio().toString());

                                idProductoAct = p.getId();
                                break;
                            case 401:
                            case 403:
                            case 500:
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<Producto> call, Throwable t) {
                    }
                });*/
            }
        });

        comboCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                c = ((Categoria) parent.getItemAtPosition(position));
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
}
