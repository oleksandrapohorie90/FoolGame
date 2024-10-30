package task1_two_human_players_20_cards;

public class FoolDemo {
    static Player player1;
    static Player player2;
    static DeckOfCards cards;
    public static void main(String[] args) throws InterruptedException {
        cards = new DeckOfCards();

        //deck thats used for both players and add cards
        player1 = new Player(cards, "Alex");
        player2 = new Player(cards, "Estuardo");


         cards.chooseTrumpCard();
        //cards.printDeckOfCards();

//        player1.deck = cards.drawCard(6);
//        player1.printDeckOfCards();
        player1.drawCard(6);
        player2.drawCard(6);

        Card player1LowestCard = player1.getLowestCard(cards.trumpCard);
        Card player2LowestCard = player2.getLowestCard(cards.trumpCard);

        //assign turns from getPlayer1Turn()
        player1.turn = getPlayer1Turn(player1LowestCard, player2LowestCard);
        player2.turn = !player1.turn;

//        player1.getLowestCard(trumpCard);
//        player2.getLowestCard(trumpCard);


        player1.printDeckOfCards();
        System.out.println("Player1 lowest cards is " + player1LowestCard.suit + " " + player1LowestCard.rank);

        player2.printDeckOfCards();
        System.out.println("Player one turn is " + player1.turn);
        System.out.println("Player two turn is " + player2.turn);
        //play(player1, player2);
        //play(player1, player2);
        //play(player1, player2);

        //to check when the deck is empty, cards is the main deck of cards, 20 in the beginning, then 8(6 for each player)
        System.out.println("=================The deck is not empty======================");
        while (!cards.cards.isEmpty()) {
            Thread.sleep(1000);
            play(player1, player2);
            System.out.println("Cards amount is " + cards.cards.size());
        }
        System.out.println("===============Now The Main deck is empty=====================");
        while (!player1.deckOfCardsOnHand.isEmpty() && !player2.deckOfCardsOnHand.isEmpty()) {
            Thread.sleep(1000);
            play(player1, player2);
            System.out.println("Cards on hand amount for " + player1.name + " is " + player1.deckOfCardsOnHand.size() + " | Cards on hand amount for " + player2.name + " is " + player2.deckOfCardsOnHand.size());
        }
        if (player1.deckOfCardsOnHand.size() > player2.deckOfCardsOnHand.size()) {
            System.out.println(player1.name + " has lost the game and is a FOOL!");
        } else if (player1.deckOfCardsOnHand.size() < player2.deckOfCardsOnHand.size()) {
            System.out.println(player2.name + " has lost the game and is a FOOL!");
        } else {
            System.out.println("There is no FOOL, the game ended even!");
        }
        System.out.println("The game is over, the deck is empty!");

    }

    //TODO: move to another Class - line 22, 23
    public static boolean getPlayer1Turn(Card player1Card, Card player2Card) {
        if(!player1Card.suit.equals(cards.trumpCard.suit) && player2Card.suit.equals(cards.trumpCard.suit)){
            return false; //we know that player 1 doesnt have the trump card but player 2 has it, we return false for turn on player1
        } else if (player1Card.suit.equals(cards.trumpCard.suit) && !player2Card.suit.equals(cards.trumpCard.suit)) {
            return true;
        } else { //in case when both of us have trump cards or none of us has trump cards
            return player1Card.rank < player2Card.rank;
        }

    }

    public static void play(Player player1, Player player2) {
        Card player1AttackingCard;
        boolean player2Status;
        //if player1 turn is true then player1 is attacking player
        //if player1 turn is true, then player2 is the defending player

        if (player1.turn) {
            player1AttackingCard = player1.getAttackingCard();
            player2Status = player2.getDefendingCard(player1AttackingCard);
            //playerStatus means if you are able to defend then the status var will be true
            if (player2Status) {
                System.out.println(player1.name + " did the turn and " + player2.name + " defended successfully!");
                player1.turn = false;
                player2.turn = true;
                System.out.println("defendingPlayer has: " + player2.deckOfCardsOnHand.size() + " cards now and attackingPlayer has " + player1.deckOfCardsOnHand.size() + " cards.");
                player1.drawCard(1);
                player2.drawCard(1);
            } else {
                System.out.println(player1.name + " did the turn and " + player2.name + " couldn't defended successfully, so he took the card in and lost his turn!");
                player1.turn = true; //we need to keep the turns values accurate, player1 turn remains true
                player2.turn = false; //player2 turn remain false
                player1.drawCard(1); //draw more cards from gen deck and its attacking player turn again
                System.out.println("defendingPlayer has: " + player2.deckOfCardsOnHand.size() + " cards now, but attacking player has: " + player1.deckOfCardsOnHand.size());
            }
        } else {
            player1AttackingCard = player2.getAttackingCard();
            player2Status = player1.getDefendingCard(player1AttackingCard);
            //playerStatus means if you are able to defend then the status var will be true
            if (player2Status) {
                System.out.println(player2.name + " did the turn and " + player1.name + " defended successfully!");
                player2.turn = false;
                player1.turn = true;
                System.out.println("defendingPlayer has: " + player1.deckOfCardsOnHand.size() + " cards now and attackingPlayer has " + player2.deckOfCardsOnHand.size() + " cards.");
                player2.drawCard(1);
                player1.drawCard(1);
            } else {
                System.out.println(player2.name + " did the turn and " + player1.name + " couldn't defended successfully, so he took the card in and lost his turn!");
                player2.turn = true; //we need to keep the turns values accurate, player2 turn remains true
                player1.turn = false; //player1 turn remain false
                player2.drawCard(1); //draw more cards from gen deck and its attacking player turn again
                System.out.println("defendingPlayer has: " + player1.deckOfCardsOnHand.size() + " cards now, but attacking player has: " + player2.deckOfCardsOnHand.size() + " cards.");
            }
        }


    }
}
