package task5_SimplifiedPoker_two_human_players_52_cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimplifiedPokerDemo {
    private static DeckOfCards cards = new DeckOfCards();
    ;
    private static Player player1 = new Player(cards, "Alex", 0);
    private static final List<Card> cardsOnHand = new ArrayList<>(Arrays.asList(
            new Card("Diamonds", 15),
            new Card("Clubs", 9)
    ));
    private static final List<Card> faceUpCards = new ArrayList<>(Arrays.asList(
            new Card("Spades", 11),
            new Card("Hearts", 13),
            new Card("Diamonds", 5),
            new Card("Diamonds", 2),
            new Card("Spades", 3)
    ));

    public static void main(String[] args) {

        //System.out.println("The amount of cards in the main deck is: " + cards.cards.size());

        Combinations combinations = new Combinations();
        List<Card> royalFlush = combinations.getRoyalFlush(cardsOnHand, faceUpCards);
        List<Card> straightFlush = combinations.getStraightFlush(cardsOnHand, faceUpCards);
        List<Card> fourOfAkind = combinations.getFourOfAKind(cardsOnHand, faceUpCards);
        List<Card> fullHouse = combinations.getFullHouse(cardsOnHand, faceUpCards);
        List<Card> flush = combinations.getFlush(cardsOnHand, faceUpCards);
        List<Card> straight = combinations.getStraight(cardsOnHand, faceUpCards);
        List<Card> threeOfAKind = combinations.getThreeOfAKind(cardsOnHand, faceUpCards);
        List<Card> twoPair = combinations.getTwoPair(cardsOnHand, faceUpCards);
        List<Card> onePair = combinations.getOnePair(cardsOnHand, faceUpCards);
        List<Card> highCard = combinations.getHighCard(cardsOnHand, faceUpCards);

        //System.out.println("The size of FourOfAKind list is: " + fourOfAkind.size());
        //System.out.println("The size of RoyalFlush list is: " + royalFlush.size());
        if (!royalFlush.isEmpty()) {
            System.out.println("The player has Royal Flush!");
        } else if (!straightFlush.isEmpty()) {
            System.out.println("The size of StraightFlush list is: " + straightFlush.size());
            System.out.println("The player has Straight Flush!");
        } else if (!fourOfAkind.isEmpty()) {
            System.out.println("The player has FourOfAKind!");
        } else if (!fullHouse.isEmpty()) {
            System.out.println("The player has FullHouse!");
        } else if (!flush.isEmpty()) {
            System.out.println("The player has Flush!");
        } else if (!straight.isEmpty()) {
            System.out.println("The player has Straight!");
        } else if (!threeOfAKind.isEmpty()) {
            System.out.println("The player has ThreeOfAKind!");
        } else if (!twoPair.isEmpty()) {
            System.out.println("The player has TwoPair!");
        } else if (!onePair.isEmpty()) {
            System.out.println("The player has OnePair!");
        } else if (!highCard.isEmpty()) {
            System.out.println("The player has a HighCard!");
        } else {
            System.out.println("There is no valid combinations!");
        }

    }
}
