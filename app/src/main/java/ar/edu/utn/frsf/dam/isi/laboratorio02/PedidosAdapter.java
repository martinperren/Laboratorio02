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

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.BaseDatos;
import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoConDetalle;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoHolder;

public class PedidosAdapter extends ArrayAdapter<PedidoConDetalle> {
    private Context mContext;
    private List<PedidoConDetalle> listaPedidos;
    private BaseDatos bd;
    //private PedidoRepository repositorioPedido = new PedidoRepository();






    public PedidosAdapter(Context context, List<PedidoConDetalle> list) {
        super(context, 0, list);
        bd = new BaseDatos(context);
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
        Pedido pedido = super.getItem(position).getPedido();




       holder.btnCancelar.setFocusable(false);
        holder.btnCancelar.setClickable(false);



        holder.tvMailPedido.setText("Contacto: " + pedido.getMailContacto());
        holder.tvHoraEntrega.setText("Fecha de Entrega: "+ pedido.getFecha().toString());
        if (pedido.getRetirar()) {
            holder.tipoEntrega.setImageResource(R.drawable.retira);
        } else {
            holder.tipoEntrega.setImageResource(R.drawable.envio);
        }
        
        
        
        holder.tvCantidadItems.setText("Items: " + Integer.toString(pedido.getDetalle().size()));
 double total  = 0;
        
        for (int i =0; i<pedido.getDetalle().size();i++){
            int amount = pedido.getDetalle().get(i).getCantidad();
            double price = pedido.getDetalle().get(i).getProducto().getPrecio();
            total = total + amount * price;
            
        }
        
       holder.tvPrecio.setText("A pagar: $" + Double.toString(total));
        
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
                i.putExtra("idPedidoREQ",bd.getPedidoDAO().getAll().get(position).getId());
                System.out.println("get id: "+bd.getPedidoDAO().getAll().get(position).getId());
                mContext.startActivity(i);

                }

        });






        holder.btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int indice =  position;
                Pedido pedidoSeleccionado = listaPedidos.get(indice).getPedido();
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



