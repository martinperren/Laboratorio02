package ar.edu.utn.frsf.dam.isi.laboratorio02.modelo;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoDetalle;

public class PedidoConDetalle {
    @Embedded
    public Pedido pedido;

    @Relation(parentColumn = "ID_PEDIDO", entityColumn = "PEDIDO_ID", entity = PedidoDetalle.class)
    public List<PedidoDetalle> detalles;

    public List<PedidoDetalle> getDetalles() {
        return detalles;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public void setDetalles(List<PedidoDetalle> detalles) {
        this.detalles = detalles;
    }

}
