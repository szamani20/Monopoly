package gui;

import java.util.EventObject;
import java.util.List;
import java.util.Map;

public class GameEvent extends EventObject { // this method is used only to gather and pack information as an EventObject and then save it on a file or, to gather information from a file and then load it

    private String firstPlayerName;
    private int firstPlayerLastPos;
    private int firstPlayerLastDice1Face;
    private int firstPlayerLastDice2Face;
    private boolean firstPlayerTurn;
    private int firstPlayerLastMoney;
    private int firstPlayerLstDebt;
    private int firstPlayerLastProperty;
    private int firstPlayerLastDorm;
    private boolean firstPlayerStuck;
    private int firstPlayerDoubleSix;
    private boolean firstPlayerDoubleSixBan;
    private List<Integer> firstPlayerDormIndexes;
    private List<Integer> firstPlayerPositions;
    private List<Integer> firstPlayerDice1Faces;
    private List<Integer> firstPlayerDice2Faces;
    private List<Integer> firstPlayerMoney;
    private List<Integer> firstPlayerDebts;
    private List<Integer> firstPlayerPropertyIndexes;
    private Map<Integer, Integer> firstPlayerQuarters;

    private String secondPlayerName;
    private int secondPlayerLastPos;
    private int secondPlayerLastDice1Face;
    private int secondPlayerLastDice2Face;
    private boolean secondPlayerTurn;
    private int secondPlayerLastMoney;
    private int secondPlayerLastDebt;
    private int secondPlayerLastProperty;
    private int secondPlayerLastDorm;
    private boolean secondPlayerStuck;
    private int secondPlayerDoubleSix;
    private boolean secondPlayerDoubleSixBan;
    private List<Integer> secondPlayerDormIndexes;
    private List<Integer> secondPlayerPositions;
    private List<Integer> secondPlayerDice1Faces;
    private List<Integer> secondPlayerDice2Faces;
    private List<Integer> secondPlayerMoney;
    private List<Integer> secondPlayerDebts;
    private List<Integer> secondPlayerPropertyIndexes;
    private Map<Integer, Integer> secondPlayerQuarters;

