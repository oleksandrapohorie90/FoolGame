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

    List<Card> deckOfCardsOnHand;
    boolean turn;
    DeckOfCards deckOfCardsMain;
    String name;

    public Player(DeckOfCards deckOfCardsMain, String name) {
        this.deckOfCardsOnHand = new ArrayList<>();
        this.deckOfCardsMain = deckOfCardsMain;
        this.name = name;
    }

    //players draw cards - 6 each
    public void drawCard(int numberOfCards) {
        deckOfCardsOnHand.addAll(deckOfCardsMain.drawCard(numberOfCards));
    }

    //player gets lowest card to decide who attacks first
    //iterate through the list
    //use a trump card as a parameter
    //ignore all the cards that are not trump
    public Card getLowestCard(Card trumpCard) {
        List<Card> cards = new ArrayList<>();
        System.out.println("The number of deck cards: " + deckOfCardsOnHand.size());
        //iterate through players cards - 6
        for (Card card : deckOfCardsOnHand) {
            if (card.suit.equals(trumpCard.suit)) {
                cards.add(card); //only trump cards
            }
        }
        System.out.println("The number of trumpCard cards: " + cards.size());
        cards = cards.isEmpty() ? deckOfCardsOnHand : cards;

        //search for the lowest trump card if any was added to the cards list, to get the minimum value from the list
        return Collections.min(cards, Comparator.comparingInt(Card::getRank));
    }


    //print the deckOfCards
    public void printDeckOfCards() {
        System.out.println("The deck has: " + deckOfCardsOnHand.size() + " cards");
        for (Card card : deckOfCardsOnHand) {
            System.out.println("The card suit is: " + card.suit + "\nThe card rank is: " + card.rank);
        }
    }

    //attack and remove
    public Card getAttackingCard() {
        if (deckOfCardsOnHand.isEmpty()) {
            System.out.println("Your deck is empty");
            return null;
        }
        return deckOfCardsOnHand.removeFirst(); //this will allow to make a turn with first card

    }
//TODO: compare suits
    public boolean getDefendingCard(Card attackingCard) {
        boolean status = false;
        for (int i = 0; i < deckOfCardsOnHand.size(); i++) {
            //System.out.println("The rank of attacking card" + attackingCard.rank + " the rank of defending card " + deckOfCardsOnHand.get(i).rank);
            if (deckOfCardsOnHand.get(i).rank > attackingCard.rank) { //I was able to defend
                deckOfCardsOnHand.remove(i);
                status = true;
                break;
            }
        }
        if (!status) {
            deckOfCardsOnHand.add(attackingCard); // only add if no defense is possible
        }
        return status;

    }

}
