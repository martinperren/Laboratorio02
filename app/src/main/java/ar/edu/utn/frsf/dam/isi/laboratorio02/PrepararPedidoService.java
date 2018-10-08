package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;

public class PrepararPedidoService extends IntentService {

    private PedidoRepository pr = new PedidoRepository();

    public PrepararPedidoService() {
        super("PrepararPedidoService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            Thread.sleep(6000);
            List<Pedido> lista = pr.getLista();
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
