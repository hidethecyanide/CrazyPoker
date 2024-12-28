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
        if(hand.get(0).getValue() > hand.get(1).getValue()){
            numHighHand = hand.get(0).getValue();
            numLowHand = hand.get(1).getValue();
        }
        else{
            numHighHand = hand.get(1).getValue();
            numLowHand = hand.get(0).getValue();
        }
    }
    // Example hand evaluator (you would expand this)
    public void isPair(List<Card> hand) {
        if(hand.get(0) == hand.get(1)){
            pair = true;
            return;
        }
        for (int i = 0; i < Table.getCommuntiyCards().size(); i++) {
            if (hand.get(0) == Table.getCommuntiyCards().get(i)){
                pair = true;
                numHighHand = hand.get(0).getValue();
                return;
            }
            else if(hand.get(1) == Table.getCommuntiyCards().get(i)) {
                pair = true;
                numHighHand = hand.get(1).getValue();
                return;
            }
        }
    }

    public void isTwoPair(List<Card> hand) {
        int pairCount = 0;
        if(hand.get(0) == hand.get(1)){
            pair = true;
            pairCount++;
        }

        for (int i = 0; i < Table.getCommuntiyCards().size(); i++) {
            if (hand.get(0) == Table.getCommuntiyCards().get(i)){
                pair = true;
                numHighHand = hand.get(0).getValue();
                return;
            }
            else if(hand.get(1) == Table.getCommuntiyCards().get(i)) {
                pair = true;
                numHighHand = hand.get(1).getValue();
                return;
            }
        }

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
