package nl.avans.avansbioscoopapp;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

public class movie_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        /*
            Try getting the image from the url, catch any exception and prevent crashing.
         */
        try {
            //Set request url from intent
            String url = getIntent().getStringExtra("image");
            //define image view where it should go
            ImageView imageView = (ImageView) findViewById(R.id.movieView);
            // get image from url and load them in
            Picasso.get()
                    .load(url)
                    .placeholder(R.color.colorAccent)
                    .into(imageView);

        }
        catch (Exception ex)
        {
            //used for debugging, just breakpoint it.
            ex = ex;
        }
        /*
        Try parsing the title from the intent, and try putting it in the box,
        If the title we get from the calling intent is smaller than 2, we'll throw an error
        because the data is most likely garbled.
         */
        TextView movieTitle = (TextView) findViewById(R.id.movieTitle);
        try {
            String title = (String) getIntent().getStringExtra("title");
            if (title.length() < 2)
            {
                throw new Exception("Title too short. Assuming no title received");
            }
            movieTitle.setText(title);
        }
        catch (Exception ex)
        {
            movieTitle.setText("Could not fetch the title.");
        }
        /*
        Try parsing the description of the movie,
        if the received data is < 2 we'll throw an error because its most likely
        garbled data.
         */
        TextView movieDescription = (TextView) findViewById(R.id.movieDescription);
        try{
            String description = (String) getIntent().getStringExtra("description");
            if (description.length() < 2)
            {
                throw new Exception("Description too short. Assuming no title received");
            }
            movieDescription.setText(description);
        }
        catch (Exception ex)
        {
            movieDescription.setText("Could not fetch description");
        }
        /*
        Parse rating information, the rating is technically a double, but we're parsing it as an
        int instead. That said, the intent string can still be a string, so we've wrapped it
        around a try catch to prevent exceptions.
         */
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setMax(5);
        ratingBar.setNumStars(5);
        int rating;
        try {
            rating = Integer.parseInt(getIntent().getStringExtra("rating"));
        }
        catch (Exception ex)
        {
            rating = 0;
        }
        ratingBar.setRating(rating);
    }
}
