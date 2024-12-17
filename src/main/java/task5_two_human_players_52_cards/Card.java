package task5_two_human_players_52_cards;

public class Card {

    //refers to the suit of cards: hearts, diamonds, clubs, spades - mast'(chervi, bubni, trefi, piki)
    private String suit;
    //refers to the 13 unique ranks repeated across 4 suites: 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack(Valet), Queen, King, Ace
    private Integer rank;

    public Card(String suit, Integer rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Integer getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", rank='" + rank + "}";
    }
}
