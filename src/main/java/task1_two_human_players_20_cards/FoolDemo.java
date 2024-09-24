package task1_two_human_players_20_cards;

import java.util.List;

public class FoolDemo {
    public static void main(String[] args) {
        DeckOfCards card = new DeckOfCards();
        Player player1 = new Player();
        Player player2 = new Player();

        card.addCards();
        card.chooseTrumpCard();
        card.printDeckOfCards();

        player1.deck = card.drawCard(6);
        player1.printDeckOfCards();

    }
}
