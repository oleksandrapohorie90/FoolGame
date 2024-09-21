package task1_two_human_players_20_cards;

public class Card {

    //refers to the suit of cards: hearts, diamonds, clubs, spades - mast'(chervi, bubni, trefi, piki)
    String suit;
    //refers to the 9 unique ranks repeated across 4 suites: 6, 7, 8, 9, 10, Jack(Valet), Queen, King, Ace
    String rank;
    //refers to which color isTrump - kozyr'
    boolean isTrump;

    public Card(String suit, String rank, boolean isTrump) {
        this.suit = suit;
        this.rank = rank;
        this.isTrump = isTrump;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", rank='" + rank + '\'' +
                ", isTrump=" + isTrump +
                '}';
    }
}
