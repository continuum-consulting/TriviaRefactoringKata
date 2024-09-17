package trivia;

import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
    Board board = new Board();

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

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

    public boolean add(String playerName) {
        // 0 what does mean?
        Player player = new Player(playerName);
        board.addPlayer(player);

        System.out.println(player.name() + " was added");
        System.out.println("They are player number " + board.getAmountOfPlayers());
        return true;
    }

    public void roll(int roll) {
        Player currentPlayer = board.getCurrentPlayer();

        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.inPenaltyBox()) {
            if (doesPlayerGetOutOfPenaltyBox(roll)) {
                board.getCurrentPlayer().gettingOutOfPenaltyBox();

                System.out.println(currentPlayer + " is getting out of the penalty box");
                board.getCurrentPlayer().rollPosition(roll);
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(currentPlayer + " is not getting out of the penalty box");
                board.getCurrentPlayer().stayingInPenaltyBox();
            }
        } else {
            board.getCurrentPlayer().rollPosition(roll);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }
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
        int place = board.getCurrentPlayer().place();
        if (place == 0) return "Pop";
        if (place == 4) return "Pop";
        if (place == 8) return "Pop";
        if (place == 1) return "Science";
        if (place == 5) return "Science";
        if (place == 9) return "Science";
        if (place == 2) return "Sports";
        if (place == 6) return "Sports";
        if (place == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = board.getCurrentPlayer();
        if (currentPlayer.inPenaltyBox() && !currentPlayer.isGettingOutOfPenaltyBox()) {
            board.nextTurn();
            return true;
        } else {
            return wasCorrectAnswer();
        }
    }

    private boolean wasCorrectAnswer() {
        System.out.println("Answer was correct!!!!");
        board.getCurrentPlayer().addCoin();

        boolean winner = didPlayerWin();
        board.nextTurn();
        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        board.getCurrentPlayer().placeInPenaltyBox();

        board.nextTurn();
        return true;
    }

    private boolean didPlayerWin() {
        return !(board.getCurrentPlayer().purse() == 6);
    }
}
