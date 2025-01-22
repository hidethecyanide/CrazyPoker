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
        scoringHand.clear();
        scoringHand.addAll(hand); // Add player's two cards
        scoringHand.addAll(Table.communityCards);
        scoringHand.sort((c1, c2) -> c2.getValue() - c1.getValue());
        boolean secondPair = false;
        int k = 0;
        Card highPair1 = null;
        Card highPair2 = null;
        for (int i = 0; i < scoringHand.size() - 1; i++) {
            if (scoringHand.get(i).getValue() == scoringHand.get(i + 1).getValue()) {
                if (secondPair) {
                    Card card1 = scoringHand.get(i);
                    Card card2 = scoringHand.get(i + 1);

                    // Reorganize scoringHand
                    List<Card> frontPair = new ArrayList<>();
                    frontPair.add(highPair1);
                    frontPair.add(highPair2);
                    frontPair.add(card1);
                    frontPair.add(card2);
                    for (int j = 0; j < scoringHand.size(); j++) {
                        if (j != k && j != k+1 && j != i && j != i + 1 && frontPair.size() < 5) {
                            frontPair.add(scoringHand.get(j));
                        }
                    }
                    scoringHand.clear();
                    scoringHand.addAll(frontPair);
                    System.out.println("Two pair");
                    for(Card card : scoringHand){
                        System.out.println(card.toString());
                    }
                    return true;
                } else {
                    secondPair = true;
                    highPair1 = scoringHand.get(i);
                    highPair2 = scoringHand.get(i + 1);
                    k=i;
                    i++;
                }
            }

        }
        return false;
    }
    public boolean isThreeOfAKind(List<Card> hand) {
        scoringHand.clear();
        scoringHand.addAll(hand); // Add player's two cards
        scoringHand.addAll(Table.communityCards);

        // Sort the cards in descending order of value
        scoringHand.sort((c1, c2) -> c2.getValue() - c1.getValue());

        // Check for a three of a kind
        for (int i = 0; i < scoringHand.size() - 2; i++) {
            if ((scoringHand.get(i).getValue() == scoringHand.get(i + 1).getValue()) &&
                    scoringHand.get(i).getValue() == scoringHand.get(i + 2).getValue()) {
                // Three of a kind found
                Card card1 = scoringHand.get(i);
                Card card2 = scoringHand.get(i + 1);
                Card card3 = scoringHand.get(i + 2);

                // Reorganize scoringHand
                List<Card> frontThree = new ArrayList<>();
                frontThree.add(card1);
                frontThree.add(card2);
                frontThree.add(card3);


                // Add the highest remaining cards
                for (int j = 0; j < scoringHand.size(); j++) {
                    if (j != i && j != i + 1 && j != i + 2 && frontThree.size() < 5) {
                        frontThree.add(scoringHand.get(j));
                    }
                }

                // Update scoringHand
                scoringHand.clear();
                scoringHand.addAll(frontThree);
                System.out.println("Three of a kind");
                for(Card card : scoringHand){
                    System.out.println(card.toString());
                }
                return true;
            }
        }
        return false;
    }
    public boolean isStraight(List<Card> hand) {
        scoringHand.clear();
        scoringHand.addAll(hand); // Add player's two cards
        scoringHand.addAll(Table.communityCards);
        scoringHand.sort((c1, c2) -> c2.getValue() - c1.getValue());
        int straightLength = 1;
        List<Card> straightCards = new ArrayList<>();
        straightCards.add(scoringHand.get(0)); // Add first card to start the straight check
        for (int i = 1; i < scoringHand.size(); i++) {
            int current = scoringHand.get(i - 1).getValue();
            int next = scoringHand.get(i).getValue();
            if (current == next) {
                continue;
            } else if (current == next + 1) {
                straightLength++;
                straightCards.add(scoringHand.get(i));
            } else {
                straightLength = 1;
                straightCards.clear();
                straightCards.add(scoringHand.get(i));
            }
            if (straightLength == 4 && scoringHand.get(0).getValue() == 14 && straightCards.get(0).getValue() == 5) {
                straightCards.add(scoringHand.get(0));
                straightLength++;
            }
            if (straightLength == 5) {
                scoringHand.clear();
                scoringHand.addAll(straightCards);
                System.out.println("Straight");
                for(Card card : scoringHand){
                    System.out.println(card.toString());
                }
                return true;
            }
        }
        return false;
    }
    public boolean isFlush(List<Card> hand) {

        //TODO: simplify flush
        scoringHand.clear();
        scoringHand.addAll(hand); // Add player's two cards
        scoringHand.addAll(Table.communityCards);
        scoringHand.sort((c1, c2) -> c2.getValue() - c1.getValue());
        int numOfSpades = 0;
        int numOfHearts = 0;
        int numOfClubs = 0;
        int numOfDiamonds = 0;
        for(Card card : scoringHand){
            switch (card.suit) {
                case "Spades":
                    numOfSpades++;
                    break;
                case "Hearts":
                    numOfHearts++;
                    break;
                case "Clubs":
                    numOfClubs++;
                    break;
                case "Diamonds":
                    numOfDiamonds++;
                    break;
            }
        }
        if(numOfSpades >= 5 || numOfHearts >= 5 || numOfClubs >= 5 || numOfDiamonds >= 5){
            ArrayList<Card> flush = new ArrayList<>();
            if(numOfSpades >= 5) {
                for (Card card : scoringHand) {
                    if (card.suit.equals("Spades") && flush.size() <= 5) {
                        flush.add(card);
                    }
                }
                scoringHand=flush;
                System.out.println("Flush");
                for(Card card : scoringHand){
                    System.out.println(card.toString());
                }
                return true;
            }
            else if(numOfHearts >= 5) {
                for (Card card : scoringHand) {
                    if (card.suit.equals("Hearts") && flush.size() <= 5) {
                        flush.add(card);
                    }
                }
                scoringHand=flush;
                System.out.println("Flush");
                for(Card card : scoringHand){
                    System.out.println(card.toString());
                }
                return true;
            }
            else if(numOfClubs >= 5) {
                for (Card card : scoringHand) {
                    if (card.suit.equals("Clubs") && flush.size() <= 5) {
                        flush.add(card);
                    }
                }
                scoringHand=flush;
                System.out.println("Flush");
                for(Card card : scoringHand){
                    System.out.println(card.toString());
                }
                return true;
            }
            else {
                for (Card card : scoringHand) {
                    if (card.suit.equals("Diamonds") && flush.size() <= 5) {
                        flush.add(card);
                    }
                }
                scoringHand=flush;
                System.out.println("Flush");
                for(Card card : scoringHand){
                    System.out.println(card.toString());
                }
                return true;
            }
        }
        return false;
    }
    public boolean isFullHouse(List<Card> hand) {
        // Combine player's hand and community cards
        scoringHand.clear();
        scoringHand.addAll(hand);
        scoringHand.addAll(Table.communityCards);

        // Sort the cards in descending order of value
        scoringHand.sort((c1, c2) -> c2.getValue() - c1.getValue());

        // Find the Three of a Kind
        Card threeKind1 = null, threeKind2 = null, threeKind3 = null;
        for (int i = 0; i < scoringHand.size() - 2; i++) {
            if (scoringHand.get(i).getValue() == scoringHand.get(i + 1).getValue() &&
                    scoringHand.get(i).getValue() == scoringHand.get(i + 2).getValue()) {
                threeKind1 = scoringHand.get(i);
                threeKind2 = scoringHand.get(i + 1);
                threeKind3 = scoringHand.get(i + 2);

                // Remove the three-of-a-kind cards from the hand
                scoringHand.remove(i);
                scoringHand.remove(i);
                scoringHand.remove(i);

                break;
            }
        }

        // If no Three of a Kind found, return false
        if (threeKind1 == null) {
            return false;
        }

        // Find a Pair
        Card pair1 = null, pair2 = null;
        for (int i = 0; i < scoringHand.size() - 1; i++) {
            if (scoringHand.get(i).getValue() == scoringHand.get(i + 1).getValue()) {
                pair1 = scoringHand.get(i);
                pair2 = scoringHand.get(i + 1);

                // Remove the pair cards from the hand
                scoringHand.remove(i);
                scoringHand.remove(i);

                break;
            }
        }

        // If no Pair found, return false
        if (pair1 == null) {
            return false;
        }

        // Reorganize scoringHand
        scoringHand.clear();
        scoringHand.add(threeKind1);
        scoringHand.add(threeKind2);
        scoringHand.add(threeKind3);
        scoringHand.add(pair1);
        scoringHand.add(pair2);

        System.out.println("Full House");
        for(Card card : scoringHand){
            System.out.println(card.toString());
        }

        return true;
    }
    public boolean isFourOfAKind(List<Card> hand) {
        scoringHand.clear();
        scoringHand.addAll(hand); // Add player's two cards
        scoringHand.addAll(Table.communityCards);

        // Sort the cards in descending order of value
        scoringHand.sort((c1, c2) -> c2.getValue() - c1.getValue());

        // Check for a three of a kind
        for (int i = 0; i < scoringHand.size() - 3; i++) {
            if ((scoringHand.get(i).getValue() == scoringHand.get(i + 1).getValue()) &&
                    (scoringHand.get(i).getValue() == scoringHand.get(i + 2).getValue()) &&
                        (scoringHand.get(i).getValue() == scoringHand.get(i + 3).getValue())){
                // Four of a kind found
                Card card1 = scoringHand.get(i);
                Card card2 = scoringHand.get(i + 1);
                Card card3 = scoringHand.get(i + 2);
                Card card4 = scoringHand.get(i + 3);

                // Reorganize scoringHand
                List<Card> frontFour = new ArrayList<>();
                frontFour.add(card1);
                frontFour.add(card2);
                frontFour.add(card3);
                frontFour.add(card4);

                // Add the highest remaining cards
                for (int j = 0; j < scoringHand.size(); j++) {
                    if (j != i && j != i + 1 && j != i + 2 && j != i + 3 && frontFour.size() < 5) {
                        frontFour.add(scoringHand.get(j));
                    }
                }
                // Update scoringHand
                scoringHand.clear();
                scoringHand.addAll(frontFour);
                System.out.println("Four of a kind");
                for(Card card : scoringHand){
                    System.out.println(card.toString());
                }

                return true;
            }
        }
        return false;
    }
    public boolean isFiveOfAKind(List<Card> hand) {
        return false;
    }
    public boolean isStraightFlush(List<Card> hand) {
        scoringHand.clear();
        scoringHand.addAll(hand); // Add player's two cards
        scoringHand.addAll(Table.communityCards);
        scoringHand.sort((c1, c2) -> c2.getValue() - c1.getValue());
        int numOfSpades = 0;
        int numOfHearts = 0;
        int numOfClubs = 0;
        int numOfDiamonds = 0;
        for(Card card : scoringHand){
            switch (card.suit) {
                case "Spades":
                    numOfSpades++;
                    break;
                case "Hearts":
                    numOfHearts++;
                    break;
                case "Clubs":
                    numOfClubs++;
                    break;
                case "Diamonds":
                    numOfDiamonds++;
                    break;
            }
        }
        ArrayList<Card> flush = new ArrayList<>();
        boolean flushFound = false;
        if(numOfSpades >= 5 || numOfHearts >= 5 || numOfClubs >= 5 || numOfDiamonds >= 5) {
            flushFound = true;
            if(numOfSpades >= 5) {
                for (Card card : scoringHand) {
                    if (card.suit.equals("Spades")) {
                        flush.add(card);
                    }
                }
            }
            else if(numOfHearts >= 5) {
                for (Card card : scoringHand) {
                    if (card.suit.equals("Hearts")) {
                        flush.add(card);
                    }
                }
            }
            else if(numOfClubs >= 5) {
                for (Card card : scoringHand) {
                    if (card.suit.equals("Clubs")) {
                        flush.add(card);
                    }
                }
            }
            else {
                for (Card card : scoringHand) {
                    if (card.suit.equals("Diamonds")){
                        flush.add(card);
                    }
                }
            }
        }
        if(!flushFound){
            return false;
        }
        int straightLength = 1;
        List<Card> straightCards = new ArrayList<>();
        scoringHand = flush;
        straightCards.add(scoringHand.get(0)); // Add first card to start the straight check
        for (int i = 1; i < scoringHand.size(); i++) {
            int current = scoringHand.get(i - 1).getValue();
            int next = scoringHand.get(i).getValue();
            if (current == next) {
                continue;
            } else if (current == next + 1) {
                straightLength++;
                straightCards.add(scoringHand.get(i));
            } else {
                straightLength = 1;
                straightCards.clear();
                straightCards.add(scoringHand.get(i));
            }
            if (straightLength == 4 && scoringHand.get(0).getValue() == 14 && straightCards.get(0).getValue() == 5) {
                straightCards.add(scoringHand.get(0));
                straightLength++;
            }
            if (straightLength == 5) {
                scoringHand.clear();
                scoringHand.addAll(straightCards);
                if(scoringHand.get(0).getValue() == 14){
                    System.out.println("Royal Flush");
                }
                else {
                    System.out.println("Straight Flush");
                }
                for(Card card : scoringHand){
                    System.out.println(card.toString());
                }
                return true;
            }
        }
        return false;
    }
    public boolean isRoyalFlush(List<Card> hand) {
        if(isStraightFlush(hand)){
            return scoringHand.get(0).getValue() == 14;
        }
        return false;
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
