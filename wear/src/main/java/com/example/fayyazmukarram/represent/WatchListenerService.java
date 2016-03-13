package com.example.fayyazmukarram.represent;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by fayyazmukarram on 2/29/16.
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String first_page = "/0";
    private static final String second_page = "/1";
    private static final String third_page = "/2";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        //use the 'path' field in sendmessage to differentiate use cases

        if( messageEvent.getPath().equalsIgnoreCase("/0")) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            String string = value;
            String[] parts = string.split(",");
            String part1 = parts[0];
            String part2 = parts[1];
            String part3 = parts[2];


            intent.putExtra("0", part1);
            intent.putExtra("1", part2);
            intent.putExtra("2", part3);
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}