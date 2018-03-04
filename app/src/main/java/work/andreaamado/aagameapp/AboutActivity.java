package work.andreaamado.aagameapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button andreaWebBtn = (Button) findViewById(R.id.text_link_andrea);
        Button areumWebBtn = (Button) findViewById(R.id.text_link_areum);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        TextView toolbar_title = (TextView) findViewById(R.id.action_bar_title);
        toolbar_title.setText(getResources().getString(R.string.title_activity_about));

        andreaWebBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent andreadWeb = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.andreaamado.ca"));
                startActivity(andreadWeb);
            }
        });

        areumWebBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent andreadWeb = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.areuman.com"));
                startActivity(andreadWeb);
            }
        });

    }
}
