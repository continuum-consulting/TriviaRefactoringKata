package trivia;

import java.util.ArrayList;

public class Board {
    ArrayList<Player> players = new ArrayList<>();
    int currentPlayerIndex = 0;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getAmountOfPlayers() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        currentPlayerIndex++;
        if (currentPlayerIndex == getAmountOfPlayers()) currentPlayerIndex = 0;
    }
}
