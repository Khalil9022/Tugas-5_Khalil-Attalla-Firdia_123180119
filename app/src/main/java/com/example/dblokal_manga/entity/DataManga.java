package com.example.dblokal_manga.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "manga_db")

public class DataManga {
    @NonNull
    @PrimaryKey(autoGenerate = true) //menandakan primary key yaitu id
    @ColumnInfo(name = "id") //info buat atribut dalam database
    private int id; // tipe data dari masing masin gatributnya

    @ColumnInfo(name = "nama_manga")
    private String nama_manga;

    @ColumnInfo(name = "deskripsi")
    private String deskripsi;

    @ColumnInfo(name = "genre")
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_manga() {
        return nama_manga;
    }

    public void setNama_manga(String nama_manga) {
        this.nama_manga = nama_manga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
