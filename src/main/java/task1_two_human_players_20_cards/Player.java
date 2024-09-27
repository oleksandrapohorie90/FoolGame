package task1_two_human_players_20_cards;

import java.util.ArrayList;
import java.util.List;

public class Player {
    //Player
    //	Attributes
    //		deck
    //
    //	Methods
    //		drawCard +
    //      showLowestCard +
    //      compareRanks +
    //      attack
    //      defend

    List<Card> deck;
    DeckOfCards deckOfCards = new DeckOfCards();

    public Player() {
        this.deck = new ArrayList<>();
    }

    //players draw cards
    public void drawCard(int numberOfCards) {
        deck.addAll(deckOfCards.drawCard(numberOfCards));
    }

    //player shows lowest card to decide who attacks first
    //iterate through the list
    //use a trump card as a parameter
    //ignore all the cards that are not trump
    public Card showLowestCard(Card trumpCard) {
        List<Card> cards = new ArrayList<>();
        Card tempCard = null;

        for (Card card : deck) {
            if (card.suit.equals(trumpCard.suit)) {
                cards.add(card);
            }
        }
        cards = cards.isEmpty() ? deck : cards;

        for (int i = 0; i < cards.size() - 1; i++) {
            tempCard = compareRanks(cards.get(i), cards.get(i++));
        }
        return tempCard;
    }

    //compare ranks when attacking
    public Card compareRanks(Card first, Card next) {
        if (first.rank > next.rank) {
            return first;
        }
        return next;
    }


    //print the deckOfCards
    public void printDeckOfCards() {
        System.out.println("The deck has: " + deck.size() + " cards");
        for (Card card : deck) {
            System.out.println("The card suit is: " + card.suit + "\nThe card rank is: " + card.rank);
        }
    }


    //attack and remove
    public void attack() {
        if (deck.isEmpty()) {
            System.out.println("Your deck is empty");
        } else {
            deck.removeFirst(); //?
        }
    }

    public void defend() {

    }

}
