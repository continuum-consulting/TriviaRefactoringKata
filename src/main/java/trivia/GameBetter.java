package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
    ArrayList<Player> players = new ArrayList<>();

    int[] places = new int[6];
    int[] purses = new int[6]; //Has to do with coins

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    //TODO possibility to add in board/game/...
    int currentPlayerIndex = 0;
    boolean isGettingOutOfPenaltyBox;

    public GameBetter() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        // 0 what does mean?
        Player player = new Player(playerName);
        players.add(player);

        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;

        System.out.println(player.name() + " was added");
        System.out.println("They are player number " + howManyPlayers());
        return true;
    }

    //TODO possibility to add a board/game/... and ask that object how many players2 there are
    public int howManyPlayers() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void roll(int roll) {
        Player currentPlayer = getCurrentPlayer();

        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.inPenaltyBox()) {
            if (doesPlayerGetOutOfPenaltyBox(roll)) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer + " is getting out of the penalty box");
                movePlayer(roll);
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(currentPlayer + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            movePlayer(roll);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }
    }

    private void movePlayer(int roll) {
        places[currentPlayerIndex] = places[currentPlayerIndex] + roll;
        if (places[currentPlayerIndex] > 11) places[currentPlayerIndex] = places[currentPlayerIndex] - 12;

        System.out.println(players.get(currentPlayerIndex)
                + "'s new location is "
                + places[currentPlayerIndex]);
    }

    private static boolean doesPlayerGetOutOfPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    private void askQuestion() {
        if (currentCategory().equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory().equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory().equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory().equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }

    private String currentCategory() {
        if (places[currentPlayerIndex] == 0) return "Pop";
        if (places[currentPlayerIndex] == 4) return "Pop";
        if (places[currentPlayerIndex] == 8) return "Pop";
        if (places[currentPlayerIndex] == 1) return "Science";
        if (places[currentPlayerIndex] == 5) return "Science";
        if (places[currentPlayerIndex] == 9) return "Science";
        if (places[currentPlayerIndex] == 2) return "Sports";
        if (places[currentPlayerIndex] == 6) return "Sports";
        if (places[currentPlayerIndex] == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (getCurrentPlayer().inPenaltyBox() && !isGettingOutOfPenaltyBox) {
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
            return true;
        } else {
            return wasCorrectAnswer();
        }
    }

    private boolean wasCorrectAnswer() {
        System.out.println("Answer was correct!!!!");
        purses[currentPlayerIndex]++;
        System.out.println(players.get(currentPlayerIndex)
                + " now has "
                + purses[currentPlayerIndex]
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        getCurrentPlayer().placeInPenaltyBox();

        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayerIndex] == 6);
    }
}
