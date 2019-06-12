package genteterra.com;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//####################################################################################Microsoft###################################################################################

//####################################################################################Microsoft###################################################################################


public class form3 extends AppCompatActivity {
    //####################################################################################Microsoft###################################################################################


//####################################################################################Microsoft###################################################################################


    private String term1;
    private String term2;
    private String voltagem;
    private String comunicador;
    private String antsatelital;
    private String script;
    private String antgprs;
    private String data;
    private String horainicio;
    private String horafim;
    private String pontoref;
    private String id;
    private String lacre1;
    private String lacre2;
    private String antAmp1;
    private String antAmp2;
    private String antSat1;
    private String antSat2;
    private String chicote1;
    private String chicote2;
    private String interface1;
    private String interface2;
    private String login;
    private String draga;
    private String empresa;
    private String mineradoras;
    private String problema;
    private String solucao;
    private String observacao;
    private String condsys;
    private String visita;
    private String respdraga;
    private String kmrd;
    private String deslocamento;
    private String pagamento;


    private EditText condsys1, visita1, respdraga1, kmrd1, deslocamento1, pagamento1;

    public String getTerm1() {
        return term1;
    }

    public void setTerm1(String term1) {
        this.term1 = term1;
    }

    public String getTerm2() {
        return term2;
    }

    public void setTerm2(String term2) {
        this.term2 = term2;
    }

    public String getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(String voltagem) {
        this.voltagem = voltagem;
    }

    public String getComunicador() {
        return comunicador;
    }

    public void setComunicador(String comunicador) {
        this.comunicador = comunicador;
    }

    public String getAntsatelital() {
        return antsatelital;
    }

