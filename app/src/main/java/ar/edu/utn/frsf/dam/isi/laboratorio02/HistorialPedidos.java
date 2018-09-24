package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoHolder;


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


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub


                Intent i = new Intent(HistorialPedidos.this,AltaPedidos.class);
                i.putExtra("idPedidoREQ",repositorioPedido.getLista().get(pos).getId());
                startActivity(i);



                Log.v("long clicked","pos: " + pos);

                return true;
            }
        });





    }
}