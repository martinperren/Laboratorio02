package ar.edu.utn.frsf.dam.isi.laboratorio02.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;

@Dao
public interface CategoriaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insertCategoria(Categoria categoria);
    @Delete
     void deleteCategoria(Categoria categoria);
    @Update
     void updateCategoria(Categoria categoria);
    @Query("SELECT * FROM CATEGORIA")
    List<Categoria> getAll();


}
