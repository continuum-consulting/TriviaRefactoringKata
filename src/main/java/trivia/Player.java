package trivia;

import java.util.Objects;

public final class Player {
    private final String name;
    private  int place = 0;
    private final int purse = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public void rollPosition(int roll) {
        place = place + roll;
        if (place > 11) place = place - 12;
    }

    public int place() {
        return place;
    }

    public int purse() {
        return purse;
    }

    public boolean inPenaltyBox() {
        return inPenaltyBox;
    }

    public void getOutOfPenaltyBox() {
        inPenaltyBox = false;
    }

    public void placeInPenaltyBox() {
        System.out.println(name + " was sent to the penalty box");
        inPenaltyBox = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Player) obj;
        return Objects.equals(this.name, that.name) &&
                this.place == that.place &&
                this.purse == that.purse &&
                this.inPenaltyBox == that.inPenaltyBox;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place, purse, inPenaltyBox);
    }

    @Override
    public String toString() {
        return name;
    }


}
