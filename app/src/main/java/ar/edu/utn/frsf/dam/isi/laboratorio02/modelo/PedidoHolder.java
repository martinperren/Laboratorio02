package ar.edu.utn.frsf.dam.isi.laboratorio02.modelo;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ar.edu.utn.frsf.dam.isi.laboratorio02.R;

public class PedidoHolder {

    public TextView tvMailPedido;
    public TextView tvHoraEntrega;
    public TextView tvCantidadItems;
    public TextView tvPrecio;
    public TextView estado;
    public ImageView tipoEntrega;
    public Button btnCancelar;


    public PedidoHolder(View base) {
        this.tipoEntrega = (ImageView) base.findViewById(R.id.imModoEntrega);
        this.tvMailPedido = (TextView) base.findViewById(R.id.tvMailPedido);
        this.tvHoraEntrega = (TextView) base.findViewById(R.id.tvHoraEntrega);
        this.tvCantidadItems= (TextView) base.findViewById(R.id.tvCantidadItems);
        this.tvPrecio= (TextView) base.findViewById(R.id.tvPrecio);
        this.estado= (TextView) base.findViewById(R.id.tvEstado);
        this.btnCancelar = (Button) base.findViewById(R.id.btnCancelar);
    }


}


