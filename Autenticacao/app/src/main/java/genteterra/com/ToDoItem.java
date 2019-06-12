package genteterra.com;

/**
 * Represents an item in a ToDo list
 */
public class ToDoItem {
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
    private String id_vmt;
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

    public String getId_vmt() {
        return id_vmt;
    }

    public void setId_vmt(String id_vmt) {
        this.id_vmt = id_vmt;
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

    public String getCondsys() {
        return condsys;
    }

    public void setCondsys(String condsys) {
        this.condsys = condsys;
    }

    public String getVisita() {
        return visita;
    }

    public void setVisita(String visita) {
        this.visita = visita;
    }

    public String getRespdraga() {
        return respdraga;
    }

    public void setRespdraga(String respdraga) {
        this.respdraga = respdraga;
    }

    public String getKmrd() {
        return kmrd;
    }

    public void setKmrd(String kmrd) {
        this.kmrd = kmrd;
    }

    public String getDeslocamento() {
        return deslocamento;
    }

    public void setDeslocamento(String deslocamento) {
        this.deslocamento = deslocamento;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    /**
     * Item text
     */
    @com.google.gson.annotations.SerializedName("text")
    private String mText;


    /**
     * Item Id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    /**
     * Indicates if the item is completed
     */
    @com.google.gson.annotations.SerializedName("complete")
    private boolean mComplete;

    /**
     * ToDoItem constructor
     */
    public ToDoItem() {

    }

    @Override
    public String toString() {
        return getText();
    }

    /**
     * Initializes a new ToDoItem
     *
     * @param text
     *            The item text
     * @param id
     *            The item id
     */
    public ToDoItem(String text, String id) {
        this.setText(text);
        this.setId(id);
    }

    /**
     * Returns the item text
     */
    public String getText() {
        return mText;
    }

    /**
     * Sets the item text
     *
     * @param text
     *            text to set
     */
    public final void setText(String text) {
        mText = text;
    }

    /**
     * Returns the item id
     */
    public String getId() {
        return mId;
    }

    /**
     * Sets the item id
     *
     * @param id
     *            id to set
     */
    public final void setId(String id) {
        mId = id;
    }

    /**
     * Indicates if the item is marked as completed
     */
    public boolean isComplete() {
        return mComplete;
    }

    /**
     * Marks the item as completed or incompleted
     */
    public void setComplete(boolean complete) {
        mComplete = complete;
    }


    @Override
    public boolean equals(Object o) {
        return o instanceof ToDoItem && ((ToDoItem) o).mId == mId;
    }
}