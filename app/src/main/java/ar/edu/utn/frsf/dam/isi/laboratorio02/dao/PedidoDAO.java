package ar.edu.utn.frsf.dam.isi.laboratorio02.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoConDetalle;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Producto;

@Dao
public interface PedidoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPedido(Pedido pedido);
    @Delete
    void deletePedido(Pedido pedido);
    @Update
    void updatePedido(Pedido pedido);
    @Query("SELECT * FROM Pedido")
    List<Pedido> getAll();
    @Query("SELECT * FROM PEDIDO WHERE ID_PEDIDO = :id")
    Pedido getPedidoById(String id);
    @Query("SELECT * FROM PEDIDO")
    List<PedidoConDetalle> getAllConDetalle();
    @Query("SELECT * FROM PEDIDO WHERE ID_PEDIDO = :id")
    List<PedidoConDetalle> getPedidoByIdConDetalle(String id);




}