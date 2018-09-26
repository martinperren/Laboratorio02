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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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


    public View getView(final int position, View convertView, ViewGroup parent) {



        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lista_pedidos_format, parent, false);

        PedidoHolder holder = (PedidoHolder) convertView.getTag();
        if (holder == null) {
            holder = new PedidoHolder(convertView);
            convertView.setTag(holder);
        }
        Pedido pedido = super.getItem(position);




       holder.btnCancelar.setFocusable(false);
        holder.btnCancelar.setClickable(false);

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






        holder.btnDetalle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(mContext,AltaPedidos.class);
                i.putExtra("idPedidoREQ",repositorioPedido.getLista().get(position).getId());
                System.out.println("get id: "+repositorioPedido.getLista().get(position).getId());
                mContext.startActivity(i);

                }

        });






        holder.btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int indice =  position;
                Pedido pedidoSeleccionado = listaPedidos.get(indice);
                if( pedidoSeleccionado.getEstado().equals(Pedido.Estado.REALIZADO)||
                        pedidoSeleccionado.getEstado().equals(Pedido.Estado.ACEPTADO)||
                        pedidoSeleccionado.getEstado().equals(Pedido.Estado.EN_PREPARACION)){
                    pedidoSeleccionado.setEstado(Pedido.Estado.CANCELADO);
                    PedidosAdapter.this.notifyDataSetChanged();
                    return;
                }}

        });




        holder.estado.setText(pedido.getEstado().toString());



        return convertView;


    }








}



