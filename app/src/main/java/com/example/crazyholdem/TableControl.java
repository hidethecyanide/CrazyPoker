package com.example.crazyholdem;

public class TableControl {
    public int numPlayers;
    public int timeSetting;
    public int roundsPerIncrease;
    public int stake;
    public Table table;

    public TableControl(int numPlayers, int timeSetting, int roundsPerIncrease, int stake) {
        this.numPlayers = numPlayers;
        this.timeSetting = timeSetting;
        this.roundsPerIncrease = roundsPerIncrease;
        this.stake = stake;
        table = new Table(stake);
    }

    public void setTable(int stake) {
        int i = 0;
        for (i = 0; i < numPlayers - 1; i++) {
            Player player = new Player("AI" + i, stake * 100);
            table.addPlayer(player);
        }
        Player player = new Player("You", stake * 100);
        player.setDealer(true);
        table.addPlayer(player);
        table.resetRound();
    }

    //TODO: dealer shift and winner logic
    public void gameControl(int stake) {

        while (table.getPlayers().size() > 1) {
            int pot = 0;
            table.prePlay(roundsPerIncrease);
            pot += table.startRound(3);
            pot += table.startRound(1);
            pot += table.startRound(1);
            table.showdown(pot);
            for (Player player : table.getPlayers()) {
                if(player.getMoney() <= 0){
                    table.removePlayer(player);
                }
            }
        }
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setTimeSetting(int timeSetting) {
        this.timeSetting = timeSetting;
    }

    public void setRoundsPerIncrease(int roundsPerIncrease) {
        this.roundsPerIncrease = roundsPerIncrease;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }
}
