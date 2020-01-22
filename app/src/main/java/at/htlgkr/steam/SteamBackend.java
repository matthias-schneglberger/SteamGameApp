package at.htlgkr.steam;


import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SteamBackend {
    List<Game> gameList = new ArrayList<>();
    private final String TAG = "SteamBackend";

    public SteamBackend() {
        // Implementieren Sie diesen Konstruktor.
    }

    public void loadGames(InputStream inputStream) {
        // Implementieren Sie diese Methode.
        // Diese methode lädt alle Games in eine Variable welche sich im Steam Backend befinden muss.

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        br.lines().skip(1).forEach(a -> {
            String[] parts = a.split(";");

            String name = parts[0];
            String date = parts[1];
            String price = parts[2];

            SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");

            try {
                gameList.add(new Game(name, formater.parse(date), Double.valueOf(price)));
            } catch (ParseException e) {
                Log.e(TAG, "loadGames: Error parsing: " + a);
            }
        });

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void store(OutputStream fileOutputStream) {
        // Implementieren Sie diese Methode.
        // Diese methode schreibt alle Games in den fileOutputStream.

        PrintWriter outPrintWriter = new PrintWriter(new OutputStreamWriter(fileOutputStream));

        gameList.forEach(a -> outPrintWriter.write(a.toCsvString() + "\n"));
        outPrintWriter.flush();
    }

    public List<Game> getGames() {
        return gameList;
    }

    public void setGames(List<Game> games) {
        this.gameList = games;
    }

    public void addGame(Game newGame) {
        gameList.add(newGame);
    }

    public double sumGamePrices() {
        return gameList.stream().mapToDouble(a -> a.getPrice()).sum();
    }

    public double averageGamePrice() {
        // Implementieren Sie diese Methode mit Streams.
        return gameList.stream().mapToDouble(a -> a.getPrice()).average().getAsDouble();
    }

    public List<Game> getUniqueGames() {
        // Implementieren Sie diese Methode mit Streams.
        return gameList.stream().distinct().collect(Collectors.toList());
    }

    public List<Game> selectTopNGamesDependingOnPrice(int n) {
        // Implementieren Sie diese Methode mit Streams.
        return gameList.stream().sorted((a,b) -> (int)(b.getPrice()-a.getPrice())).limit(n).collect(Collectors.toList());
    }
}
