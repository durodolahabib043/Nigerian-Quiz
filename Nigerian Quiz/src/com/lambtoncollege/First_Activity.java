package com.lambtoncollege;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lambtoncollege.quiz.R;

public class First_Activity extends Activity implements OnClickListener {

    private Button startButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance_layout);
        startButton = (Button)findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       Intent intent = new Intent(First_Activity.this, MainActivity.class);        startActivity(intent);
        System.exit(0);
        
        
    }

}