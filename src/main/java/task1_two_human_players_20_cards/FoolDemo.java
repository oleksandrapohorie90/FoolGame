package task1_two_human_players_20_cards;

import java.util.List;

public class FoolDemo {
    public static void main(String[] args) {
        DeckOfCards card = new DeckOfCards();
        Card trumpCard = new Card("Hearts", 6);

        //deck thats used for both players and add cards
        card.addCards();
        Player player1 = new Player(card);
        Player player2 = new Player(card);


//        card.chooseTrumpCard();
          //card.printDeckOfCards();
//
//        player1.deck = card.drawCard(6);
//        player1.printDeckOfCards();
        player1.drawCard(6);
        player2.drawCard(6);

        Card player1LowestCard = player1.showLowestCard(trumpCard);
        player2.showLowestCard(trumpCard);

        player1.printDeckOfCards();
        System.out.println("Player1 lowest card is " + player1LowestCard.suit + " " + player1LowestCard.rank);


    }
}
