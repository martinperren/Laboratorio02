package ar.edu.utn.frsf.dam.isi.laboratorio02.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoDetalle;


@Dao
    public interface PedidoDetalleDAO {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertCategoria(PedidoDetalle pedido);
        @Delete
        void deleteCategoria(PedidoDetalle pedido);
        @Update
        void updateCategoria(PedidoDetalle pedido);
        @Query("SELECT * FROM PEDIDODETALLE")
        List<Categoria> getAll();
    }



