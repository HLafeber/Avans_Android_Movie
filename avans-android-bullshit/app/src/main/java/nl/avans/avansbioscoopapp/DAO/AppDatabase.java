package nl.avans.avansbioscoopapp.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import nl.avans.avansbioscoopapp.Objects.MovieList;

@Database(entities = {MovieList.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieListDao movieListDao();
}
