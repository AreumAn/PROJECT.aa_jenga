package work.andreaamado.aagameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class GameRandomActivity extends AppCompatActivity {

    String[] question;
    String cate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String[] myQuestions = intent.getStringArrayExtra("myQuestions");
        cate = intent.getStringExtra("cate");

        if(cate.equals("Mix")) {
            //question = new String[20];
            question = new String[400];
        } else {
            //question = new String[5];
            question = new String[100];
        }

        for(int i=0; i < myQuestions.length; i++) {
            question[i] = myQuestions[i];
        }

        setContentView(R.layout.activity_game_random);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        TextView toolbar_title = (TextView) findViewById(R.id.action_bar_title);
        toolbar_title.setText(getResources().getString(R.string.title_activity_game_random));
    }

}
