package genteterra.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class formulario1 extends AppCompatActivity {
private Button btnvoltar1, btnproximo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario1);
        btnvoltar1 = (Button) findViewById(R.id.btnvoltar1);
        btnproximo1 = (Button) findViewById(R.id.btnproximo1);

        btnproximo1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(formulario1.this, formulario2.class);
                startActivity(it);
            }
        });

        btnvoltar1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(formulario1.this, formulario.class);
                startActivity(it);
            }
        });
    }
}
