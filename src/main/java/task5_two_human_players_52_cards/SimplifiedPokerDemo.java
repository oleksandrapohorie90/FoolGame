package task5_two_human_players_52_cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimplifiedPokerDemo {
    private static DeckOfCards cards;
    private static Player player1 = new Player(cards, "Alex", 0);
    private static List<Card> cardsOnHand = new ArrayList<>(Arrays.asList(
            new Card("Diamonds", 10),
            new Card("Diamonds", 9)
    ));
    private static List<Card> faceUpCards = new ArrayList<>(Arrays.asList(
            new Card("Diamonds", 8),
            new Card("Diamonds", 6),
            new Card("Diamonds", 11),
            new Card("Hearts", 15),
            new Card("Hearts", 14)
    ));

    public static void main(String[] args) {
        cards = new DeckOfCards();
        //System.out.println("The amount of cards in the main deck is: " + cards.cards.size());

        Combinations combinations = new Combinations();
        List<Card> royalFlush = combinations.getRoyalFlush(cardsOnHand, faceUpCards);
        List<Card> straightFlush = combinations.getStraightFlush(cardsOnHand, faceUpCards);

        System.out.println("The size of RoyalFlush list is: " + royalFlush.size());
        if (royalFlush.size() == 5) {
            System.out.println("The player has Royal Flush!");
        }

        System.out.println("The size of StraightFlush list is: " + straightFlush.size());
        if (straightFlush.size() == 5) {
            System.out.println("The player has Straight Flush!");
        }

    }
}
