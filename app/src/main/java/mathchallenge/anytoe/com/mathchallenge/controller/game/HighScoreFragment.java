package mathchallenge.anytoe.com.mathchallenge.controller.game;

import android.content.Context;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.controller.ParentActivity;
import mathchallenge.anytoe.com.mathchallenge.model.highscore.LocalHighscore;
import mathchallenge.anytoe.com.mathchallenge.model.highscore.LocalHighscoreItem;
import mathchallenge.anytoe.com.mathchallenge.model.user.User;

public class HighScoreFragment extends ListFragment implements TextView.OnEditorActionListener {

    private static final String ARG_PARAM1 = "LocalHighscore";
    private LocalHighscore localHighscore;

    public static HighScoreFragment newInstance() {
        HighScoreFragment fragment = new HighScoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HighScoreFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        showList();
    }

    private void showList() {
        ParentActivity activity = (ParentActivity) getActivity();

//        TextView highscoreTitle = (TextView) getActivity().findViewById(R.id.highscore_title);
//        highscoreTitle.setText(R.string.title_highscore);

        LocalHighscore localHighscore = activity.getLocalHighscore();
        List<LocalHighscoreItem> localHighscoreList = localHighscore.GetLocalHighscoreList();
        getListView().setVisibility(View.VISIBLE);
        final HighScoreListAdapter adapter = new HighScoreListAdapter(getActivity(), this, localHighscoreList, activity.getUser());
        setListAdapter(adapter);

        getListView().smoothScrollToPosition(adapter.getSelectedPosition());

        final ListView view = getListView();
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setSelection(adapter.getSelectedPosition());
            }
        }, 100L);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_highscore, container, false);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        showList();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getView().getWindowToken(), 0);
        return false;
    }
}
