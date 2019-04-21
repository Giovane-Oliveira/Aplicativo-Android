package genteterra.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


public class formulario2 extends AppCompatActivity {
  private Button btnvoltar2, btnprox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario2);
        btnvoltar2 = (Button) findViewById(R.id.btnvoltar2);
        btnprox = (Button) findViewById(R.id.btnprox);

        btnvoltar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(formulario2.this, formulario1.class);
                startActivity(it);
            }
        });



        btnprox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent it = new Intent(formulario2.this, formulario3.class);
                startActivity(it);


            }
        });
    }
}
