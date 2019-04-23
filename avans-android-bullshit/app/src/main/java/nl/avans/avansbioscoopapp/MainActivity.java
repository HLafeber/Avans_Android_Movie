package nl.avans.avansbioscoopapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nl.avans.avansbioscoopapp.Adapters.MoviesAdapter;
import nl.avans.avansbioscoopapp.Adapters.PageAdapter;
import nl.avans.avansbioscoopapp.Adapters.RecyclerItemClickListener;
import nl.avans.avansbioscoopapp.Fragments.movie_list_main;
import nl.avans.avansbioscoopapp.Fragments.movie_list_user;
import nl.avans.avansbioscoopapp.Objects.Film;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener
        , movie_list_main.OnFragmentInteractionListener, movie_list_user.OnFragmentInteractionListener {

    //Tab stuff
    TabLayout tabLayout;
    TabItem tabMain;
    TabItem tabList;
    ViewPager viewPager;
    RecyclerView recyclerView;
    List<Film> films;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set view
        setContentView(R.layout.activity_main);

        //Tab Initialization
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabMain = (TabItem) findViewById(R.id.tabMain);
        tabList = (TabItem) findViewById(R.id.tabList);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        recyclerView = (RecyclerView) findViewById(R.id.movie_list);

        //init adapter
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        final MoviesAdapter moviesAdapter = new MoviesAdapter(this);
        GridLayoutManager gLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        //Set the adapter to show the movies
        recyclerView.setAdapter(moviesAdapter);
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Pre-fill the array list with movies, will be filled later on
        films = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            films.add(new Film());
        }
        moviesAdapter.setMovieList(films);


        //Create local database, not properly implemented, disabled to prevent crashes
        //AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "MovieList").build();

        //Adapter to get information from the api
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addEncodedQueryParam("api_key", "f2a602049196e977fd3fc61a45ffe4ac");
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        isFilmAvailable service = restAdapter.create(isFilmAvailable.class);
        service.getPopularMovies(new Callback<Film.FilmResult>() {
            @Override
            public void success(Film.FilmResult filmResult, Response response) {
                moviesAdapter.setMovieList(filmResult.getResults());
                films = filmResult.getResults();
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

        //Touch hanlder to detect which item is pressed :: HLafeber
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this.getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                ///Get item information from the adapter
                String title = moviesAdapter.getMovieList().get(position).getName();
                String description = moviesAdapter.getMovieList().get(position).getDescription();
                String image = moviesAdapter.getMovieList().get(position).getPoster();

                ///Create a new intent and fill with extra data
                Intent intent = new Intent(getApplicationContext(), movie_details.class);
                intent.putExtra("image",image);
                intent.putExtra("title",title);
                intent.putExtra("rating",(int) moviesAdapter.getMovieList().get(position).getRating());
                intent.putExtra("description",description);
                ///Kick off the new intent
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //unused.
            }
        }));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //required cause we implement fragments
    }

    //Required cause implementation.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

}