    public GameEvent(Object source, String firstPlayerName, int firstPlayerLastPos,
                     int firstPlayerLastDice1Face, int firstPlayerLastDice2Face, boolean firstPlayerTurn,
                     int firstPlayerLastMoney, int firstPlayerLstDebt, int firstPlayerLastProperty, int firstPlayerLastDorm, boolean firstPlayerStuck,
                     int firstPlayerDoubleSix, boolean firstPlayerDoubleSixBan, List<Integer> firstPlayerDormIndexes, List<Integer> firstPlayerPositions,
                     List<Integer> firstPlayerDice1Faces, List<Integer> firstPlayerDice2Faces, List<Integer> firstPlayerMoney,
                     List<Integer> firstPlayerDebts, List<Integer> firstPlayerPropertyIndexes, Map<Integer, Integer> firstPlayerQuarters,
                     String secondPlayerName, int secondPlayerLastPos, int secondPlayerLastDice1Face,
                     int secondPlayerLastDice2Face, boolean secondPlayerTurn, int secondPlayerLastMoney,
                     int secondPlayerLastDebt, int secondPlayerLastProperty, int secondPlayerLastDorm, boolean secondPlayerStuck,
                     int secondPlayerDoubleSix, boolean secondPlayerDoubleSixBan, List<Integer> secondPlayerDormIndexes, List<Integer> secondPlayerPositions,
                     List<Integer> secondPlayerDice1Faces, List<Integer> secondPlayerDice2Faces,
                     List<Integer> secondPlayerMoney, List<Integer> secondPlayerDebts, List<Integer> secondPlayerPropertyIndexes, Map<Integer, Integer> secondPlayerQuarters) {
        super(source);
        this.firstPlayerName = firstPlayerName;
        this.firstPlayerLastPos = firstPlayerLastPos;
        this.firstPlayerLastDice1Face = firstPlayerLastDice1Face;
        this.firstPlayerLastDice2Face = firstPlayerLastDice2Face;
        this.firstPlayerTurn = firstPlayerTurn;
        this.firstPlayerLastMoney = firstPlayerLastMoney;
        this.firstPlayerLstDebt = firstPlayerLstDebt;
        this.firstPlayerLastProperty = firstPlayerLastProperty;
        this.firstPlayerLastDorm = firstPlayerLastDorm;
        this.firstPlayerStuck = firstPlayerStuck;
        this.firstPlayerDoubleSix = firstPlayerDoubleSix;
        this.firstPlayerDoubleSixBan = firstPlayerDoubleSixBan;
        this.firstPlayerDormIndexes = firstPlayerDormIndexes;
        this.firstPlayerPositions = firstPlayerPositions;
        this.firstPlayerDice1Faces = firstPlayerDice1Faces;
        this.firstPlayerDice2Faces = firstPlayerDice2Faces;
        this.firstPlayerMoney = firstPlayerMoney;
        this.firstPlayerDebts = firstPlayerDebts;
        this.firstPlayerPropertyIndexes = firstPlayerPropertyIndexes;
        this.firstPlayerQuarters = firstPlayerQuarters;
        this.secondPlayerName = secondPlayerName;
        this.secondPlayerLastPos = secondPlayerLastPos;
        this.secondPlayerLastDice1Face = secondPlayerLastDice1Face;
        this.secondPlayerLastDice2Face = secondPlayerLastDice2Face;
        this.secondPlayerTurn = secondPlayerTurn;
        this.secondPlayerLastMoney = secondPlayerLastMoney;
        this.secondPlayerLastDebt = secondPlayerLastDebt;
        this.secondPlayerLastProperty = secondPlayerLastProperty;
        this.secondPlayerLastDorm = secondPlayerLastDorm;
        this.secondPlayerStuck = secondPlayerStuck;
        this.secondPlayerDoubleSix = secondPlayerDoubleSix;
        this.secondPlayerDoubleSixBan = secondPlayerDoubleSixBan;
        this.secondPlayerDormIndexes = secondPlayerDormIndexes;
        this.secondPlayerPositions = secondPlayerPositions;
        this.secondPlayerDice1Faces = secondPlayerDice1Faces;
        this.secondPlayerDice2Faces = secondPlayerDice2Faces;
        this.secondPlayerMoney = secondPlayerMoney;
        this.secondPlayerDebts = secondPlayerDebts;
        this.secondPlayerPropertyIndexes = secondPlayerPropertyIndexes;
        this.secondPlayerQuarters = secondPlayerQuarters;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public int getFirstPlayerLastPos() {
        return firstPlayerLastPos;
    }

    public int getFirstPlayerLastDice1Face() {
        return firstPlayerLastDice1Face;
    }

    public int getFirstPlayerLastDice2Face() {
        return firstPlayerLastDice2Face;
    }

    public boolean isFirstPlayerTurn() {
        return firstPlayerTurn;
    }

    public int getFirstPlayerLastMoney() {
        return firstPlayerLastMoney;
    }

    public int getFirstPlayerLstDebt() {
        return firstPlayerLstDebt;
    }

    public int getFirstPlayerLastProperty() {
        return firstPlayerLastProperty;
    }

    public boolean isFirstPlayerStuck() {
        return firstPlayerStuck;
    }

    public int getFirstPlayerDoubleSix() {
        return firstPlayerDoubleSix;
    }

    public boolean isFirstPlayerDoubleSixBan() {
        return firstPlayerDoubleSixBan;
    }

    public List<Integer> getFirstPlayerPositions() {
        return firstPlayerPositions;
    }

    public List<Integer> getFirstPlayerDice1Faces() {
        return firstPlayerDice1Faces;
    }

    public List<Integer> getFirstPlayerDice2Faces() {
        return firstPlayerDice2Faces;
    }

    public List<Integer> getFirstPlayerMoney() {
        return firstPlayerMoney;
    }

    public List<Integer> getFirstPlayerDebts() {
        return firstPlayerDebts;
    }

    public List<Integer> getFirstPlayerPropertyIndexes() {
        return firstPlayerPropertyIndexes;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public int getSecondPlayerLastPos() {
        return secondPlayerLastPos;
    }

    public int getSecondPlayerLastDice1Face() {
        return secondPlayerLastDice1Face;
    }

    public int getSecondPlayerLastDice2Face() {
        return secondPlayerLastDice2Face;
    }

    public boolean isSecondPlayerTurn() {
        return secondPlayerTurn;
    }

    public int getSecondPlayerLastMoney() {
        return secondPlayerLastMoney;
    }

    public int getSecondPlayerLastDebt() {
        return secondPlayerLastDebt;
    }

    public int getSecondPlayerLastProperty() {
        return secondPlayerLastProperty;
    }

    public boolean isSecondPlayerStuck() {
        return secondPlayerStuck;
    }

    public int getSecondPlayerDoubleSix() {
        return secondPlayerDoubleSix;
    }

    public boolean isSecondPlayerDoubleSixBan() {
        return secondPlayerDoubleSixBan;
    }

    public List<Integer> getSecondPlayerPositions() {
        return secondPlayerPositions;
    }

    public List<Integer> getSecondPlayerDice1Faces() {
        return secondPlayerDice1Faces;
    }

    public List<Integer> getSecondPlayerDice2Faces() {
        return secondPlayerDice2Faces;
    }

    public List<Integer> getSecondPlayerMoney() {
        return secondPlayerMoney;
    }

    public List<Integer> getSecondPlayerDebts() {
        return secondPlayerDebts;
    }

    public List<Integer> getSecondPlayerPropertyIndexes() {
        return secondPlayerPropertyIndexes;
    }

    public int getFirstPlayerLastDorm() {
        return firstPlayerLastDorm;
    }

    public List<Integer> getFirstPlayerDormIndexes() {
        return firstPlayerDormIndexes;
    }

    public Map<Integer, Integer> getFirstPlayerQuarters() {
        return firstPlayerQuarters;
    }

    public int getSecondPlayerLastDorm() {
        return secondPlayerLastDorm;
    }

    public List<Integer> getSecondPlayerDormIndexes() {
        return secondPlayerDormIndexes;
    }

    public Map<Integer, Integer> getSecondPlayerQuarters() {
        return secondPlayerQuarters;
    }
}
