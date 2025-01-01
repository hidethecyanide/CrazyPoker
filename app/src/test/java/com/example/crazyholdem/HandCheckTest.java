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
        player1.playerHand.addCardToHand(new Card("Hearts", 3));
        player1.playerHand.addCardToHand(new Card("Diamonds", 3));

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

    @Ignore
    public void threeKindRecognition() {
        // Set up a player and deal community cards
        Player player1 = new Player("Player 1", 100);
        table.dealCommunityCards(5); // Assuming table manages community cards

        // Add specific cards to the player's hand to ensure a pair
        player1.playerHand.addCardToHand(new Card("Hearts", 3));
        player1.playerHand.addCardToHand(new Card("Diamonds", 2));

        // Test if a pair is correctly identified
        assertTrue("Player's hand should contain a pair",
                player1.playerHand.isPair(player1.playerHand.getHand()));
    }
}
