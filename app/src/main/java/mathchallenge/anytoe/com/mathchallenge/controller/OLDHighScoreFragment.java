package mathchallenge.anytoe.com.mathchallenge.controller;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.controller.game.HighScoreListAdapter;
import mathchallenge.anytoe.com.mathchallenge.model.highscore.HighScore;

public class OLDHighScoreFragment extends ListFragment implements TextView.OnEditorActionListener {

    public static OLDHighScoreFragment newInstance() {
        OLDHighScoreFragment fragment = new OLDHighScoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public OLDHighScoreFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setListAdapter(new HighScoreListAdapter(getActivity(), this, HighScore.ITEMS));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_highscore, container, false);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        setListAdapter(new HighScoreListAdapter(getActivity(), this, HighScore.ITEMS_CHANGED));
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getView().getWindowToken(), 0);
        return false;
    }
}
