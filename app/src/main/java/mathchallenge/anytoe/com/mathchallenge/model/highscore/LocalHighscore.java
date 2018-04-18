package mathchallenge.anytoe.com.mathchallenge.model.highscore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by anytoe on 12/10/2015.
 */
public class LocalHighscore implements Serializable {

    private ArrayList<LocalHighscoreItem> items;

    public LocalHighscore() {
        items = new ArrayList<>();
    }

    public void add(LocalHighscoreItem highscore) {

        for (LocalHighscoreItem item : items) {
            item.setIsNew(false);
        }

        items.add(highscore);
        Collections.sort(items, new CustomComparator());

        if (items.size() > 20) {
            items.remove(20);
        }
    }

    public boolean hasHighscore() {
        return items.size() > 0;
    }

    public List<LocalHighscoreItem> GetLocalHighscoreList() {
        return items;
    }

    public class CustomComparator implements Comparator<LocalHighscoreItem> {

        @Override
        public int compare(LocalHighscoreItem highscore1, LocalHighscoreItem highscore2) {
            int compareResult = highscore2.getHighscore() - highscore1.getHighscore();
            return compareResult;
        }
    }

}
