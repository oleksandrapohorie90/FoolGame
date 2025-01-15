package task5_two_human_players_52_cards;

import java.util.*;
import java.util.stream.Collectors;

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


//    public Map<String, String> getMostRepeatedSuit(List<Card> cards) {
//        Map<String, String> mostRepeatedSuit = new HashMap<>();
//
//        List<String> suits = cards.stream()
//                .map(Card::getSuit)
//                .toList();
//
//        // Find the most repeated suit in the list
//        String mostRepeated = suits.stream()
//                .distinct()
//                .max(Comparator.comparingInt(a -> Collections.frequency(suits, a)))
//                .orElse(null);
//        System.out.println("The most repeated suit is: " + mostRepeated);
//        // Find the amount of repeated suits - must be 5
//        Integer mostRepeatedCount = mostRepeated != null
//                ? Collections.frequency(suits, mostRepeated)
//                : 0;
//        mostRepeatedSuit.put("count", String.valueOf(mostRepeatedCount));
//        mostRepeatedSuit.put("suit", mostRepeated);
//
//        System.out.println("The most repeated suit number is: " + mostRepeatedCount);
//        return mostRepeatedSuit;
//    }
    public Map<String, String> getMostRepeatedSuit(List<Card> cards) {
        Map<String, Long> suitCounts = cards.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));

        return suitCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> {
                    Map<String, String> result = new HashMap<>();
                    result.put("suit", entry.getKey());
                    result.put("count", String.valueOf(entry.getValue()));
                    return result;
                })
                .orElseGet(() -> {
                    Map<String, String> result = new HashMap<>();
                    result.put("suit", null);
                    result.put("count", "0");
                    return result;
                });
    }

    //the return type can be boolean, will see
