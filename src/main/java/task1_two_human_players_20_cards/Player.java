package task1_two_human_players_20_cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player {
    //Player
    //	Attributes
    //		deck
    //      turn

    //	Methods
    //		drawCard +
    //      showLowestCard +
    //      compareRanks +
    //      attack
    //      defend +

    List<Card> deck;
    boolean turn;
    DeckOfCards deckOfCards;
    String name;

    public Player(DeckOfCards deckOfCards, String name) {
        this.deck = new ArrayList<>();
        this.deckOfCards = deckOfCards;
        this.name = name;
    }

    //players draw cards - 6 each
    public void drawCard(int numberOfCards) {
        deck.addAll(deckOfCards.drawCard(numberOfCards));
        deckOfCards.addCards();
    }

    //player gets lowest card to decide who attacks first
    //iterate through the list
    //use a trump card as a parameter
    //ignore all the cards that are not trump
    public Card getLowestCard(Card trumpCard) {
        List<Card> cards = new ArrayList<>();
        Card tempCard = null;
        System.out.println("The number of deck cards: " + deck.size());
        //iterate through players cards - 6
        for (Card card : deck) {
            if (card.suit.equals(trumpCard.suit)) {
                cards.add(card);
            }
        }
        System.out.println("The number of trumpCard cards: " + cards.size());
        cards = cards.isEmpty() ? deck : cards;

        if (cards.size() == 1) {
            tempCard = cards.getFirst();
        }

//        tempCard.rank = cards.getFirst().rank;
//        for (int i = 1; i < cards.size(); i++) {
//           if (cards.get(i).rank< tempCard.rank) {
//              tempCard.rank = cards.get(i).rank;
//           }
//        }

        return Collections.min(cards, Comparator.comparingInt(Card::getRank));
    }


    //print the deckOfCards
    public void printDeckOfCards() {
        System.out.println("The deck has: " + deck.size() + " cards");
        for (Card card : deck) {
            System.out.println("The card suit is: " + card.suit + "\nThe card rank is: " + card.rank);
        }
    }

    //attack and remove
    public Card getAttackingCard() {
        if (deck.isEmpty()) {
            System.out.println("Your deck is empty");
            return null;
        }
        return deck.removeFirst(); //this will allow to make a turn with first card

    }

    public boolean getDefendingCard(Card attackingCard) {
        if (deck.isEmpty()) {
            System.out.println("Your deck is empty");
            return false;
        }
        boolean status = false;
        for (int i = 0; i <deck.size() ; i++) {
            if(deck.get(i).rank > attackingCard.rank){
                deck.remove(i);
                status = true;
            } else {
                deck.add(attackingCard);
                status = false;
            }
        }

        return status;

    }

}
