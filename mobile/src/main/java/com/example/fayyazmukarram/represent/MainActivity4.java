package com.example.fayyazmukarram.represent;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by fayyazmukarram on 2/29/16.
 */
public class MainActivity4 extends AppCompatActivity {

    ImageView dianeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_more);

//        String[] committees = {"Budget and Fiscal Review, Chair","Environmental Quality","Judiciary","Labor and Industrial Relations","Legislative Ethics"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(),android.R.layout.simple_list_item_1,committees);
//        getListView().setAdapter(adapter);

//        String[] bills = {"Budget and Fiscal Review, Chair","Environmental Quality","Judiciary","Labor and Industrial Relations","Legislative Ethics"};
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getListView().getContext(),android.R.layout.simple_list_item_2,bills);
//        getListView().setAdapter(adapter2);
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        dianeButton = (ImageButton) findViewById(R.id.imageView21);
//
//
//        if (extras != null) {
//            String repName = extras.getString("REP_NAME");
//            dianeButton.setImageDrawable(getResources().getDrawable(R.drawable.diane));
//
//        }

        ImageButton twitter1 = (ImageButton) findViewById(R.id.imageButton);

        ImageButton mail1 = (ImageButton) findViewById(R.id.imageButton2);

        ImageButton web1 = (ImageButton) findViewById(R.id.imageButton3);

        twitter1.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/DianeFeinstein"));
                startActivity(myIntent);
            }
        });

        mail1.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:senator@feinstein.senate.gov"));
                startActivity(myIntent);
            }
        });

        web1.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.feinstein.senate.gov/public"));
                startActivity(myIntent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}