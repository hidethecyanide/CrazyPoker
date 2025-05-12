package com.example.crazyholdem;


public class Player {

    private String name;
    int money;
    int allInAmount;
    private boolean isDealer;
    private boolean isAllIn;
    private boolean isFolded;
    PlayerHand playerHand;
    int bet;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.allInAmount = 0;
        this.isDealer = false;
        this.isAllIn = false;
        this.isFolded = false;
        this.playerHand = new PlayerHand();
        this.bet = 0;
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
        setAllInAmount(money);
        money = 0;
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

    public void showHand(){
        System.out.println(playerHand.getHand());
    }

    public void setAllInAmount(int allInAmount){
        this.allInAmount = allInAmount;
    }
    public int getAllInAmount(){
        return this.allInAmount;
    }
    public void bet(int amount){
        money -= amount;
        bet=amount;
    }
    public int getBet(){
        return bet;
    }
    public void resetBet(){
        bet = 0;
    }



}
