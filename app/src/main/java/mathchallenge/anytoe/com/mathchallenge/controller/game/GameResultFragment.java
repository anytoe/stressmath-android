package mathchallenge.anytoe.com.mathchallenge.controller.game;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.controller.gamestate.GameActivityState;
import mathchallenge.anytoe.com.mathchallenge.model.challenge.MathChallenge;

public class GameResultFragment extends Fragment {

    private static final String MATH_CHALLENGE = "MatchChallengeParam";
    private MathChallenge mathChallenge;
    private OnGameResultFragmentListener mListener;

    public static GameResultFragment newInstance(MathChallenge matchChallenge) {
        GameResultFragment fragment = new GameResultFragment();
        Bundle args = new Bundle();
        args.putSerializable(MATH_CHALLENGE, matchChallenge);
        fragment.setArguments(args);
        return fragment;
    }

    public GameResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(MATH_CHALLENGE))
            mathChallenge = (MathChallenge) savedInstanceState.getSerializable(MATH_CHALLENGE);
        else if (getArguments() != null)
            mathChallenge = (MathChallenge) getArguments().getSerializable(MATH_CHALLENGE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(MATH_CHALLENGE, mathChallenge);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();

        mListener = (OnGameResultFragmentListener) getActivity();

        final View resultContainer = getActivity().findViewById(R.id.game_button_resultcontainer);
        final TextView myTextView = (TextView) resultContainer.findViewById(R.id.game_button_result);
        final View button = resultContainer.findViewById(R.id.game_button_restart);

        String textPattern = getString(R.string.gameresult_yourscore);
        String textReplacement = mathChallenge.getScore() + "";
        String resultText = textPattern.replace("{0}", textReplacement);
        myTextView.setText(resultText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.playAgainClicked();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gameresult, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnGameResultFragmentListener {
        void playAgainClicked();
    }

}
