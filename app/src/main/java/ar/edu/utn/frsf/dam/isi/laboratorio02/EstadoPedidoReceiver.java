package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;


public class EstadoPedidoReceiver extends BroadcastReceiver {


    public static final String ESTADO_ACEPTADO = "ar.edu.utn.frsf.dam.isi.laboratorio02.ESTADO_ACEPTADO";
    public static final String ESTADO_CANCELADO = "ar.edu.utn.frsf.dam.isi.laboratorio02.ESTADO_CANCELADO";
    public static final String ESTADO_EN_PREPARACION = "ar.edu.utn.frsf.dam.isi.laboratorio02.ESTADO_EN_PREPARACION";
    public static final String ESTADO_LISTO = "ar.edu.utn.frsf.dam.isi.laboratorio02.ESTADO_LISTO";

    PedidoRepository repositoryPedido = new PedidoRepository();


    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ESTADO_LISTO)) {
            Bundle b = intent.getExtras();
            Integer idPedido = (Integer) b.get("idPedido");
            Pedido p = repositoryPedido.buscarPorId(idPedido);


            Intent destino = new Intent(context, AltaPedidos.class);
            destino.putExtra("idPedidoREQ", p.getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, p.getId(), destino, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new NotificationCompat.Builder(context, "CANAL01")
                    .setSmallIcon(R.drawable.envio)
                    .setContentTitle("Tu pedido está listo")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine("ID: " + p.getId())
                            .addLine("Mail:" + p.getMailContacto())
                            .addLine("Previsto para:" + p.getFecha().toString()))
                    .build();
            NotificationManagerCompat manager = NotificationManagerCompat.from(context);

            manager.notify(p.getId(), notification);
        }

        if (intent.getAction().equals(ESTADO_EN_PREPARACION)) {
            Bundle b = intent.getExtras();
            Integer idPedido = (Integer) b.get("idPedido");
            Pedido p = repositoryPedido.buscarPorId(idPedido);


            Intent destino = new Intent(context, AltaPedidos.class);
            destino.putExtra("idPedidoREQ", p.getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, p.getId(), destino, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new NotificationCompat.Builder(context, "CANAL01")
                    .setSmallIcon(R.drawable.envio)
                    .setContentTitle("Tu pedido está siendo preparado")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine("ID: " + p.getId())
                            .addLine("Mail:" + p.getMailContacto())
                            .addLine("Previsto para:" + p.getFecha().toString()))
                    .build();
            NotificationManagerCompat manager = NotificationManagerCompat.from(context);

            manager.notify(p.getId(), notification);
        }


        if (intent.getAction().equals(ESTADO_ACEPTADO)) {
            Bundle b = intent.getExtras();
            Integer idPedido = (Integer) b.get("idPedido");
            Pedido p = repositoryPedido.buscarPorId(idPedido);

            double total = 0;
            for (int i = 0; i < p.getDetalle().size(); i++) {
                int amount = p.getDetalle().get(i).getCantidad();
                double price = p.getDetalle().get(i).getProducto().getPrecio();
                total = total + amount * price;

            }


            Intent destino = new Intent(context, AltaPedidos.class);
            destino.putExtra("idPedidoREQ", p.getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setAction(Long.toString(System.currentTimeMillis()));
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context, p.getId(), destino, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new NotificationCompat.Builder(context, "CANAL01")
                    .setSmallIcon(R.drawable.envio)
                    .setContentTitle("Tu pedido fue aceptado")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine("ID: " + p.getId())
                            .addLine("El costo será de $" + total)
                            .addLine("Previsto para:" + p.getFecha().toString()))
                    .build();
            NotificationManagerCompat manager = NotificationManagerCompat.from(context);
            manager.notify(p.getId(), notification);


        }


    }


}
