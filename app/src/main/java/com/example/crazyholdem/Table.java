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
        ArrayList<Player> winners = nonFoldedPlayers;
        Collections.sort(nonFoldedPlayers, (p1, p2) -> p2.playerHand.getHandStrength() - p1.playerHand.getHandStrength());
        int handStrength = nonFoldedPlayers.get(0).playerHand.getHandStrength();
        for (int i = 0; i < nonFoldedPlayers.size(); i++) {
            if (nonFoldedPlayers.get(i).playerHand.getHandStrength() < handStrength) {
                winners.remove(nonFoldedPlayers.get(i));
            }
        }

        for (int i = 0 ; i < winners.size(); i++){
            HandType handType = HandType.HIGH_CARD;
            for (Player player : winners) {
                int value = player.playerHand.scoringHand.get(i).getValue();
                if (value > handType.getStrength()) {
                    handType.strength = value;
                }
            }

            for (Player player : winners){
                if (player.playerHand.scoringHand.get(i).getValue() != handStrength){
                    winners.remove(player);
                }
            }
        }

        for (Player winner : winners) {
            winner.setMoney(winner.money += pot / winners.size());
        }
    }
//public void startRound();

}
