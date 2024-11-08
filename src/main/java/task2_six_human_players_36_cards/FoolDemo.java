package task2_six_human_players_36_cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoolDemo {

    static List<Player> players = new ArrayList<>();
    static DeckOfCards cards;


    public static void main(String[] args) throws InterruptedException {
        cards = new DeckOfCards();

        //to select trump cards before creating players
        cards.chooseTrumpCard();

        //deck thats used for both players and add cards

//        players.add(new Player(cards, "Alex"));
//        players.add(new Player(cards, "Estuardo"));
        assignPlayers(6);
        //cards.printDeckOfCards();

//        player1.deck = cards.drawCard(6);
//        player1.printDeckOfCards();
        players.get(0).drawCard(6);
        players.get(1).drawCard(6);

        Card player1LowestCard = players.get(0).getLowestCard();
        Card player2LowestCard = players.get(1).getLowestCard();

        //assign turns from getPlayer1Turn()
        //TODO: this line will be deleted, the getPlayer1Turn() parameters have changed ->
        // players.get(0).turn = getPlayer1Turn(player1LowestCard, player2LowestCard);
        players.get(1).turn = !players.get(0).turn;

//        player1.getLowestCard(trumpCard);
//        player2.getLowestCard(trumpCard);

        players.get(0).printDeckOfCards();
        //System.out.println("Player1 lowest cards is " + player1LowestCard.suit + " " + player1LowestCard.rank);

        players.get(1).printDeckOfCards();
        System.out.println("This is " + players.get(0).name + " turn " + players.get(0).turn);
        System.out.println("This is " + players.get(1).name + " turn " + players.get(1).turn);

        //to check when the deck is empty, cards is the main deck of cards, 20 in the beginning, then 8(6 for each player)
        System.out.println("=================The deck is not empty======================");
        while (!cards.cards.isEmpty()) {
            Thread.sleep(1000);
            play(players.get(0), players.get(1));
            System.out.println("Cards amount in the deck is " + cards.cards.size());
        }
        System.out.println("===============Now The Main deck is empty=====================");
        while (!players.get(0).deckOfCardsOnHand.isEmpty() && !players.get(1).deckOfCardsOnHand.isEmpty()) {
            Thread.sleep(1000);
            play(players.get(0), players.get(1));
            System.out.println("Cards on hand amount for " + players.get(0).name + " is " + players.get(0).deckOfCardsOnHand.size() + " | Cards on hand amount for " + players.get(1).name + " is " + players.get(1).deckOfCardsOnHand.size());
        }
        if (players.get(0).deckOfCardsOnHand.size() > players.get(1).deckOfCardsOnHand.size()) {
            System.out.println(players.get(0).name + " has lost the game and is a FOOL!");
        } else if (players.get(0).deckOfCardsOnHand.size() < players.get(1).deckOfCardsOnHand.size()) {
            System.out.println(players.get(1).name + " has lost the game and is a FOOL!");
        } else {
            System.out.println("There is no FOOL, the game ended even!");
        }
        System.out.println("The game is over, the deck is empty!");

    }

    //TODO: move to another Class - line 22, 23
    public static boolean getPlayer1Turn(List<Player> players) {
        Map<Player, Card> playersWithLowestCard = new HashMap<>();

        //at this point we get the lowest(trump or any card) of each Player and assign them to the map, bc we need to know the names
        for (int i = 0; i < players.size(); i++) {
            playersWithLowestCard.put(players.get(i), players.get(i).getLowestCard());
        }

        //now will have to get the lowest card and then we will know the Player
        //we want to return not boolean but Player
        //TODO: Remember to delete below, it wont be used!

//        if (!player1Card.suit.equals(cards.trumpCard.suit) && player2Card.suit.equals(cards.trumpCard.suit)) {
//            return false; //we know that player 1 doesnt have the trump card but player 2 has it, we return false for turn on player1
//        } else if (player1Card.suit.equals(cards.trumpCard.suit) && !player2Card.suit.equals(cards.trumpCard.suit)) {
//            return true;
//        } else { //in case when both of us have trump cards or none of us has trump cards
//            return player1Card.rank < player2Card.rank;
//        }
return false;
    }

    //this method allows to assign name and cards to each Player
    public static void assignPlayers(int numberOfPlayers) {

        for (int i = 0; i < numberOfPlayers; i++) {

            players.add(new Player(cards, "Player" + i));
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
                System.out.println("defendingPlayer has: " + player2.deckOfCardsOnHand.size() + " cards now, but attacking player has: " + player1.deckOfCardsOnHand.size());
                player1.drawCard(1); //draw more cards from gen deck and its attacking player turn again
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
