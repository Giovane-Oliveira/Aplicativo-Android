package genteterra.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class formulario extends AppCompatActivity {
private Button btnproximo, btnvoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        btnproximo = (Button) findViewById(R.id.btnproximo);
        btnvoltar = (Button) findViewById(R.id.btnvoltar);

        btnproximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(formulario.this, formulario1.class);
                startActivity(it);
            }
        });

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(formulario.this, MainActivity.class);
                startActivity(it);
            }
        });
    }
}
