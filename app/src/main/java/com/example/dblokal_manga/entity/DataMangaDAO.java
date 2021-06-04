package com.example.dblokal_manga.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataMangaDAO {
    @Insert
        //fungsi insert
    long insertData(DataManga dataManga); //nantinya dia akan ambil dari kelas data sekolah

    @Query("Select * from manga_db") //untuk memanggil seluruh data yg ada di db
    List<DataManga> getData(); //si db itu ngambil datanya dari list yang ada di data sekolah

    @Update
        //fungsi edit data
    int updateData(DataManga item);

    @Delete
        //fungsi hapus data
    void deleteData(DataManga item);
}
