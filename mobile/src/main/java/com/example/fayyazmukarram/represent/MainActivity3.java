package com.example.fayyazmukarram.represent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by fayyazmukarram on 2/29/16.
 */
public class MainActivity3 extends Activity {

    ExpandableListAdapter adapter;
    ExpandableListView expListView;
    ArrayList<Storage> fill_time;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        expListView = (ExpandableListView) findViewById(R.id.exp);

        //Get Extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras.get("fill") != null) {

            ArrayList<Storage> fill_time = (ArrayList<Storage>) extras.get("fill");
            this.fill_time = fill_time;
            // setting list adapter
            if ( extras.get("start_activity") != null) {
//                System.out.println(extras.get("m"));
//                System.out.println(_header.get(0).position + " " + _header.get(0).name);

                if (extras.get("start_activity").equals(fill_time.get(0).position + " " + fill_time.get(0).name)) {
                    this.position = 0;
                } else if (extras.get("start_activity").equals(fill_time.get(1).position + " " + fill_time.get(1).name)) {
                    this.position = 1;
                } else if (extras.get("start_activity").equals(fill_time.get(2).position + " " + fill_time.get(2).name)) {
                    this.position = 2;
                } else {
                    this.position = -1;
                }
            }
                adapter = new CustomAdapter(this, this.fill_time, this.position);
                expListView.setAdapter(adapter);

            }



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