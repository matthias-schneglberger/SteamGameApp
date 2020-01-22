package at.htlgkr.steamgameapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import at.htlgkr.steam.Game;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class GameAdapter extends BaseAdapter{

    Context context;
    int listViewItemLayoutId;
    List<Game> games;
    private LayoutInflater inflater;

    public GameAdapter(Context context, int listViewItemLayoutId, List<Game> games) {
        this.context = context;
        this.listViewItemLayoutId = listViewItemLayoutId;
        this.games = games;

        this.inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Object getItem(int position) {
        return games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View givenView, ViewGroup parent) {

        Game game = games.get(position);
        View listItem = (givenView == null) ? inflater.inflate(this.listViewItemLayoutId, null) : givenView;
        ((TextView) listItem.findViewById(R.id.gameName)).setText(game.getName());

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        ((TextView) listItem.findViewById(R.id.gameDate)).setText(format.format(game.getReleaseDate()));
        ((TextView) listItem.findViewById(R.id.gamePrice)).setText(String.valueOf(game.getPrice()));
        return listItem;
    }
}
