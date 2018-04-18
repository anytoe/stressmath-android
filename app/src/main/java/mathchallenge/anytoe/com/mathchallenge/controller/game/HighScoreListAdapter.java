package mathchallenge.anytoe.com.mathchallenge.controller.game;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.model.highscore.LocalHighscoreItem;
import mathchallenge.anytoe.com.mathchallenge.model.user.User;

/**
 * Created by anytoe on 09/10/2015.
 */
public class HighScoreListAdapter extends ArrayAdapter<LocalHighscoreItem> {

    private TextView.OnEditorActionListener actionListener;
    private User user;
    private int selectedPosition = -1;

    public HighScoreListAdapter(Context context, TextView.OnEditorActionListener actionListener, List<LocalHighscoreItem> items, User user) {
        super(context, R.layout.listitem_localhighscoreitem, items);
        this.actionListener = actionListener;
        this.user = user;
    }

    public boolean hasSelectedPosition() {
        return selectedPosition > -1;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.listitem_localhighscoreitem, null);
        }

        LocalHighscoreItem item = getItem(position);

//        if (item.isNew()) {
//            view.setBackgroundResource(R.color.DarkPrimary);
//            selectedPosition = position;
//        } else {
//            view.setBackgroundResource(R.color.Primary);
//        }

        TextView name = (TextView) view.findViewById(R.id.listitem_localhighscoreitem_position);
        name.setText((position + 1) + ".");
        if (item.isNew())
            name.setTextColor(ContextCompat.getColor(getContext(), R.color.Accent));
        else
            name.setTextColor(ContextCompat.getColor(getContext(), R.color.TextIcons));

        TextView date = (TextView) view.findViewById(R.id.listitem_localhighscoreitem_date);
        SimpleDateFormat f = new SimpleDateFormat(getContext().getString(R.string.dateformat));
        date.setText(f.format(item.getDate()));
        if (item.isNew())
            date.setTextColor(ContextCompat.getColor(getContext(), R.color.Accent));
        else
            date.setTextColor(ContextCompat.getColor(getContext(), R.color.TextIcons));

        TextView highScore = (TextView) view.findViewById(R.id.listitem_localhighscoreitem_highscore);
        highScore.setText(item.getHighscore() + "");
        if (item.isNew())
            highScore.setTextColor(ContextCompat.getColor(getContext(), R.color.Accent));
        else
            highScore.setTextColor(ContextCompat.getColor(getContext(), R.color.TextIcons));

        return view;
    }

}
