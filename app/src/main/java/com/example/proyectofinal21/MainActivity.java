package com.example.proyectofinal21;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_codigo, et_letra, et_genero,et_autor, et_nombre;
    private Button btn_guardar, btn_eliminar, btn_actualizar;//btn_consultaCodigo, btn_consultaDescripcion,

    boolean inputEt=false;
    boolean inputEd=false;
    boolean input1=false;
    boolean input2=false;
    boolean input3=false;
    int resultadoInsert=0;

    String senal = "";
    String codigo = "";
    String letra = "";
    String nombre = "";
    String autor = "";
    String genero = "";


    MantenimientoMySQL manto = new MantenimientoMySQL();
    Dto datos = new Dto();

    //Banderas para saber estados del metodo CRUD
    boolean estadoGuarda = false;
    boolean estadoEliminar = false;

    AlertDialog.Builder dialogo;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_close)
                    .setTitle("Advertencia")
                    .setMessage("¿Realmente desea salir?")
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finishAffinity();
                        }
                    })
                    .show();
            return true;
        }
        //para las demas cosas, se reenvia el evento al listener habitual
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow));
        toolbar.setTitleTextColor(getResources().getColor(R.color.mycolor1));
        toolbar.setTitleMargin(0, 0, 0, 0);
        toolbar.setSubtitle("Himnario");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.mycolor));

        ///y esto para pantalla completa (oculta incluso la barra de estado)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        et_codigo = findViewById(R.id.et_codigo);
        et_letra = findViewById(R.id.et_letra);
        et_autor = findViewById(R.id.et_autor);
        et_nombre = findViewById(R.id.et_nombre);
        et_genero =  findViewById(R.id.et_genero);
        btn_guardar =  findViewById(R.id.btn_guardar);
        // btn_consultaCodigo = (Button) findViewById(R.id.btn_consultaCodigo);
        //btn_consultaDescripcion = findViewById(R.id.btn_consultaDescripcion);
        btn_eliminar = findViewById(R.id.btn_eliminar);
        btn_actualizar = findViewById(R.id.btn_actualizar);
        //tv_resultado = (TextView) findViewById(R.id.tv_resultado);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogConfirmacion();
            }
        });


        /******************************************************************/
        //BLOQUE DE CÓDIGO PARA MOSTRAR DATOS DE LA BUSQUEDA//
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                senal = bundle.getString("senal");
                codigo = bundle.getString("codigo");
                letra = bundle.getString("letra");
                nombre = bundle.getString("nombre");
                genero = bundle.getString("genero");
                autor = bundle.getString("autor");
                if (senal.equals("1")) {
                    et_codigo.setText(codigo);
                    et_letra.setText(letra);
                    et_nombre.setText(nombre);
                    et_autor.setText(autor);
                    et_genero.setText(genero);
                    //finish();
                }else if(senal.equals("2")){

                }
            }
        }catch (Exception e){

        }
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("Campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }
                if(et_letra.getText().toString().length()==0){
                    et_letra.setError("Campo obligatorio");
                    inputEd = false;
                }else {
                    inputEd=true;
                }
                if(et_autor.getText().toString().length()==0){
                    et_autor.setError("Campo obligatorio");
                    input1 = false;
                }else {
                    input1=true;
                }
                if(et_nombre.getText().toString().length()==0){
                    et_nombre.setError("Campo obligatorio");
                    input3 = false;
                }else {
                    input3=true;
                }
                if(et_genero.getText().toString().length()==0){
                    et_genero.setError("Campo obligatorio");
                    input2 = false;
                }else {
                    input2=true;
                }

                if (inputEt && inputEd && input1 && input2&& input3){
                    String codigo = et_codigo.getText().toString();
                    String letra = et_letra.getText().toString();
                    String autor = et_autor.getText().toString();
                    String nombre = et_nombre.getText().toString();
                    String genero = et_genero.getText().toString();
                    manto.guardar(MainActivity.this, codigo, letra, autor,nombre, genero);

                    limpiarDatos();
                    et_codigo.requestFocus();

                    /*
                    estadoGuarda = manto.guardar1(MainActivity.this, codigo, descripcion, precio);
                    if(estadoGuarda){
                        Toast.makeText(MainActivity.this, "Registro Almacenado Correctamente.", Toast.LENGTH_SHORT).show();
                        limpiarDatos();
                        et_codigo.requestFocus();
                    }*/

                }


            }
        });

        //Evento clic del botón eliminar.
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }

                if(inputEt){
                    String codigo = et_codigo.getText().toString();
                    manto.eliminar(MainActivity.this, codigo);

                    limpiarDatos();
                    et_codigo.requestFocus();
                    /*
                    if(estadoEliminar){
                        Toast.makeText(MainActivity.this, "Registro Eliminado correctamente.", Toast.LENGTH_SHORT).show();
                        limpiarDatos();
                    }else{
                         Toast toast = Toast.makeText(getApplicationContext(), "--> Nothing." +
                                        "\nNo hay información que eliminar.", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                        limpiarDatos();
                    }*/
                }
            }
        });

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_codigo.getText().toString().length()==0){
                    et_codigo.setError("campo obligatorio");
                    inputEt = false;
                }else {
                    inputEt=true;
                }

                if(inputEt) {

                    String cod = et_codigo.getText().toString();
                    String letra = et_letra.getText().toString();
                    String autor = et_autor.getText().toString();
                    String nombre = et_nombre.getText().toString();
                    String genero = et_genero.getText().toString();

                    datos.setCodigo(Integer.parseInt(cod));
                    datos.setLetra(letra);
                    datos.setAutor(autor);
                    datos.setNombre(nombre);
                    datos.setGenero(genero);

                    manto.modificar(MainActivity.this, datos);
                    limpiarDatos();
                    et_codigo.requestFocus();
                }

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Que Buscas", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    public void limpiarDatos(){
        et_codigo.setText(null);
        et_letra.setText(null);
        et_autor.setText(null);
        et_nombre.setText(null);
        et_genero.setText(null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_limpiar) {
            et_codigo.setText(null);
            et_letra.setText(null);
            et_autor.setText(null);
            et_nombre.setText(null);
            et_genero.setText(null);
            return true;
        }else if(id == R.id.action_listaArticulos){
            Intent spinnerActivity = new Intent(MainActivity.this, ConsultaRecicleView.class);
            startActivity(spinnerActivity);
            return true;
        }else if(id == R.id.action_salir){
            DialogConfirmacion();
            return true;
        }else if(id == R.id.action_guardar){
            Intent spinnerActivity = new Intent(MainActivity.this, MainActivity.class);
            startActivity(spinnerActivity);
            return true;
        }else if(id == R.id.action_Inicio){
            Intent spinnerActivity = new Intent(MainActivity.this, Inicio.class);
            startActivity(spinnerActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void DialogConfirmacion(){

                MainActivity.this.finishAffinity();
                //MainActivity.this.finish();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo, int id) {
                Toast.makeText(getApplicationContext(), "Operación Cancelada.", Toast.LENGTH_LONG).show();
            }
        });
        dialogo.show();
    }
}
