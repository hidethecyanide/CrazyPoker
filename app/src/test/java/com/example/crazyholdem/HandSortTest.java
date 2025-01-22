package com.example.crazyholdem;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HandSortTest {
    private static Table table;

    @BeforeClass
    public static void setUp() {
        // Initialize Table and Deck objects before all tests
        table = new Table();
        table.resetRound();
    }

    @Before
    public void reset(){
        table.resetRound();
    }

    @Ignore
    public void checkPlayerSort1() {
        Player player1 = new Player("Player 1", 100);
        table.addPlayer(player1);

        Table.communityCards.add(new Card("Hearts",5));
        Table.communityCards.add(new Card("Hearts",4));
        Table.communityCards.add(new Card("Diamonds",5));
        Table.communityCards.add(new Card("Hearts",3));
        Table.communityCards.add(new Card("Hearts",2));


        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player1, new Card("Hearts", 6));
        table.dealCard(player1, new Card("Spades", 4));

        Player player2 = new Player("Player 2", 100);
        table.addPlayer(player2);

        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player2, new Card("Hearts", 7));
        table.dealCard(player2, new Card("Spades", 14));

        table.showdown(10);
        assertEquals(110,player1.getMoney());
    }

    @Ignore
    public void checkPlayerSort2() {
        table.dealCommunityCards(5);
        System.out.println("Community Cards:");
        for (int i = 0; i < 5; i++) {
            System.out.println(Table.communityCards.get(i));
        }

        Player player1 = new Player("Player 1", 100);
        table.addPlayer(player1);
        table.dealCard(player1);
        table.dealCard(player1);
        System.out.println("Player 1 Hand:");
        System.out.println(player1.playerHand.getHand());

        Player player2 = new Player("Player 2", 100);
        table.addPlayer(player2);
        table.dealCard(player2);
        table.dealCard(player2);
        System.out.println("Player 2 Hand:");
        System.out.println(player2.playerHand.getHand());

        Player player3 = new Player("Player 3", 100);
        table.addPlayer(player3);
        table.dealCard(player3);
        table.dealCard(player3);
        System.out.println("Player 3 Hand:");
        System.out.println(player3.playerHand.getHand());

        table.showdown(10);
        assertNotEquals(99,player1.getMoney());
    }

    @Test
    public void allInTest() {
        Player player1 = new Player("Player 1", 100);
        table.addPlayer(player1);

        Table.communityCards.add(new Card("Hearts",5));
        Table.communityCards.add(new Card("Hearts",4));
        Table.communityCards.add(new Card("Diamonds",5));
        Table.communityCards.add(new Card("Hearts",3));
        Table.communityCards.add(new Card("Spades",2));


        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player1, new Card("Clubs", 6));
        table.dealCard(player1, new Card("Spades", 4));

        Player player2 = new Player("Player 2", 100);
        table.addPlayer(player2);

        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player2, new Card("Hearts", 7));
        table.dealCard(player2, new Card("Spades", 14));

        Player player3 = new Player("Player 3", 40);

        table.addPlayer(player3);
        table.dealCard(player3, new Card("Spades", 6));
        table.dealCard(player3, new Card("Spades", 14));
        player3.setAllIn(true);


        table.showdown(160);
        assertEquals(200,player1.getMoney());
    }
}