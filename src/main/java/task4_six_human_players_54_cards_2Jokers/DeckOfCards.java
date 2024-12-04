package task4_six_human_players_54_cards_2Jokers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {
    //main deck of cards
    List<Card> cards;
    Card trumpCard;
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    Integer[] ranks = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public DeckOfCards() {
        this.cards = new ArrayList<>();
        addCards();
        System.out.println("The amount of cards in the main deck is: " + cards.size());
    }

    //print the deckOfCards
    public void printDeckOfCards() {
        for (Card card : cards) {
            System.out.println("The card suit is: " + card.suit + "\nThe card rank is: " + card.rank);
        }
    }

    //20 cards
    public List<Card> addCards() {
        for (String suit : suits) {
            for (Integer rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        //to mix all cards
        Collections.shuffle(cards);

        return cards;
    }

    //to add a joker card
    public void addJokerCard(int numberOfJokers) {
        for (int i = 0; i < numberOfJokers; i++) {
            cards.add(new Card("Joker", 15));
        }
    }

    //to choose the trumpCard
    public void chooseTrumpCard() {
        //taking one card to be trumpCard to choose suit - kozir'
        trumpCard = cards.getLast();
        //now collecting all the cards that are the same suit as above - kozirnie
        for (Card card : cards) {
            if (card.suit.equals(trumpCard.suit)) {
                card.isTrump = true;
            }
        }
        System.out.println("The trumpCard is " + trumpCard);
    }

    //to draw the card and add it to the players deck
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
