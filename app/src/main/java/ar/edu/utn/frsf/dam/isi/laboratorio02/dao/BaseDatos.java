package ar.edu.utn.frsf.dam.isi.laboratorio02.dao;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Pedido;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoConDetalle;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.PedidoDetalle;
import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Producto;

public class BaseDatos {

    private static BaseDatos _REPO= null;
    private CategoriaDAO categoriaDAO;
    private ProductoDAO productoDAO;
    private PedidoDAO pedidoDAO;
    private PedidoDetalleDAO pedidoDetalleDAO;


    public BaseDatos(Context ctx){

        RoomAbsDB database = Room.databaseBuilder(ctx,RoomAbsDB.class, "database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        categoriaDAO = database.categoriaDAO();
        productoDAO = database.productoDao();
        pedidoDAO = database.pedidoDao();
        pedidoDetalleDAO = database.pedidoDetalleDao();
    }

    public static  BaseDatos getInstance(Context ctx){
        if(_REPO==null) _REPO = new BaseDatos(ctx);
        return _REPO;
    }

 /*   public void DeleteAll(){
        this.database.clearAllTables();
    }*/

    public void insertCategoria(Categoria categoria) {categoriaDAO.insertCategoria(categoria);}

    public void insertPedidoDetalle (PedidoDetalle pd) {pedidoDetalleDAO.insertPedidoDetalle(pd);}

    public void insertProducto(Producto producto) {productoDAO.insertProducto(producto);}

    public void updateProducto(Producto producto) {productoDAO.updateProducto(producto);}

    public List<Categoria> getCategoria() {
        return categoriaDAO.getAll();
    }

    public List<Producto> getProducto() {
        return productoDAO.getAll();
    }

    public List<Producto> getProductoById(String id) {
        return productoDAO.getProductoById(id);
    }

    public PedidoDetalleDAO getPedidoDetalleDAO() {
        return pedidoDetalleDAO;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public Pedido getPedidoById(String id) {
        return pedidoDAO.getPedidoById(id);
    }

    public PedidoDetalle getPedidoDetalleById(String id) {
        return pedidoDetalleDAO.getPedidoDetalleById(id);
    }

    public List<PedidoConDetalle> getPedidoByIdConDetalle (String id){
        return pedidoDAO.getPedidoByIdConDetalle(id);
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
