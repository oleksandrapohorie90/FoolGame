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

    public boolean hasTrumpCards() {
        for (Card card : deckOfCardsOnHand) {
            if (card.suit.equals(deckOfCardsMain.trumpCard.suit)) {
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

    public boolean getDefendingCard(Card attackingCard) {
        boolean status = false; //this will show whose turn it is
        //List<Card> cards = getTrumpCards(); -> we were removing a card from temporary list but we need to remove from deck of cards on hand

        //Im checking the attacker card if its trump and Im checking the defender cards if there is any trump
        if (attackingCard.suit.equals(deckOfCardsMain.trumpCard.suit) && hasTrumpCards()) {
            for (int i = 0; i < deckOfCardsOnHand.size(); i++) {
                //TODO:Explain this later, implemented to get rid of +1 card on attacking player
                if ((deckOfCardsOnHand.get(i).rank > attackingCard.rank) && (deckOfCardsOnHand.get(i).suit.equals(deckOfCardsMain.trumpCard.suit))) { //we are comparing only trump cards
                    deckOfCardsOnHand.remove(i);
                    status = true; // I was able to defend
                    break;
                }
                //if my trump card rank is lower - I still take the card
            }
        } else if (!attackingCard.suit.equals(deckOfCardsMain.trumpCard.suit)) {
            //Im checking if I have any card that is matching the suit of attacking card and is higher in rank then that, if there is any then I keep my trumpCard for later, if not then I have to use my trumpCard to beat attacking card
            for (int i = 0; i < deckOfCardsOnHand.size(); i++) {
                if ((deckOfCardsOnHand.get(i).suit.equals(attackingCard.suit)) && (deckOfCardsOnHand.get(i).rank > attackingCard.rank)) {
                    deckOfCardsOnHand.remove(i);
                    status = true; // I was able to defend and kept my trump card since I have the same suit card and it has a higher rank
                    break;
                } else {
                    for (int j = 0; j < deckOfCardsOnHand.size(); j++) {
                        if(deckOfCardsOnHand.get(j).suit.equals(deckOfCardsMain.trumpCard.suit)){
                            //now I need to use my trump card, it may remove the trump card or it may remove not the trump one, so I need to check for trump only
                            deckOfCardsOnHand.remove(j);
                            status = true; // I was able to defend
                            break;
                        }
                    }
                }
                //the 3rd condition is I neither have the matching suite in higher rank or I don't have a trump card to defend
            }
      }
        //else {
//            for (int i = 0; i < deckOfCardsOnHand.size(); i++) {
//                if (deckOfCardsOnHand.get(i).rank > attackingCard.rank) { //I was able to defend
//                    deckOfCardsOnHand.remove(i);
//                    status = true;
//                    break;
//                }else{
//                    System.out.println("Else 2");
//                }
//            }
//        }

        if (!status) {
            deckOfCardsOnHand.add(attackingCard); // only add if no defense is possible
        }
        return status;

    }
}


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


