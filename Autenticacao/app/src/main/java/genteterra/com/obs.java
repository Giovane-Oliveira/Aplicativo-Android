package genteterra.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class obs extends AppCompatActivity {
private Button proximo, voltar;
private EditText problema, solucao, obs;
    private String data, horainicio, horafim, pontoref, id, login, draga, empresa, mineradora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obs);
        proximo = findViewById(R.id.btnobs2);
        voltar = findViewById(R.id.btnobs1);
        problema = findViewById(R.id.edtproblema);
        solucao = findViewById(R.id.edtsolucao);
        obs = findViewById(R.id.edtobs);

        //recuperando objeto do tipo pacote
        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        id = bundle.getString("ID");
        login = bundle.getString("LOGIN");
        draga = bundle.getString("DRAGA");
        empresa = bundle.getString("EMPRESA");
        mineradora = bundle.getString("MINERADORA");
        data = bundle.getString("DATA");
        horainicio = bundle.getString("HRINICIO");
        horafim = bundle.getString("HRFIM");
        pontoref = bundle.getString("PONTOREF");

        voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        proximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(obs.this, form1.class);
                it.putExtra("ID", id);
                it.putExtra("LOGIN", login);
                it.putExtra("DRAGA", draga);
                it.putExtra("EMPRESA", empresa);
                it.putExtra("MINERADORA", mineradora);
                it.putExtra("DATA", data);
                it.putExtra("HRINICIO", horainicio);
                it.putExtra("HRFIM", horafim);
                it.putExtra("PONTOREF", pontoref);
                it.putExtra("PROBLEMA", problema.getText().toString());
                it.putExtra("SOLUCAO", solucao.getText().toString());
                it.putExtra("OBS", obs.getText().toString());
                startActivity(it);

            }
        });
    }
}
