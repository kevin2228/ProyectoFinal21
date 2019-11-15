package com.example.proyectofinal21;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class busqueda {

    Dto datos = new Dto();

    boolean estadoGuardar = false;
    boolean estadoEliminar = false;

    private ProgressDialog pd;
    AlertDialog.Builder dialogo1;
    AlertDialog.Builder dialogo;
    ProgressDialog progressDialog;

    //List<Productos> productosList;
    //List<> productosList;
    ProductsAdapter adapter;

    public void consultarDescripcion(final Context context, final String autor){

        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Espere por favor, estamos trabajando en su peticion");

        String url = Config.urlbuscarhimnario;

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @SuppressLint("ResourceType")
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("0")) {
                            Toast.makeText(context, "No se encontraron resultados para la busqueda realizada.", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }else{
                            try {
                                JSONArray jsonArray = new JSONArray(response);

                                String nombre = jsonArray.getJSONObject(0).getString("nombre");


                                datos.setNombre(nombre);

                                Intent intent = new Intent(context, MainActivity.class);

                                intent.putExtra("nombre", nombre);

                                context.startActivity(intent);

                                progressDialog.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error !=null){
                            Toast.makeText(context, "No se logro establecer conexion con el servidor, verifique su acceso a Internet", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                }) {

        }
    }

    }

