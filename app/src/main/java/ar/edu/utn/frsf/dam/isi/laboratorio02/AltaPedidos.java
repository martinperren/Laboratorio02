package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
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
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Producto;

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
    private Pedido elPedido = new Pedido();
    private Button btnVolver;
    private static final int CODIGO_ACTIVIDAD1 = 1;
    private List<PedidoDetalle> listaPedido= new ArrayList<>();
    private PedidoDetalle pedido;
    private RadioGroup optGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pedidos);
        edtMail =  findViewById(R.id.edtMail);
        edtDirEnvio =  findViewById(R.id.edtDirEnvio);
        edtDirEnvio.setEnabled(false);
        edtHora =  findViewById(R.id.edtHora);
        tvTotal = findViewById(R.id.tvTotal);
        optGroup = (RadioGroup) findViewById(R.id.optEntrega);
        optLocal= (RadioButton) findViewById(R.id.optLocal);
        optDomicilio= (RadioButton) findViewById(R.id.optDomicilio);
        listaProductos = findViewById(R.id.listaProductos);
        adapterPedidos = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice);
        listaProductos.setAdapter(adapterPedidos);
        btnHacerPedido =  findViewById(R.id.btnHacerPedido);
        btnAgregarProducto =  findViewById(R.id.btnAgregarProducto);
        Integer j;

        optGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int id) {
                        if(optDomicilio.isChecked()){
                            edtDirEnvio.setEnabled(true);
                        }else{
                            edtDirEnvio.setEnabled(false);
                        }

                    }});





        Intent i= getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            int id = (Integer) b.get("idPedidoREQ");

            for(j=0;j<repositorioPedido.getLista().size();j++){
            if(repositorioPedido.getLista().get(j).getId().equals(id)) {
                elPedido = repositorioPedido.getLista().get(j);
            }
            }
            edtMail.setText(elPedido.getMailContacto());
            edtDirEnvio.setText(elPedido.getDireccionEnvio());
            //
            listaPedido.add(elPedido.getDetalle().get(j));
            adapterPedidos = new ArrayAdapter<>(AltaPedidos.this, android.R.layout.simple_list_item_single_choice, listaPedido);
            listaProductos.setAdapter(adapterPedidos);


        }







        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AltaPedidos.this,ListaProductos.class);
                i.putExtra("requestCode", "1");
                startActivityForResult(i, CODIGO_ACTIVIDAD1);
            }
        });

        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pedido = (PedidoDetalle) adapterView.getItemAtPosition(i);
            }
        });

        btnQuitarProducto =  findViewById(R.id.btnQuitarProducto);
        btnQuitarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaPedido.remove(pedido);
                adapterPedidos = new ArrayAdapter<>(AltaPedidos.this, android.R.layout.simple_list_item_single_choice, listaPedido);
                listaProductos.setAdapter(adapterPedidos);
                double totalAnterior= Double.parseDouble((tvTotal.getText().subSequence(19,tvTotal.getText().length())).toString());
                double totalActual=pedido.getCantidad()*pedido.getProducto().getPrecio();
                double total= totalAnterior-totalActual;
                tvTotal.setText(tvTotal.getText().subSequence(0,19)+Double.toString(total));
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


        btnHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] horaIngresada = edtHora.getText().toString().split(":");
                GregorianCalendar hora = new GregorianCalendar();
                int valorHora = Integer.valueOf(horaIngresada[0]);
                int valorMinuto = Integer.valueOf(horaIngresada[1]);
                if(valorHora<0 || valorHora>23){
                    Toast.makeText(AltaPedidos.this,
                            "La hora ingresada ("+valorHora+") es incorrecta",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(valorMinuto <0 || valorMinuto >59){
                    Toast.makeText(AltaPedidos.this,
                            "Los minutos ("+valorMinuto+") son incorrectos",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                hora.set(Calendar.HOUR_OF_DAY,valorHora);
                hora.set(Calendar.MINUTE,valorMinuto);
                hora.set(Calendar.SECOND,Integer.valueOf(0));
                elPedido.setFecha(hora.getTime());
                elPedido.setRetirar(optLocal.isChecked());
                elPedido.setMailContacto(edtMail.getText().toString());
                elPedido.setDireccionEnvio(edtDirEnvio.getText().toString());
                elPedido.setEstado(Pedido.Estado.REALIZADO);
                Log.d("APP_LAB02","Pedido "+elPedido.toString());



                repositorioPedido.guardarPedido(elPedido);
                elPedido=new Pedido();

                Intent i = new Intent(AltaPedidos.this,HistorialPedidos.class);
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

            //???
            PedidoDetalle pedidod = new PedidoDetalle(cantidad, product.buscarPorId(id));



            listaPedido.add(pedidod);
            adapterPedidos = new ArrayAdapter<>(AltaPedidos.this, android.R.layout.simple_list_item_single_choice, listaPedido);
            listaProductos.setAdapter(adapterPedidos);
            double totalAnterior= Double.parseDouble((tvTotal.getText().subSequence(19,tvTotal.getText().length())).toString());
            double totalActual=cantidad*pedidod.getProducto().getPrecio();
            double total= totalAnterior+totalActual;
            tvTotal.setText(tvTotal.getText().subSequence(0,19)+Double.toString(total));

        }
    }
}