//    public List<Card> getRoyalFlush(List<Card> cardsOnHand, List<Card> faceUpCards) {
//        /* 1. Royal Flush (A♦ K♦ Q♦ J♦ T♦)
//         * The best hand possible, a royal flush consists of A, K, Q, J and 10, all of the same suit.
//         */
//        List<Card> royalFlash = new ArrayList<>();
//        faceUpCards.addAll(cardsOnHand);
//        System.out.println("All the cards combined are: " + faceUpCards);
//        Map<String, String> map = getMostRepeatedSuit(faceUpCards);
//
//        int mostRepeatedCount = Integer.parseInt(map.get("count"));
//        String mostRepeatedSuit = map.get("suit");
//
//        // Check if we have any of the Royal Flush ranks and if they are of the same suit
//        if (mostRepeatedCount >= 5) {
//            System.out.println("We are in the if in getRoyalFlush");
//            for (Card faceUpCard : faceUpCards) {
//                if (faceUpCard.getRank() == 14 || faceUpCard.getRank() == 13 || faceUpCard.getRank() == 12 || faceUpCard.getRank() == 11 || faceUpCard.getRank() == 10) {
//                    if (faceUpCard.getSuit().equals(mostRepeatedSuit)) {
//                        royalFlash.add(faceUpCard);
//                    }
//                }
//            }
//        }
//
//        return royalFlash;
//    }

    public List<Card> getRoyalFlush(List<Card> cardsOnHand, List<Card> faceUpCards) {
        List<Card> royalFlush = new ArrayList<>();
        List<Card> combinedCards = new ArrayList<>();
        combinedCards.addAll(cardsOnHand);
        combinedCards.addAll(faceUpCards);

        Map<String, String> suitInfo = getMostRepeatedSuit(combinedCards);

        String mostRepeatedSuit = suitInfo.get("suit");
        if (mostRepeatedSuit != null) {
            for (Card card : combinedCards) {
                if (card.getSuit().equals(mostRepeatedSuit) &&
                        (card.getRank() == 10 || card.getRank() == 11 || card.getRank() == 12 ||
                                card.getRank() == 13 || card.getRank() == 14)) {
                    royalFlush.add(card);
                }
            }
        }

        return royalFlush.size() == 5 ? royalFlush : new ArrayList<>();
    }

    public List<Card> getStraightFlush(List<Card> cardsOnHand, List<Card> faceUpCards) {
        /**
         *  2. Straight Flush (T♥ 9♥ 8♥ 7♥ 6♥)
         *      * Also very rare, a straight flush consists of any straight that is all the same suit.
         */
        List<Card> straightFlush = new ArrayList<>();
        List<Card> combinedList = new ArrayList<>();
        combinedList.addAll(cardsOnHand);
        combinedList.addAll(faceUpCards);

        System.out.println("All the cards combined are: " + combinedList);
        Map<String, String> map = getMostRepeatedSuit(combinedList);

        int mostRepeatedCount = Integer.parseInt(map.get("count"));
        String mostRepeatedSuit = map.get("suit");

        if (mostRepeatedCount >= 5) {
            System.out.println("We are in the if in getRoyalFlush");
            List<Card> consecutiveCards = new ArrayList<>();
            combinedList.stream()
                    .sorted(Comparator.comparing(Card::getRank))
                    .forEach(card -> {
                        if (consecutiveCards.isEmpty() || card.getRank() - consecutiveCards.getLast().getRank() == 1) {
                            consecutiveCards.add(card);
                        }
                    });
            System.out.println("The Consecutive list is: " + consecutiveCards);
            consecutiveCards.forEach(System.out::println);
            for (Card consecutiveCard : consecutiveCards) {
                if (consecutiveCard.getSuit().equals(mostRepeatedSuit)) {
                    straightFlush.add(consecutiveCard);
                }
            }
        }
        return straightFlush;
    }

    //TODO:To accurately count the occurrences of each rank among the cards in hand and the face-up cards, you can modify the getMostRepeatedRank method to count only the cards that are part of the current hand (i.e., the cardsOnHand list).

    public static Map<String, String> getMostRepeatedRank(List<Card> cards) {
        Map<String, String> mostRepeatedRank = new HashMap<>();

        List<String> ranks = cards.stream()
                .map(card -> String.valueOf(card.getRank()))
                .toList();

        // Find the most repeated suit in the list
        String mostRepeated = ranks.stream()
                .distinct()
                .max(Comparator.comparingInt(a -> Collections.frequency(ranks, a)))
                .orElse(null);
        System.out.println("The most repeated rank is: " + mostRepeated);
        // Find the amount of repeated suits - must be 5
        Integer mostRepeatedCount = mostRepeated != null
                ? Collections.frequency(ranks, mostRepeated)
                : 0;
        mostRepeatedRank.put("count", String.valueOf(mostRepeatedCount));
        mostRepeatedRank.put("rank", mostRepeated);

        System.out.println("The most repeated rank number is: " + mostRepeatedCount);
        return mostRepeatedRank;
    }

    //3. Four-of-a-Kind (J♦ J♣ J♠ J♥ K♦)
    //     * Four of a kind, or 'quads', consists of four cards of equal value along with another card known as a side card.
    //     * <p>
    public List<Card> getFourOfAKind(List<Card> cardsOnHand, List<Card> faceUpCards) {

        List<Card> fourOfAKind = new ArrayList<>();
        List<Card> combinedList = new ArrayList<>();
        combinedList.addAll(cardsOnHand);
        combinedList.addAll(faceUpCards);
        Map<String, String> map = getMostRepeatedRank(combinedList);

        String mostRepeatedCount = map.get("count");
        String mostRepeatedRank = map.get("rank");
        System.out.println("=========The size of the list of the combinedCards is " + combinedList.size() + "================");
        if (Integer.parseInt(mostRepeatedCount) >= 4) {
            for (Card card : combinedList) {
                if (card.getRank() == Integer.parseInt(mostRepeatedRank)) {
                    fourOfAKind.add(card);
                }
            }
        }

        System.out.println("The most repeated rank is " + mostRepeatedRank + "And the count is " + mostRepeatedCount);
        return fourOfAKind;
    }

    //* 4. Full House (A♥ A♣ A♦ 9♠ 9♣) 10 , 14
    //     * A full house consists of three cards of one value and two cards of another.
    //     * <p>
    public List<Card> getFullHouse(List<Card> cardsOnHand, List<Card> faceUpCards) {

        List<Card> fullHouse = new ArrayList<>();
        List<Card> combinedList = new ArrayList<>();
        combinedList.addAll(cardsOnHand);
        combinedList.addAll(faceUpCards);
        Map<String, String> map = getMostRepeatedRank(combinedList);

        String mostRepeatedCount = map.get("count");
        String mostRepeatedRank = map.get("rank");
        System.out.println("=========The size of the list of the combinedCards is " + combinedList.size() + "================");
        if (Integer.parseInt(mostRepeatedCount) == 3) {
            for (int i = 0; i < combinedList.size(); i++) {
                if (combinedList.get(i).getRank() == Integer.parseInt(mostRepeatedRank)) {
                    fullHouse.add(combinedList.get(i));
                }else if(Objects.equals(combinedList.get(i).getRank(), combinedList.get(i + 1).getRank())){

                }else{

                }

            }
        }

        System.out.println("The most repeated rank is " + mostRepeatedRank + "And the count is " + mostRepeatedCount);
        return fullHouse;
    }

}
