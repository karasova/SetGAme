package com.example.setgame;

import java.util.List;

public class CardsResponse extends Response{
    List<Card> cards;

    @Override
    public String toString() {
        return "CardsResponse{" +
                "status='" + status + "'}" + cards;
    }
}


