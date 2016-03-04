package com.example.fayyazmukarram.represent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by fayyazmukarram on 3/1/16.
 */
public class WatchOne extends Fragment{

    ImageView dianeButton;
    public static String repName="";
    private GoogleApiClient mGoogleApiClient;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.watch1, container, false);

        dianeButton = (ImageButton) v.findViewById(R.id.diane);

//        Intent intent = getActivity().getIntent();
//        Bundle extras = intent.getExtras();
//
//        if (extras != null) {
//            repName = extras.getString("REP_NAME");
//            dianeButton.setImageDrawable(getResources().getDrawable(R.drawable.diane));
//        }

        dianeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(getActivity().getBaseContext(), WatchToPhoneService.class);
                getActivity().startService(sendIntent);
            }
        });

        return v;
    }

}
