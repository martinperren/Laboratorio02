package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoHolder;

public class PedidosAdapter extends ArrayAdapter<Pedido> {
    private Context mContext;
    private List<Pedido> listaPedidos;
    private PedidoRepository repositorioPedido = new PedidoRepository();

    public PedidosAdapter(Context context, List<Pedido> list) {
        super(context, 0, list);
        mContext = context;
        listaPedidos = list;



    }


    public View getView(int position, View convertView, ViewGroup parent) {


        View fila = convertView;
        if (fila == null)
            fila = LayoutInflater.from(mContext).inflate(R.layout.lista_pedidos_format, parent, false);

        PedidoHolder holder = (PedidoHolder) fila.getTag();
        if (holder == null) {
            holder = new PedidoHolder(fila);
            fila.setTag(holder);
        }
        Pedido pedido = super.getItem(position);




        holder.tvMailPedido.setText("Contacto: " + pedido.getMailContacto());
        holder.tvHoraEntrega.setText(pedido.getFecha().toString());
        if (pedido.getRetirar()) {
            holder.tipoEntrega.setImageResource(android.R.drawable.star_big_on);
        } else {
            holder.tipoEntrega.setImageResource(android.R.drawable.star_big_off);
        }
        holder.tvCantidadItems.setText("");
        holder.tvPrecio.setText("");

        switch (pedido.getEstado()) {
            case LISTO:
                holder.estado.setTextColor(Color.DKGRAY);
                break;
            case ENTREGADO:
                holder.estado.setTextColor(Color.BLUE);
                break;
            case CANCELADO:
            case RECHAZADO:
                holder.estado.setTextColor(Color.RED);
                break;
            case ACEPTADO:
                holder.estado.setTextColor(Color.GREEN);
                break;
            case EN_PREPARACION:
                holder.estado.setTextColor(Color.MAGENTA);
                break;
            case REALIZADO:
                holder.estado.setTextColor(Color.BLUE);
                break;
        }


        holder.estado.setText(pedido.getEstado().toString());



        return fila;


    }







}



