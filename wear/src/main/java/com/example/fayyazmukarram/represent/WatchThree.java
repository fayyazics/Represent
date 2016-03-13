package com.example.fayyazmukarram.represent;

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
public class WatchThree extends Fragment {

    ImageView virgilButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.watch3,container,false);

        virgilButton = (ImageButton) v.findViewById(R.id.virgil);

        virgilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(getActivity().getBaseContext(), WatchToPhoneService.class);
                sendIntent.putExtra("REP_NAME","start_activity");
                getActivity().startService(sendIntent);
            }
        });

        return v;
    }
}
