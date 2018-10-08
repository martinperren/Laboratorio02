package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.ProductoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoDetalle;

public class AltaPedidos extends AppCompatActivity {

    private EditText edtMail;
    private EditText edtDirEnvio;
    private EditText edtHora;
    private RadioButton optLocal;
    private RadioButton optDomicilio;
    private ListView listaProductos;
    private TextView tvTotal;
    private ArrayAdapter<PedidoDetalle> adapterPedidos;
    private ProductoRepository product = new ProductoRepository();
    private PedidoRepository repositorioPedido = new PedidoRepository();
    private Button btnAgregarProducto;
    private Button btnQuitarProducto;
    private Button btnHacerPedido;
    private Pedido pedido;
    private Button btnVolver;
    private static final int CODIGO_ACTIVIDAD1 = 1;
    private List<PedidoDetalle> listaPedido = new ArrayList<>();
    private PedidoDetalle pedidoDetalle;
    private RadioGroup optGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pedidos);
        edtMail = findViewById(R.id.edtMail);
        edtDirEnvio = findViewById(R.id.edtDirEnvio);
        edtDirEnvio.setEnabled(false);
        edtHora = findViewById(R.id.edtHora);
        tvTotal = findViewById(R.id.tvTotal);
        optGroup = (RadioGroup) findViewById(R.id.optEntrega);
        optLocal = (RadioButton) findViewById(R.id.optLocal);
        optDomicilio = (RadioButton) findViewById(R.id.optDomicilio);
        listaProductos = findViewById(R.id.listaProductos);
        adapterPedidos = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice);
        listaProductos.setAdapter(adapterPedidos);
        btnHacerPedido = findViewById(R.id.btnHacerPedido);
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto);
        btnQuitarProducto = findViewById(R.id.btnQuitarProducto);
        btnVolver = findViewById(R.id.btnVolver);

        optGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int id) {
                        if (optLocal.isChecked()) {
                            edtDirEnvio.setEnabled(false);
                            edtDirEnvio.setText("");
                        } else {
                            edtDirEnvio.setEnabled(true);
                        }

                    }
                });




        Intent i = getIntent();
        Bundle b = i.getExtras();
        int id = 1;

        if (b != null) {
            id = (Integer) b.get("idPedidoREQ");

            for (int j = 0; j < repositorioPedido.getLista().size(); j++) {
                if (repositorioPedido.getLista().get(j).getId().equals(id)) {
                    pedido = repositorioPedido.getLista().get(j);
            }
            }
            if(id>0) {
                edtMail.setText(pedido.getMailContacto());
                edtDirEnvio.setText(pedido.getDireccionEnvio());

                optDomicilio.setChecked(!pedido.getRetirar());
                optLocal.setChecked(pedido.getRetirar());

            }


            //


            for (int j = 0; j < pedido.getDetalle().size(); j++) {
                listaPedido.add(pedido.getDetalle().get(j));
                double totalAnterior = Double.parseDouble((tvTotal.getText().subSequence(19, tvTotal.getText().length())).toString());
                double totalActual = pedido.getDetalle().get(j).getCantidad() * pedido.getDetalle().get(j).getProducto().getPrecio();
                double total = totalAnterior + totalActual;
                tvTotal.setText(tvTotal.getText().subSequence(0, 19) + Double.toString(total));
            }


            adapterPedidos = new ArrayAdapter<>(AltaPedidos.this, android.R.layout.simple_list_item_single_choice, listaPedido);
            listaProductos.setAdapter(adapterPedidos);


            btnVolver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AltaPedidos.this, HistorialPedidos.class);
                    startActivity(i);

                }
            });

        }else{
            pedido = new Pedido();
        }


        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AltaPedidos.this, ListaProductos.class);
                i.putExtra("requestCode", "1");
                startActivityForResult(i, CODIGO_ACTIVIDAD1);
            }
        });

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pedidoDetalle = (PedidoDetalle) adapterView.getItemAtPosition(i);
            }
        });


        btnQuitarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedido.quitarDetalle(pedidoDetalle);
                listaPedido.remove(pedidoDetalle);
                adapterPedidos = new ArrayAdapter<>(AltaPedidos.this, android.R.layout.simple_list_item_single_choice, listaPedido);
                listaProductos.setAdapter(adapterPedidos);
                double totalAnterior = Double.parseDouble((tvTotal.getText().subSequence(19, tvTotal.getText().length())).toString());
                double totalActual = pedidoDetalle.getCantidad() * pedidoDetalle.getProducto().getPrecio();
                if(totalAnterior>0){
                double total = totalAnterior - totalActual;
                tvTotal.setText(tvTotal.getText().subSequence(0, 19) + Double.toString(total));}
            }
        });


       btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AltaPedidos.this, MainActivity.class);
                startActivity(i);

            }
        });


        btnHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.currentThread().sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // buscar pedidos no aceptados y aceptarlos automáticamente
                        List<Pedido> lista = repositorioPedido.getLista();
                        for(Pedido p:lista){
                            if(p.getEstado().equals(Pedido.Estado.REALIZADO))
                                p.setEstado(Pedido.Estado.ACEPTADO);
                            Intent intentAceptado = new Intent(AltaPedidos.this,EstadoPedidoReceiver.class);
                            intentAceptado.putExtra("idPedido",p.getId());
                            intentAceptado.setAction(EstadoPedidoReceiver.ESTADO_ACEPTADO);
                            sendBroadcast(intentAceptado);




                        }





                    }
                };
                Thread unHilo = new Thread(r);
                unHilo.start();



                String[] horaIngresada = edtHora.getText().toString().split(":");
                GregorianCalendar hora = new GregorianCalendar();
                int valorHora = Integer.valueOf(horaIngresada[0]);
                int valorMinuto = Integer.valueOf(horaIngresada[1]);
                if (valorHora < 0 || valorHora > 23) {
                    Toast.makeText(AltaPedidos.this,
                            "La hora ingresada (" + valorHora + ") es incorrecta",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (valorMinuto < 0 || valorMinuto > 59) {
                    Toast.makeText(AltaPedidos.this,
                            "Los minutos (" + valorMinuto + ") son incorrectos",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                hora.set(Calendar.HOUR_OF_DAY, valorHora);
                hora.set(Calendar.MINUTE, valorMinuto);
                hora.set(Calendar.SECOND, Integer.valueOf(0));

                pedido.setFecha(hora.getTime());
                pedido.setRetirar(optLocal.isChecked());
                pedido.setMailContacto(edtMail.getText().toString());
                pedido.setDireccionEnvio(edtDirEnvio.getText().toString());
                pedido.setEstado(Pedido.Estado.REALIZADO);
                Log.d("APP_LAB02", "Pedido " + pedido.toString());


                repositorioPedido.guardarPedido(pedido);

                Intent i = new Intent(AltaPedidos.this, HistorialPedidos.class);
                startActivity(i);

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            String cantidadst = data.getStringExtra("cantidad");
            String idst = data.getStringExtra("producto");
            Integer id = Integer.parseInt(idst);
            Integer cantidad = Integer.parseInt(cantidadst);


            PedidoDetalle pedidod = new PedidoDetalle(cantidad, product.buscarPorId(id));
            pedidod.setPedido(pedido);


            listaPedido.add(pedidod);
            adapterPedidos = new ArrayAdapter<>(AltaPedidos.this, android.R.layout.simple_list_item_single_choice, listaPedido);
            listaProductos.setAdapter(adapterPedidos);
            double totalAnterior = Double.parseDouble((tvTotal.getText().subSequence(19, tvTotal.getText().length())).toString());
            double totalActual = cantidad * pedidod.getProducto().getPrecio();
            double total = totalAnterior + totalActual;
            tvTotal.setText(tvTotal.getText().subSequence(0, 19) + Double.toString(total));

        }
    }
}


