package mathchallenge.anytoe.com.mathchallenge.controller.highscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.controller.ParentActivity;
import mathchallenge.anytoe.com.mathchallenge.controller.game.GameActivity;
import mathchallenge.anytoe.com.mathchallenge.controller.game.HighScoreFragment;

public class HighscoreActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
    }

    @Override
    protected void onResume() {
        super.onResume();

        HighScoreFragment highScoreFragment = HighScoreFragment.newInstance();

        openTransaction();
        addFragment(R.id.highscore_fragment_center, highScoreFragment);
        commitTransaction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }
}
