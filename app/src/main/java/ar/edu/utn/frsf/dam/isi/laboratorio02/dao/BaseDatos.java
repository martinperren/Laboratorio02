package ar.edu.utn.frsf.dam.isi.laboratorio02.dao;

import android.arch.persistence.room.Room;
import android.content.Context;

public class BaseDatos {

    private static BaseDatos _REPO= null;
    private CategoriaDAO categoriaDAO;
    private CategoriaDB categoriaDb;

    private  BaseDatos(Context ctx){

        categoriaDb = Room.databaseBuilder(ctx,CategoriaDB.class, "BaseCategoria").fallbackToDestructiveMigration().build();
        categoriaDAO = categoriaDb.categoriaDAO();
    }

    public static  BaseDatos getInstance(Context ctx){
        if(_REPO==null) _REPO = new BaseDatos(ctx);
        return _REPO;
    }

    public void DeleteAll(){
        this.categoriaDb.clearAllTables();
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }
}
