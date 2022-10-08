package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private Question[] questions = new Question[]{
            new Question(R.string.q1, true),
            new Question(R.string.q2, false),
            new Question(R.string.q3, false),
            new Question(R.string.q4, true),
            new Question(R.string.q5, true)
    };
    private int currentIndex = 0;

    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMassageIdea = 0;
        if(userAnswer == correctAnswer) resultMassageIdea = R.string.correct_answer;
        else resultMassageIdea = R.string.incorrect_answer;

        Toast.makeText(this, resultMassageIdea, Toast.LENGTH_SHORT).show();
    }
    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.questioin_text_view);

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex+1)%questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();
    }
}
