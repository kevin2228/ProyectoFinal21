package com.example.proyectofinal21;

public class Config {

    public static final String urlGuardar = "http://192.168.43.135/conexion/guardar.php";                               //Guardar datos DB.

    public static final String urlEliminar = "http://localhost/conexion/eliminar.php";                             //Eliminar datos DB.
    public static final String urlActualizar = "http://localhost/conexion/actualizar.php";                          //Actualizar datos DB.

    public static final String urlConsultaApiMySQLi = "http://localhost/conexion/Api.php";                          //Ver todos los registros DB.
    public static final String urlConsultaApiPDO = "http://localhost/conexion/buscarAll.php";                       //Ver todos los registros DB.

    public static final String urlConsultaCodigo = "http://localhost/conexion/buscarCodigo.php";            //Busquedas por código.

    public static final String urlbuscarhimnario = "http://localhost/conexion/buscarhimnario.php";//Busquedas por el nombre de la Cancion.
    public static final String urlConsultaAllArticulos = "http://localhost/conexion/buscarArticulos.php";            //Devuelve todos los registros de la tabla MySQL.

}
