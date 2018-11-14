package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.BaseDatos;
import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;

public class PrepararPedidoService extends IntentService {

    //private PedidoRepository pr = new PedidoRepository();
    private BaseDatos bd;


    public PrepararPedidoService() {
        super("PrepararPedidoService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        bd = new BaseDatos(getApplicationContext());
        try {
            Thread.sleep(2000);
            List<Pedido> lista = bd.getPedidoDAO().getAll();
            for(Pedido p:lista) {
                if (p.getEstado().equals(Pedido.Estado.ACEPTADO)) {
                    p.setEstado(Pedido.Estado.EN_PREPARACION);
                    Intent intentPreparacion = new Intent(this, EstadoPedidoReceiver.class);
                    intentPreparacion.putExtra("idPedido", p.getId());
                    intentPreparacion.setAction(EstadoPedidoReceiver.ESTADO_EN_PREPARACION);
                    sendBroadcast(intentPreparacion);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
