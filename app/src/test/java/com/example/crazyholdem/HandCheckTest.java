package com.example.crazyholdem;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class HandCheckTest {
    private static Table table; // Declare table as a static class-level variable

    @BeforeClass
    public static void setUp() {
        // Initialize Table and Deck objects before all tests
        table = new Table();
    }

    @Before
    public void reset(){
        table.resetRound();
    }

    @Test
    public void pairRecognition() {
        // Set up a player and deal community cards
        Player player1 = new Player("Player 1", 100);
        table.dealCommunityCards(5); // Assuming table manages community cards

        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player1, new Card("Hearts", 3));
        table.dealCard(player1, new Card("Diamonds", 3));

        // Test if a pair is correctly identified
        assertTrue("Player's hand should contain a pair",
                player1.playerHand.isPair(player1.playerHand.getHand()));
    }

    @Test
    public void deckSize() {
        assertEquals("Deck size should be 52", 52, table.getDeck().getDeck().size());
    }

    @Test
    public void currentCard() {
        assertEquals("Current card should be 0", 0, table.getDeck().getCurrentCard());
    }

    //TODO: check contents of arrays
    @Test
    public void threeKindRecognition() {
        // Set up a player and deal community cards
        Player player1 = new Player("Player 1", 100);

        Table.communityCards.add(new Card("Clubs",13));
        Table.communityCards.add(new Card("Hearts",5));
        Table.communityCards.add(new Card("Diamonds",3));
        Table.communityCards.add(new Card("Clubs",4));
        Table.communityCards.add(new Card("Spades",5));

        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player1, new Card("Hearts", 5));
        table.dealCard(player1, new Card("Diamonds", 12));

        // Test if a pair is correctly identified
        assertTrue("Player's hand should contain a three of a kind",
                player1.playerHand.isThreeOfAKind(player1.playerHand.getHand()));
    }

    @Test
    public void fourKindRecognition() {
        // Set up a player and deal community cards
        Player player1 = new Player("Player 1", 100);

        Table.communityCards.add(new Card("Clubs",2));
        Table.communityCards.add(new Card("Hearts",5));
        Table.communityCards.add(new Card("Diamonds",3));
        Table.communityCards.add(new Card("Clubs",3));
        Table.communityCards.add(new Card("Spades",5));

        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player1, new Card("Hearts", 3));
        table.dealCard(player1, new Card("Diamonds", 3));

        // Test if a pair is correctly identified
        assertTrue("Player's hand should contain a four of a kind",
                player1.playerHand.isFourOfAKind(player1.playerHand.getHand()));
    }

    @Test
    public void flushRecognition() {
        // Set up a player and deal community cards
        Player player1 = new Player("Player 1", 100);

        Table.communityCards.add(new Card("Clubs",2));
        Table.communityCards.add(new Card("Hearts",8));
        Table.communityCards.add(new Card("Diamonds",3));
        Table.communityCards.add(new Card("Hearts",3));
        Table.communityCards.add(new Card("Hearts",5));

        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player1, new Card("Hearts", 11));
        table.dealCard(player1, new Card("Hearts", 12));

        // Test if a pair is correctly identified
        assertTrue("Player's hand should contain a flush",
                player1.playerHand.isFlush(player1.playerHand.getHand()));
    }

    @Test
    public void twoPairRecognition() {
        // Set up a player and deal community cards
        Player player1 = new Player("Player 1", 100);

        Table.communityCards.add(new Card("Clubs",2));
        Table.communityCards.add(new Card("Hearts",8));
        Table.communityCards.add(new Card("Diamonds",3));
        Table.communityCards.add(new Card("Spades",3));
        Table.communityCards.add(new Card("Hearts",12));

        // Add specific cards to the player's hand to ensure a pair
        table.dealCard(player1, new Card("Hearts", 11));
        table.dealCard(player1, new Card("Hearts", 12));



        // Test if a pair is correctly identified
        assertTrue("Player's hand should contain a two pair",
                player1.playerHand.isTwoPair(player1.playerHand.getHand()));
    }


}
