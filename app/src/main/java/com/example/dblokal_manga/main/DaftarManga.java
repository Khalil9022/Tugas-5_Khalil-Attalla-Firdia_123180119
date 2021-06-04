package com.example.dblokal_manga.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dblokal_manga.R;
import com.example.dblokal_manga.adapter.MangaAdapter;
import com.example.dblokal_manga.entity.AppDatabase;
import com.example.dblokal_manga.entity.DataManga;
import com.example.dblokal_manga.presenter.MangaPresenter;

import java.util.List;

public class DaftarManga extends AppCompatActivity implements MainContact.hapus{

    private AppDatabase appDatabase;
    private MangaAdapter mangaAdapter;
    private MangaPresenter mangaPresenter;
    View view;
    RecyclerView recyclerView;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mangaPresenter = new MangaPresenter(this);
        recyclerView = findViewById(R.id.rc_main);
        iv_back = findViewById(R.id.iv_back);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        readData(appDatabase);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarManga.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void readData(AppDatabase appDatabase) {
        List list;
        list = appDatabase.dao().getData();
        //view.getData(list);
        mangaAdapter = new MangaAdapter(getApplicationContext(), list, this);
        recyclerView.setAdapter(mangaAdapter);
    }

    @Override
    public void sukses() {
        Toast.makeText(getApplicationContext(), "Data Berhasil di hapus", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), DaftarManga.class));
    }

    @Override
    public void deleteData(DataManga item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // resetForm();
                        mangaPresenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}