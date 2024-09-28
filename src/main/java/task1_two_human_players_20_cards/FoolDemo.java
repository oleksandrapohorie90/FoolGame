package task1_two_human_players_20_cards;

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

        Card player1LowestCard = player1.getLowestCard(trumpCard);
        Card player2LowestCard = player2.getLowestCard(trumpCard);
        player1.getLowestCard(trumpCard);
        player2.getLowestCard(trumpCard);

        player1.printDeckOfCards();
        System.out.println("Player1 lowest card is " + player1LowestCard.suit + " " + player1LowestCard.rank);

        //now we have cards and we can do wat we need with those 2 cards

    }
}
