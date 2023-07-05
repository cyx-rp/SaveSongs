package sg.edu.rp.c346.id22022868.savesongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    //Increases by 1 when db schema changes
    private static final int DATABASE_VER = 1;

    //Defines filename of the database
    private static final String DATABASE_NAME = "songs.db";

    //Adding the columns
    private static final String TABLE_SONG = "Song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //This creates the SQL table and executes it
        String createTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_STARS + " INTEGER)";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
        onCreate(db);

    }

    public void insertSong(String title, String singers, int year, int stars) {

        // Get an instance of the database for writing
        //this line creates the database
        SQLiteDatabase db = this.getWritableDatabase();

        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();

        // Store the column name as key and the title as value
        values.put(COLUMN_TITLE, title);

        // Store the column name as key and the singers as value
        values.put(COLUMN_SINGERS, singers);

        // Store the column name as key and the year as value
        values.put(COLUMN_YEAR, year);

        // Store the column name as key and the date as value
        values.put(COLUMN_STARS, stars);

        // Insert the row into the TABLE_TASK
        db.insert(TABLE_SONG, null, values);

        // Close the database connection
        db.close();
    }

    public ArrayList<Song> getSongDetails() {

        ArrayList<Song> tasks = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};

        //Creating the object
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);

        //moveToFirst moves to the first row of the table
        //it takes in boolean
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song obj = new Song(title, singers, year, stars);
                tasks.add(obj);
                //moveToNext proceeds to the next row
            } while (cursor.moveToNext());
        }

        //Closing the db connection
        cursor.close();
        db.close();
        return tasks;

    }
































}
