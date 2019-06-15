package genteterra.com;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.iid.FirebaseInstanceId;
import com.microsoft.windowsazure.mobileservices.MobileServiceActivityResult;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceAuthenticationProvider;
import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceUser;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.query.Query;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
//####################################################################################Microsoft###################################################################################
//####################################################################################Microsoft###################################################################################
//####################################################################################Autenticação################################################################################

//####################################################################################Autenticação################################################################################

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private EditText edtlogin, edtpasswd;
    private CheckBox check;
    String usuario, senha;
    private Button cadastrar;
    private int count;
    private int contadora;
    public static final String SHAREDPREFFILE = "temp";
    public static final String USERIDPREF = "uid";
    public static final String TOKENPREF = "tkn";


    private static final String TAG = "MainActivity";



//####################################################################################Autenticação################################################################################

    // You can choose any unique number here to differentiate auth providers from each other. Note this is the same code at login() and onActivityResult().
    public static final int WindowsAzureActiveDirectory_LOGIN_REQUEST_CODE = 1;


//####################################################################################Autenticação################################################################################



//####################################################################################Microsoft###################################################################################

    private Query mPullQuery;


    ArrayList<ToDoItem> mAdapter;


    /**
     * Client reference
     */
    public static MobileServiceClient mClient;

    /**
     * Table used to access data from the mobile app backend.
     */
    // private MobileServiceTable<ToDoItem> mToDoTable;

    //Offline Sync
    /**
     * Table used to store data locally sync with the mobile app backend.
     */
    private MobileServiceSyncTable<ToDoItem> mToDoTable;


    private ProgressBar mProgressBar;

    /**
     * Initializes the activity
     */


//####################################################################################Microsoft###################################################################################
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtlogin = findViewById(R.id.edt1);
        edtpasswd = findViewById(R.id.edt2);
        cadastrar = findViewById(R.id.btn2);
        check = findViewById(R.id.lembrar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }



//####################################################################################Microsoft###################################################################################
        mProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);

        // Initialize the progress bar
        mProgressBar.setVisibility(ProgressBar.GONE);

        try {
            // Create the client instance, using the provided mobile app URL.
            mClient = new MobileServiceClient(
                    "https://vmtandroid.azurewebsites.net",
                    this).withFilter(new ProgressFilter());

                // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });

            authenticate();

            registerPush();

            //Init local storage
            initLocalStore().get();
            String PREFS = "sync";
            SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
            contadora = prefs.getInt("contadora", 0);
            if(contadora == 1)
            {

                sync();

            }


        } catch (MalformedURLException e) {
            createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
        } catch (Exception e) {
            createAndShowDialog(e, "Error");
        }


