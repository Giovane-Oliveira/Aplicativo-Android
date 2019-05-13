package genteterra.com;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import android.database.sqlite.SQLiteDatabase;


public class Form extends AppCompatActivity {
    private EditText data, horainicio, horafim, pontoref, id;
    Calendar myCalendar = Calendar.getInstance();
    Cursor c, d, e;
    private String login, sdraga, sempresa, smineradora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        final Spinner spinner1 = findViewById(R.id.spinner1);
        final Spinner spinner2 = findViewById(R.id.spinner2);
        final Spinner spinner3 = findViewById(R.id.spinner3);
        id = findViewById(R.id.idd);
        data = findViewById(R.id.edtform1_0);
        horainicio = findViewById(R.id.edtform1_1);
        horafim = findViewById(R.id.edtform1_2);
        pontoref = findViewById(R.id.edtform1_3);
        Button voltar = findViewById(R.id.btnvoltar);
        Button proximo = findViewById(R.id.btnproximo);

        Bundle bundle = getIntent().getExtras();
        login = bundle.getString("login");


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

               int Hold = spinner1.getSelectedItemPosition()  ;
                sdraga = (String) spinner1.getItemAtPosition(Hold);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                int count = spinner2.getSelectedItemPosition()  ;
                sempresa = (String) spinner2.getItemAtPosition(count);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                int pos = spinner3.getSelectedItemPosition()  ;
                smineradora = (String) spinner3.getItemAtPosition(pos);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        //Selecao de dragas spinner1
        List draga = new ArrayList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, draga);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);


        //Selecao de empresas spinner2
        List empresa;
        empresa = new ArrayList();
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, empresa);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter1);


        //Selecao de mineradoras spinner3
        List mineradora;
        mineradora = new ArrayList();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, mineradora);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter2);

        AdminSqlLite admin = new AdminSqlLite(this, "adm", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        c = db.rawQuery("select nome from draga", null);
        d = db.rawQuery("select nome from empresa", null);
        e = db.rawQuery("select nome from mineradora", null);

        if (c.moveToFirst() && d.moveToFirst() && e.moveToFirst()) {
            do {
                dataAdapter.add(c.getString(0));
                dataAdapter1.add(d.getString(0));
                dataAdapter2.add(e.getString(0));
            }
            while (c.moveToNext() && d.moveToNext() && e.moveToNext());
            if (db != null) {
                db.close();
            }
        }


        horafim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Form.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        horafim.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }

        });

        horainicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Form.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        horainicio.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            private void updateLabel() {

                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

                data.setText(sdf.format(myCalendar.getTime()));
            }
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        data.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(Form.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        proximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String Id = id.getText().toString();
                String Data = data.getText().toString();
                String Hrinicio = horainicio.getText().toString();
                String Hrfim = horafim.getText().toString();
                String Pontoref = pontoref.getText().toString();
                if (Data.trim().isEmpty() || Hrinicio.trim().isEmpty() || Hrfim.trim().isEmpty() || Pontoref.trim().isEmpty() ||
                Id.trim().isEmpty()) {
                    AlertDialog.Builder dig;
                    dig = new AlertDialog.Builder(Form.this);
                    dig.setMessage("Preencha os campos!");
                    dig.setNeutralButton("ok", null);
                    dig.show();
                } else {
                    Intent it = new Intent(Form.this, obs.class);
                    it.putExtra("ID", Id);
                    it.putExtra("LOGIN", login);
                    it.putExtra("DRAGA", sdraga);
                    it.putExtra("EMPRESA", sempresa);
                    it.putExtra("MINERADORA", smineradora);
                    it.putExtra("DATA", Data);
                    it.putExtra("HRINICIO", Hrinicio);
                    it.putExtra("HRFIM", Hrfim);
                    it.putExtra("PONTOREF", Pontoref);
                    startActivity(it);
                }

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


    }



}
