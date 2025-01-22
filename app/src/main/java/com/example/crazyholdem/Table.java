package com.example.crazyholdem;

import java.util.ArrayList;
import java.util.Collections;

public class Table {

    private ArrayList<Player> players;
    private Deck deck;
    private int standingBet;
    public static ArrayList<Card> communityCards;
    private int stake;
    private ArrayList<Player> nonFoldedPlayers;

    public Table() {
        players = new ArrayList<>();
        deck = new Deck();
        deck.createDeck();
        deck.shuffleDeck();
        standingBet = 0;
        communityCards = new ArrayList<>();
        stake = 0;
        nonFoldedPlayers = new ArrayList<>();
    }
    public Deck getDeck() {
        return deck;
    }
    public int getStake() {
        return stake;
    }
    public void setStake(int stake) {
        this.stake = stake;
    }
    public enum HandType {
        ROYAL_FLUSH(11),
        STRAIGHT_FLUSH(10),
        FIVE_KIND(9),
        FOUR_KIND(8),
        FULL_HOUSE(7),
        FLUSH(6),
        STRAIGHT(5),
        THREE_KIND(4),
        TWO_PAIR(3),
        PAIR(2),
        HIGH_CARD(1);

        private int strength;

        HandType(int strength) {
            this.strength = strength;
        }

        public int getStrength() {
            return strength;
        }
    }
    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(Player player) {
        players.remove(player);
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public int getStandingBet(){
        return standingBet;
    }
    public void setStandingBet(int standingBet){
        this.standingBet = standingBet;
    }

    public void dealCard(Player player, Card card) {
        player.playerHand.addCardToHand(card);
    }

    public void dealCard(Player player) {
        player.playerHand.addCardToHand(deck.getCardFromDeck(deck.getCurrentCard()));
        deck.setCurrentCard(deck.getCurrentCard() + 1);
    }
    public void dealCommunityCards(int numberOfCards) {

        int start = deck.getCurrentCard();
        int end = start + numberOfCards;

        if (end > deck.getDeck().size()) {
            throw new IllegalStateException("Not enough cards remaining in the deck");
        }

        for (int i = start; i < end; i++) {
            communityCards.add(deck.getDeck().get(i));
        }
        deck.setCurrentCard(end);
    }

    public void clearCommunityCards(){
        communityCards.clear();
    }
    public void showdown(int pot) {
        //TODO: code all-in clauses
        if (nonFoldedPlayers.isEmpty()) {
            throw new IllegalStateException("No players to evaluate during showdown.");
        }
        for( Player player : nonFoldedPlayers){
            player.playerHand.evaluateHand();
        }

        // Step 2: Sort players by hand strength
        ArrayList<Player> winners = new ArrayList<>(nonFoldedPlayers);
        Collections.sort(winners, (p1, p2) -> p2.playerHand.getHandStrength() - p1.playerHand.getHandStrength());
        int handStrength = winners.get(0).playerHand.getHandStrength();
        winners.removeIf(player -> player.playerHand.getHandStrength() != handStrength);
        for (int i = 0; i < 5; i++) {
            int maxValue = 0;

            // Determine the maximum value for the current card position
            for (Player player : winners) {
                int cardValue = player.playerHand.scoringHand.get(i).getValue();
                if (cardValue > maxValue) {
                    maxValue = cardValue;
                }
            }
            // Create a new list for players with the maximum value
            ArrayList<Player> filteredWinners = new ArrayList<>();
            for (Player player : winners) {
                if (player.playerHand.scoringHand.get(i).getValue() == maxValue) {
                    filteredWinners.add(player);
                }
            }
            winners = filteredWinners; // Update the winners list
        }
        int remainingPot = pot;
        for (Player winner : winners) {
            int eligiblePot;
            if (winner.isAllIn()) {
                eligiblePot = winner.getMoney() * nonFoldedPlayers.size();
                eligiblePot = Math.min(eligiblePot, remainingPot); // Cap at the remaining pot
            } else {
                eligiblePot = remainingPot;
            }

            // Distribute the eligible pot to the winner(s)
            int share = eligiblePot / winners.size();
            if (winner.isAllIn()) {
                winner.setMoney(share);
            }
            else {
                winner.setMoney(winner.money + share);
            }
            remainingPot -= eligiblePot;

            System.out.println(winner.getName() + " has won " + share);
        }

        // Handle remaining pot (if any) recursively
        if (remainingPot > 0 && nonFoldedPlayers.size() > winners.size()) {
            nonFoldedPlayers.removeAll(winners); // Exclude current winners
            showdown(remainingPot); // Recalculate with remaining players
        }

    }
    //public void startRound();
    public void resetRound(){
        deck.resetDeck();
        clearCommunityCards();
        standingBet = 0;
        nonFoldedPlayers.clear();
        nonFoldedPlayers = players;
        for (Player player : players) {
            player.playerHand.resetHand();
        }
    }
}
