package work.andreaamado.aagameapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Find the toolbar view inside the activity layout
        // Hello Andrea! Git test!
        // Yes, testing from my machine now!
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        // OK

        TextView toolbar_title = (TextView) findViewById(R.id.action_bar_title);
        toolbar_title.setText(getResources().getString(R.string.title_activity_help));

    }

}
