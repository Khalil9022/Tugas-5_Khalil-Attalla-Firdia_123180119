package com.example.dblokal_manga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dblokal_manga.adapter.MangaAdapter;
import com.example.dblokal_manga.entity.AppDatabase;
import com.example.dblokal_manga.entity.DataManga;
import com.example.dblokal_manga.main.DaftarManga;
import com.example.dblokal_manga.main.MainContact;
import com.example.dblokal_manga.presenter.MangaPresenter;

public class EditManga extends AppCompatActivity implements MainContact.view {
    private AppDatabase appDatabase;
    private MangaPresenter mangaPresenter;
    private MangaAdapter mangaAdapter;
    private EditText etNama, etDeskripsi, etGenre ;
    private Button btnSubmit;
    private String setNama, setDeskripsi, setGenre;
    private boolean edit = false;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_manga);

        etNama = findViewById(R.id.et_nama_manga);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etGenre = findViewById(R.id.et_genre);

        btnSubmit = findViewById(R.id.btn_submit);
        mangaPresenter = new MangaPresenter(this);
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        setNama = getIntent().getStringExtra("nama");
        setDeskripsi = getIntent().getStringExtra("deskripsi");
        setGenre = getIntent().getStringExtra("genre");

        id = getIntent().getIntExtra("id", 99);

        etNama.setText(setNama);
        etDeskripsi.setText(setDeskripsi);
        etGenre.setText(setGenre);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void resetForm() {
        etNama.setText("");
        etDeskripsi.setText("");
        etGenre.setText("");
        btnSubmit.setText("Submit");
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), DaftarManga.class));
    }

    @Override
    public void editData(DataManga item) {
        etNama.setText(item.getNama_manga());
        etDeskripsi.setText(item.getDeskripsi());
        etGenre.setText(item.getGenre());

        edit = true;
        btnSubmit.setText("Update");
    }

    @Override
    public void onClick(View v) {
        String NamaManga, Deskripsi, Genre;
        NamaManga = etNama.getText().toString();
        Deskripsi = etDeskripsi.getText().toString();
        Genre = etGenre.getText().toString();
        if(v ==  btnSubmit){
            if(NamaManga.equals("") || Deskripsi.equals("") || Genre.equals("")) {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {

                mangaPresenter.editData(NamaManga,Deskripsi,Genre, id, appDatabase);
                edit = false;
            }
            resetForm();
        }
    }
}