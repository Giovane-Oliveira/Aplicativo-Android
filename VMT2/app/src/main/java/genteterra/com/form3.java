package genteterra.com;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class form3 extends AppCompatActivity {

    private EditText condsys, visita, respdraga, kmrd, deslocamento, pagamento;
    private int count;
    private String term1, term2, voltagem, comunicador, antsatelital, script, antgprs ,data, horainicio, horafim,
            pontoref, id, lacre1, lacre2, antAmp1, antAmp2, antSat1, antSat2, chicote1, chicote2,
            interface1, interface2,login, draga, empresa, mineradoras,problema,solucao,observacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);
        Button gerarcsv = findViewById(R.id.btnform3);
        Button finalizar = findViewById(R.id.button);
        Button voltar = findViewById(R.id.btnform32);
        condsys = findViewById(R.id.condsys);
        visita = findViewById(R.id.visita);
        respdraga = findViewById(R.id.respdraga);
        kmrd = findViewById(R.id.km);
        deslocamento = findViewById(R.id.desloc);
        pagamento = findViewById(R.id.pag);

        //recuperando objeto do tipo pacote
        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        id = bundle.getString("ID");
        login = bundle.getString("LOGIN");
        draga = bundle.getString("DRAGA");
        empresa = bundle.getString("EMPRESA");
        mineradoras = bundle.getString("MINERADORA");
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
        term1 = bundle.getString("TERM1");
        term2= bundle.getString("TERM2");
        voltagem = bundle.getString("VOLTAGEM");
        comunicador = bundle.getString("COMUNICADOR");
        antsatelital = bundle.getString("ANTSATELITAL");
        script = bundle.getString("SCRIPT");
        antgprs = bundle.getString("ANTGPRS");


        voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
                                         @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                         public void onClick(View v) {
                                             //Intent it = new Intent(form3.this, MainActivity.class);
                                             //startActivity(it);
                                             final AlertDialog.Builder builder = new AlertDialog.Builder(form3.this);
                                             builder.setTitle("Sair?");
                                             builder.setMessage("Deseja realmente sair?");
                                             builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                                 @Override
                                                 public void onClick(DialogInterface dialog, int which) {
                                                     finishAffinity();
                                                 }
                                             });
                                                     builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                                         @Override
                                                         public void onClick(DialogInterface dialog, int which) {

                                                         }
                                                     });
                                             AlertDialog alerta = builder.create();
                                             alerta.show();
                                                 }

                                     });


        gerarcsv.setOnClickListener(new View.OnClickListener(){
            AdminSqlLite admin = new AdminSqlLite(form3.this, "Administracao", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            Cursor c = null;
            @Override
            public void onClick(View v) { //main code begins here
                try {

                    c = db.rawQuery("SELECT * FROM vmts WHERE usuario = '" + login + "'", null);
                    int rowcount = 0;
                    int colcount = 0;
                    File sdCardDir = Environment.getExternalStorageDirectory();
                    String filename = "vmt.csv";
                    // the name of the file to export with
                    File saveFile = new File(sdCardDir, filename);
                    FileWriter fw = new FileWriter(saveFile);

                    BufferedWriter bw = new BufferedWriter(fw);
                    rowcount = c.getCount();
                    colcount = c.getColumnCount();
                    if (rowcount > 0) {
                        c.moveToFirst();

                        for (int i = 0; i < colcount; i++) {
                            if (i != colcount - 1) {

                                bw.write(c.getColumnName(i) + ",");

                            } else {

                                bw.write(c.getColumnName(i));

                            }
                        }
                        bw.newLine();

                        for (int i = 0; i < rowcount; i++) {
                            c.moveToPosition(i);

                            for (int j = 0; j < colcount; j++) {
                                if (j != colcount - 1)
                                    bw.write(c.getString(j) + ",");
                                else
                                    bw.write(c.getString(j));
                            }
                            bw.newLine();
                        }
                        bw.flush();
                        Toast.makeText(form3.this, "Os dados foram exportados com sucesso!", Toast.LENGTH_SHORT).show();


                    }
                } catch (Exception ex) {
                    if (db.isOpen()) {
                        db.close();
                        Toast.makeText(form3.this, ex.getMessage(), Toast.LENGTH_SHORT).show();


                    }

                } finally {

                }

            }
        });

    }

    @SuppressLint("Recycle")
    public void cadastro(View v) {
        if(count > 0){

            Toast.makeText(this, "O VMT já foi lançado!", Toast.LENGTH_SHORT).show();

        } else {
            AdminSqlLite admin = new AdminSqlLite(this, "Administracao", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            String Id = id;
            String LOGIN = login;
            String DRAGA = draga;
            String EMPRESA = empresa;
            String MINERADORA = mineradoras;
            String Data = data;
            String Horainicio = horainicio;
            String Horafim = horafim;
            String Pontoref = pontoref;
            String Problema = problema;
            String Solucao = solucao;
            String Obs = observacao;
            String Lacre1 = lacre1;
            String Lacre2 = lacre2;
            String AntAmp1 = antAmp1;
            String AntAmp2 = antAmp2;
            String Antsat1 = antSat1;
            String Antsat2 = antSat2;
            String Chicote1 = chicote1;
            String Chicote2 = chicote2;
            String Interface1 = interface1;
            String Interface2 = interface2;
            String Term1 = term1;
            String Term2 = term2;
            String Voltagem = voltagem;
            String Comunicador = comunicador;
            String Antsatelital = antsatelital;
            String Script = script;
            String Gprs = antgprs;
            String Condsys = condsys.getText().toString();
            String Visita = visita.getText().toString();
            String Respdraga = respdraga.getText().toString();
            String Kmrd = kmrd.getText().toString();
            String Deslocamento = deslocamento.getText().toString();
            String Pagamento = pagamento.getText().toString();

            Cursor linha = (db.rawQuery("select * from vmts where id=" + Id, null));

            if (!linha.moveToFirst()) {

                // Passando os valores para a tabela

                ContentValues registro = new ContentValues();
                registro.put("id", Id);
                registro.put("usuario", LOGIN);
                registro.put("draga", DRAGA);
                registro.put("empresa", EMPRESA);
                registro.put("mineradora", MINERADORA);
                registro.put("data", Data);
                registro.put("horainicio", Horainicio);
                registro.put("horafim", Horafim);
                registro.put("pontoref", Pontoref);
                registro.put("problema", Problema);
                registro.put("solucao", Solucao);
                registro.put("obs", Obs);
                registro.put("lacreentrada", Lacre1);
                registro.put("lacresaida", Lacre2);
                registro.put("antampentrada", AntAmp1);
                registro.put("antampsaida", AntAmp2);
                registro.put("antsatentrada", Antsat1);
                registro.put("antsatsaida", Antsat2);
                registro.put("chicoteentrada", Chicote1);
                registro.put("chicotesaida", Chicote2);
                registro.put("interfaceentrada", Interface1);
                registro.put("interfacesaida", Interface2);
                registro.put("termentrada", Term1);
                registro.put("termsaida", Term2);
                registro.put("voltagem", Voltagem);
                registro.put("comunicador", Comunicador);
                registro.put("antsatelital", Antsatelital);
                registro.put("script", Script);
                registro.put("gprs", Gprs);
                registro.put("condsys", Condsys);
                registro.put("visita", Visita);
                registro.put("respdraga", Respdraga);
                registro.put("kmrd", Kmrd);
                registro.put("deslocamento", Deslocamento);
                registro.put("pagamento", Pagamento);

                // Inserindo os valores na tabela do db
                db.insert("vmts", null, registro);
                db.close();

                // Limpando os campos
                id = "";
                login = "";
                draga = "";
                empresa = "";
                mineradoras = "";
                data = "";
                horainicio = "";
                horafim = "";
                lacre1 = "";
                lacre2 = "";
                antAmp1 = "";
                antAmp2 = "";
                antSat1 = "";
                antSat2 = "";
                chicote1 = "";
                chicote2 = "";
                interface1 = "";
                interface2 = "";
                term1 = "";
                term2 = "";
                voltagem = "";
                comunicador = "";
                antsatelital = "";
                script = "";
                antgprs = "";
                condsys.setText("");
                visita.setText("");
                respdraga.setText("");
                kmrd.setText("");
                deslocamento.setText("");
                pagamento.setText("");

                // Mensagem de cadastro com sucesso

                Toast.makeText(this, "Os dados foram salvos com sucesso", Toast.LENGTH_SHORT).show();

                count++;

            } else {

                db.close();
                Toast.makeText(this, "Este cadastro já existe", Toast.LENGTH_SHORT).show();


            }
        }
    }

    }



