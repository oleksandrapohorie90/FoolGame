package task5_two_human_players_52_cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
    //main deck of cards
    List<Card> cards;
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    Integer[] ranks = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public DeckOfCards() {
        this.cards = new ArrayList<>();
        addCards();
        System.out.println("The amount of cards in the main deck is: " + cards.size());
    }

    public void printDeckOfCards() {
        for (Card card : cards) {
            System.out.println("The card suit is: " + card.getSuit() + "\nThe card rank is: " + card.getRank());
        }
    }

    public List<Card> addCards() {
        for (String suit : suits) {
            for (Integer rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }

    public List<Card> drawCard(int amountOfCards) {
        List<Card> playerDeckOfCards = new ArrayList<>();
        for (int i = 0; i < amountOfCards; i++) {
            if (cards.isEmpty()) {
                break;
            }
            playerDeckOfCards.add(cards.getLast());
            cards.remove(cards.getLast());
        }
        return playerDeckOfCards;
    }


}