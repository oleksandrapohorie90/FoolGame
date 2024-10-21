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
        Card tempCard = null;
        System.out.println("The number of deck cards: " + deckOfCardsOnHand.size());
        //iterate through players cards - 6
        for (Card card : deckOfCardsOnHand) {
            if (card.suit.equals(trumpCard.suit)) {
                cards.add(card);
            }
        }
        System.out.println("The number of trumpCard cards: " + cards.size());
        cards = cards.isEmpty() ? deckOfCardsOnHand : cards;

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

    public boolean getDefendingCard(Card attackingCard) {
        if (deckOfCardsOnHand.isEmpty()) {
            System.out.println("Your deck is empty");
            return false;
        }
        boolean status = false;
        for (int i = 0; i < deckOfCardsOnHand.size() ; i++) {
            if(deckOfCardsOnHand.get(i).rank > attackingCard.rank){
                deckOfCardsOnHand.remove(i);
                status = true;
            } else {
                deckOfCardsOnHand.add(attackingCard);
                status = false;
            }
        }

        return status;

    }

}
