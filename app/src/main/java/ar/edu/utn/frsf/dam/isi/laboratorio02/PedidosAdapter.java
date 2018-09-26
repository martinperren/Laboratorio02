package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.dao.PedidoRepository;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;


public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.PedidoHolder> {


    public int pos;

    public static class PedidoHolder extends RecyclerView.ViewHolder {

        public TextView tvMailPedido;
        public TextView tvHoraEntrega;
        public TextView tvCantidadItems;
        public TextView tvPrecio;
        public TextView estado;
        public ImageView tipoEntrega;
        public Button btnCancelar;
        public Button btnDetalle;


        public PedidoHolder(View base) {
            super(base);
            this.tipoEntrega = (ImageView) base.findViewById(R.id.imModoEntrega);
            this.tvMailPedido = (TextView) base.findViewById(R.id.tvMailPedido);
            this.tvHoraEntrega = (TextView) base.findViewById(R.id.tvHoraEntrega);
            this.tvCantidadItems = (TextView) base.findViewById(R.id.tvCantidadItems);
            this.tvPrecio = (TextView) base.findViewById(R.id.tvPrecio);
            this.estado = (TextView) base.findViewById(R.id.tvEstado);
            this.btnCancelar = (Button) base.findViewById(R.id.btnCancelar);
            this.btnDetalle = (Button) base.findViewById(R.id.btnVerDetalle);
        }


    }


    private List<Pedido> listaPedidos;
    private PedidoRepository repositorioPedido = new PedidoRepository();


    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    @Override
    public PedidosAdapter.PedidoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_pedidos_format, parent, false);
        PedidoHolder vh = new PedidoHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(PedidoHolder holder, int position) {

        Pedido pedido = listaPedidos.get(position);
        pos = position;
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


        holder.btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), AltaPedidos.class);
                i.putExtra("idPedidoREQ", repositorioPedido.getLista().get(pos).getId());
                System.out.println("get id: " + repositorioPedido.getLista().get(pos).getId());
                view.getContext().startActivity(i);

            }

        });


        holder.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pedido pedidoSeleccionado = listaPedidos.get(pos);
                if (pedidoSeleccionado.getEstado().equals(Pedido.Estado.REALIZADO) ||
                        pedidoSeleccionado.getEstado().equals(Pedido.Estado.ACEPTADO) ||
                        pedidoSeleccionado.getEstado().equals(Pedido.Estado.EN_PREPARACION)) {
                    pedidoSeleccionado.setEstado(Pedido.Estado.CANCELADO);
                    PedidosAdapter.this.notifyDataSetChanged();
                    return;
                }
            }

        });


        holder.estado.setText(pedido.getEstado().toString());


    }


    public PedidosAdapter(List<Pedido> myDataset) {
        listaPedidos = myDataset;


    }


}