package mathchallenge.anytoe.com.mathchallenge.model.highscore;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by anytoe on 12/10/2015.
 */
public class LocalHighscoreItem implements Serializable {

    private static final long serialVersionUID = 4138462132635780439L;

    private long timeMs;
    private int score;
    private transient boolean isNew;

    public LocalHighscoreItem(int score, boolean isNew) {
        this.score = score;
        this.isNew = isNew;
        this.timeMs = new Date().getTime();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public Date getDate() {
        return new Date(timeMs);
    }

    public int getHighscore() {
        return score;
    }
}
