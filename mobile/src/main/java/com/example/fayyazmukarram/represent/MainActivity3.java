package com.example.fayyazmukarram.represent;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by fayyazmukarram on 2/29/16.
 */
public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ImageButton Rep1 = (ImageButton) findViewById(R.id.seerep1);
        ImageButton Rep2 = (ImageButton) findViewById(R.id.seerep2);
        ImageButton Rep3 = (ImageButton) findViewById(R.id.seerep3);
        ImageButton twitter1 = (ImageButton) findViewById(R.id.imageView11);
        ImageButton twitter2 = (ImageButton) findViewById(R.id.imageView15);
        ImageButton twitter3 = (ImageButton) findViewById(R.id.imageView18);
        ImageButton mail1 = (ImageButton) findViewById(R.id.imageView12);
        ImageButton mail2 = (ImageButton) findViewById(R.id.imageView14);
        ImageButton mail3 = (ImageButton) findViewById(R.id.imageView17);
        ImageButton web1 = (ImageButton) findViewById(R.id.imageView13);
        ImageButton web2 = (ImageButton) findViewById(R.id.imageView16);
        ImageButton web3 = (ImageButton) findViewById(R.id.imageView19);

        Rep1.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent1 = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(myIntent1);
            }
        });
        Rep2.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(MainActivity3.this, MainActivity6.class);
                startActivity(myIntent);
            }
        });
        Rep3.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(MainActivity3.this, MainActivity7.class);
                startActivity(myIntent);
            }
        });
        twitter1.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/DianeFeinstein"));
                startActivity(myIntent);
            }
        });
        twitter2.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/NotMcConnell"));
                startActivity(myIntent);
            }
        });
        twitter3.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/VirgilGoode"));
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
        mail2.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:unisbarakat@gmail.com"));
                startActivity(myIntent);
            }
        });
        mail3.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:nabeelsaleem@hotmail.com"));
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
        web2.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mcconnell.senate.gov/public"));
                startActivity(myIntent);
            }
        });
        web3.setOnClickListener(new View.OnClickListener() {
            // When the button is pressed/clicked, it will run the code below
            @Override
            public void onClick(View v) {
                // Intent is what you use to start another activity
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Virgil_Goode"));
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