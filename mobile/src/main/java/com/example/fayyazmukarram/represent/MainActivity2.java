package com.example.fayyazmukarram.represent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

/**
 * Created by fayyazmukarram on 2/29/16.
 */
public class MainActivity2 extends AppCompatActivity {

    private Button locationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        locationButton = (Button) findViewById(R.id.button);

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loc = new Intent(getApplicationContext(), MainActivity5.class);
                startActivity(loc);
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendIntent.putExtra("REP_NAME", "rep");
                startService(sendIntent);
            }
        });
        //Edit Text

        final EditText et = (EditText) findViewById(R.id.editText);
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    handled = true;

                    switch (v.getId()) {
                        case R.id.editText:
                            int el = Integer.parseInt(et.getText().toString());
                            if (el == 94704) {
                                ///take me to next page
                                Intent enterzip = new Intent(MainActivity2.this, MainActivity3.class);
                                startActivity(enterzip);
                                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                                sendIntent.putExtra("REP_NAME", "rep");
                                startService(sendIntent);
                            }
                            else if (el == 90034) {
                                ///take me to next page
                                Intent enterzip = new Intent(MainActivity2.this, MainActivity3.class);
                                startActivity(enterzip);
                                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                                sendIntent.putExtra("REP_NAME", "rep");
                                startService(sendIntent);
                            }
                            else {
                                et.setText("");
                            }
                            break;
                    }

                }
                return handled;
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
