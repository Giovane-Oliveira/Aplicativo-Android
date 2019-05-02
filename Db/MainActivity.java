package com.example.bancodedados;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private EditText id, nome, telefone, endereco;
private Button editar, consultar, deletar, adicionar;
private Cursor linha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.edt1);
        nome = findViewById(R.id.edt2);
        telefone = findViewById(R.id.edt3);
        endereco = findViewById(R.id.edt4);
        editar = findViewById(R.id.edit);
        consultar = findViewById(R.id.cons);
        deletar = findViewById(R.id.del);
        adicionar = findViewById(R.id.add);
    }

    public void cadastro(View v){
        AdminSqlLite admin = new AdminSqlLite(this,"Administracao",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String Codigo = id.getText().toString();
        String Nome = nome.getText().toString();
        String Telefone = telefone.getText().toString();
        String Endereco = endereco.getText().toString();

        Cursor linha = db.rawQuery("select * from usuarios where id=" + Codigo, null);

        if(!linha.moveToFirst()){

            // Passando os valores para a tabela

            ContentValues registro = new ContentValues();
            registro.put("id", Codigo);
            registro.put("nome", Nome);
            registro.put("telefone", Telefone);
            registro.put("endereco", Endereco);

            // Inserindo os valores na tabela do db
            db.insert("usuarios", null, registro);
            db.close();

            // Limpando os campos
            id.setText("");
            nome.setText("");
            telefone.setText("");
            endereco.setText("");

            // Mensagem de cadastro com sucesso

            Toast.makeText(this, "Os dados foram salvos com sucesso", Toast.LENGTH_SHORT).show();

        } else{

            db.close();
            Toast.makeText(this, "Este cadastro já existe", Toast.LENGTH_SHORT).show();

        }

    }

    public void consulta(View v) {
        AdminSqlLite admin = new AdminSqlLite(this, "Administracao", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String Codigo = id.getText().toString();

        Cursor linha = db.rawQuery("select nome, telefone, endereco from usuarios where id=" + Codigo, null);
        if(linha.moveToFirst()) {
            nome.setText(linha.getString(0));
            telefone.setText(linha.getString(1));
            endereco.setText(linha.getString(2));

        } else {
            Toast.makeText(this, "Não existe esse cadastro", Toast.LENGTH_SHORT).show();
                db.close();

        }

    }

    public void deletar(View v) {
        AdminSqlLite admin = new AdminSqlLite(this, "Administracao", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String Codigo = id.getText().toString();
        int quant = db.delete("usuarios", "id=" + Codigo, null);
        db.close();

        // Limpando os campos
        id.setText("");
        nome.setText("");
        telefone.setText("");
        endereco.setText("");

        if(quant == 1){

            Toast.makeText(this, "O usuário foi removido", Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(this, "Não existe esse cadastro", Toast.LENGTH_SHORT).show();
        }

    }
    public void editar(View v) {
        AdminSqlLite admin = new AdminSqlLite(this, "Administracao", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String Codigo = id.getText().toString();
        String Nome = nome.getText().toString();
        String Telefone = telefone.getText().toString();
        String Endereco = endereco.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("id", Codigo);
        registro.put("nome", Nome);
        registro.put("telefone", Telefone);
        registro.put("endereco", Endereco);
        int quant = db.update("usuarios", registro, "id=" + Codigo, null);
        db.close();
        if(quant == 1){
            Toast.makeText(this, "Os dados foram modificados", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Não existe esse cadastro", Toast.LENGTH_SHORT).show();

        }
    }
        }





