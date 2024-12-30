package com.example.crazyholdem;

import java.util.ArrayList;
import java.util.List;
public class PlayerHand {

    int handStrength;
    ArrayList<Card> hand;
    ArrayList<Card> scoringHand;

    public PlayerHand() {
        this.hand = new ArrayList<>();
        handStrength = 0;
    }

    //TODO: code logic for evaluation
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
    public boolean isThreeOfAKind(List<Card> hand) {
        return true;
    }
    public boolean isStraight(List<Card> hand) {
        return true;
    }
    public boolean isFlush(List<Card> hand) {
        return true;
    }
    public boolean isFullHouse(List<Card> hand) {
        return true;
    }
    public boolean isFourOfAKind(List<Card> hand) {
        return true;
    }
    public boolean isFiveOfAKind(List<Card> hand) {
        return true;
    }
    public boolean isStraightFlush(List<Card> hand) {
        return true;
    }

    public boolean isRoyalFlush(List<Card> hand) {
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
    public Table.HandType evaluateHand(){

        Table.HandType handType = null;

        if (isRoyalFlush(hand)) {
            handType = Table.HandType.ROYAL_FLUSH;
        } else if (isStraightFlush(hand)) {
            handType = Table.HandType.STRAIGHT_FLUSH;
        } else if (isFiveOfAKind(hand)) {
            handType = Table.HandType.FIVE_KIND;
        } else if (isFourOfAKind(hand)) {
            handType = Table.HandType.FOUR_KIND;
        } else if (isFullHouse(hand)) {
            handType = Table.HandType.FULL_HOUSE;
        } else if (isFlush(hand)) {
            handType = Table.HandType.FLUSH;
        } else if (isStraight(hand)) {
            handType = Table.HandType.STRAIGHT;
        } else if (isThreeOfAKind(hand)) {
            handType = Table.HandType.THREE_KIND;
        } else if (isTwoPair(hand)) {
            handType = Table.HandType.TWO_PAIR;
        } else if (isPair(hand)) {
            handType = Table.HandType.PAIR;
        } else if (isHighCard(hand)) {
            handType = Table.HandType.HIGH_CARD;
        }
        return handType;
        //TODO: update based on hand type
    }

}
