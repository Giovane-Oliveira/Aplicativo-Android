package genteterra.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class formulario3 extends AppCompatActivity {
private Button btnvoltar3, btncon;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario3);
        btnvoltar3 = (Button) findViewById(R.id.btnvoltar3);
        btncon = (Button) findViewById(R.id.btncon);
    }
}
