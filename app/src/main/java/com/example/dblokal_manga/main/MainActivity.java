package com.example.dblokal_manga.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dblokal_manga.R;
import com.example.dblokal_manga.entity.AppDatabase;
import com.example.dblokal_manga.entity.DataManga;

public class MainActivity extends AppCompatActivity {
    private EditText etNama, etDeskripsi, etGenre ;
    private Button btnSubmit, btnLihat;
    private String setNama, setDeskripsi, setGenre ;

    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.et_nama_manga);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etGenre = findViewById(R.id.et_genre);

        btnSubmit = findViewById(R.id.btn_submit);
        btnLihat = findViewById(R.id.btn_lihat);
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
                Intent submit = new Intent(getApplicationContext(), DaftarManga.class);
                startActivity(submit);
            }
        });
        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lihat = new Intent(getApplicationContext(), DaftarManga.class);
                startActivity(lihat);
            }
        });
    }

    public void input(){
        setNama = etNama.getText().toString();
        setDeskripsi = etDeskripsi.getText().toString();
        setGenre = etGenre.getText().toString();

        final DataManga dataManga = new DataManga();

        dataManga.setNama_manga(setNama);
        dataManga.setDeskripsi(setDeskripsi);
        dataManga.setGenre(setGenre);


        new InsertData(appDatabase, dataManga).execute();
    }

    class InsertData extends AsyncTask<Void, Void, Long> {
        private AppDatabase database;
        private DataManga dataManga;

        public InsertData(AppDatabase database, DataManga dataManga) {
            this.database = database;
            this.dataManga = dataManga;
        }


        @Override
        protected Long doInBackground(Void... voids) {
            return database.dao().insertData(dataManga);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Toast.makeText(getApplicationContext(), "sukses ditambahkan", Toast.LENGTH_SHORT).show();

        }

    }
}