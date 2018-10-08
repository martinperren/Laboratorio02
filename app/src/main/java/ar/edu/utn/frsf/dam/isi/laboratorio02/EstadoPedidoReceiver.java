package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

      
      if(intent.getAction().equals(ESTADO_ACEPTADO)) {
          Bundle b = intent.getExtras();
          Integer idPedido = (Integer) b.get("idPedido");
          Pedido p = repositoryPedido.buscarPorId(idPedido);
          Toast.makeText(context, "Pedido para " + p.getMailContacto() + " ha cambiado de estado a ACEPTADO", Toast.LENGTH_LONG).show();

      }

    }


}
