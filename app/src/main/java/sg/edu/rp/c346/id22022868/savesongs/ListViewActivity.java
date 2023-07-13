package sg.edu.rp.c346.id22022868.savesongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {



    Button btnShowAll5;
    Spinner spinnerYear;
    ListView lvSongs;
    ArrayList<Song> songs;
    ArrayAdapter aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        btnShowAll5 = findViewById(R.id.button);
        //spinnerYear = findViewById(R.id.spinner);
        lvSongs = findViewById(R.id.listView);

        //setting listview components
        songs = new ArrayList<Song>();
        aa = new ArrayAdapter(ListViewActivity.this, android.R.layout.simple_list_item_1, songs);
        lvSongs.setAdapter(aa);

        // Create the DBHelper object, passing in the activity's Context
        DBHelper db = new DBHelper(ListViewActivity.this);

        songs.clear();

        //Adding Song objects to the arraylist
        songs.addAll(db.getSongDetails());

        //Closing database connection
        db.close();


        btnShowAll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //for loop to get stars
                //if-else statement to get those with 5
                //add() to songs list if == 5

                /*ArrayList<Song> rated5 = new ArrayList<Song>();

                for (int i = 0; i < songs.size(); i++) {
                    int starNo = songs.get(i).getStar();

                    if (starNo == 5) {
                        rated5.add(songs.get(i));
                        Log.i("added", "5* songs were added");
                    }
                }*/

                songs.clear();

                songs.addAll(db.filterSongStars(5));

                //songs = rated5;

                aa.notifyDataSetChanged();
            }
        });

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Song target = songs.get(position);

                int passId = songs.get(position).getId();
                String passTitle = songs.get(position).getTitle();
                String passSingers = songs.get(position).getSingers();
                int passYear = songs.get(position).getYear();
                int passStars = songs.get(position).getStar();

                Intent third = new Intent(ListViewActivity.this, EditActivity.class);

                third.putExtra("data", (Serializable) target);
                third.putExtra("id", passId);
                third.putExtra("title", passTitle);
                third.putExtra("singers", passSingers);
                third.putExtra("year", passYear);
                third.putExtra("stars", passStars);

                startActivity(third);

            }
        });












    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper db = new DBHelper(ListViewActivity.this);

        songs.clear();

        songs.addAll(db.getSongDetails());

        aa.notifyDataSetChanged();

    }
}