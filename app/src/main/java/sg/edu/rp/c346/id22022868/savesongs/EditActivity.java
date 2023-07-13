package sg.edu.rp.c346.id22022868.savesongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    EditText etID;
    EditText etTitle;
    EditText etSingers;
    EditText etYear;
    RadioGroup rgStars;
    Button btnUpdate;
    Button btnDelete;
    Button btnCancel;
    int id;
    String title;
    String singers;
    int year;
    int stars;
    Song data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        etID.setEnabled(false);


        //receiving Intent from 2nd activity, passing data of lv item selected
        Intent third = getIntent();
        data = (Song) third.getSerializableExtra("data");
        id = third.getIntExtra("id", 0);
        title = third.getStringExtra("title");
        singers = third.getStringExtra("singers");
        year = third.getIntExtra("year", 0);
        stars = third.getIntExtra("stars", 0);

        //setting the et such that it shows the data of the lv item selected
        //etID.setText(Integer.toString(id));
        etTitle.setText(title);
        etSingers.setText(singers);
        etYear.setText(Integer.toString(year));
        rgStars.check(stars);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(EditActivity.this);



                data.setTitle(title);
                data.setSinger(singers);
                data.setYear(year);
                data.setStars(stars);

                //if else statement to get ID of button selected so that we can assign value to that button
                if (rgStars.getCheckedRadioButtonId() == R.id.radioButton) {
                    stars = 1;
                }
                else if (rgStars.getCheckedRadioButtonId() == R.id.radioButton2) {
                    stars = 2;
                }
                else if (rgStars.getCheckedRadioButtonId() == R.id.radioButton3) {
                    stars = 3;
                }
                else if (rgStars.getCheckedRadioButtonId() == R.id.radioButton4) {
                    stars = 4;
                }
                else {
                    stars = 5;
                }

                data.setStars(stars);

                db.updateSong(data);
                db.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(EditActivity.this);


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






    }






















}