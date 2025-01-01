package com.example.crazyholdem;

import java.util.ArrayList;
import java.util.List;
public class PlayerHand {

    Table.HandType handType;
    ArrayList<Card> hand;
    ArrayList<Card> scoringHand;

    public PlayerHand() {
        this.handType = null;
        this.hand = new ArrayList<>();
        this.scoringHand = new ArrayList<>();
    }
    //TODO: code logic for evaluation
    public boolean isHighCard(List<Card> hand) {
        scoringHand.clear();
        scoringHand.addAll(hand); // Add player's two cards
        scoringHand.addAll(Table.communityCards); // Add community cards
        // Sort the cards in descending order of value
        scoringHand.sort((c1, c2) -> c2.getValue() - c1.getValue());
        // Trim scoringHand to retain only the top 5 cards
        while (scoringHand.size() > 5) {
            scoringHand.remove(scoringHand.size() - 1); // Remove the smallest card
        }
        return true;
    }

    public boolean isPair(List<Card> hand) {
        // Combine the player's hand and community cards
        scoringHand.clear();
        scoringHand.addAll(hand); // Add player's two cards
        scoringHand.addAll(Table.communityCards);

        // Sort the cards in descending order of value
        scoringHand.sort((c1, c2) -> c2.getValue() - c1.getValue());

        // Check for a pair
        for (int i = 0; i < scoringHand.size() - 1; i++) {
            if (scoringHand.get(i).getValue() == scoringHand.get(i + 1).getValue()) {
                // Pair found: Extract the pair
                Card card1 = scoringHand.get(i);
                Card card2 = scoringHand.get(i + 1);

                // Reorganize scoringHand
                List<Card> frontPair = new ArrayList<>();
                frontPair.add(card1);
                frontPair.add(card2);

                // Add the highest remaining cards
                for (int j = 0; j < scoringHand.size(); j++) {
                    if (j != i && j != i + 1 && frontPair.size() < 5) {
                        frontPair.add(scoringHand.get(j));
                    }
                }

                // Update scoringHand
                scoringHand.clear();
                scoringHand.addAll(frontPair);

                return true;
            }
        }
        // No pair found
        return false;
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
    public void addCardToHand(Card card){
        hand.add(card);
    }
    public ArrayList<Card> getHand(){
        return hand;
    }
    public int getHandStrength(){
        return handType.getStrength();
    }
    public void setHandStrength(Table.HandType handStrength){
        this.handType = handStrength;
    }
    public void resetHand(){
        handType = null;
        hand.clear();
        scoringHand.clear();
    }
    public void evaluateHand(){
        if (isRoyalFlush(hand)) {
            setHandStrength(Table.HandType.ROYAL_FLUSH);
        } else if (isStraightFlush(hand)) {
            setHandStrength(Table.HandType.STRAIGHT_FLUSH);
        } else if (isFiveOfAKind(hand)) {
            setHandStrength(Table.HandType.FIVE_KIND);
        } else if (isFourOfAKind(hand)) {
            setHandStrength(Table.HandType.FOUR_KIND);
        } else if (isFullHouse(hand)) {
            setHandStrength(Table.HandType.FULL_HOUSE);
        } else if (isFlush(hand)) {
            setHandStrength(Table.HandType.FLUSH);
        } else if (isStraight(hand)) {
            setHandStrength(Table.HandType.STRAIGHT);
        } else if (isThreeOfAKind(hand)) {
            setHandStrength(Table.HandType.THREE_KIND);
        } else if (isTwoPair(hand)) {
            setHandStrength(Table.HandType.TWO_PAIR);
        } else if (isPair(hand)) {
            setHandStrength(Table.HandType.PAIR);
        } else if (isHighCard(hand)) {
            setHandStrength(Table.HandType.HIGH_CARD);
        }

    }

}
