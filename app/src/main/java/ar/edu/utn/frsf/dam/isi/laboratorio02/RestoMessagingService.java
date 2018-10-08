package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;

public class RestoMessagingService extends FirebaseMessagingService {

    PedidoRepository pr = new PedidoRepository();

    public RestoMessagingService() {
    }


    public void onMessageReceived(RemoteMessage remoteMessage) {


        if (remoteMessage.getData().size() > 0) {

            Integer id = Integer.parseInt(remoteMessage.getData().get("ID_PEDIDO"));
            String status = remoteMessage.getData().get("NUEVO_ESTADO"); //deberiamos revisar si esta preparado?
            Pedido p = pr.buscarPorId(id);
            p.setEstado(Pedido.Estado.LISTO);
            Intent intentListo = new Intent(this, EstadoPedidoReceiver.class);
            intentListo.putExtra("idPedido", p.getId());
            intentListo.setAction(EstadoPedidoReceiver.ESTADO_LISTO);
            sendBroadcast(intentListo);
        } else {
            onMessageReceived(remoteMessage);
            System.out.println("nada");


        }


    }


}
