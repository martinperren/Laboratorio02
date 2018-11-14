package ar.edu.utn.frsf.dam.isi.laboratorio02.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Producto;

@Dao
public interface ProductoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducto(Producto producto);
    @Delete
    void deleteProducto(Producto producto);
    @Update
    void updateProducto(Producto producto);
    @Query("SELECT * FROM PRODUCTO WHERE ID_PRODUCTO = :id")
    List<Producto> getProductoById(String id);
    @Query("SELECT * FROM PRODUCTO")
    List<Producto> getAll();
}