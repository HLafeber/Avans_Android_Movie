package nl.avans.avansbioscoopapp.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Film implements Serializable, Parcelable {
    private String name;
    @SerializedName("poster_path")
    private String posterUrl;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("genre_ids")
    private List genreIds = new ArrayList();
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String description;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("vote_average")
    private double rating;
    @SerializedName("runtime")
    private String runTime;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("video")
    private Boolean video;

    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";


    public Film(){

    }

    public Film(String name, String posterUrl, String description, String backdrop_path, String releaseDate, float rating, String runTime, String tagline, String homepage) {
        this.name = name;
        this.posterUrl = posterUrl;
        this.description = description;
        this.backdrop_path = backdrop_path;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.runTime = runTime;
        this.tagline = tagline;
        this.homepage = homepage;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public static String getTmdbImagePath() {
        return TMDB_IMAGE_PATH;
    }

    public String getName() {
        return originalTitle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return TMDB_IMAGE_PATH + posterUrl;
    }


    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return TMDB_IMAGE_PATH  + id;
    }
    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (!name.equals(film.name)) return false;
        if (!posterUrl.equals(film.posterUrl)) return false;
        if (!description.equals(film.description)) return false;
        return backdrop_path.equals(film.backdrop_path);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + posterUrl.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + backdrop_path.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", description='" + description + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterUrl);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.description);
        dest.writeString(this.releaseDate);
        dest.writeList(this.genreIds);
        dest.writeValue(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.title);
        dest.writeString(this.backdrop_path);
        dest.writeValue(this.popularity);
        dest.writeValue(this.voteCount);
        dest.writeValue(this.video);
        dest.writeValue(this.rating);
    }

    protected Film(Parcel in) {
        this.posterUrl = in.readString();
        this.adult = in.readByte() != 0;
        this.description = in.readString();
        this.releaseDate = in.readString();
        this.genreIds = new ArrayList();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.rating = (Double) in.readValue(Double.class.getClassLoader());
    }
    public static class FilmResult {
        private List<Film> results;

        public List<Film> getResults() {
            return results;
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };


}