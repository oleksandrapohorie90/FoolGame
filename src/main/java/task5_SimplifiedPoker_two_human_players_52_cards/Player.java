package task5_SimplifiedPoker_two_human_players_52_cards;

import java.util.ArrayList;
import java.util.List;

public class Player {

    List<Card> deckOfCardsOnHand;
    boolean turn;
    DeckOfCards deckOfCardsMain;
    String name;
    int index;

    public Player(DeckOfCards deckOfCardsMain, String name, int index) {
        this.deckOfCardsOnHand = new ArrayList<>();
        this.deckOfCardsMain = deckOfCardsMain;
        this.name = name;
        this.index = index;
    }

    //players draw cards - 6 each
    public void drawCard(int numberOfCards) {
        deckOfCardsOnHand.addAll(deckOfCardsMain.drawCard(numberOfCards));
    }

    public boolean hasJokerCard() {
        for (Card card : deckOfCardsOnHand) {
            if (card.getSuit().equals("Joker")) {
                return true; //I have joker cards I set it to true
            }
        }
        return false;
    }

    //attack and remove, the attacking card can be any
    public Card getAttackingCard() {
        if (deckOfCardsOnHand.isEmpty()) {
            System.out.println("Your deck is empty");
            return null;
        }
        return deckOfCardsOnHand.removeFirst(); //this will allow to make a turn with first card
    }


}




