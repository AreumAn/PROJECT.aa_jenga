package work.andreaamado.aagameapp;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
    TextView lblquestionCat;    // show category
    // multiple dialog
    TextView txtTitle;
    TextView txtBody;
    Button btnRandom;
    Button btnNumber;
    // single dialog
    TextView txtBodyDialog;
    Button btnSingleDialog;

    int showedQuestion = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_game_random_fragment, container, false);

        lblquestion = (TextView) view.findViewById(R.id.question);
        lblquestionCat = (TextView) view.findViewById(R.id.question_number);
        lblquestionCat.setText(((GameRandomActivity)getActivity()).cate);

        // Make Quiz 1
        makeFirstQuiz();

        // Click the ENTER button
        view.findViewById(R.id.btn_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog singleDialog = new Dialog(getActivity());
                singleDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                singleDialog.setContentView(R.layout.layout_custom_singledialog);
                singleDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                singleDialog.setCanceledOnTouchOutside(false);
                txtBodyDialog = (TextView)singleDialog.findViewById(R.id.txtBodyDialog);
                btnSingleDialog = (Button)singleDialog.findViewById(R.id.btnDialog);
                // Check out of questions
                if(showedQuestion < ((GameRandomActivity)getActivity()).question.length) {  makeQuiz(); }
                else {
                    singleDialog.show();
                    txtBodyDialog.setText("Out of Questions! It's going to Category page :) ");

                    btnSingleDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goMain();
                            singleDialog.dismiss();
                        }
                    });

                }

                }
        });


        // Click the END button
        view.findViewById(R.id.btn_end).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
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

        // How many question showed
        showedQuestion++;

    }

    public void goMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    // Show the custom dialog from layout_custom_dialog.xml file
    public void showDialog(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //initializing views of custom dialog
        txtTitle = (TextView)dialog.findViewById(R.id.txtTitle);
        txtBody = (TextView)dialog.findViewById(R.id.txtBody);
        btnRandom = (Button)dialog.findViewById(R.id.btnRandom);
        btnNumber = (Button)dialog.findViewById(R.id.btnNumber);


        txtTitle.setText("Confirm");
        txtBody.setText("Do you want to finish this game?");
        btnRandom.setText("NO!");
        btnNumber.setText("YES");


        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain();
                dialog.dismiss();
            }
        });


    }
}
