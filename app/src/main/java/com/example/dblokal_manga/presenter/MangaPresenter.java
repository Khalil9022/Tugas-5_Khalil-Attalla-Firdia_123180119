package com.example.dblokal_manga.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dblokal_manga.entity.AppDatabase;
import com.example.dblokal_manga.entity.DataManga;
import com.example.dblokal_manga.main.MainContact;

public class MangaPresenter implements MainContact.datapresenter{

    MainContact.view view;
    MainContact.hapus viewH;

    public MangaPresenter(MainContact.view view) {
        this.view = view;
    }

    public MangaPresenter(MainContact.hapus viewH) {
        this.viewH = viewH;
    }

    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase database;
        private DataManga dataManga;
        public EditData(AppDatabase database, DataManga dataManga) {
            this.database = database;
            this.dataManga = dataManga;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.dao().updateData(dataManga);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            view.sukses();
        }
    }

    @Override
    public void editData(String nama_manga, String deskripsi, String genre, int id, AppDatabase database) {
        final DataManga dataManga = new DataManga();
        dataManga.setNama_manga(nama_manga);
        dataManga.setDeskripsi(deskripsi);
        dataManga.setGenre(genre);
        dataManga.setId(id);
        new EditData(database, dataManga).execute();
    }

    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        private DataManga dataManga;
        Context context;
        public DeleteData(AppDatabase database, DataManga dataManga) {
            this.database = database;
            this.dataManga = dataManga;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataManga);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewH.sukses();
        }

    }

    @Override
    public void deleteData(DataManga dataManga, AppDatabase database) {
        new DeleteData(database,dataManga).execute();
    }
}
