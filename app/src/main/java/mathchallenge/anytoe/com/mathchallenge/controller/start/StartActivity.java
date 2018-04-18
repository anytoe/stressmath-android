package mathchallenge.anytoe.com.mathchallenge.controller.start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import mathchallenge.anytoe.com.mathchallenge.controller.ParentActivity;
import mathchallenge.anytoe.com.mathchallenge.controller.game.GameActivity;
import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.controller.highscore.HighscoreActivity;

public class StartActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button eventLayout = (Button) findViewById(R.id.start_button_newgame);
        eventLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, GameActivity.class);
                startActivity(intent);
            }

        });

        Button highscoreButton = (Button) findViewById(R.id.start_button_highscore);
        highscoreButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, HighscoreActivity.class);
                startActivity(intent);
            }

        });
        highscoreButton.setEnabled(getLocalHighscore().hasHighscore());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

}
