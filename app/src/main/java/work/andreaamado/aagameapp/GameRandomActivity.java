package work.andreaamado.aagameapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.OptionalDataException;


public class GameRandomActivity extends AppCompatActivity {

    // For real
    //String[] question = new String[100];
    // For test
    //String[] question = new String[5];
    String[] question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String[] myQuestions = intent.getStringArrayExtra("myQuestions");
        String cate = intent.getStringExtra("cate");

        System.out.println(myQuestions.length);

        if(cate.equals("Mix")) {
            question = new String[20];
        } else {
            question = new String[5];
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
