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

    // Method to evaluate the hand (stub for actual implementation)
    public void evaluateHand(List<Card> hand) {
        // Logic to analyze the hand and set the appropriate fields.
        // Example:
        // if (isPair(hand)) this.pair = true;
        // Add checks for other hand types.
    }

    // Example hand evaluator (you would expand this)
    private void isPair(List<Card> hand) {
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
    public void clearHand(){
        hand.clear();
    }
    public void addCardToHand(Card card){
        hand.add(card);
    }
    public ArrayList<Card> getHand(){
        return hand;
    }
    public int updateHandStrength(){
        if (royalFlush) handStrength = Table.HandType.ROYAL_FLUSH.getStrength();
        else if (straightFlush) handStrength = Table.HandType.STRAIGHT_FLUSH.getStrength();
        else if (fiveKind) handStrength = Table.HandType.FIVE_KIND.getStrength();
        else if (fourKind) handStrength = Table.HandType.FOUR_KIND.getStrength();
        else if (fullHouse) handStrength = Table.HandType.FULL_HOUSE.getStrength();
        else if (flush) handStrength = Table.HandType.FLUSH.getStrength();
        else if (straight) handStrength = Table.HandType.STRAIGHT.getStrength();
        else if (threeKind) handStrength = Table.HandType.THREE_KIND.getStrength();
        else if (twoPair) handStrength = Table.HandType.TWO_PAIR.getStrength();
        else if (pair) handStrength = Table.HandType.PAIR.getStrength();
        else handStrength = Table.HandType.HIGH_CARD.getStrength();
        return handStrength;
    }

    public int getHandStrength(){
        return handStrength;
    }
}
