package com.lambtoncollege;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lambtoncollege.quiz.R;

public class ResultActivity extends Activity implements OnClickListener {

    private Button doneButton;
    private Button doAgainButton;
    private TextView messageTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        messageTextView = (TextView)findViewById(R.id.message_label);
        doneButton = (Button)findViewById(R.id.finish_button);
        doAgainButton = (Button)findViewById(R.id.do_again_button);
        doneButton.setOnClickListener(this);
        doAgainButton.setOnClickListener(this);
        doAgainButton.setVisibility(View.INVISIBLE);
        int correctAnswerCount = this.getIntent().getIntExtra("correctAnswerCount", 0);
        //Score 0/5, 1/5 and 2/5 Message => "Please try again!"
        //Score 3/5 Message => "Good job!"
        ///Score 4/5 Message => "Excellent work!"
        //Score 5/5 Message => "You are a genius!"
        String message = "";
        switch (correctAnswerCount) {
        case 0:
        case 1:
        case 2:
            message = String.format(Locale.US, "Your score is %d, \n Please try again!", correctAnswerCount);
            doAgainButton.setVisibility(View.VISIBLE);
            break;
        case 3:
            message = "Good job!";
            break;
        case 4:
            message = "Excellent work!";
            break;
        case 5:
            message = "You are a genius!";
        }
        messageTextView.setText(message);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.finish_button:
            System.exit(0);
            break;
        case R.id.do_again_button:
            Intent intent = new Intent(ResultActivity.this, First_Activity.class);
            startActivity(intent);
            System.exit(0);
            break;
        }
    }


}