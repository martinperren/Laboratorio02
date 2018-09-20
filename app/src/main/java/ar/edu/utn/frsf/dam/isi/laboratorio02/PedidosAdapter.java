package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;

public class PedidosAdapter extends ArrayAdapter<Pedido> {
    private Context mContext;
    private List<Pedido> listaPedidos = new ArrayList<>();

    public PedidosAdapter(Context context, ArrayList<Pedido> list) {
        super(context, 0 , list);
        mContext = context;
        listaPedidos = list;
    }


    public View getView(int position, View convertView, ViewGroup parent){


        View fila = convertView;
        if(fila == null)
            fila = LayoutInflater.from(mContext).inflate(R.layout.lista_pedidos_format,parent,false);




        Pedido pedido =  super.getItem(position);
        TextView tvID =  fila.findViewById(R.id.tvID);
        TextView tvContacto=  fila.findViewById(R.id.tvContacto);
        TextView tvFecha=  fila.findViewById(R.id.tvFecha);
        TextView tvDireccion = fila.findViewById(R.id.tvDirEnvio);
        ImageView iwestado =  fila.findViewById(R.id.iwestado);

        if(pedido.getRetirar()){
            iwestado.setImageResource(android.R.drawable.star_big_on);
        }else{
            iwestado.setImageResource(android.R.drawable.star_big_off);
        }
//        tvID.setText(pedido.getId());
        tvContacto.setText(" Contacto: "+pedido.getMailContacto());
//        tvFecha.setText(pedido.getFecha().toString());
        tvDireccion.setText(pedido.getDireccionEnvio());
        return fila;






       }




}



