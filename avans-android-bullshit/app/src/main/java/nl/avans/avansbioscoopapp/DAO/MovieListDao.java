package nl.avans.avansbioscoopapp.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import nl.avans.avansbioscoopapp.Objects.MovieList;

@Dao
public interface MovieListDao {
    @Query("SELECT * FROM movielist")
    List<MovieList> getAll();

    @Query("SELECT * FROM movielist WHERE uid IN (:movieIds)")
    List<MovieList> loadAllByIds(int[] movieIds);

    @Insert
    void insertAll(MovieList... movieLists);

    @Insert
    void insert(MovieList movieList);

    @Delete
    void delete(MovieList movieList);

}
