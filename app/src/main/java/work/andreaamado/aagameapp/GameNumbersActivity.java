package work.andreaamado.aagameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class GameNumbersActivity extends AppCompatActivity {

    String[] question = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String[] myQuestions = intent.getStringArrayExtra("myQuestions");

        for(int i=0; i < myQuestions.length; i++) {
            question[i] = myQuestions[i];
//            System.out.println("first page: " + question[i]);
        }

        setContentView(R.layout.activity_game_numbers);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        TextView toolbar_title = (TextView) findViewById(R.id.action_bar_title);
        toolbar_title.setText(getResources().getString(R.string.title_activity_game_numbers));
    }

}
