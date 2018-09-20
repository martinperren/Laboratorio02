package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;


public class HistorialPedidos extends AppCompatActivity {


    ListView listView;
    private PedidosAdapter adapterProductos;
    private PedidoRepository repositorioPedido = new PedidoRepository();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_pedidos);


        listView=(ListView)findViewById(R.id.listaPedidos);
        adapterProductos = new PedidosAdapter(this,repositorioPedido.getLista());
          listView.setAdapter(adapterProductos);

    }
}