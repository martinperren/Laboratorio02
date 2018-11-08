package ar.edu.utn.frsf.dam.isi.laboratorio02.dao;

import android.arch.persistence.room.Room;
import android.content.Context;

public class BaseDatos {

    private static BaseDatos _REPO= null;
    private CategoriaDAO categoriaDAO;
    private ProductoDAO productoDAO;
    private PedidoDAO pedidoDAO;
    private RoomAbsDB database;

    private  BaseDatos(Context ctx){

        database = Room.databaseBuilder(ctx,RoomAbsDB.class, "database").fallbackToDestructiveMigration().build();
        categoriaDAO = database.categoriaDAO();
        productoDAO = database.productoDao();
        pedidoDAO = database.pedidoDao();
    }

    public static  BaseDatos getInstance(Context ctx){
        if(_REPO==null) _REPO = new BaseDatos(ctx);
        return _REPO;
    }

    public void DeleteAll(){
        this.database.clearAllTables();
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public PedidoDAO getPedidoDAO() {
        return pedidoDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public void setProductoDAO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }
}
