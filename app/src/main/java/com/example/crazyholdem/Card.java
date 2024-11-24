package com.example.crazyholdem;

public class Card {

        String suit;
        int value;

        public Card(String suit, int value) {
            this.suit = suit;
            this.value = value;
        }

        public String getSuit() {
            return suit;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

}
