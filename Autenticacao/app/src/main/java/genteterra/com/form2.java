package genteterra.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class form2 extends AppCompatActivity {
    private EditText term1, term2, voltagem, comunicador, antsatelital, script, antgprs;
    private String data, horainicio, horafim, pontoref, id, lacre1, lacre2, antAmp1,
            antAmp2, antSat1, antSat2, chicote1, chicote2, interface1, interface2, login, draga,
            empresa, mineradora, problema, solucao, observacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
        term1 = findViewById(R.id.termentrada);
        term2 = findViewById(R.id.termsaida);
        voltagem = findViewById(R.id.voltagem);
        comunicador = findViewById(R.id.comunicador);
        antsatelital = findViewById(R.id.antsatelital);
        script = findViewById(R.id.script);
        antgprs = findViewById(R.id.gprs);
        Button voltar = findViewById(R.id.btnform21);
        Button proximo = findViewById(R.id.btnform2);

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
        problema = bundle.getString("PROBLEMA");
        solucao = bundle.getString("SOLUCAO");
        observacao = bundle.getString("OBS");
        lacre1 = bundle.getString("LACRE1");
        lacre2 = bundle.getString("LACRE2");
        antAmp1 = bundle.getString("ANTAMP1");
        antAmp2 = bundle.getString("ANTAMP2");
        antSat1 = bundle.getString("ANTSAT1");
        antSat2 = bundle.getString("ANTSAT2");
        chicote1 = bundle.getString("CHICOTE1");
        chicote2 = bundle.getString("CHICOTE2");
        interface1 = bundle.getString("INTERFACE1");
        interface2 = bundle.getString("INTERFACE2");

        proximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(form2.this, form3.class);
                it.putExtra("ID", id);
                it.putExtra("LOGIN", login);
                it.putExtra("DRAGA", draga);
                it.putExtra("EMPRESA", empresa);
                it.putExtra("MINERADORA", mineradora);
                it.putExtra("DATA", data);
                it.putExtra("HRINICIO", horainicio);
                it.putExtra("HRFIM", horafim);
                it.putExtra("PONTOREF", pontoref);
                it.putExtra("PROBLEMA", problema);
                it.putExtra("SOLUCAO", solucao);
                it.putExtra("OBS", observacao);
                it.putExtra("LACRE1", lacre1);
                it.putExtra("LACRE2", lacre2);
                it.putExtra("ANTAMP1", antAmp1);
                it.putExtra("ANTAMP2", antAmp2);
                it.putExtra("ANTSAT1", antSat1);
                it.putExtra("ANTSAT2", antSat2);
                it.putExtra("CHICOTE1", chicote1);
                it.putExtra("CHICOTE2", chicote2);
                it.putExtra("INTERFACE1", interface1);
                it.putExtra("INTERFACE2", interface2);
                it.putExtra("TERM1", term1.getText().toString());
                it.putExtra("TERM2", term2.getText().toString());
                it.putExtra("VOLTAGEM", voltagem.getText().toString());
                it.putExtra("COMUNICADOR", comunicador.getText().toString());
                it.putExtra("ANTSATELITAL", antsatelital.getText().toString());
                it.putExtra("SCRIPT", script.getText().toString());
                it.putExtra("ANTGPRS", antgprs.getText().toString());
                startActivity(it);
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
