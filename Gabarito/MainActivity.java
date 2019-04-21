package genteterra.com;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;
public class MainActivity extends AppCompatActivity {

    private EditText edtlogin, edtpasswd;
    private Button btnok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtlogin = (EditText) findViewById(R.id.edtlogin);
        edtpasswd = (EditText) findViewById(R.id.edtpasswd);
        btnok = (Button) findViewById(R.id.btnok);

        // Recuperando Valores
        String V2 = edtlogin.getText().toString();
        String V1 = edtlogin.getText().toString();

        if (V2.isEmpty() || V1.isEmpty()) {

            AlertDialog.Builder dig = new AlertDialog.Builder(this);
            dig.setMessage("Preencha os campos");
            dig.setNeutralButton("ok", null);
            dig.show();
        } else {

            btnok.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent it = new Intent(MainActivity.this, formulario.class);
                    startActivity(it);
                }
            });


        }
    }
}

