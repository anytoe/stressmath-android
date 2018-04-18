package mathchallenge.anytoe.com.mathchallenge.controller.game;

import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.model.challenge.MathChallenge;
import mathchallenge.anytoe.com.mathchallenge.model.challenge.MathProblem;


public class GameFragment extends Fragment {

    private static final String ARG_PARAM1 = "MatchChallenge";

    private MathChallenge mathChallenge;
    private CountDownTimer gameTimer;
    private Timer countDownTimer;
    private List<Button> buttonList;

    ProgressBar mProgressBar;

    private int counter = 3;
    private OnGameResultFragmentListener mListener;

    private HashMap<Integer, String> comments;

    public static GameFragment newInstance() {
        MathChallenge mathChallenge = new MathChallenge();
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, mathChallenge);
        fragment.setArguments(args);
        return fragment;
    }

    public GameFragment() {
        // Required empty public constructor
        buttonList = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mathChallenge = (MathChallenge) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        comments = new HashMap<>();
        comments.put(1, getString(R.string.game_comment1));
        comments.put(2, getString(R.string.game_comment2));
        comments.put(3, getString(R.string.game_comment3));
        comments.put(4, getString(R.string.game_comment4));

        mListener = (OnGameResultFragmentListener) getActivity();

        mProgressBar = (ProgressBar) getActivity().findViewById(R.id.problem_progressbar);
        if (!mathChallenge.hasStarted()) {
            scheduleTimer();
        } else {
            startGame();
        }
    }

    public void startGame() {
        int remainingProblemTime = mathChallenge.getRemainingProblemTime();
        if (remainingProblemTime > 0) {
            MathProblem problem = mathChallenge.GetCurrentMathProblem();
            int currentStartTimeMs = mathChallenge.getCurrentStartTimeMs();
            mProgressBar.setMax(currentStartTimeMs);
            mProgressBar.setProgress(remainingProblemTime);
            startMathProblem(problem, currentStartTimeMs, remainingProblemTime);
        } else {
            mProgressBar.setProgress(0);
            mListener.gameFinished(mathChallenge);
        }
    }

    private void scheduleTimer() {
        final TextView myTextView = (TextView) getActivity().findViewById(R.id.game_button_counter);
        myTextView.setVisibility(View.VISIBLE);

        final Timer timer = new Timer();
        countDownTimer = timer;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myTextView.setText(counter + "");
                        if (counter == 0) {
                            timer.cancel();
                            myTextView.setVisibility(View.GONE);
                            getActivity().setTitle(getString(R.string.game_started));
                            nextMathProblem();
                        } else {
                            counter--;
                            getActivity().setTitle(getString(R.string.game_timercounting));
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private void nextMathProblem() {
        MathProblem problem = mathChallenge.GetNextMathProblem();
        int availableTime = mathChallenge.getCurrentStartTimeMs();
        int timeLeft = mathChallenge.getRemainingProblemTime();
        startMathProblem(problem, availableTime, timeLeft);
    }

    private void startMathProblem(MathProblem problem, int time, int timeLeft) {
        InitializeTimer(time, timeLeft);
        setMathChallenge(problem);
    }

    private void setMathChallenge(MathProblem mathProblem) {
        final View gameContainer = getActivity().findViewById(R.id.game_button_gamecontainer);
        gameContainer.setVisibility(View.VISIBLE);
        final TextView question = (TextView) gameContainer.findViewById(R.id.problem_question);
        question.setText(mathProblem.getProblem() + " = ?");
        String[] randomizedAnswers = mathProblem.getRandomizedAnswers();
        final TextView currentScore = (TextView) getActivity().findViewById(R.id.problem_feedbackmessage);
        setGameSolutionButton(R.id.problem_button_1, randomizedAnswers[0], currentScore);
        setGameSolutionButton(R.id.problem_button_2, randomizedAnswers[1], currentScore);
        setGameSolutionButton(R.id.problem_button_3, randomizedAnswers[2], currentScore);
        setGameSolutionButton(R.id.problem_button_4, randomizedAnswers[3], currentScore);

//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                triggerAnimation(currentScore);
//            }
//        });
    }

    private void triggerAnimation(final TextView textView) {

        if (!comments.containsKey(mathChallenge.getCorrectAnswerCount()))
            return;

        textView.setText(comments.get(mathChallenge.getCorrectAnswerCount()));

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.text_appearing);
        //animation.reset();
        animation.setDuration(300);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setVisibility(View.INVISIBLE);
                //animation.reset();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        textView.setAnimation(animation);
        textView.animate();

        textView.setVisibility(View.VISIBLE);
        animation.start();
    }

    private void setGameSolutionButton(int buttonId, String text, TextView currentScore) {
        final Button button = (Button) getActivity().findViewById(buttonId);
        button.setOnClickListener(new OnAnswerClickListener(text, currentScore));
        button.setText(text);
        buttonList.add(button);
    }

    private void InitializeTimer(int time, int timeLeft) {
        mProgressBar.setMax(time);
        mProgressBar.setProgress(timeLeft);
        gameTimer = new CountDownTimer(timeLeft, 50) {

            @Override
            public void onTick(final long millisUntilFinished) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        mProgressBar.setProgress((int) (millisUntilFinished));
                    }
                });
            }

            @Override
            public void onFinish() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        mProgressBar.setProgress(0);
                        for (Button button : buttonList)
                            button.setEnabled(false);
                        mListener.gameFinished(mathChallenge);
                    }
                });
            }
        };
        gameTimer.start();
    }

    @Override
    public void onDetach() {
        if (gameTimer != null)
            gameTimer.cancel();

        super.onDetach();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (gameTimer != null)
            gameTimer.cancel();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

    public class OnAnswerClickListener implements View.OnClickListener {

        private String answer;
        private TextView currentScore;

        public OnAnswerClickListener(String answer, TextView currentScore) {
            this.answer = answer;
            this.currentScore = currentScore;
        }

        @Override
        public void onClick(View v) {
            gameTimer.cancel();
            float progress = mProgressBar.getProgress();
            float max = mProgressBar.getMax();
            float answerFactor = progress / max;

            if (mathChallenge.answer(answer, answerFactor)) {
                getActivity().setTitle(getString(R.string.game_scorecount).replace("{0}", mathChallenge.getScore() + ""));
//                currentScore.setText(mathChallenge.getScore() + "");
//                currentScore.setVisibility(View.VISIBLE);
                nextMathProblem();
            } else {
                mListener.gameFinished(mathChallenge);
            }
        }
    }

    public interface OnGameResultFragmentListener {
        void gameFinished(MathChallenge challenge);
    }

}
