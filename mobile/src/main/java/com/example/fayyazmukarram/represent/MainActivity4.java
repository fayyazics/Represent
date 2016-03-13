package com.example.fayyazmukarram.represent;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by fayyazmukarram on 2/29/16.
 */
public class MainActivity4 extends AppCompatActivity {

    ListView bills_lv;
    ListView coms_lv;

    private List<Storage> _listDataHeader;
    private Context _context;

    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_more);

        bills_lv=(ListView) findViewById(R.id.listView2);
        coms_lv=(ListView) findViewById(R.id.listView2);
        }




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


//        ImageButton twitter1 = (ImageButton) findViewById(R.id.imageButton);
//        twitter1.setOnClickListener(new View.OnClickListener() {
//            // When the button is pressed/clicked, it will run the code below
//            @Override
//            public void onClick(View v) {
//                // Intent is what you use to start another activity
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/DianeFeinstein"));
//                startActivity(myIntent);
//            }
//        });
//
//        ImageButton mail1 = (ImageButton) findViewById(R.id.imageButton2);
//        mail1.setOnClickListener(new View.OnClickListener() {
//            // When the button is pressed/clicked, it will run the code below
//            @Override
//            public void onClick(View v) {
//                // Intent is what you use to start another activity
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:senator@feinstein.senate.gov"));
//                startActivity(myIntent);
//            }
//        });
//
//        ImageButton web1 = (ImageButton) findViewById(R.id.imageButton3);
//        web1.setOnClickListener(new View.OnClickListener() {
//            // When the button is pressed/clicked, it will run the code below
//            @Override
//            public void onClick(View v) {
//                // Intent is what you use to start another activity
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.feinstein.senate.gov/public"));
//                startActivity(myIntent);
//            }
//        });
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
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        //Setting Committies List
//        ListView comListView = (ListView) convertView.findViewById(R.id.committiesList);
//        ArrayAdapter<String> listAdapter;
//        listAdapter = new ArrayAdapter<String>(_context, R.layout.list_committies,R.id.comTitle, this._listDataHeader.get(groupPosition).comList);
//        comListView.setAdapter(listAdapter);
//
//        //Setting Bills List
//        ListView bListView = (ListView) convertView.findViewById(R.id.billsList);
//        ArrayAdapter<String> bListAdapter;
//        bListAdapter = new ArrayAdapter<String>(_context, R.layout.list_bills,R.id.bill_title, this._listDataHeader.get(groupPosition).bList);
//        bListView.setAdapter(bListAdapter);
//
//
//
//        Utility.setListViewHeightBasedOnChildren(comListView);
//        Utility.setListViewHeightBasedOnChildren(bListView);
////        //set image
////        ImageView img = (ImageView) findViewById(R.id.image);
////        new CustomAdapter.ImageLoadTask(this._listDataHeader.get(position).image, img).execute();
////
////        //set name
////        TextView name = (TextView) findViewById(R.id.textView7);
////        name.setText(this._listDataHeader.get(position).position + " " + this._listDataHeader.get(position).name);
////
////        //set party
////        TextView party = (TextView) findViewById(R.id.textView8);
////        party.setText(this._listDataHeader.get(position).party);
////
////        //set web
////        ImageButton web = (ImageButton) findViewById(R.id.imageButton3);
////        web.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String url = _listDataHeader.get(position).web;
////                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
////                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////                startActivity(intent);
////            }
////        });
////        //set twitter
////        ImageButton twitter = (ImageButton) findViewById(R.id.imageButton);
////        twitter.setOnClickListener(new View.OnClickListener() {
////
////            //fix it where it also opens twitter if app installed
////            @Override
////            public void onClick(View v) {
////                String url = _listDataHeader.get(position).twitter;
////                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
////                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////                startActivity(intent);
////            }
////        });
    //        return convertView;
//    }

//    //set email
//    ImageButton email = (ImageButton) findViewById(R.id.imageButton2);
//    email.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//                /* Create the Intent */
//            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//                 /* Fill it with Data */
//            emailIntent.setType("plain/text");
//            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{_listDataHeader.get(position).email});
//                /* Send it off to the Activity-Chooser */
//            startActivity(Intent.createChooser(emailIntent, ""));
//
//        }
//    });
}