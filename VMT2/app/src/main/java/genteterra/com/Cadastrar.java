package genteterra.com;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastrar extends AppCompatActivity {
private Button cadastro;
private EditText usuario, senha, repsenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        usuario = findViewById(R.id.insuser);
        senha = findViewById(R.id.inssenha);
        repsenha = findViewById(R.id.inssenha2);
        cadastro = findViewById(R.id.cdt);


        cadastro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AdminSqlLite admin = new AdminSqlLite(Cadastrar.this, "Usuarios", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                String user = usuario.getText().toString();
                String senha1 = senha.getText().toString();
                String senha2 = repsenha.getText().toString();
                if(senha1.equals(senha2)){
                    Cursor fila = db.rawQuery("select nome,senha from usuario where nome='" + user + "'", null);

                    if (!fila.moveToFirst()) {
                        ContentValues registro = new ContentValues();
                        registro.put("nome", user);
                        registro.put("senha", senha1);
                        // Inserindo os valores na tabela do db
                        db.insert("usuario", null, registro);
                        db.close();
                        //Limpando os valores
                        usuario.setText("");
                        senha.setText("");
                        repsenha.setText("");
                        Toast.makeText(Cadastrar.this, "Seu login e senha foi cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(Cadastrar.this, MainActivity.class);
                        startActivity(it);
                    }else{
                        Toast.makeText(Cadastrar.this, "Já existe um usuário com o mesmo nome!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Cadastrar.this, "As senhas não correspondem!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


}

