package task1_two_human_players_20_cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {

    //maybe use Map, no need to create a Card class ?
    List<Card> cards;
    Card trumpCard;
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    String[] ranks = {"6", "7", "8", "9", "10"};

    public DeckOfCards() {
        this.cards = new ArrayList<>();
    }

    //20 cards
    public List<Card> addCards() {
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        //to mix all cards
        Collections.shuffle(cards);

        return cards;
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
    public List<Card> drawCard(int amountOfCards) throws Exception {
        List<Card> playerDeckOfCards = new ArrayList<>();
        if (!cards.isEmpty()) {
            for (int i = 0; i < amountOfCards; i++) {
                playerDeckOfCards.add(cards.getLast());
                cards.remove(cards.getLast());
            }
        }
        return playerDeckOfCards;
    }


}
