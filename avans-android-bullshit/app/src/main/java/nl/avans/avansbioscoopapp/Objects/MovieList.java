package nl.avans.avansbioscoopapp.Objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MovieList {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "list_name")
    public String listName;
}