    public void setAntsatelital(String antsatelital) {
        this.antsatelital = antsatelital;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getAntgprs() {
        return antgprs;
    }

    public void setAntgprs(String antgprs) {
        this.antgprs = antgprs;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafim() {
        return horafim;
    }

    public void setHorafim(String horafim) {
        this.horafim = horafim;
    }

    public String getPontoref() {
        return pontoref;
    }

    public void setPontoref(String pontoref) {
        this.pontoref = pontoref;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLacre1() {
        return lacre1;
    }

    public void setLacre1(String lacre1) {
        this.lacre1 = lacre1;
    }

    public String getLacre2() {
        return lacre2;
    }

    public void setLacre2(String lacre2) {
        this.lacre2 = lacre2;
    }

    public String getAntAmp1() {
        return antAmp1;
    }

    public void setAntAmp1(String antAmp1) {
        this.antAmp1 = antAmp1;
    }

    public String getAntAmp2() {
        return antAmp2;
    }

    public void setAntAmp2(String antAmp2) {
        this.antAmp2 = antAmp2;
    }

    public String getAntSat1() {
        return antSat1;
    }

    public void setAntSat1(String antSat1) {
        this.antSat1 = antSat1;
    }

    public String getAntSat2() {
        return antSat2;
    }

    public void setAntSat2(String antSat2) {
        this.antSat2 = antSat2;
    }

    public String getChicote1() {
        return chicote1;
    }

    public void setChicote1(String chicote1) {
        this.chicote1 = chicote1;
    }

    public String getChicote2() {
        return chicote2;
    }

    public void setChicote2(String chicote2) {
        this.chicote2 = chicote2;
    }

    public String getInterface1() {
        return interface1;
    }

    public void setInterface1(String interface1) {
        this.interface1 = interface1;
    }

    public String getInterface2() {
        return interface2;
    }

    public void setInterface2(String interface2) {
        this.interface2 = interface2;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDraga() {
        return draga;
    }

    public void setDraga(String draga) {
        this.draga = draga;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getMineradoras() {
        return mineradoras;
    }

    public void setMineradoras(String mineradoras) {
        this.mineradoras = mineradoras;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);
        Button finalizar = findViewById(R.id.button);
        Button voltar = findViewById(R.id.btnform32);
        condsys1 = findViewById(R.id.condsys);
        visita1 = findViewById(R.id.visita);
        respdraga1 = findViewById(R.id.respdraga);
        kmrd1 = findViewById(R.id.km);
        deslocamento1 = findViewById(R.id.desloc);
        pagamento1 = findViewById(R.id.pag);

       condsys = condsys1.getText().toString();
       visita = visita1.getText().toString();
       respdraga = respdraga1.getText().toString();
       kmrd = kmrd1.getText().toString();
       deslocamento = deslocamento1.getText().toString();
       pagamento = pagamento1.getText().toString();


        //recuperando objeto do tipo pacote
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        setId(bundle.getString("ID"));
        setLogin(bundle.getString("LOGIN"));
        setDraga(bundle.getString("DRAGA"));
        setEmpresa(bundle.getString("EMPRESA"));
        setMineradoras(bundle.getString("MINERADORA"));
        setData(bundle.getString("DATA"));
        setHorainicio(bundle.getString("HRINICIO"));
        setHorafim(bundle.getString("HRFIM"));
        setPontoref(bundle.getString("PONTOREF"));
        setProblema(bundle.getString("PROBLEMA"));
        setSolucao(bundle.getString("SOLUCAO"));
        setObservacao(bundle.getString("OBS"));
        setLacre1(bundle.getString("LACRE1"));
        setLacre2(bundle.getString("LACRE2"));
        setAntAmp1(bundle.getString("ANTAMP1"));
        setAntAmp2(bundle.getString("ANTAMP2"));
        setAntSat1(bundle.getString("ANTSAT1"));
        setAntSat2(bundle.getString("ANTSAT2"));
        setChicote1(bundle.getString("CHICOTE1"));
        setChicote2(bundle.getString("CHICOTE2"));
        setInterface1(bundle.getString("INTERFACE1"));
        setInterface2(bundle.getString("INTERFACE2"));
        setTerm1(bundle.getString("TERM1"));
        setTerm2(bundle.getString("TERM2"));
        setVoltagem(bundle.getString("VOLTAGEM"));
        setComunicador(bundle.getString("COMUNICADOR"));
        setAntsatelital(bundle.getString("ANTSATELITAL"));
        setScript(bundle.getString("SCRIPT"));
        setAntgprs(bundle.getString("ANTGPRS"));

        String PREFS = "form3";
        SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
        editor.putString("id_vmt",getId());
        editor.putString("login",getLogin());
        editor.putString("draga",getDraga());
        editor.putString("empresa",getEmpresa());
        editor.putString("mineradoras",getMineradoras());
        editor.putString("mineradoras",getData());
        editor.putString("horainicio",getHorainicio());
        editor.putString("horafim",getHorafim());
        editor.putString("pontoref",getPontoref());
        editor.putString("problema",getProblema());
        editor.putString("solucao",getSolucao());
        editor.putString("obsevacao",getObservacao());
        editor.putString("lacre1",getLacre1());
        editor.putString("lacre2",getLacre2());
        editor.putString("antAmp1",getAntAmp1());
        editor.putString("antAmp2",getAntAmp2());
        editor.putString("antSat1",getAntSat1());
        editor.putString("antSat2",getAntSat2());
        editor.putString("chicote1",getChicote1());
        editor.putString("chicote2",getChicote2());
        editor.putString("interface1",getInterface1());
        editor.putString("interface2",getInterface2());
        editor.putString("term1",getTerm1());
        editor.putString("term2",getTerm2());
        editor.putString("voltagem",getVoltagem());
        editor.putString("comunicador",getComunicador());
        editor.putString("antsatelital",getAntsatelital());
        editor.putString("script",getScript());
        editor.putString("antgprs",getAntgprs());
        editor.putString("condsys",condsys);
        editor.putString("visita",visita);
        editor.putString("respdraga",respdraga);
        editor.putString("kmrd",kmrd);
        editor.putString("deslocamento",deslocamento);
        editor.putString("pagamento",pagamento);
        editor.commit();

        limpardados();




        //####################################################################################Microsoft###################################################################################



            voltar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });

            finalizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(form3.this, MainActivity.class);
                    startActivity(it);

                }
            });



//####################################################################################Microsoft###################################################################################



    }

    //####################################################################################Microsoft###################################################################################







    //####################################################################################Microsoft###################################################################################

    public void limpardados(){

       setId("");
       setLogin("");
       setDraga("");
       setEmpresa("");
       setMineradoras("");
       setData("");
       setHorainicio("");
       setHorafim("");
       setPontoref("");
       setProblema("");
       setSolucao("");
       setObservacao("");
       setLacre1("");
       setLacre2("");
        setAntAmp1("");
        setAntAmp2("");
        setAntSat1("");
        setAntSat2("");
        setChicote1("");
        setChicote2("");
        setInterface1("");
        setInterface2("");
        setTerm1("");
        setTerm2("");
        setVoltagem("");
        setComunicador("");
        setAntsatelital("");
        setScript("");
        setAntgprs("");
        condsys1.setText("");
        visita1.setText("");
        respdraga1.setText("");
         kmrd1.setText("");
         deslocamento1.setText("");
        pagamento1.setText("");
    }


    }



