package trivia;

import java.util.Objects;

public final class Player {
    private final String name;
    private final int place = 0;
    private final int purse = 0;
    private final boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String name() {
        return name;
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
        return "Player[" +
                "name=" + name + ", " +
                "place=" + place + ", " +
                "purse=" + purse + ", " +
                "inPenaltyBox=" + inPenaltyBox + ']';
    }


}
