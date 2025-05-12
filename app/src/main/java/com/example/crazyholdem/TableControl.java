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
            pot += table.startRound(0);
            table.resetRound();
            pot += table.startRound(3);
            table.resetRound();
            pot += table.startRound(1);
            table.resetRound();
            pot += table.startRound(1);
            table.resetRound();
            table.showdown(pot);
            table.resetRound();
            for (Player player : table.getPlayers()) {
                if(player.getMoney() <= 0){
                    table.removePlayer(player);
                }
            }
            int dealerIndex = -1;
            for (int i = 0; i < table.getPlayers().size(); i++) {
                if (table.getPlayers().get(i).isDealer()) {
                    dealerIndex = i;
                    break;
                }
            }
            if (dealerIndex != -1) {
                int newDealerIndex = (dealerIndex + 1) % table.getPlayers().size();
                table.getPlayers().get(newDealerIndex).setDealer(true);
                table.getPlayers().get(dealerIndex).setDealer(false);
            }
            else {
                System.out.println("No dealer found.");
            }
            //TODO: Shop
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
