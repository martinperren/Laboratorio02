package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;


public class HistorialPedidos extends AppCompatActivity {


    ListView listView;
    private PedidosAdapter adapterProductos;
    private PedidoRepository repositorioPedido = new PedidoRepository();
    private Button btnNuevo;
    private Button btnMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_pedidos);


        listView = (ListView) findViewById(R.id.listaPedidos);
        adapterProductos = new PedidosAdapter(this, repositorioPedido.getLista());
        listView.setAdapter(adapterProductos);

        btnNuevo = findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HistorialPedidos.this, AltaPedidos.class);
                startActivity(i);
            }
        });

        btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HistorialPedidos.this, MainActivity.class);
                startActivity(i);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {


                Intent i = new Intent(HistorialPedidos.this, AltaPedidos.class);
                i.putExtra("idPedidoREQ", repositorioPedido.getLista().get(pos).getId());
                startActivity(i);

                return true;
            }
        });


    }


}