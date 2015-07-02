package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstPlayer { // for some reason two separate Player class are built, the reasons are not convincing enough :|
    private String name;

    private int lastPos;
    private int lastDice1Face;
    private int lastDice2Face;
    private int lastMoney;
    private int lastDebt;
    private int lastProperty;
    private int lastDorm;
    private boolean stuck;
    private int doubleSix;
    private boolean doubleSixBan;

    private boolean turn;

    private List<Integer> propertyIndexes;
    private List<Integer> dormIndexes;
    private List<Integer> positions;
    private List<Integer> dice1Faces;
    private List<Integer> dice2Faces;
    private List<Integer> money;
    private List<Integer> debts;
    private Map<Integer, Integer> quarters; // index ------> amount

    public FirstPlayer(String name) {
        this.name = name;

        lastPos = 1;
        lastMoney = 10000;

        turn = true;
        stuck = false;
        doubleSix = 0;
        doubleSixBan = false;

        propertyIndexes = new ArrayList<>();
        dormIndexes = new ArrayList<>();
        positions = new ArrayList<>();
        dice1Faces = new ArrayList<>();
        dice2Faces = new ArrayList<>();
        money = new ArrayList<>();
        quarters = new HashMap<>();

        money.add(10000);
        positions.add(1);
    }

    public void move(int dice1Face, int dice2Face, int lastPos) { //calculated position(alpha-0)
        positions.add(lastPos);
        dice1Faces.add(dice1Face);
        dice2Faces.add(dice2Face);

        this.lastPos = lastPos;
        this.lastDice1Face = dice1Face;
        this.lastDice2Face = dice2Face;
    }

    public String getName() {
        return this.name;
    }

    public int getLastPos() {
        return lastPos;
    }

    public void setLastPos(int lastPos) {
        this.lastPos = lastPos;
        positions.add(this.lastPos);
    }

    public int getLastDice1Face() {
        return lastDice1Face;
    }

    public void setLastDice1Face(int lastDice1Face) {
        this.lastDice1Face = lastDice1Face;
    }

    public int getLastDice2Face() {
        return lastDice2Face;
    }

    public void setLastDice2Face(int lastDice2Face) {
        this.lastDice2Face = lastDice2Face;
    }

    public int getLastMoney() {
        return lastMoney;
    }

    public void setLastMoney(int lastMoney) {
        this.lastMoney = lastMoney;
        money.add(this.lastMoney);
    }

    public int getLastDebt() {
        return lastDebt;
    }

    public void setLastDebt(int lastDebt) {
        this.lastDebt = lastDebt;
//        debts.add(this.lastDebt);
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    public List<Integer> getDice1Faces() {
        return dice1Faces;
    }

    public void setDice1Faces(List<Integer> dice1Faces) {
        this.dice1Faces = dice1Faces;
    }

    public List<Integer> getDice2Faces() {
        return dice2Faces;
    }

    public void setDice2Faces(List<Integer> dice2Faces) {
        this.dice2Faces = dice2Faces;
    }

    public List<Integer> getMoney() {
        return money;
    }

    public void setMoney(List<Integer> money) {
        this.money = money;
    }

    public List<Integer> getDebts() {
        return debts;
    }

    public void setDebts(List<Integer> debts) {
        this.debts = debts;
    }

    public int getLastProperty() {
        return lastProperty;
    }

    public void setLastProperty(int lastProperty) {
        this.lastProperty = lastProperty;
        propertyIndexes.add(lastProperty);
    }

    public List<Integer> getPropertyIndexes() {
        return propertyIndexes;
    }

    public void setPropertyIndexes(List<Integer> propertyIndexes) {
        this.propertyIndexes = propertyIndexes;
    }

    public boolean isStuck() {
        return stuck;
    }

    public void setStuck(boolean stuck) {
        this.stuck = stuck;
    }

    public int getDoubleSix() {
        return doubleSix;
    }

    public void setDoubleSix(int doubleSix) {
        this.doubleSix = doubleSix;
    }

    public boolean isDoubleSixBan() {
        return doubleSixBan;
    }

    public void setDoubleSixBan(boolean doubleSixBan) {
        this.doubleSixBan = doubleSixBan;
    }

    public Map<Integer, Integer> getQuarters() {
        return quarters;
    }

    public void setQuarters(int propertyIndex, int quarter) {
        quarters.put(propertyIndex, quarter);
    }

    public void setQuartersLoad(Map<Integer, Integer> quarters) {
        this.quarters = quarters;
    }

    public int getLastDorm() {
        return lastDorm;
    }

    public void setLastDorm(int lastDorm) {
        this.lastDorm = lastDorm;
        dormIndexes.add(lastDorm);
    }

    public List<Integer> getDormIndexes() {
        return dormIndexes;
    }

    public void setDormIndexes(List<Integer> dormIndexes) {
        this.dormIndexes = dormIndexes;
    }
}
