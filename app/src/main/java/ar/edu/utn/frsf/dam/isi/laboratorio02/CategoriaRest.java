package ar.edu.utn.frsf.dam.isi.laboratorio02;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frsf.dam.isi.laboratorio02.modelo.Categoria;

public class CategoriaRest {

    public CategoriaRest() {
    }

    // realiza el POST de una categoría al servidor REST
    public void crearCategoria(Categoria c) {


        try {

            //Variables de conexión y stream de escritura y lectura
            HttpURLConnection urlConnection = null;
            DataOutputStream printout = null;
            InputStream in = null;
            //Crear el objeto json que representa una categoria


            JSONObject categoriaJson = new JSONObject();
            categoriaJson.put("nombre", c.getNombre());


            //Abrir una conexión al servidor para enviar el POST


            URL url = new URL("http://10.0.2.2:2700/categorias");


            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            //Obtener el outputStream para escribir el JSON
            printout = new DataOutputStream(urlConnection.getOutputStream());
            String str = categoriaJson.toString();
            byte[] jsonData = str.getBytes("UTF-8");
            printout.write(jsonData);
            printout.flush();
            //Leer la respuesta
            in = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader isw = new InputStreamReader(in);
            StringBuilder sb = new StringBuilder();
            int data = isw.read();
            //Analizar el codigo de lar respuesta
            if (urlConnection.getResponseCode() == 200 ||
                    urlConnection.getResponseCode() == 201) {
                while (data != -1) {
                    char current = (char) data;
                    sb.append(current);

                    data = isw.read();
                }
                //analizar los datos recibidos
                Log.d("LAB_04", sb.toString());
            } else {
                // lanzar excepcion o mostrar mensaje de error
                // que no se pudo ejecutar la operacion
            }
            // caputurar todas las excepciones y en el bloque finally
            // cerrar todos los streams y HTTPUrlCOnnection
            if (printout != null) try {
                printout.close();
            }
            finally {
                urlConnection.disconnect(); ///? agregue los finnaly pero ???
            }
            if (in != null) try {
                in.close();
            }
            finally {
                urlConnection.disconnect(); ///? agregue los finnaly pero ???
            }
        }
        catch(JSONException e){
            System.out.println("JSONException" +e);
        }
        catch(MalformedURLException e){
            System.out.println("MalformedURLException" +e);
        }
        catch(IOException e){
            System.out.println("IOException" +e);
        }



    }

    // definir el método

    public List<Categoria> listarTodas(){
        List<Categoria> resultado = new ArrayList<>();
        try {
// inicializar variables
        HttpURLConnection urlConnection = null;
        InputStream in =null;
// GESTIONAR LA CONEXION
        URL url = new URL("http://10.0.2.2:2700/categorias");
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept-Type","application/json");
        urlConnection.setRequestMethod("GET");
// Leer la respuesta
        in = new BufferedInputStream(urlConnection.getInputStream());
        InputStreamReader isw = new InputStreamReader(in);
        StringBuilder sb = new StringBuilder();
        int data = isw.read();
// verificar el codigo de respuesta
        if( urlConnection.getResponseCode() ==200 ||
                urlConnection.getResponseCode()==201){
            while (data != -1) {
                char current = (char) data;
                sb.append(current);
                data = isw.read();
            }
            // ver datos recibidos
            Log.d("LAB_04",sb.toString());
            // Transformar respuesta a JSON
            JSONTokener tokener = new JSONTokener(sb.toString());
            JSONArray listaCategorias = (JSONArray) tokener.nextValue();

            // iterar todas las entradas del arreglo
            for(int i=0;i<listaCategorias.length();i++){
                Categoria cat = new Categoria();
                ///////////////////////////
                cat.setNombre(listaCategorias.getJSONObject(i).getString("nombre"));
                cat.setId(listaCategorias.getJSONObject(i).getInt("id"));
                // analizar cada element del JSONArray
                //armar una instancia de categoría y agregarla a la lista
                resultado.add(cat);
            }
        }else{
            // lanzar excepcion o mostrar mensaje de error
            // que no se pudo ejecutar la operacion
        }
//NO OLVIDAR CERRAR inputStream y conexion
        if (urlConnection !=null) try {
            urlConnection.disconnect();
        }
        finally {
            urlConnection.disconnect(); ///? agregue los finnaly pero ???
        }
        if (in != null) try {
            in.close();
        }
        finally {
            urlConnection.disconnect(); ///? agregue los finnaly pero ???
        }}
        catch(JSONException e){
        System.out.println("JSONException" +e);
    }
        catch(MalformedURLException e){
        System.out.println("MalformedURLException" +e);
    }
        catch(IOException e){
        System.out.println("IOException" +e);
    }

return resultado;
}

}

