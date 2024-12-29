package com.example.crazyholdem;

import java.util.ArrayList;
import java.util.List;
public class PlayerHand {

    int handStrength;
    ArrayList<Card> hand;
    ArrayList<Card> scoringHand;

    // Default constructor
    public PlayerHand() {

        this.hand = new ArrayList<>();
        handStrength = 0;
    }
    public boolean isHighCard(List<Card> hand) {
        return true;
    }
    // Example hand evaluator (you would expand this)
    public boolean isPair(List<Card> hand) {
        return true;
    }
    public boolean isTwoPair(List<Card> hand) {
        return true;
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

    public void setHandStrength(int handStrength){
        this.handStrength = handStrength;
    }
    public void resetHand(){
        handStrength = 0;
        hand.clear();
        scoringHand.clear();
    }
    public int evaluateHand(){
        return handStrength;
    }

}