//####################################################################################Microsoft###################################################################################


        String PREFS = "MinhaPreferencia";
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String nomeUsuario = prefs.getString("nomeUsuario", "");
        String senhaUsuario = prefs.getString("idUsuario", "");
        int id = prefs.getInt("id", 0);
        edtlogin.setText(nomeUsuario);
        edtpasswd.setText(senhaUsuario);
        if(id ==1){

            check.setChecked(true);

        }else{

            check.setChecked(false);
        }

        cadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Cadastrar.class);
                startActivity(it);
            }
        });

    }



    //####################################################################################Microsoft###################################################################################
    public static void registerPush() {
        final String token = FirebaseInstanceId.getInstance().getToken();
        if (token != null) {
            new AsyncTask<Void, Void, Void>() {
                protected Void doInBackground(Void... params) {
                    mClient.getPush().register(token);
                    return null;
                }
            }.execute();
        }
    }
    private void createTable(){


        // Offline sync table instance.
        mToDoTable = mClient.getSyncTable("ToDoItem", ToDoItem.class);


        // Load the items from Azure.
        //refreshItemsFromTable();

        mPullQuery = mClient.getTable(ToDoItem.class).where().field("complete").eq(false);

        // Get the remote table instance to use.
        //mToDoTable = mClient.getTable(ToDoItem.class);

        String PREFS = "internet";
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        count = prefs.getInt("count", 0);

        if(isNetworkAvailable() && count == 0){
            Toast.makeText(MainActivity.this, "Online!", Toast.LENGTH_SHORT).show();
        }

        else if(isNetworkAvailable() && count == 1){
            Toast.makeText(MainActivity.this, "Sincronização efetuada com sucesso!", Toast.LENGTH_SHORT).show();

            addItem();
            //  sync();
            SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
            editor.putInt("count", 0);
            editor.commit();

        }

        else if(isNetworkAvailable() == false && count != 0){
            addItem();
            SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
            editor.putInt("count", 0);
            editor.commit();


        }

        else if(isNetworkAvailable() == false && count == 0){

            Toast.makeText(MainActivity.this, "Offline!", Toast.LENGTH_SHORT).show();

        }


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void addItem() {

        if (mClient == null) {
            return;
        }

        String PREFS = "form3";
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);

        // Create a new item
        final ToDoItem item = new ToDoItem();

        item.setId_vmt(prefs.getString("id_vmt", ""));
        item.setLogin(prefs.getString("login", ""));
        item.setDraga(prefs.getString("draga", ""));
        item.setEmpresa(prefs.getString("empresa", ""));
        item.setMineradoras(prefs.getString("mineradoras", ""));
        item.setData(prefs.getString("data", ""));
        item.setHorainicio(prefs.getString("horainicio", ""));
        item.setHorafim(prefs.getString("horafim", ""));
        item.setPontoref(prefs.getString("pontoref", ""));
        item.setProblema(prefs.getString("problema", ""));
        item.setSolucao(prefs.getString("solucao", ""));
        item.setObservacao(prefs.getString("observacao", ""));
        item.setLacre1(prefs.getString("lacre1", ""));
        item.setLacre2(prefs.getString("lacre2", ""));
        item.setAntAmp1(prefs.getString("antAmp1", ""));
        item.setAntAmp2(prefs.getString("antAmp2", ""));
        item.setAntSat1(prefs.getString("antSat1", ""));
        item.setAntSat2(prefs.getString("antSat2", ""));
        item.setChicote1(prefs.getString("chicote1", ""));
        item.setChicote2(prefs.getString("chicote2", ""));
        item.setInterface1(prefs.getString("interface1", ""));
        item.setInterface2(prefs.getString("interface2", ""));
        item.setTerm1(prefs.getString("term1", ""));
        item.setTerm2(prefs.getString("term2", ""));
        item.setVoltagem(prefs.getString("voltagem", ""));
        item.setComunicador(prefs.getString("comunicador", ""));
        item.setAntsatelital(prefs.getString("antsatelital", ""));
        item.setScript(prefs.getString("script", ""));
        item.setAntgprs(prefs.getString("antgprs", ""));
        item.setCondsys(prefs.getString("condsys", ""));
        item.setVisita(prefs.getString("visita", ""));
        item.setRespdraga(prefs.getString("respdraga", ""));
        item.setKmrd(prefs.getString("kmrd", ""));
        item.setDeslocamento(prefs.getString("deslocamento", ""));
        item.setPagamento(prefs.getString("pagamento", ""));

        // Insert the new item
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final ToDoItem entity = addItemInTable(item);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!entity.isComplete()) {
                                mAdapter = new ArrayList<ToDoItem>();
                                mAdapter.add(entity);
                                Toast.makeText(MainActivity.this, "VMT lançada com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }
                return null;
            }
        };

        runAsyncTask(task);

    limparcache();

    }

    /**
     * Add an item to the Mobile Service Table
     *
     * @param item The item to Add
     */
    public ToDoItem addItemInTable(ToDoItem item) throws ExecutionException, InterruptedException {
        ToDoItem entity = mToDoTable.insert(item).get();
        return entity;
    }

    private void refreshItemsFromTable() {

        // Get the items that weren't marked as completed and add them in the
        // adapter

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTable();

                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();
                    final MobileServiceList<ToDoItem> results = mToDoTable.read(mPullQuery).get();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.clear();

                            for (ToDoItem item : results) {
                                mAdapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        runAsyncTask(task);
    }


    private AsyncTask<Void, Void, Void> initLocalStore() throws MobileServiceLocalStoreException, ExecutionException, InterruptedException {

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {

                    MobileServiceSyncContext syncContext = mClient.getSyncContext();

                    if (syncContext.isInitialized())
                        return null;
                    SQLiteLocalStore localStore = new SQLiteLocalStore(mClient.getContext(), "OfflineStore", null, 1);
                    Map<String, ColumnDataType> tableDefinition = new HashMap<String, ColumnDataType>();
                    tableDefinition.put("id", ColumnDataType.String);
                    tableDefinition.put("id_vmt", ColumnDataType.String);
                    tableDefinition.put("text", ColumnDataType.String);
                    tableDefinition.put("login", ColumnDataType.String);
                    tableDefinition.put("draga", ColumnDataType.String);
                    tableDefinition.put("empresa", ColumnDataType.String);
                    tableDefinition.put("mineradoras", ColumnDataType.String);
                    tableDefinition.put("data", ColumnDataType.String);
                    tableDefinition.put("horainicio", ColumnDataType.String);
                    tableDefinition.put("horafim", ColumnDataType.String);
                    tableDefinition.put("pontoref", ColumnDataType.String);
                    tableDefinition.put("problema", ColumnDataType.String);
                    tableDefinition.put("solucao", ColumnDataType.String);
                    tableDefinition.put("observacao", ColumnDataType.String);
                    tableDefinition.put("lacre1", ColumnDataType.String);
                    tableDefinition.put("lacre2", ColumnDataType.String);
                    tableDefinition.put("antAmp1", ColumnDataType.String);
                    tableDefinition.put("antAmp2", ColumnDataType.String);
                    tableDefinition.put("antSat1", ColumnDataType.String);
                    tableDefinition.put("antSat2", ColumnDataType.String);
                    tableDefinition.put("chicote1", ColumnDataType.String);
                    tableDefinition.put("chicote2", ColumnDataType.String);
                    tableDefinition.put("interface1", ColumnDataType.String);
                    tableDefinition.put("interface2", ColumnDataType.String);
                    tableDefinition.put("term1", ColumnDataType.String);
                    tableDefinition.put("term2", ColumnDataType.String);
                    tableDefinition.put("voltagem", ColumnDataType.String);
                    tableDefinition.put("comunicador", ColumnDataType.String);
                    tableDefinition.put("antsatelital", ColumnDataType.String);
                    tableDefinition.put("script", ColumnDataType.String);
                    tableDefinition.put("antgprs", ColumnDataType.String);
                    tableDefinition.put("condsys", ColumnDataType.String);
                    tableDefinition.put("visita", ColumnDataType.String);
                    tableDefinition.put("respdraga", ColumnDataType.String);
                    tableDefinition.put("kmrd", ColumnDataType.String);
                    tableDefinition.put("deslocamento", ColumnDataType.String);
                    tableDefinition.put("pagamento", ColumnDataType.String);
                    tableDefinition.put("complete", ColumnDataType.Boolean);

                    localStore.defineTable("ToDoItem", tableDefinition);

                    SimpleSyncHandler handler = new SimpleSyncHandler();

                    syncContext.initialize(localStore, handler).get();

                } catch (final Exception e) {
                    createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        return runAsyncTask(task);
    }

    //Offline Sync
    /**
     * Sync the current context and the Mobile Service Sync Table
     * @return
     */

    @SuppressLint("StaticFieldLeak")
    public void sync(){
  if(isNetworkAvailable()){
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        mClient.getSyncContext().push().get();
                        mToDoTable.pull(mPullQuery).get();

                    } catch (Exception exception) {
                        createAndShowDialog(exception, "Error");
                    }
                    return null;
                }
            }.execute();
            }
        }


    /**
     * Creates a dialog and shows it
     *
     * @param exception The exception to show in the dialog
     * @param title     The dialog title
     */
    private void createAndShowDialogFromTask(final Exception exception, String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAndShowDialog(exception, "Error");
            }
        });
    }


    /**
     * Creates a dialog and shows it
     *
     * @param exception The exception to show in the dialog
     * @param title     The dialog title
     */
    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if (exception.getCause() != null) {
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }

    /**
     * Creates a dialog and shows it
     *
     * @param message The dialog message
     * @param title   The dialog title
     */
    private void createAndShowDialog(final String message, final String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }

    /**
     * Run an ASync task on the corresponding executor
     *
     * @param task
     * @return
     */
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    private class ProgressFilter implements ServiceFilter {

        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(ServiceFilterRequest request, NextServiceFilterCallback nextServiceFilterCallback) {

            final SettableFuture<ServiceFilterResponse> resultFuture = SettableFuture.create();


            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.VISIBLE);
                }
            });

            ListenableFuture<ServiceFilterResponse> future = nextServiceFilterCallback.onNext(request);

            Futures.addCallback(future, new FutureCallback<ServiceFilterResponse>() {
                @Override
                public void onFailure(Throwable e) {
                    resultFuture.setException(e);
                }

                @Override
                public void onSuccess(ServiceFilterResponse response) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
                        }
                    });

                    resultFuture.set(response);
                }
            });

            return resultFuture;
        }
    }

    //####################################################################################Microsoft###################################################################################


    @Override
    public void onRequestPermissionsResult ( int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // If request is cancelled, the result arrays are empty.
            // permission was granted, yay! Do the
            // contacts-related task you need to do.
            // permission denied, boo! Disable the
            // functionality that depends on this permission.
            // other 'case' lines to check for other
            // permissions this app might request

        }
    }

    public void limparcache(){

        String PREFS = "form3";
        SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();

    }


    public void checktrue(){

if(check.isChecked()) {
    String PREFS = "MinhaPreferencia";
    SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
    editor.putString("nomeUsuario", usuario);
    editor.putString("idUsuario", senha);
    editor.putInt("id", 1);
    editor.commit();


}else{

    String PREFS = "MinhaPreferencia";
    SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
    editor.putString("nomeUsuario", "");
    editor.putString("idUsuario", "");
    editor.putInt("id", 0);
    editor.commit();

}

    }

    //metodo de ingreso
    public void validarusuario (View v){
        final String V2 = edtlogin.getText().toString();
        final String V1 = edtpasswd.getText().toString();

        if (V2.trim().isEmpty() || V1.trim().isEmpty()) {

            AlertDialog.Builder dig;
            dig = new AlertDialog.Builder(MainActivity.this);
            dig.setMessage("Preencha os campos");
            dig.setNeutralButton("ok", null);
            dig.show();
        } else {
            AdminSqlLite admin = new AdminSqlLite(this, "Usuarios", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
             usuario = edtlogin.getText().toString();
             senha = edtpasswd.getText().toString();
            checktrue();

            @SuppressLint("Recycle") Cursor fila = db.rawQuery("select nome,senha from usuario where nome='" + usuario + "' and senha='" + senha + "'", null);
            //perguntamos ao cursor se tem algo amazanado no banco de dados
            if (fila.moveToFirst()) {
                //capturamos os valores através do cursor para novas variáveis
                String usua = fila.getString(0);
                String pass = fila.getString(1);
                //preguntamos si los datos ingresados son iguales
                if (usuario.equals(usua) && senha.equals(pass)) {
                    //se são iguais vão para uma nova tela
                    Intent vem = new Intent(this, Form.class);
                    vem.putExtra("login", usua);
                    startActivity(vem);
                    //limpamos as caixas de texto
                    edtlogin.setText("");
                    edtpasswd.setText("");
                    String PREFS = "internet";
                    SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
                    editor.putInt("count",count = 1);
                        editor.commit();
                }
            } else {

                Toast.makeText(this, "Login ou senha inválido!", Toast.LENGTH_SHORT).show();


            }
        }
    }

   /* private void authenticate() {
        // Sign in using the Google provider.
        mClient.login(MobileServiceAuthenticationProvider.WindowsAzureActiveDirectory, "https://vmtandroid.azurewebsites.net", WindowsAzureActiveDirectory_LOGIN_REQUEST_CODE);
    }*/

    private void authenticate() {


        // We first try to load a token cache if one exists.
        if (loadUserTokenCache(mClient))
        {
            createTable();
            String PREFS = "sync";
            SharedPreferences.Editor editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit();
            editor.putInt("contadora", 1);
            editor.commit();


        }
        // If we failed to load a token cache, sign in and create a token cache
        else
        {

            // Sign in using the Google provider.
            mClient.login(MobileServiceAuthenticationProvider.WindowsAzureActiveDirectory, "vmtandroid", WindowsAzureActiveDirectory_LOGIN_REQUEST_CODE);

        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // When request completes
        if (resultCode == RESULT_OK) {
            // Check the request code matches the one we send in the login request
            if (requestCode == WindowsAzureActiveDirectory_LOGIN_REQUEST_CODE) {
                MobileServiceActivityResult result = mClient.onActivityResult(data);
                if (result.isLoggedIn()) {
                    // sign-in succeeded
                    createAndShowDialog(String.format("You are now signed in - %1$2s", mClient.getCurrentUser().getUserId()), "Success");
                    createTable();
                } else {
                    // sign-in failed, check the error message
                    String errorMessage = result.getErrorMessage();
                    createAndShowDialog(errorMessage, "Error");
                }
            }
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // When request completes
        if (resultCode == RESULT_OK) {
            // Check the request code matches the one we send in the sign-in request
            if (requestCode == WindowsAzureActiveDirectory_LOGIN_REQUEST_CODE) {
                MobileServiceActivityResult result = mClient.onActivityResult(data);
                if (result.isLoggedIn()) {
                    // sign-in succeeded
                    createAndShowDialog(String.format("You are now signed in - %1$2s", mClient.getCurrentUser().getUserId()), "Success");
                    cacheUserToken(mClient.getCurrentUser());
                    createTable();
                } else {
                    // sign-in failed, check the error message
                    String errorMessage = result.getErrorMessage();
                    createAndShowDialog(errorMessage, "Error");
                }
            }
        }
    }

    private void cacheUserToken(MobileServiceUser user)
    {
        SharedPreferences prefs = getSharedPreferences(SHAREDPREFFILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USERIDPREF, user.getUserId());
        editor.putString(TOKENPREF, user.getAuthenticationToken());
        editor.commit();
    }

    private boolean loadUserTokenCache(MobileServiceClient client)
    {
        SharedPreferences prefs = getSharedPreferences(SHAREDPREFFILE, Context.MODE_PRIVATE);
        String userId = prefs.getString(USERIDPREF, null);
        if (userId == null)
            return false;
        String token = prefs.getString(TOKENPREF, null);
        if (token == null)
            return false;

        MobileServiceUser user = new MobileServiceUser(userId);
        user.setAuthenticationToken(token);
        client.setCurrentUser(user);

        return true;
    }

}
