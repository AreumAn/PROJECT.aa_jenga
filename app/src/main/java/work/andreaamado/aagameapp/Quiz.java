package work.andreaamado.aagameapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by areum on 2017-11-11.
 */

public class Quiz {

    public String question;
//    public String answer;
//    public List<String> answers;

    public int selectedNumber = -1;


    public Quiz(String question) {
//        public Quiz(String question, String answer, List<String> incorrectAnswer) {
        this.question = question;
//        this.answer = answer;
//        this.answers = new ArrayList<String>(incorrectAnswer);

        // correct answer 1 + incorrect answers 3 : and then shuffle
//        answers.add(answer);
//        Collections.shuffle(answers);
    }


}
