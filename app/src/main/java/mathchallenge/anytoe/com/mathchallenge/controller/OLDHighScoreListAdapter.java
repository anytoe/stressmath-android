package mathchallenge.anytoe.com.mathchallenge.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import mathchallenge.anytoe.com.mathchallenge.R;
import mathchallenge.anytoe.com.mathchallenge.model.highscore.HighScore;

/**
 * Created by anytoe on 09/10/2015.
 */
public class OLDHighScoreListAdapter{ //} extends ArrayAdapter<HighScore.HighScoreItem> {

//    private TextView.OnEditorActionListener actionListener;
//
//    public OLDHighScoreListAdapter(Context context, TextView.OnEditorActionListener actionListener, List<HighScore.HighScoreItem> items) {
//        super(context, R.layout.old_listitem_highscoreitem, items);
//        this.actionListener = actionListener;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View view = convertView;
//
//        if (view == null) {
//            LayoutInflater vi = LayoutInflater.from(getContext());
//            view = vi.inflate(R.layout.old_listitem_highscoreitem, null);
//        }
//
//        HighScore.HighScoreItem item = getItem(position);
//
//        if (item.isUser)
//            view.setBackgroundColor(getContext().getResources().getColor(R.color.Accent));
//        else
//            view.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
//
//        TextView ranking = (TextView) view.findViewById(R.id.listitem_highscoreitem_ranking);
//        ranking.setText(item.ranking + ".");
//
//        TextView name = (TextView) view.findViewById(R.id.listitem_highscoreitem_name);
//        EditText nameEditable = (EditText) view.findViewById(R.id.listitem_highscoreitem_nameeditable);
//        if (!item.isEditableUser) {
//            name.setText(item.displayName);
//            name.setVisibility(View.VISIBLE);
//            nameEditable.setVisibility(View.GONE);
//        } else {
//            nameEditable.setText(item.displayName);
//            nameEditable.setVisibility(View.VISIBLE);
//            nameEditable.setOnEditorActionListener(actionListener);
//            name.setVisibility(View.GONE);
//        }
//
//        TextView highScore = (TextView) view.findViewById(R.id.listitem_highscoreitem_highscore);
//        highScore.setText(item.highScore + "");
//
//        return view;
//    }

}
