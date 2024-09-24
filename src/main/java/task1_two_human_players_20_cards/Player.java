package task1_two_human_players_20_cards;

import java.util.ArrayList;
import java.util.List;

public class Player {
    List<Card> deck;

    public Player() {
        this.deck = new ArrayList<>();
    }

    //print the deckOfCards
    public void printDeckOfCards() {
        System.out.println("The deck has: " + deck.size() + " cards");
        for (Card card : deck) {
            System.out.println("The card suit is: " + card.suit + "\nThe card rank is: " + card.rank);
        }
    }
}
