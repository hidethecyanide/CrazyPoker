package com.example.crazyholdem;

import org.junit.Before;
import org.junit.BeforeClass;
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

    @Test
    public void checkPlayerSort1() {
        Player player1 = new Player("Player 1", 100);
        table.addPlayer(player1);

        Table.communityCards.add(new Card("Diamonds",12));
        Table.communityCards.add(new Card("Diamonds",9));
        Table.communityCards.add(new Card("Diamonds",2));
        Table.communityCards.add(new Card("Clubs",12));
        Table.communityCards.add(new Card("Spades",6));


        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player1, new Card("Diamonds", 7));
        table.dealCard(player1, new Card("Spades", 9));

        Player player2 = new Player("Player 2", 100);
        table.addPlayer(player2);

        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player2, new Card("Diamonds", 3));
        table.dealCard(player2, new Card("Hearts", 9));

        table.showdown(10);
        assertEquals(110,player1.getMoney());
    }
}