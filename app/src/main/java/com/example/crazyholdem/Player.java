package com.example.crazyholdem;

import java.util.ArrayList;

public class Player {

    private String name;
    int money;
    private ArrayList<Card> hand;
    private boolean isDealer;
    private boolean isAllIn;
    private boolean isFolded;
    PlayerHand playerHand;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.isDealer = false;
        this.isAllIn = false;
        this.isFolded = false;
        this.playerHand = new PlayerHand();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getMoney(){
        return this.money;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public boolean isDealer(){
        return this.isDealer;
    }

    public void setDealer(boolean isDealer){
        this.isDealer = isDealer;
    }

    public boolean isAllIn(){
        return this.isAllIn;
    }

    public void setAllIn(boolean isAllIn){
        this.isAllIn = isAllIn;
    }

    public boolean isFolded(){
        return this.isFolded;
    }

    public void setFolded(boolean isFolded){
        this.isFolded = isFolded;
    }

    public void fold(){
        this.isFolded = true;
    }

    public void checkHand(){
        playerHand.isHighCard(playerHand.hand);
        playerHand.isPair(playerHand.hand);
        //playerHand.isTwoPair(playerHand.hand);
        //playerHand.isThreeKind(playerHand.hand);
        //playerHand.isStraight(playerHand.hand);
        //playerHand.isFlush(playerHand.hand);
        //playerHand.isFullHouse(playerHand.hand);
        //playerHand.isFourKind(playerHand.hand);
        //playerHand.isFiveKind(playerHand.hand);
        //playerHand.isStraightFlush(playerHand.hand);
        //playerHand.isRoyalFlush(playerHand.hand);
    }

}
