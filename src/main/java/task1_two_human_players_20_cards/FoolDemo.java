package task1_two_human_players_20_cards;

public class FoolDemo {
    static Player player1;
    static Player player2;

    public static void main(String[] args) {
        DeckOfCards cards = new DeckOfCards();
        Card trumpCard = new Card("Hearts", 6);

        //deck thats used for both players and add cards
        cards.addCards();
        player1 = new Player(cards, "Alex");
        player2 = new Player(cards, "Estuardo");


//        cards.chooseTrumpCard();
        //cards.printDeckOfCards();
//
//        player1.deck = cards.drawCard(6);
//        player1.printDeckOfCards();
        player1.drawCard(6);
        player2.drawCard(6);

        Card player1LowestCard = player1.getLowestCard(trumpCard);
        Card player2LowestCard = player2.getLowestCard(trumpCard);

        //assign turns
        player1.turn = getPlayer1Turn(player1LowestCard, player2LowestCard);
        player2.turn = !player1.turn;

//        player1.getLowestCard(trumpCard);
//        player2.getLowestCard(trumpCard);


        player1.printDeckOfCards();
        System.out.println("Player1 lowest cards is " + player1LowestCard.suit + " " + player1LowestCard.rank);

        player2.printDeckOfCards();
        System.out.println("Player one turn is " + player1.turn);
        System.out.println("Player two turn is " + player2.turn);

        play(player1, player2);

    }

    //will move to another Class - line 22, 23
    public static boolean getPlayer1Turn(Card player1Card, Card player2Card) {
        return player1Card.rank < player2Card.rank;
    }

    public static void play(Player player1, Player player2) {
        Card player1AttackingCard;
        boolean player2Status;
        Player attackingPlayer = player1.turn ? player1 : player2;
        Player defendingPlayer = player1.turn ? player2 : player1;

        if (attackingPlayer.turn) {
            player1AttackingCard = attackingPlayer.getAttackingCard();
            player2Status = defendingPlayer.getDefendingCard(player1AttackingCard);
            if (player2Status) {
                System.out.println(attackingPlayer.name + " did the turn and " + defendingPlayer.name + " defended successfully!");
                attackingPlayer.turn = false;
            } else {
                System.out.println(attackingPlayer.name + " did the turn and " + defendingPlayer.name + " couldn't defended successfully, so he took the card in and lost his turn!");
                attackingPlayer.turn = true;
            }
        }


    }
}
