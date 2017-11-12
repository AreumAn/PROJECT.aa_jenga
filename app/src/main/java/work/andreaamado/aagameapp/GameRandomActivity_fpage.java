package work.andreaamado.aagameapp;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by areum on 2017-11-11.
 */

public class GameRandomActivity_fpage extends Fragment {

    List<Quiz> questions = new ArrayList<Quiz>();

    TextView lblquestion;       // show quiz

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_game_random_fragment, container, false);

        lblquestion = (TextView) view.findViewById(R.id.question);

        for(int i=0; i <((GameRandomActivity)getActivity()).question.length; i++) {
            System.out.println("random page: " + ((GameRandomActivity)getActivity()).question[i]);
        }

        // Make Quiz 1
        makeFirstQuiz();

        // Click the NEXT button
        view.findViewById(R.id.btn_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        makeQuiz();
                }
        });

        return view;
    }

    // make quiz1 (with make questions array)
    public void makeFirstQuiz() {
        // make questions array and shuffle
        for (int i = 0; i < ((GameRandomActivity)getActivity()).question.length; i++) {
            questions.add(new Quiz(((GameRandomActivity)getActivity()).question[i]));
        }
        Collections.shuffle(questions);

        // show
        makeQuiz();

    }

    // make quiz and answers statements
    public void makeQuiz() {

        Quiz quiz = questions.get(0);
        // remove used questions element that was used for preventing duplication
        questions.remove(0);

        // set number of quiz and question.
        lblquestion.setText(quiz.question);

    }
}
