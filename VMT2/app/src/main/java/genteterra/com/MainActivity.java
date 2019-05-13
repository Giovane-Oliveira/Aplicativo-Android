package genteterra.com;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private EditText edtlogin, edtpasswd;
    private CheckBox check;
    String usuario, senha;
    private Button cadastrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtlogin = findViewById(R.id.edt1);
        edtpasswd = findViewById(R.id.edt2);
        cadastrar = findViewById(R.id.btn2);
        check = findViewById(R.id.lembrar);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                /*
                Show an expanation to the user *asynchronously* -- don't block
                this thread waiting for the user's response! After the user
                sees the explanation, try again to request the permission.
                */

            } else {

                // No explanation needed, we can request the permission.

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

            }

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                /*
                Show an expanation to the user *asynchronously* -- don't block
                this thread waiting for the user's response! After the user
                sees the explanation, try again to request the permission.
                */

            } else {

                // No explanation needed, we can request the permission.

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }


        String PREFS = "MinhaPreferencia";
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String nomeUsuario = prefs.getString("nomeUsuario", "");
        String senhaUsuario = prefs.getString("idUsuario", "");
        int id = prefs.getInt("id", 0);
        edtlogin.setText(nomeUsuario);
        edtpasswd.setText(senhaUsuario);
        if(id ==1){

            check.setChecked(true);

        }else{

            check.setChecked(false);
        }

        cadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Cadastrar.class);
                startActivity(it);
            }
        });

    }



    @Override
    public void onRequestPermissionsResult ( int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // If request is cancelled, the result arrays are empty.
            // permission was granted, yay! Do the
            // contacts-related task you need to do.
            // permission denied, boo! Disable the
            // functionality that depends on this permission.
            // other 'case' lines to check for other
            // permissions this app might request

        }
    }


    public void checktrue(){

if(check.isChecked()) {
    String PREFS = "MinhaPreferencia";
    SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
    editor.putString("nomeUsuario", usuario);
    editor.putString("idUsuario", senha);
    editor.putInt("id", 1);
    editor.commit();


}else{

    String PREFS = "MinhaPreferencia";
    SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
    editor.putString("nomeUsuario", "");
    editor.putString("idUsuario", "");
    editor.putInt("id", 0);
    editor.commit();


}

    }

    //metodo de ingreso
    public void validarusuario (View v){
        final String V2 = edtlogin.getText().toString();
        final String V1 = edtpasswd.getText().toString();

        if (V2.trim().isEmpty() || V1.trim().isEmpty()) {

            AlertDialog.Builder dig;
            dig = new AlertDialog.Builder(MainActivity.this);
            dig.setMessage("Preencha os campos");
            dig.setNeutralButton("ok", null);
            dig.show();
        } else {
            AdminSqlLite admin = new AdminSqlLite(this, "Usuarios", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
             usuario = edtlogin.getText().toString();
             senha = edtpasswd.getText().toString();
            checktrue();
            @SuppressLint("Recycle") Cursor fila = db.rawQuery("select nome,senha from usuario where nome='" + usuario + "' and senha='" + senha + "'", null);
            //preguntamos si el cursor tiene algun valor almacenado
            if (fila.moveToFirst()) {
                //capturamos los valores del cursos y lo almacenamos en variable
                String usua = fila.getString(0);
                String pass = fila.getString(1);
                //preguntamos si los datos ingresados son iguales
                if (usuario.equals(usua) && senha.equals(pass)) {
                    //si son iguales entonces vamos a otra ventana
                    //Menu es una nueva actividad empty
                    Intent vem = new Intent(this, Form.class);
                    vem.putExtra("login", usua);
                    startActivity(vem);
                    //limpiamos las las cajas de texto
                    edtlogin.setText("");
                    edtpasswd.setText("");


                }
            } else {

                Toast.makeText(this, "Login ou senha inválido!", Toast.LENGTH_SHORT).show();


            }
        }
    }

}
