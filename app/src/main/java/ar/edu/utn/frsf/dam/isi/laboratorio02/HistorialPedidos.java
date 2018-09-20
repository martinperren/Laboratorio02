package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;


public class HistorialPedidos extends AppCompatActivity {


    ListView listView;
    private PedidosAdapter adapterProductos;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_pedidos);
        listView=(ListView)findViewById(R.id.listaPedidos);

        //adapterProductos = new PedidosAdapter(this,pedidos.getLista());
        ArrayList<Pedido> dataModels = new ArrayList<>();




        dataModels.add(new Pedido("Apple Pie", "Android 1.0",false));
        dataModels.add(new Pedido("Banana Bread", "Android 1.1",true));
        dataModels.add(new Pedido("Cupcake", "Android 1.5",false));



        adapterProductos = new PedidosAdapter(this,dataModels);

        listView.setAdapter(adapterProductos);

    }
}