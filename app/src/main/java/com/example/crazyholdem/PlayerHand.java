package com.example.crazyholdem;

import java.util.ArrayList;
import java.util.List;
public class PlayerHand {
    boolean highCard;
    boolean pair;
    boolean twoPair;
    boolean threeKind;
    boolean straight;
    boolean flush;
    boolean fullHouse;
    boolean fourKind;
    boolean fiveKind;
    boolean straightFlush;
    boolean royalFlush;
    int numHighHand;
    int numLowHand;
    int handStrength;
    ArrayList<Card> hand;
    ArrayList<Card> scoringHand;

    // Default constructor
    public PlayerHand() {
        this.highCard = true;
        this.pair = false;
        this.twoPair = false;
        this.threeKind = false;
        this.straight = false;
        this.flush = false;
        this.fullHouse = false;
        this.fourKind = false;
        this.fiveKind = false;
        this.straightFlush = false;
        this.royalFlush = false;
        this.hand = new ArrayList<>();
        handStrength = 0;
    }

    // Method to reset all hand types (useful for new rounds)
    public void resetHandTypes() {
        this.pair = false;
        this.twoPair = false;
        this.threeKind = false;
        this.straight = false;
        this.flush = false;
        this.fullHouse = false;
        this.fourKind = false;
        this.fiveKind = false;
        this.straightFlush = false;
        this.royalFlush = false;
    }
    public void setNumHighHand(int numHighHand){
        this.numHighHand = numHighHand;
    }
    public void setNumLowHand(int numLowHand){
        this.numLowHand = numLowHand;
    }
    public int getNumHighHand(){
        return numHighHand;
    }
    public int getNumLowHand() {
        return numLowHand;
    }

    public void isHighCard(List<Card> hand) {
        highCard = true;

    }
    // Example hand evaluator (you would expand this)
    public void isPair(List<Card> hand){}

    public void isTwoPair(List<Card> hand) {
    }
    public void clearHand(){
        hand.clear();
    }
    public void addCardToHand(Card card){
        hand.add(card);
    }
    public ArrayList<Card> getHand(){
        return hand;
    }
    public int getHandStrength(){
        return handStrength;
    }
}
