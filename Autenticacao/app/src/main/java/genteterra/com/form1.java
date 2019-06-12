package genteterra.com;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class form1 extends AppCompatActivity  {
    private EditText lacre1, lacre2, antAmp1, antAmp2, antSat1, antSat2, chicote1, chicote2, interface1, interface2;
    // variáveis do activity anterior
    private String data, horainicio, horafim, pontoref, id, login, draga, empresa, mineradora,
    problema, solucao, observacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        lacre1 = findViewById(R.id.lacreentrada);
        lacre2 = findViewById(R.id.lacresaida);
        antAmp1 = findViewById(R.id.antampentrada);
        antAmp2 = findViewById(R.id.antampsaida);
        antSat1 = findViewById(R.id.antsatentrada);
        antSat2 = findViewById(R.id.antsatsaida);
        chicote1 = findViewById(R.id.chicoteen);
        chicote2 = findViewById(R.id.chicotesai);
        interface1 = findViewById(R.id.interfaceen);
        interface2 = findViewById(R.id.interfacesai);
        Button proximo = findViewById(R.id.btnform1);
        Button voltar = findViewById(R.id.btnform12);

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


        proximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(form1.this, form2.class);
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
                it.putExtra("LACRE1", lacre1.getText().toString());
                it.putExtra("LACRE2", lacre2.getText().toString());
                it.putExtra("ANTAMP1", antAmp1.getText().toString());
                it.putExtra("ANTAMP2", antAmp2.getText().toString());
                it.putExtra("ANTSAT1", antSat1.getText().toString());
                it.putExtra("ANTSAT2", antSat2.getText().toString());
                it.putExtra("CHICOTE1", chicote1.getText().toString());
                it.putExtra("CHICOTE2", chicote2.getText().toString());
                it.putExtra("INTERFACE1", interface1.getText().toString());
                it.putExtra("INTERFACE2", interface2.getText().toString());
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
