package com.example.fayyazmukarram.represent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.fabric.sdk.android.Fabric;

/**
 * Created by fayyazmukarram on 3/10/16.
 */

public class CustomAdapter extends BaseExpandableListAdapter{

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

    private Context _context;

    private static final String TWITTER_KEY = "M5dDv4YwQLglds2uXRxGqqYYF";
    private static final String TWITTER_SECRET = "4r0gIMzE70tjSe7xoaMzajHGboxygEM4xCjTqBt11P3hM0KykM";

    //List Data
    private List<Storage> _listDataHeader;


    private int ExpandPos = -1;

    public CustomAdapter(Context context, List<Storage> listDataHeader) {
        this._context = context;
        this._listDataHeader = listDataHeader;
    }


    public CustomAdapter(Context context, List<Storage> listDataHeader, int expandPos) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.ExpandPos = expandPos;
    }


    @Override
    public Object getChild(int groupPosition, int childPosititon) {

        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.coms_bills, null);
        }

        //Setting Committees List
        ListView comListView = (ListView) convertView.findViewById(R.id.committeesList);
        ArrayAdapter<String> listAdapter;
        listAdapter = new ArrayAdapter<String>(_context, R.layout.coms,R.id.coms, this._listDataHeader.get(groupPosition).comList);
        comListView.setAdapter(listAdapter);

        //Setting Bills List
        ListView billListView = (ListView) convertView.findViewById(R.id.billsList);
        ArrayAdapter<String> bListAdapter;
        bListAdapter = new ArrayAdapter<String>(_context, R.layout.bills,R.id.bills, this._listDataHeader.get(groupPosition).billList);
        billListView.setAdapter(bListAdapter);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView( final int groupPosition,  final boolean isExpanded,
                              View convertView,  final ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.rep_info, null);

        }


        //expand button

        ImageButton fullProfile = (ImageButton) convertView.findViewById(R.id.arrow);

        fullProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (groupPosition == ExpandPos) {
                    ((ExpandableListView) parent).collapseGroup(groupPosition);
                    ExpandPos = -1;
                }

                if (isExpanded) {
                    ((ExpandableListView) parent).collapseGroup(groupPosition);
                } else {
                    ((ExpandableListView) parent).expandGroup(groupPosition, true);
                }

            }
        });

        if (ExpandPos != -1 && groupPosition == ExpandPos){
            ((ExpandableListView) parent).expandGroup(ExpandPos, true);
        }

        //set name
        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(this._listDataHeader.get(groupPosition).position + " " + this._listDataHeader.get(groupPosition).name);

        //set party
        TextView party = (TextView) convertView.findViewById(R.id.party);
        party.setText(this._listDataHeader.get(groupPosition).party);

        //set image
        ImageView img = (ImageView) convertView.findViewById(R.id.photo);
        new ImageLoadTask(this._listDataHeader.get(groupPosition).image, img).execute();







        //SET TWEET

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(_context, new Twitter(authConfig));


        final LinearLayout myLayout
                = (LinearLayout) convertView.findViewById(R.id.tweets);
        long tweetId = this._listDataHeader.get(groupPosition).tweet;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                myLayout.addView(new TweetView(_context, result.data));
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Load Tweet failure", exception);
            }
        });






        //set web
        ImageButton web = (ImageButton) convertView.findViewById(R.id.website);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = _listDataHeader.get(groupPosition).web;
                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                _context.startActivity(intent);
            }
        });

        //set email
        ImageButton email = (ImageButton) convertView.findViewById(R.id.mail);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Create the Intent */
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                 /* Fill it with Data */
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{_listDataHeader.get(groupPosition).email});
                /* Send it off to the Activity-Chooser */
                _context.startActivity(Intent.createChooser(emailIntent, ""));

            }
        });


        //set twitter
        ImageButton twitter = (ImageButton) convertView.findViewById(R.id.twitter);
        twitter.setOnClickListener(new View.OnClickListener() {

            //fix it where it also opens twitter if app installed
            @Override
            public void onClick(View v) {
                String url = _listDataHeader.get(groupPosition).twitter;
                Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                _context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
