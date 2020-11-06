package com.spaceinc.math.app.fragment;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.spaceinc.app.R;
import com.spaceinc.math.app.MainActivity;

public class GameFragment extends Fragment implements View.OnClickListener {
    private EndGameFragment endGameFragment;
    private View view;
    private Bundle bundle;
    private Resources resources;
    private Random random;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private TextView question;
    private TextView score;
    private TextView timerView;
    private CountDownTimer timer;
    private int scoreCounter;
    private Map<Integer, String> equationMap;
    private List<Button> buttonList;
    private Integer rightAnswer;
    private Integer rightButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        endGameFragment = new EndGameFragment();
        view = inflater.inflate(R.layout.game_fragment, container, false);
        bundle = getArguments();
        bundle.putInt("SCORE", 0);
        resources = getResources();
        initViews();
        scoreCounter = 0;
        equationMap = new HashMap<>();
        buttonList = new ArrayList<>();
        setEquationMap();
        setButtonMap();
        nextQuestion();
        return view;
    }

    @Override
    public void onClick(View v) {
        Button received = view.findViewById(v.getId());
        if (received.getText().equals(rightAnswer.toString())) {
            timer.cancel();
            scoreCounter = scoreCounter + 1 * 10;
            bundle.putInt("SCORE", scoreCounter);
            score.setText(String.format(resources.getString(R.string.score), bundle.get("SCORE")));
            nextQuestion();
        } else {
            timer.cancel();
            endGame();
        }
    }

    private void nextQuestion() {
        rightAnswer = randomizeExampleKey();
        rightButton = randomizeButtonKey();
        question.setText(equationMap.get(rightAnswer));
        for (int i = 0; i < buttonList.size(); i++) {
            if (i == rightButton) {
                buttonList.get(i).setText(String.valueOf(rightAnswer));
            } else {
                buttonList.get(i).setText(String.valueOf(randomizeExampleKey()));
            }
        }
        setCountDown();
    }

    private void setCountDown() {
        timer = new CountDownTimer(7000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String timerText = resources.getString(R.string.timer);
                timerView.setText(String.format(timerText, millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    private void endGame() {
        ((MainActivity) getActivity()).loadFragment(new EndGameFragment());
    }

    private void initViews() {
        btn1 = view.findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        btn2 = view.findViewById(R.id.button2);
        btn2.setOnClickListener(this);
        btn3 = view.findViewById(R.id.button3);
        btn3.setOnClickListener(this);
        btn4 = view.findViewById(R.id.button4);
        btn4.setOnClickListener(this);
        question = view.findViewById(R.id.question_view);
        timerView = view.findViewById(R.id.timer_view);
        score = view.findViewById(R.id.score_view);
    }

    private void setEquationMap() {
        equationMap.put(0, " 6 - 6 ");
        equationMap.put(1, " 25 + 4 - 28 ");
        equationMap.put(2, " 7 - 9 + 4 ");
        equationMap.put(3, " 38 - 2 - 33 ");
        equationMap.put(4, " 8 - 4 ");
        equationMap.put(5, " 12 - 7 ");
        equationMap.put(6, " - 3 + 9 ");
        equationMap.put(7, " 8 - 1 ");
        equationMap.put(8, " 18 - 10 ");
        equationMap.put(9, " 11 - 2 ");
        equationMap.put(10, " 27 - 13 - 4");
        equationMap.put(11, " - 3 + 18 - 4 ");
        equationMap.put(12, " 13 - 1 ");
        equationMap.put(13, " 17 - 4 ");
        equationMap.put(14, " 19 - 5 ");
        equationMap.put(15, " 55 - 38 - 2 ");
        equationMap.put(16, " -27 + 43 ");
        equationMap.put(17, " 17 - 0");
        equationMap.put(18, " 8 - 4 + 14 ");
        equationMap.put(19, " 27 - 8 ");
        equationMap.put(20, " 37 - 13 - 4 ");
    }

    private void setButtonMap() {
        buttonList.add( btn1);
        buttonList.add( btn2);
        buttonList.add( btn3);
        buttonList.add( btn4);
    }

    private Integer randomizeExampleKey() {
        random = new Random();
        Integer key = random.nextInt(20);
        return key;
    }

    private Integer randomizeButtonKey() {
        random = new Random();
        Integer key = random.nextInt(4);
        return key;
    }
}
