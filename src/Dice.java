public class Dice {

    private int dice1;
    private int dice2;

    public Dice() {
        dice1 = 0;
        dice2 = 0;
    }

    public int rollDice(boolean isMultiDice) {
        if (isMultiDice) {
            return rollMultiDice();
        } else {
            return rollSingleDice();
        }
    }

    private int rollSingleDice() {
        dice1 = (int) (Math.random() * 6) + 1;
        return dice1;
    }

    private int rollMultiDice() {
        dice1 = (int) (Math.random() * 6) + 1;
        dice2 = (int) (Math.random() * 6) + 1;
        return dice1 + dice2;
    }
}