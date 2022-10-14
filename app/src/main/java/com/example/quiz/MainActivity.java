package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Zadanie1";
    private static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "quiz.correctAnswer";
    private static final int REQUEST_CODE_PROMPT = 0;
    private boolean answerWasShown;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
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
        if(answerWasShown){
            resultMassageIdea = R.string.answer_was_shown;
        }else {
            if (userAnswer == correctAnswer) resultMassageIdea = R.string.correct_answer;
            else resultMassageIdea = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMassageIdea, Toast.LENGTH_SHORT).show();
    }
    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Wywołana została metoda cyklu życia: onCreate");
        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.questioin_text_view);
        promptButton = findViewById(R.id.propmt_button);

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
        promptButton.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, PromptActivitty.class);
            boolean correctAnswer = questions[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
            startActivityForResult(intent, REQUEST_CODE_PROMPT);
        });
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex+1)%questions.length;
                answerWasShown =false;
                setNextQuestion();
            }
        });
        setNextQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){return;}
        if(resultCode != REQUEST_CODE_PROMPT){
            if(data == null){ return;}
            answerWasShown = data.getBooleanExtra(PromptActivitty.ANSWER_SHOWN, false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Wywołana została metoda cyklu życia: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Wywołana została metoda cyklu życia: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Wywołana została metoda cyklu życia: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Wywołana została metoda cyklu życia: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Wywołana została metoda cyklu życia: onDestroy");
    }

   @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
       Log.d(TAG, "Wywołana została metoda cyklu życia: onSaveInstanceState");
       outState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }
}
