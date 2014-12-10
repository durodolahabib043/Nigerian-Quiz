package com.lambtoncollege;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lambtoncollege.entity.Question;
import com.lambtoncollege.quiz.R;
import com.lambtoncollege.service.QXmlParseService;

public class MainActivity extends Activity implements OnClickListener {

    private final static int MAX_QUESTION_CNT = 5;
    private int questionCount;
    private int correctCount = 0;
    List<Question> questions = null;

    private Button nextButton;
    private RadioGroup radioGroup;
    private TextView questionLabel;
    private RadioButton radioButtonA;
    private RadioButton radioButtonB;
    private RadioButton radioButtonC;
    private RadioButton radioButtonD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        questionLabel = (TextView)findViewById(R.id.question_label);
        nextButton = (Button)findViewById(R.id.next_button);
        radioGroup = (RadioGroup)findViewById(R.id.choices);
        radioButtonA = (RadioButton)findViewById(R.id.choice_a);
        radioButtonB = (RadioButton)findViewById(R.id.choice_b);
        radioButtonC = (RadioButton)findViewById(R.id.choice_c);
        radioButtonD = (RadioButton)findViewById(R.id.choice_d);

        nextButton.setOnClickListener(this);
        questions = selectQuestions();
        nextButton.setVisibility(View.INVISIBLE);

        editWidgetsOnScreen(0);
    }

    private void editWidgetsOnScreen(int index) {
        Question question = questions.get(index);
        questionLabel.setText(question.getQuestion());
        radioButtonA.setText(question.getChoiceA());
        radioButtonB.setText(question.getChoiceB());
        radioButtonC.setText(question.getChoiceC());
        radioButtonD.setText(question.getChoiceD());
        radioButtonA.setTextColor(Color.BLACK);
        radioButtonB.setTextColor(Color.BLACK);
        radioButtonC.setTextColor(Color.BLACK);
        radioButtonD.setTextColor(Color.BLACK);
    }

    @Override
    public void onClick(View v) {
        questionCount++;
        if (questionCount == MAX_QUESTION_CNT) {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("correctAnswerCount", correctCount);
            startActivity(intent);
            System.exit(0);
        } else {
            if (questionCount == MAX_QUESTION_CNT - 1) {
                nextButton.setText(R.string.done_button);
            }
            editWidgetsOnScreen(questionCount);
            nextButton.setVisibility(View.INVISIBLE);
            radioButtonA.setEnabled(true);
            radioButtonB.setEnabled(true);
            radioButtonC.setEnabled(true);
            radioButtonD.setEnabled(true);
            radioGroup.clearCheck();
        }
    }

    public void onRadioButtonClicked(View view) {
        RadioButton checkedRadio = (RadioButton)view;
        int correctAnswer = questions.get(questionCount).getAnswer();
        checkedRadio.setTextColor(Color.RED);
        RadioButton correctRadio = (RadioButton)radioGroup.getChildAt(correctAnswer-1);
        correctRadio.setTextColor(Color.GREEN);
        int correctRadioId = correctRadio.getId();
        if (checkedRadio.getId() == correctRadioId) {
            correctCount++;
        }
        nextButton.setVisibility(View.VISIBLE);
        radioButtonA.setEnabled(false);
        radioButtonB.setEnabled(false);
        radioButtonC.setEnabled(false);
        radioButtonD.setEnabled(false);

    }

    private List<Question> selectQuestions() {

        List<Question> list = null;
        ArrayList<Question> selectQuestions = new ArrayList<Question>();
        InputStream is = null;
        AssetManager manager = getAssets();
        try {
            is = manager.open("questions.xml");
            list = new QXmlParseService().getQuestionsByParseXml(is);

            int questionSumCount = list.size();
            int selectQuestionCount = questionSumCount < MAX_QUESTION_CNT ? questionSumCount : MAX_QUESTION_CNT;
            ArrayList<Integer> tempNumberArray = new ArrayList<Integer>();
            for (int i = 0; i < questionSumCount; i++) {
                tempNumberArray.add(Integer.valueOf(i));
            }

            int[] randomNumbers = new int[selectQuestionCount];
            for (int i = 0; i < selectQuestionCount; i++) {
                int randomNumber = (int)(Math.random() * tempNumberArray.size());
                randomNumbers[i] = tempNumberArray.get(randomNumber);
                tempNumberArray.remove(randomNumber);
            }

            for (int i = 0; i < randomNumbers.length; i++) {
                selectQuestions.add(list.get(randomNumbers[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectQuestions;
    }

}