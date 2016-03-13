package com.example.fayyazmukarram.represent;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.twitter.sdk.android.core.TwitterException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by fayyazmukarram on 2/29/16.
 */
public class MainActivity2 extends AppCompatActivity implements LocationListener {

    private Button locationButton;
    public LocationManager locationManager;
    public static ArrayList<Storage> listDataHeader;

    Double latitude;
    Double longitude;
    String provider;
    String profileImage;
    ProgressDialog progress;

    //Getting Data from URL
    public class ReverseGeocoding extends AsyncTask {

        private Address address;
        private String GEOCODINGKEY = "&key=AIzaSyAz4wZywF62ebKn8cbHTmOqlaDy-wK3n2Q";
        private String REVERSE_GEOCODING_URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";
        Context context;

        public ReverseGeocoding(Context context) {
            this.context = context;
        }

        private StringBuilder readResponse(InputStream inputStream) throws IOException, NullPointerException {
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            if (params[0] != null) {
                String result = "";
                try {
                    String mUrl = REVERSE_GEOCODING_URL + params[0] + ","
                            + params[1] + GEOCODINGKEY;
                    URL url = new URL(mUrl);
                    HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                    httpsURLConnection.setReadTimeout(20000);
                    httpsURLConnection.setConnectTimeout(30000);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.connect();
                    int mStatus = httpsURLConnection.getResponseCode();
                    if (mStatus == 200)
                        return readResponse(httpsURLConnection.getInputStream()).toString();
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progress = ProgressDialog.show(MainActivity2.this, "Preparing Data",
//                    "Fetching data ....", true);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            progress.dismiss();
        }
    }

    //GET Congress GPS Info
    public class getCongress extends AsyncTask {
        Context context;
        private Address address;
        private String KEY = "&apikey=4c5a600ad22949c4b1678a00907461cb";
        private String URL = "https://congress.api.sunlightfoundation.com/legislators/locate?latitude=";

        public getCongress(Context context) {
            this.context = context;
        }

        private StringBuilder readResponse(InputStream inputStream) throws IOException, NullPointerException {
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder;
        }


        @Override
        protected Object doInBackground(Object[] params) {
            if (params[0] != null) {
                String result = "";
                try {
                    String mUrl = URL + params[0] + "&longitude="
                            + params[1] + KEY;

                    URL url = new URL(mUrl);
                    HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                    httpsURLConnection.setReadTimeout(20000);
                    httpsURLConnection.setConnectTimeout(30000);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.connect();
                    int mStatus = httpsURLConnection.getResponseCode();
                    if (mStatus == 200)
                        return readResponse(httpsURLConnection.getInputStream()).toString();

                    return result;

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progress = ProgressDialog.show(MainActivity2.this, "Preparing Data",
//                    "Fetching data ....", true);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            progress.dismiss();
        }
    }

    //GET Congress ZIP Info
    public class getCongressZIP extends AsyncTask {
        Context context;
        private String KEY = "&apikey=4c5a600ad22949c4b1678a00907461cb";
        private String URL = "https://congress.api.sunlightfoundation.com/legislators/locate?zip=";

        public getCongressZIP(Context context) {
            this.context = context;
        }


        private StringBuilder readResponse(InputStream inputStream) throws IOException, NullPointerException {
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder;
        }


        @Override
        protected Object doInBackground(Object[] params) {
            if (params[0] != null) {
                String result = "";
                try {
                    String mUrl = URL + params[0] + KEY;

                    URL url = new URL(mUrl);
                    HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                    httpsURLConnection.setReadTimeout(20000);//changed from 10000
                    httpsURLConnection.setConnectTimeout(30000); //changed from 15000
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.connect();
                    int mStatus = httpsURLConnection.getResponseCode();
                    if (mStatus == 200)
                        return readResponse(httpsURLConnection.getInputStream()).toString();

                    return result;

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            return null;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progress = ProgressDialog.show(MainActivity2.this, "Preparing Data",
//                    "Fetching data ....", true);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            progress.dismiss();
        }
    }

    //GET TWITTER INFO
    public class getTweets extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("M5dDv4YwQLglds2uXRxGqqYYF")
                    .setOAuthConsumerSecret(
                            "4r0gIMzE70tjSe7xoaMzajHGboxygEM4xCjTqBt11P3hM0KykM")
                    .setOAuthAccessToken(
                            "1138156074-b3ssD96MvqpvBCllTP9RHA3JGZLDFHuciyv2qe4")
                    .setOAuthAccessTokenSecret(
                            "RHWuIfhNLcCeVs8CxRzknKpYbJAOu1T9HJlnMMy7oNwj8");
            TwitterFactory tf = new TwitterFactory(cb.build());
            twitter4j.Twitter twitter = tf.getInstance();
            Long id = null;

            try {
                List<twitter4j.Status> statuses;
                String user;
                user = (String) params[0];
                id = null;
                try {
                    statuses = twitter.getUserTimeline(user);

                    if (statuses.size() > 0) {
                        id = statuses.get(0).getId();
                        profileImage = statuses.get(0).getUser().getOriginalProfileImageURL();
                    }

                } catch (twitter4j.TwitterException e) {
                    e.printStackTrace();
                }

            } catch (TwitterException te) {
                te.printStackTrace();
            }

            return id;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progress = ProgressDialog.show(MainActivity2.this, "Preparing Data",
//                    "Fetching data ....", true);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            progress.dismiss();
        }
    }

    //GET Bills
    public class getBills extends AsyncTask {
        Context context;
        private Address address;
        private String KEY = "&apikey=4c5a600ad22949c4b1678a00907461cb";
        private String URL = "https://congress.api.sunlightfoundation.com/bills?sponsor_id=";

        public getBills(Context context) {
            this.context = context;
        }


        private StringBuilder readResponse(InputStream inputStream) throws IOException, NullPointerException {
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder;
        }


        @Override
        protected Object doInBackground(Object[] params) {
            if (params[0] != null) {
                String result = "";
                try {
                    String mUrl = URL + params[0] + KEY;

                    URL url = new URL(mUrl);
                    HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                    httpsURLConnection.setReadTimeout(10000);
                    httpsURLConnection.setConnectTimeout(15000);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.connect();
                    int mStatus = httpsURLConnection.getResponseCode();
                    if (mStatus == 200)
                        return readResponse(httpsURLConnection.getInputStream()).toString();

                    return result;

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progress = ProgressDialog.show(MainActivity2.this, "Preparing Data",
//                    "Fetching data ....", true);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            progress.dismiss();
        }
    }

    //GET Committees
    public class getCommittees extends AsyncTask {
        Context context;
        private Address address;
        private String KEY = "&apikey=4c5a600ad22949c4b1678a00907461cb";
        private String URL = "https://congress.api.sunlightfoundation.com/committees?member_ids=";

        public getCommittees(Context context) {
            this.context = context;
        }


        private StringBuilder readResponse(InputStream inputStream) throws IOException, NullPointerException {
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder;
        }


        @Override
        protected Object doInBackground(Object[] params) {
            if (params[0] != null) {
                String result = "";
                try {
                    String mUrl = URL + params[0] + KEY;

                    URL url = new URL(mUrl);
                    HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                    httpsURLConnection.setReadTimeout(10000);
                    httpsURLConnection.setConnectTimeout(15000);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.connect();
                    int mStatus = httpsURLConnection.getResponseCode();
                    if (mStatus == 200)
                        return readResponse(httpsURLConnection.getInputStream()).toString();

                    return result;

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
            return null;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progress = ProgressDialog.show(MainActivity2.this, "Preparing Data",
//                    "Fetching data ....", true);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            progress.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        locationButton = (Button) findViewById(R.id.button);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        Location location = locationManager.getLastKnownLocation(provider);

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation(view);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET

                }, 10);
                return;

            }
        }

        //Edit Text
        final EditText zipcode = (EditText) findViewById(R.id.editText);
        zipcode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    handled = true;

                    if (zipcode.getText().length() != 5) {
                        Toast.makeText(getApplicationContext(), "You need to enter exactly 5 digits", Toast.LENGTH_LONG).show();

                    } else {

                        System.out.println("ZIP CODE IS : " + Integer.parseInt(zipcode.getText().toString()));

                        prepareZIPData(Integer.parseInt(zipcode.getText().toString()));

                        //Go to next Mobile Screen
                        Intent myIntent = new Intent(getBaseContext(), MainActivity3.class);
                        myIntent.putExtra("fill", listDataHeader);
                        startActivity(myIntent);

                        //GO TO WATCH
                        Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                        sendIntent.putExtra("fill", listDataHeader);
                        startService(sendIntent);
                    }
                }
                return handled;
            }
        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String data = (String) extras.get("start_activity");

            Intent myIntent = new Intent(this, MainActivity3.class);
            myIntent.putExtra("fill", listDataHeader);
            myIntent.putExtra("start_activity", data);
            startActivity(myIntent);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);

    }


    @Override
    public void onLocationChanged(Location location) {

        if (location != null) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            Log.i("Location info: Lat", latitude.toString());
            Log.i("Location info: Lng", longitude.toString());
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public void getLocation(View view) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
                return;
            }
        }


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        Location location = locationManager.getLastKnownLocation(provider);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // Build the alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Location Services Not Active");
            builder.setMessage("Please enable Location Services and GPS");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Show location settings when the user acknowledges the alert dialog
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        } else {

            onLocationChanged(location);

            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            this.latitude = latitude;
            this.longitude = longitude;

            Log.i("Location info: Lat", latitude.toString());
            Log.i("Location info: Lng", longitude.toString());



            makeData(latitude, longitude);


            //Go to next Mobile Screen
            Intent myIntent = new Intent(getBaseContext(), MainActivity3.class);
            myIntent.putExtra("header", listDataHeader);
            startActivity(myIntent);

            //GO TO WATCH
            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendIntent.putExtra("header", listDataHeader);
            startService(sendIntent);
        }


    }

    private void makeData(Double lat, Double lng) {

        listDataHeader = new ArrayList<Storage>();

        ReverseGeocoding rg = new ReverseGeocoding(getBaseContext());
        String result = "NONE";
        String State = "NONE";
        String County = "NONE";

        try {
            result = (String) rg.execute(lat, lng).get();

            if (result != null) {
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.getString("status").equals("OK")) {
                    jsonObject = jsonObject.getJSONArray("results")
                            .getJSONObject(0);

                    JSONArray address_components = jsonObject.getJSONArray("address_components");

                    for (int i = 0; i < address_components.length(); i++) {
                        JSONObject zero2 = address_components.getJSONObject(i);
                        String long_name = zero2.getString("long_name");
                        JSONArray mtypes = zero2.getJSONArray("types");
                        String Type = mtypes.getString(0);

                        if (TextUtils.isEmpty(long_name) == false || !long_name.equals(null) || long_name.length() > 0 || long_name != "") {
                            if (Type.equalsIgnoreCase("administrative_area_level_2")) {
                                County = long_name;
                            } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
                                State = long_name;
                            }
                        }
                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(" County: " + County + " State: " + State);

        //Get Congress Info
        getCongress gc = new getCongress(getBaseContext());
        String gps_result = "NONE";

        try {
            gps_result = (String) gc.execute(latitude, longitude).get();

            if (gps_result != null){

                JSONObject jsonObject = new JSONObject(gps_result);
                int count = jsonObject.getInt("count");

                for (int i = 0; i < count; i++) {

                    Storage of = new Storage();

                    JSONObject temp = jsonObject.getJSONArray("results").getJSONObject(i);

                    of.setName(temp.getString("first_name") + " " + temp.getString("last_name"));
                    of.setNumber(i);

                    if (Objects.equals(temp.getString("party"), "D")) {
                        of.setParty("Democrat");
                    } else if (Objects.equals(temp.getString("party"), "R")) {
                        of.setParty("Republican");
                    } else {
                        of.setParty("Independent");
                    }
                    of.setEmail(temp.getString("oc_email"));
                    of.setPosition(temp.getString("title") + ".");
                    of.setTwitter("https://twitter.com/" + temp.getString("twitter_id"));
                    of.setWeb(temp.getString("website"));

                    //Get Tweet and Assign it
                    try {
                        Long id = (Long) new getTweets().execute(temp.getString("twitter_id")).get();
                        if ( id != null) {
                            Long tweet = id;
                            of.setTweet(tweet);
                            of.setImage(this.profileImage);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    //Set Committees

                    getCommittees getCommittees = new getCommittees(getBaseContext());
                    result = (String) getCommittees.execute(temp.getString("bioguide_id")).get();

                    if (result != null) {

                        JSONObject jsonCom = new JSONObject(result);
                        int comCount = jsonCom.getInt("count");

                        ArrayList<String> comList = new ArrayList<String>();

                        for (int n = 0; n < comCount; n++) {
                            JSONObject tempCom = jsonCom.getJSONArray("results").getJSONObject(n);
                            comList.add(tempCom.getString("name"));
                        }

                        of.setComList(comList);
                    }

                    //Set Bills
                    getBills getBills = new getBills(getBaseContext());
                    result = (String) getBills.execute(temp.getString("bioguide_id")).get();

                    if (result != null) {

                        JSONObject jsonBill = new JSONObject(result);
                        int bCount = jsonBill.getInt("count");

                        int getCountOf = 5;

                        if (bCount < 5){
                            getCountOf = bCount;
                        }

                        ArrayList<String> bList = new ArrayList<String>();

                        for (int n = 0; n < getCountOf ; n++) {
                            JSONObject tempBill = jsonBill.getJSONArray("results").getJSONObject(n);

                            if(tempBill.getString("short_title") != null) {
                                bList.add(tempBill.getString("short_title"));
                            } else {
                                bList.add(tempBill.getString("official_title"));
                            }
                        }

                        of.setbillList(bList);
                    }

                    listDataHeader.add(i, of);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void prepareZIPData(int zip){

        listDataHeader = new ArrayList<Storage>();

        //Get Congress Info
        getCongressZIP gc = new getCongressZIP(getBaseContext());
        String fin_result = "NONE";

        try {
            fin_result = (String) gc.execute(zip).get();

            if (fin_result != null){
                JSONObject jsonObject = new JSONObject(fin_result);
                int count = jsonObject.getInt("count");

                for (int i = 0; i < count; i++) {

                    Storage of = new Storage();

                    JSONObject temp = jsonObject.getJSONArray("results").getJSONObject(i);

                    of.setName(temp.getString("first_name") + " " + temp.getString("last_name"));
                    of.setNumber(i);

                    if (Objects.equals(temp.getString("party"), "D")) {
                        of.setParty("Democrat");
                    } else if (Objects.equals(temp.getString("party"), "R")) {
                        of.setParty("Republican");
                    } else {
                        of.setParty("Independent");
                    }
                    of.setEmail(temp.getString("oc_email"));
                    of.setPosition(temp.getString("title") + ".");
                    of.setTwitter("https://twitter.com/" + temp.getString("twitter_id"));
                    of.setWeb(temp.getString("website"));

                    //Get Tweet and Assign it
                    try {
                        Long id = (Long) new getTweets().execute(temp.getString("twitter_id")).get();
                        if ( id != null) {
                            Long tweet = id;
                            of.setTweet(tweet);
                            of.setImage(this.profileImage);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

//                    //Set Committees

                    getCommittees getCommittees = new getCommittees(getBaseContext());
                    fin_result = (String) getCommittees.execute(temp.getString("bioguide_id")).get();

                    if (fin_result != null) {

                        JSONObject jsonCom = new JSONObject(fin_result);
                        int comCount = jsonCom.getInt("count");

                        ArrayList<String> comList = new ArrayList<String>();

                        for (int n = 0; n < comCount; n++) {
                            JSONObject tempCom = jsonCom.getJSONArray("results").getJSONObject(n);
                            comList.add(tempCom.getString("name"));
                        }

                        of.setComList(comList);
                    }

                    //Set Bills
                    getBills getBills = new getBills(getBaseContext());
                    fin_result = (String) getBills.execute(temp.getString("bioguide_id")).get();

                    if (fin_result != null) {

                        JSONObject jsonBill = new JSONObject(fin_result);
                        int bCount = jsonBill.getInt("count");

                        int getCountOf = 5;

                        if (bCount < 5){
                            getCountOf = bCount;
                        }

                        ArrayList<String> bList = new ArrayList<String>();

                        for (int n = 0; n < getCountOf ; n++) {
                            JSONObject tempBill = jsonBill.getJSONArray("results").getJSONObject(n);

                            if(tempBill.getString("short_title") != null) {
                                bList.add(tempBill.getString("short_title"));
                            } else {
                                bList.add(tempBill.getString("official_title"));
                            }
                        }

                        of.setbillList(bList);
                    }

                    listDataHeader.add(i,of);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }  catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

