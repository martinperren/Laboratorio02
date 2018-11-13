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

@Dao
public interface PedidoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategoria(Pedido pedido);
    @Delete
    void deleteCategoria(Pedido pedido);
    @Update
    void updateCategoria(Pedido pedido);
    @Query("SELECT * FROM PEDIDO")
    List<Pedido> getAll();
}