package com.spaceinc.math.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.spaceinc.app.R;
import com.spaceinc.math.app.MainActivity;

public class EndGameFragment extends Fragment implements View.OnClickListener {
    private GameFragment gameFragment;
    private Button exitGame;
    private Button restartGame;
    private View view;
    private TextView endGameText;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.end_game_fragment, null);
        bundle = getArguments();
        gameFragment = new GameFragment();
        endGameText = view.findViewById(R.id.finalResult);
        endGameText.setText(String.format(getResources().getString(R.string.result), bundle.get("SCORE")));
        exitGame = view.findViewById(R.id.exit);
        exitGame.setText(getResources().getString(R.string.exit));
        exitGame.setOnClickListener(this);
        restartGame = view.findViewById(R.id.restart);
        restartGame.setText(getResources().getString(R.string.restart));
        restartGame.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == exitGame.getId()) {
            System.exit(0);
        } else {
            ((MainActivity) getActivity()).loadFragment(gameFragment);
        }
    }
}
