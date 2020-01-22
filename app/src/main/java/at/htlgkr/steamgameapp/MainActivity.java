package at.htlgkr.steamgameapp;

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.htlgkr.steam.Game;
import at.htlgkr.steam.ReportType;
import at.htlgkr.steam.SteamBackend;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private static final String GAMES_CSV = "games.csv";
    private List<Game> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       SteamBackend backend = new SteamBackend();


        backend.loadGames(getInputStreamForAsset(GAMES_CSV));


        gameList = backend.getGames();
        Log.d(TAG, "onCreate: Games in List: " + gameList.toString());

        loadGamesIntoListView();
        setUpReportSelection();
        setUpSearchButton();
        setUpAddGameButton();
        setUpSaveButton();
    }

    private void loadGamesIntoListView() {

        ListView gameListView = findViewById(R.id.gameList);
        GameAdapter adapter = new GameAdapter(getApplicationContext(), R.layout.game_item_layout, gameList);
        gameListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void setUpReportSelection() {
        Spinner spinner = findViewById(R.id.chooseReport);

        ArrayAdapter<String> spinnItems = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item);
        spinnItems.add(new ReportTypeSpinnerItem(ReportType.NONE, SteamGameAppConstants.SELECT_ONE_SPINNER_TEXT).toString());
        spinnItems.add(new ReportTypeSpinnerItem(ReportType.SUM_GAME_PRICES, SteamGameAppConstants.SUM_GAME_PRICES_SPINNER_TEXT).toString());
        spinnItems.add(new ReportTypeSpinnerItem(ReportType.AVERAGE_GAME_PRICES, SteamGameAppConstants.AVERAGE_GAME_PRICES_SPINNER_TEXT).toString());
        spinnItems.add(new ReportTypeSpinnerItem(ReportType.UNIQUE_GAMES, SteamGameAppConstants.UNIQUE_GAMES_SPINNER_TEXT).toString());
        spinnItems.add(new ReportTypeSpinnerItem(ReportType.MOST_EXPENSIVE_GAMES, SteamGameAppConstants.MOST_EXPENSIVE_GAMES_SPINNER_TEXT).toString());


        spinner.setAdapter(spinnItems);
        spinnItems.notifyDataSetChanged();
    }

    private void setUpSearchButton() {
        // Implementieren Sie diese Methode.
    }

    private void setUpAddGameButton() {
        // Implementieren Sie diese Methode.
    }

    private void setUpSaveButton() {
        // Implementieren Sie diese Methode.
    }


    private InputStream getInputStreamForAsset(String filename) {
        // tries to open Stream on Assets. If fails, returns null
        Log.d(TAG, "getInputStreamForAsset: " + filename);
        AssetManager assets = getAssets();
        try {
            return assets.open(filename);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
