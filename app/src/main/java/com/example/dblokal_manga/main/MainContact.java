package com.example.dblokal_manga.main;

import android.view.View;

import com.example.dblokal_manga.entity.AppDatabase;
import com.example.dblokal_manga.entity.DataManga;

import java.util.List;

public interface MainContact {

    interface view extends View.OnClickListener{
        void resetForm();
        void sukses();
        void editData(DataManga item);
    }
    interface datapresenter{
        void editData(String nama_manga, String deskripsi, String genre, int id, AppDatabase database);
        void deleteData(DataManga dataManga, AppDatabase database);
    }
    interface Cetak extends View.OnClickListener{
        void getData(List<DataManga> list);
    }
    interface hapus{
        // void resetForm();
        void sukses();
        void deleteData(DataManga item);
    }

}
