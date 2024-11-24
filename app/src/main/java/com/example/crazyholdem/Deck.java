package com.example.crazyholdem;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> deck;
    private int numberOfJokers;
    private int numberOfCards;
    private int currentCard;

    public Deck() {
        deck = new ArrayList<>();
    }

    public void createDeck(){
        for (String suit : new String[]{"hearts", "diamonds", "clubs", "spades"}) {
            for (int j = 2; j < 15; j++) {
                deck.add(new Card(suit, j));

            }
        }
    }
    public int getNumberOfCards(){
        return numberOfCards;
    }
    public int getCurrentCard() {
        return currentCard;
    }
    public void setCurrentCard(int currentCard) {
        this.currentCard = currentCard;
    }
    public void addCardToDeck(Card card){
        deck.add(card);
    }
    public void removeCardFromDeck(Card card){
        deck.remove(card);
    }
    public Card getCardFromDeck(int index){
        return deck.get(index);
    }

    public void addJokers(int numberOfJokers){
        for (int i = 0; i < numberOfJokers; i++) {
            deck.add(new Card("joker", 0));
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }
}