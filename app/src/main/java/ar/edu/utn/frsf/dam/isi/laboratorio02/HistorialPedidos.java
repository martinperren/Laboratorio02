package ar.edu.utn.frsf.dam.isi.laboratorio02;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;




import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;



public class HistorialPedidos extends AppCompatActivity {



    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private PedidoRepository repositorioPedido = new PedidoRepository();
    private Button btnNuevo;
    private Button btnMenu;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_pedidos);


        mRecyclerView = (RecyclerView) findViewById(R.id.listaPedidos);


        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PedidosAdapter(repositorioPedido.getLista());
        mRecyclerView.setAdapter(mAdapter);



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




    }



}