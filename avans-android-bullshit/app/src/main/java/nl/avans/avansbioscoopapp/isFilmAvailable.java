package nl.avans.avansbioscoopapp;

import android.telecom.Call;

import nl.avans.avansbioscoopapp.Objects.Film;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface isFilmAvailable {
    @GET("/movie/popular")
    void getPopularMovies(Callback<Film.FilmResult> cb);
    @GET("movie/top_rated")
    Call getTopRatedMovies(@Query("f2a602049196e977fd3fc61a45ffe4ac") String apiKey);
    @GET("movie/{movie_id}/videos")
    Call getMovieTrailer(@Path("movie_id") int id, @Query("f2a602049196e977fd3fc61a45ffe4ac") String apiKey);

    void onFilmAvailable(Film film);
    void onFilmsLoaded();
}