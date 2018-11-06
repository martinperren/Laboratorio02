package ar.edu.utn.frsf.dam.isi.laboratorio02.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;

@Dao
public interface CategoriaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCategoria(Categoria categoria);
    @Delete
    public void deleteCategoria(Categoria categoria);
    @Update
    public void updateCategoria(Categoria categoria);
}
