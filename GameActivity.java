package com.example.setgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.TextView;

import com.example.setgame.Server.CardsFetch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity {
    int token;
    TextView card;
    CardsResponse cards;
    SharedPreferences preferences;
    Card[][] cards_view;
    ArrayList<TextView> views = new ArrayList<TextView>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.i("CARDS", "hello");
//        Intent i = getIntent();
        card = findViewById(R.id.card11);
        views.add(card);
        card = findViewById(R.id.card12);
        views.add(card);
        card = findViewById(R.id.card13);
        views.add(card);
        card = findViewById(R.id.card21);
        views.add(card);
        card = findViewById(R.id.card22);
        views.add(card);
        card = findViewById(R.id.card23);
        views.add(card);
        card = findViewById(R.id.card31);
        views.add(card);
        card = findViewById(R.id.card32);
        views.add(card);
        card = findViewById(R.id.card33);
        views.add(card);
        card = findViewById(R.id.card41);
        views.add(card);
        card = findViewById(R.id.card42);
        views.add(card);
        card = findViewById(R.id.card43);
        views.add(card);

        preferences = getSharedPreferences("token_names",Context.MODE_PRIVATE);
        token = preferences.getInt("token", -1);
        CardsFetch fetch = new CardsFetch();
        String fetch_str = "{\"action\": \"fetch_cards\", \"token\": " + token + "}";
        Log.d("CARDS", "hello");
        fetch.execute(new Pair<String, Function<Response, Void>>(fetch_str, new Function<Response, Void>(){
            @Override
            public Void apply(Response response) {
                cards = (CardsResponse) response;
                Log.i("CARDS", String.valueOf(cards));
                CardsView cards_view = new CardsView(cards);
                Card[][] cards_text = cards_view.fillCards();
                int i = 0;
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 3; y++) {
                        views.get(i).setText("fill = " + cards_view.cards[x][y].fill + "\ncount = " + cards_view.cards[x][y].count + "\nshape = " +
                                cards_view.cards[x][y].shape + "\ncolor = " + cards_view.cards[x][y].color);
                        i++;
                    }
                }
                return null;
            }
        }));

    }
}
