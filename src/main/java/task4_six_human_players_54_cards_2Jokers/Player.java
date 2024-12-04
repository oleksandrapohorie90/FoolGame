package task4_six_human_players_54_cards_2Jokers;

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

    //instead of using 2 objects use a list of 2 Players

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

    //player gets lowest card to decide who attacks first
    //iterate through the list
    //use a trump card as a parameter
    //ignore all the cards that are not trump
    public Card getLowestCard() {
        List<Card> cards = getTrumpCards();
        //search for the lowest trump card if any was added to the cards list, to get the minimum value from the list
        return Collections.min(cards, Comparator.comparingInt(Card::getRank));
    }

    //gets only trump cards OR return current list of cards and put it in the list
    public List<Card> getTrumpCards() {
        List<Card> cards = new ArrayList<>();
        for (Card card : deckOfCardsOnHand) {
            if (card.suit.equals(deckOfCardsMain.trumpCard.suit)) {
                cards.add(card); //only trump cards in this list if any found
            }
        }
        cards = cards.isEmpty() ? deckOfCardsOnHand : cards;
        return cards;
    }

    public boolean hasTrumpCards(String trumpCardSuit) {
        for (Card card : deckOfCardsOnHand) {
            if (card.suit.equals(trumpCardSuit)) {
                return true; //I have trump cards I set it to true
            }
        }
        return false;
    }

    //print the deckOfCards
    public void printDeckOfCards() {
        //System.out.println("The deck has: " + deckOfCardsOnHand.size() + " cards");
        for (Card card : deckOfCardsOnHand) {
            //System.out.println("The card suit is: " + card.suit + "\nThe card rank is: " + card.rank);
        }
    }

    //attack and remove, the attacking card can be any
    public Card getAttackingCard() {
        if (deckOfCardsOnHand.isEmpty()) {
            System.out.println("Your deck is empty");
            return null;
        }
        return deckOfCardsOnHand.removeFirst(); //this will allow to make a turn with first card
    }

    /*
   method to compare 2 cards and also pass the trump Card(suit) , method returns true false , loop through defender card and find first matching card that will beat the attacking card of the attacker player -
     who has the lowest card, then that person
    */

    public boolean getDefendingCard(Card attackingCard, Card defendingCard, String trumpCardSuit) {
        //Im checking the attacker card if its trump and Im checking the defender cards if there is any trump
        if (attackingCard.suit.equals(trumpCardSuit) && hasTrumpCards(trumpCardSuit)) {
            //we are comparing only trump cards
            return (defendingCard.rank > attackingCard.rank) && (defendingCard.suit.equals(trumpCardSuit)); // I was able to defend
            //if my trump card rank is lower - I still take the card
        } else if (!attackingCard.suit.equals(trumpCardSuit) && hasTrumpCards(trumpCardSuit)) {
            //Im checking if I have any card that is matching the suit of attacking card and is higher in rank then that, if there is any then I keep my trumpCard for later, if not then I have to use my trumpCard to beat attacking card
            //In this case it will only compare the 1st cards rank, not go through all of the cards ranks
            if ((defendingCard.suit.equals(attackingCard.suit)) && (defendingCard.rank > attackingCard.rank)) {
                return true; // I was able to defend
            } else {//now I need to use my trump card, it may remove the trump card or it may remove not the trump one
                //if its false it jumps back in
                return defendingCard.suit.equals(trumpCardSuit); // I was able to defend
            }
        } else {
            //I was able to defend
            return (defendingCard.suit.equals(attackingCard.suit)) && (defendingCard.rank > attackingCard.rank);
        }
    }

    //used to iterate and call getDefendingCard
    public boolean iterateCards(Card attackingCard, List<Card> cardsOnHand, String trumpCardSuit) {
        boolean status = false;
        for (int i = 0; i < cardsOnHand.size(); i++) {
            status = getDefendingCard(attackingCard, cardsOnHand.get(i), trumpCardSuit);
            if (status) {
                cardsOnHand.remove(i);
                break;
            }
        }
        if (!status) {
            cardsOnHand.add(attackingCard); // only add if no defense is possible
        }
        return status;
    }
}


/**
 * public boolean getDefendingCard(Card attackingCard, String trumpCardSuit) {
 * boolean status = false; //this will show whose turn it is
 * <p>
 * //Im checking the attacker card if its trump and Im checking the defender cards if there is any trump
 * if (attackingCard.suit.equals(trumpCardSuit) && hasTrumpCards(trumpCardSuit)) {
 * for (int i = 0; i < deckOfCardsOnHand.size(); i++) {
 * //TODO:Explain this later, implemented to get rid of +1 card on attacking player
 * if ((deckOfCardsOnHand.get(i).rank > attackingCard.rank) && (deckOfCardsOnHand.get(i).suit.equals(trumpCardSuit))) { //we are comparing only trump cards
 * deckOfCardsOnHand.remove(i);
 * status = true; // I was able to defend
 * break;
 * }
 * //if my trump card rank is lower - I still take the card
 * }
 * } else if (!attackingCard.suit.equals(trumpCardSuit) && hasTrumpCards(trumpCardSuit)) {
 * //Im checking if I have any card that is matching the suit of attacking card and is higher in rank then that, if there is any then I keep my trumpCard for later, if not then I have to use my trumpCard to beat attacking card
 * for (int i = 0; i < deckOfCardsOnHand.size(); i++) {
 * //In this case it will only compare the 1st cards rank, not go through all of the cards ranks
 * if ((deckOfCardsOnHand.get(i).suit.equals(attackingCard.suit)) && (deckOfCardsOnHand.get(i).rank > attackingCard.rank)) {
 * deckOfCardsOnHand.remove(i);
 * status = true; // I was able to defend
 * break;
 * } else {//now I need to use my trump card, it may remove the trump card or it may remove not the trump one
 * if(deckOfCardsOnHand.get(i).suit.equals(trumpCardSuit)){
 * deckOfCardsOnHand.remove(i);
 * status = true; // I was able to defend
 * break;
 * }//if its false it jumps back in
 * }
 * }
 * } else {
 * for (int i = 0; i < deckOfCardsOnHand.size(); i++) {
 * if ((deckOfCardsOnHand.get(i).suit.equals(attackingCard.suit)) && (deckOfCardsOnHand.get(i).rank > attackingCard.rank)) { //I was able to defend
 * deckOfCardsOnHand.remove(i);
 * status = true;
 * break;
 * }
 * }
 * }
 * <p>
 * if (!status) {
 * deckOfCardsOnHand.add(attackingCard); // only add if no defense is possible
 * }
 * return status;
 * <p>
 * }
 */

//old version of method
//    public boolean getDefendingCard(Card attackingCard) {
//        boolean status = false; //this will show whose turn it is
//        for (int i = 0; i < deckOfCardsOnHand.size(); i++) {
//            //System.out.println("The rank of attacking card" + attackingCard.rank + " the rank of defending card " + deckOfCardsOnHand.get(i).rank);
//            if (deckOfCardsOnHand.get(i).rank > attackingCard.rank) { //I was able to defend
//                deckOfCardsOnHand.remove(i);
//                status = true;
//                break;
//            }
//        }
//        if (!status) {
//            deckOfCardsOnHand.add(attackingCard); // only add if no defense is possible
//        }
//        return status;
//
//    }


