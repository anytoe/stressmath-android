package mathchallenge.anytoe.com.mathchallenge.controller.game;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;

import java.io.Serializable;

import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.controller.ParentActivity;
import mathchallenge.anytoe.com.mathchallenge.controller.gamestate.GameActivityState;
import mathchallenge.anytoe.com.mathchallenge.model.challenge.IChallengeFinishedListener;
import mathchallenge.anytoe.com.mathchallenge.model.challenge.MathChallenge;
import mathchallenge.anytoe.com.mathchallenge.model.highscore.LocalHighscoreItem;

public class GameActivity extends ParentActivity implements
        GameResultFragment.OnGameResultFragmentListener,
        GameFragment.OnGameResultFragmentListener {

    private GameActivityState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (savedInstanceState != null && savedInstanceState.containsKey("GameActivityState"))
            gameState = (GameActivityState) savedInstanceState.getSerializable("GameActivityState");
        else
            gameState = new GameActivityState();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable("GameActivityState", gameState);

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (gameState.isStarting())
            showGame();
    }

    private void showGame() {
        setTitle(getResources().getString(R.string.title_game));

        gameState.setIsGameRunning();
        openTransaction();
        removeFragment(R.id.game_fragment_top);
        removeFragment(R.id.game_fragment_bottom);

        GameFragment gameFragment = GameFragment.newInstance();
        addFragment(R.id.game_fragment_full, gameFragment);
        commitTransaction();
    }

    private void showGameResult(MathChallenge challenge) {
        setTitle(getResources().getString(R.string.title_highscore));

        gameState.setIsHighscore();

        openTransaction();
        removeFragment(R.id.game_fragment_full);

        GameResultFragment problemFrag = GameResultFragment.newInstance(challenge);
        addFragment(R.id.game_fragment_top, problemFrag);
        HighScoreFragment highScoreFragment = HighScoreFragment.newInstance();
        addFragment(R.id.game_fragment_bottom, highScoreFragment);
        commitTransaction();
    }

    @Override
    public void playAgainClicked() {
        showGame();
    }

    @Override
    public void gameFinished(MathChallenge challenge){
        getLocalHighscore().add(new LocalHighscoreItem(challenge.getScore(), true));
        save();
        showGameResult(challenge);
    }

}
