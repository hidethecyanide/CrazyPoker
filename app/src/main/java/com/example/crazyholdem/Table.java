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
        standingBet = 0;
        communityCards = new ArrayList<>();
        stake = 0;
        nonFoldedPlayers = new ArrayList<>();
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

        private final int strength;

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
    public static ArrayList<Card> getCommuntiyCards(){
        return communityCards;
    }
    public void setStandingBet(int standingBet){
        this.standingBet = standingBet;
    }
    public void dealCard(Player player, int cardIndex) {
        player.playerHand.addCardToHand(deck.getDeck().get(cardIndex));
    }
    public void dealCommunityCards(int numberOfCards, int cardIndex) {
        for (int i = cardIndex; i < numberOfCards + cardIndex; i++) {
            communityCards.add(deck.getDeck().get(i));
        }
    }
    public void clearCommunityCards(){
        communityCards.clear();
    }
    public void showdown(int pot) {
        for( Player player : nonFoldedPlayers){
            player.checkHand();
        }
        // Step 2: Sort players by hand strength
        Collections.sort(nonFoldedPlayers, (p1, p2) -> p2.playerHand.getHandStrength() - p1.playerHand.getHandStrength());
        int highHand = nonFoldedPlayers.get(0).playerHand.handStrength;
        ArrayList<Player> tiedPlayers = new ArrayList<Player>();
        for (int i = 0; i < nonFoldedPlayers.size(); i++) {
            if (nonFoldedPlayers.get(i).playerHand.handStrength == highHand) {
                tiedPlayers.add(nonFoldedPlayers.get(i));

            }
        }
        Collections.sort(tiedPlayers, (p1, p2) -> p2.playerHand.getNumHighHand() - p1.playerHand.getNumHighHand());
        ArrayList<Player> twoTiedPlayers = new ArrayList<>();
        for (int i = 0; i < tiedPlayers.size(); i++) {
            if (tiedPlayers.get(i).playerHand.numHighHand == tiedPlayers.get(0).playerHand.numHighHand) {
                twoTiedPlayers.add(tiedPlayers.get(i));
            }
        }
        Collections.sort(twoTiedPlayers, (p1, p2) -> p2.playerHand.getNumLowHand() - p1.playerHand.getNumLowHand());
        ArrayList<Player> winners = new ArrayList<>();
        for (int i = 0; i < twoTiedPlayers.size(); i++) {
            if (tiedPlayers.get(i).playerHand.getNumLowHand() == tiedPlayers.get(0).playerHand.getNumLowHand()) {
                winners.add(twoTiedPlayers.get(i));
            }
        }
        for (Player winner : winners) {
            winner.setMoney(winner.money += pot / winners.size());
        }

    }
public void startRound();

}
