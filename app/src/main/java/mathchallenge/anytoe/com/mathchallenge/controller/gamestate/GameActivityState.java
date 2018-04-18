package mathchallenge.anytoe.com.mathchallenge.controller.gamestate;

import java.io.Serializable;

/**
 * Created by anytoe on 06/11/2015.
 */
public class GameActivityState implements Serializable {

    private static final long serialVersionUID = 2800434294482665585L;

    private boolean isStarting = true;
    private boolean isGameRunning;
    private boolean isHighscore;

    public boolean isStarting() {
        return isStarting;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setIsGameRunning() {
        isGameRunning = true;
        isHighscore = false;
        isStarting = false;
    }

    public boolean isHighscore() {
        return isHighscore;
    }

    public void setIsHighscore() {
        isGameRunning = false;
        isHighscore = true;
        isStarting = false;
    }
}
