package com.example.dblokal_manga.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataManga.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataMangaDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase iniDb(Context context){
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "mangadb").allowMainThreadQueries().build();
        return appDatabase;
    }

    public static void destroyInstance(){
        appDatabase = null; }
}
