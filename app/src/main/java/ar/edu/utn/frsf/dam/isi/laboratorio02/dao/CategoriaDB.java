package ar.edu.utn.frsf.dam.isi.laboratorio02.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;

@Database(entities = {Categoria.class}, version = 0)
public abstract class CategoriaDB extends RoomDatabase {

    public abstract CategoriaDAO categoriaDAO();


}
