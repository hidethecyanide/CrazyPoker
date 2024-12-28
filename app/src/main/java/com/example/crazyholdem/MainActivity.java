package com.example.crazyholdem;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Table table = new Table();
        Player player1 = new Player("Player1", 100);
        Player player2 = new Player("Player2", 100);
        Player player3 = new Player("Player3", 100);
        Player player4 = new Player("Player4", 100);
        table.addPlayer(player1);
        table.addPlayer(player2);
        table.addPlayer(player3);
        table.addPlayer(player4);


    }
}