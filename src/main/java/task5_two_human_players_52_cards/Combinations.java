package task5_two_human_players_52_cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combinations {
    /**
     * Combinations:
     * 1. Royal Flush (A♦ K♦ Q♦ J♦ T♦)
     * The best hand possible, a royal flush consists of A, K, Q, J and 10, all of the same suit.
     * <p>
     * 2. Straight Flush (T♥ 9♥ 8♥ 7♥ 6♥)
     * Also very rare, a straight flush consists of any straight that is all the same suit.
     * <p>
     * 3. Four-of-a-Kind (J♦ J♣ J♠ J♥ K♦)
     * Four of a kind, or 'quads', consists of four cards of equal value along with another card known as a side card.
     * <p>
     * 4. Full House (A♥ A♣ A♦ 9♠ 9♣)
     * A full house consists of three cards of one value and two cards of another.
     * <p>
     * 5. Flush (A♠ J♠ 8♠ 4♠ 3♠)
     * A flush is a hand which has all cards of the same suit.
     * <p>
     * 6. Straight (9♥ 8♠ 7♣ 6♦ 5♣)
     * A straight has 5 cards of consecutive value that are not all the same suit.
     * <p>
     * 7. Three-of-a-Kind (7♠ 7♦ 7♣ K♦ Q♣)
     * Also known as 'trips', three of a kind is 3 cards of the same value and 2 side cards of different values.
     * <p>
     * 8. Two-Pair (9♣ 9♦ 6♣ 6♠ Q♥)
     * ​​Two pair consists of two cards of equal value, another two cards of equal value, and one extra card.
     * <p>
     * 9. One-Pair (A♦ A♥ K♠ 9♦ 4♥)
     * One pair consists of two cards of the same value, and three extra cards.
     * <p>
     * 10. High Card (A♠ J♦ 8♣ 6♠ 2♥)
     * High card is when you have five cards that do not interact with each other to make any of the above hands.
     */
    private List<Card> combinedCards = new ArrayList<>();

    public boolean has5_OfTheSameSuit(List<Card> faceUpCards) {
        int count = 0;
        String suit = faceUpCards.getFirst().getSuit();

        for (int i = 0; i < faceUpCards.size(); i++) {
            if (faceUpCards.get(i).getSuit().equals(suit)) {
                count += 1;
                i++;
            }
        }
        //checking if cards have the same suite and are at least 5
        return count >= 5;
    }

    //the return type can be boolean, will see
    public List<Card> getRoyalFlush(List<Card> cardsOnHand, List<Card> faceUpCards) {
        /** 1. Royal Flush (A♦ K♦ Q♦ J♦ T♦)
         * The best hand possible, a royal flush consists of A, K, Q, J and 10, all of the same suit.
         */
        List<Card> royalFlash = new ArrayList<>();
        faceUpCards.addAll(cardsOnHand);
        if (has5_OfTheSameSuit(faceUpCards)) {
            for (int i = 0; i < faceUpCards.size(); i++) {
                if (faceUpCards.get(i).getRank() == 14 || faceUpCards.get(i).getRank() == 13 || faceUpCards.get(i).getRank() == 12 || faceUpCards.get(i).getRank() == 11 || faceUpCards.get(i).getRank() == 10) {
                    royalFlash.add(faceUpCards.get(i));
                }

            }
        }
        return royalFlash;
    }

    public void getStraightFlush(List<Card> cardsOnHand) {

    }

    public void getFourOfAKind(List<Card> cardsOnHand) {

    }


}
