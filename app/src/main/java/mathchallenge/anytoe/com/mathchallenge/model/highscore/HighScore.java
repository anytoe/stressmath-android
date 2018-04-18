package mathchallenge.anytoe.com.mathchallenge.model.highscore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighScore {

    public static List<HighScoreItem> ITEMS = new ArrayList<>();
    public static List<HighScoreItem> ITEMS_CHANGED = new ArrayList<>();


    static {
        addItem(new HighScoreItem(1, "Jan", 232));
        addItem(new HighScoreItem(2, "Andreas", 123, true));
        addItem(new HighScoreItem(3, "Peter", 66));

        addItemChanged(new HighScoreItem(1, "Jan", 232));
        addItemChanged(new HighScoreItem(2, "Andreas", 123, false));
        addItemChanged(new HighScoreItem(3, "Peter", 66));
    }

    private static void addItem(HighScoreItem item) {
        ITEMS.add(item);
    }

    private static void addItemChanged(HighScoreItem item) {
        ITEMS_CHANGED.add(item);
    }

    public static class HighScoreItem{

        public int ranking;
        public String displayName;
        public int highScore;
        public boolean isEditableUser;
        public boolean isUser;

        public HighScoreItem(int ranking, String displayName, int highScore) {
            this.ranking = ranking;
            this.displayName = displayName;
            this.highScore = highScore;
            this.isEditableUser = false;
            this.isUser = false;
        }

        public HighScoreItem(int ranking, String displayName, int highScore, boolean isEditableUser) {
            this.ranking = ranking;
            this.displayName = displayName;
            this.highScore = highScore;
            this.isEditableUser = isEditableUser;
            this.isUser = true;
        }
    }
}
