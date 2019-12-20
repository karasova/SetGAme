package com.example.setgame;

import android.util.Log;

public class CardsView  {
    Card[][] cards = new Card[4][3];
    CardsResponse cards_response;

    public CardsView(CardsResponse cards_response) {
        this.cards_response = cards_response;
    }

    public Card[][] fillCards() {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++ ) {
                cards [i][j] = cards_response.cards.get(num);
                Log.i("CARDS", String.valueOf(cards[i][j]));
                num ++;
            }
        }
        return cards;
    }
}
