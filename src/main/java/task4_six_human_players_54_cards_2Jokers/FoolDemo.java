package task4_six_human_players_54_cards_2Jokers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoolDemo {

    static List<Player> players = new ArrayList<>();
    static DeckOfCards cards;
    static Player playerWithFirstTurn;
    static int indexOfAttackingPlayer;
    static int indexOfDefendingPlayer;

    public static void main(String[] args) throws InterruptedException {
        cards = new DeckOfCards();

        //to select trump cards before creating players
        cards.chooseTrumpCard();

        //to add jokers
        cards.addJokerCard(2);
        System.out.println("The amount of cards in the main deck is: " + cards.cards.size());
        //assigning all 6 players with names and cards
        assignPlayers(6);

        Card player1LowestCard = players.get(0).getLowestCard();
        Card player2LowestCard = players.get(1).getLowestCard();

        //to check when the deck is empty, cards is the main deck of cards, 20 in the beginning, then 8(6 for each player)
        System.out.println("=================The deck is not empty======================");
        //playerWithFirstTurn
        indexOfAttackingPlayer = getPlayer1Turn();
        //playerWhoDefends in first round
        indexOfDefendingPlayer = findIndexOfDefendingPlayer(indexOfAttackingPlayer);
        //the turn is true for the index of the user that has the lowest card
        players.get(indexOfAttackingPlayer).turn = true;
        players.get(indexOfDefendingPlayer).turn = false;

        System.out.println("This is " + players.get(indexOfAttackingPlayer).name + " turn " + players.get(indexOfAttackingPlayer).turn);
        System.out.println("This is " + players.get(indexOfDefendingPlayer).name + " turn " + players.get(indexOfDefendingPlayer).turn);

        while (!cards.cards.isEmpty()) {
            Thread.sleep(1000);
            //this will define the next attacking player - we know the index
            indexOfAttackingPlayer = play(players.get(indexOfAttackingPlayer), players.get(indexOfDefendingPlayer));
            //this will define the next defending player - it will be next to new attacking player, so indexOfAttackingPlayer++
            indexOfDefendingPlayer = findIndexOfDefendingPlayer(indexOfAttackingPlayer);
            System.out.println("Cards amount in the deck is " + cards.cards.size());
        }

        System.out.println("===============Now The Main deck is empty=====================");
        while (!players.get(0).deckOfCardsOnHand.isEmpty() && !players.get(1).deckOfCardsOnHand.isEmpty()) {
            Thread.sleep(1000);
            //this will define the next attacking player - we know the index
            indexOfAttackingPlayer = play(players.get(indexOfAttackingPlayer), players.get(indexOfDefendingPlayer));
            //this will define the next defending player - it will be next to new attacking player, so indexOfAttackingPlayer++
            indexOfDefendingPlayer = findIndexOfDefendingPlayer(indexOfAttackingPlayer);
            System.out.println("Cards on hand amount for " + players.get(indexOfAttackingPlayer).name + " is " + players.get(indexOfAttackingPlayer).deckOfCardsOnHand.size() + " | Cards on hand amount for " + players.get(indexOfDefendingPlayer).name + " is " + players.get(indexOfDefendingPlayer).deckOfCardsOnHand.size());
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
    public static int getPlayer1Turn() {
        //we need to know which player has the lowest card
        Map<Player, Card> playersWithLowestCard = new HashMap<>();

        //at this point we get the lowest(trump or any card) of each Player and assign them to the map, bc we need to know the names
        for (int i = 0; i < players.size(); i++) {
            playersWithLowestCard.put(players.get(i), players.get(i).getLowestCard());
        }

        //now will have to get the lowest card and then we will know the Player
        //we want to return not boolean but Player
        //iterate over the entries in the map
        Map.Entry<Player, Card> lowestEntry = null;
        for (Map.Entry<Player, Card> entry : playersWithLowestCard.entrySet()) {
            //if lowestEntry is null or the current card's rank is lower than the lowest so far
            if (lowestEntry == null || entry.getValue().getRank() < lowestEntry.getValue().getRank()) {
                lowestEntry = entry;
            }
        }
        //after the loop, lowestEntry will have the Player and Card with the lowest rank
        if (lowestEntry != null) { //to avoid NullPointerException
            return lowestEntry.getKey().index;
        }

        return -1;
    }

    //this method allows to assign name and cards to each Player
    public static void assignPlayers(int numberOfPlayers) {
        int index = 0;
        for (int i = 0; i < numberOfPlayers; i++) {
            //now we create a player with index
            players.add(new Player(cards, "Player" + i, i));
            //draw 6 cards for each player, will have to improve the method to do it dynamically for other 4 players
            players.get(i).drawCard(6);
        }
    }

    public static int findIndexOfDefendingPlayer(int indexOfAttackingPlayer) {
        //this will have to prevent index out of bound
        if (indexOfAttackingPlayer < players.size() - 1) {
            return indexOfAttackingPlayer + 1;
        }
        //we assume that if all the players played we will return to the first player
        return 0;
    }

    //implemented the flag to dynamically switch turns
    public static void setTurn(Player attackingPlayer, Player defendingPlayer, boolean defended) {
        attackingPlayer.turn = !defended;
        defendingPlayer.turn = defended;
    }

    //implemented the flag to dynamically switch turns
    public static void drawCard(Player attackingPlayer, Player defendingPlayer, boolean defended) {
        if (defended) {
            defendingPlayer.drawCard(1);
        }
        attackingPlayer.drawCard(1);
    }

    //implemented the flag to execute correct messages
    public static void executeMessage(Player attackingPlayer, Player defendingPlayer, boolean defended) {
        System.out.println("defendingPlayer has: " + defendingPlayer.deckOfCardsOnHand.size() + " cards now and attackingPlayer has " + attackingPlayer.deckOfCardsOnHand.size() + " cards.");
        String defendedOrNo = attackingPlayer.name + " did the turn and " + defendingPlayer.name + (defended ? " defended successfully!" : " couldn't defended successfully, so he took the card in and lost his turn!");
        System.out.println(defendedOrNo);
    }

    public static int play(Player attackingPlayer, Player defendingPlayer) {
        Card player1AttackingCard = attackingPlayer.getAttackingCard();
        //check if the defending player was able to defend
        boolean player2Status = defendingPlayer.iterateCards(player1AttackingCard, defendingPlayer.deckOfCardsOnHand, cards.trumpCard.suit);
        executeMessage(attackingPlayer, defendingPlayer, player2Status);
        setTurn(attackingPlayer, defendingPlayer, player2Status);
        drawCard(attackingPlayer, defendingPlayer, player2Status);
        if (player2Status) {
            return defendingPlayer.index;
        } else {
            return attackingPlayer.index;
        }

        //cards = cards.isEmpty() ? deckOfCardsOnHand : cards;
    }
    //TODO: 1. to remove players that dont have cards; current issues: 2. Player0 did the turn and Player1 couldn't defended successfully, so he took the card in and lost his turn!defendingPlayer has: 7 cards now, but attacking player has: 5 Cards on hand amount for Player0 is 5 | Cards on hand amount for Player1 is 7. Player0 did the turn and Player1 defended successfully!
    //TODO: I need to get plus 1 so I have +2 in the case when the attacking player remains the same and the defending player shouldn't be attacked twice
}
