package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameHandle { // this class handle the entire game and the only players references are created here statically
    public static FirstPlayer firstPlayer = new FirstPlayer("CE");
    public static SecondPlayer secondPlayer = new SecondPlayer("EE");
    public static List<Integer> propertiesPrices;
    private Random random1;
    private Random random2;
    private int firstPlayerMoneyTransfer;
    private int secondPlayerMoneyTransfer;
    public static boolean endSuspension;
    public static boolean end;

    public GameHandle() {

        propertiesPrices = new ArrayList<>();
        setPropertiesPrice();

        random1 = new Random();
        random1.setSeed(System.currentTimeMillis());

        random2 = new Random();
        random2.setSeed(System.currentTimeMillis() % 10000);

        endSuspension = false;
        end = false;
    }

    public void move() { // this method is called through MainBoard class whenever the roll button is clicked

        long allocatedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("\nmemory allocated: " + allocatedMemory / (1024L * 1024L) + "mb");

        firstPlayerMoneyTransfer = 0;
        secondPlayerMoneyTransfer = 0;

        setPlayersPosition();

        setPlayerMoney();

        checkEnd();

    }

    private void setPlayerMoney() {
        ////////////// after setting player position we need to set player money


        if (firstPlayer.isTurn()&& firstPlayer.getLastPos() == 28) // caution : position must not set here to 10 so that the animation would work properly!
            firstPlayerMoneyTransfer -= 500; // money reduction take place here

        if (secondPlayer.isTurn()&& secondPlayer.getLastPos() == 28) // caution : position must not set here to 10 so that the animation would work properly!
            secondPlayerMoneyTransfer -= 500; // money reduction take place here

        if (firstPlayer.isTurn()&& secondPlayer.getPropertyIndexes().indexOf(firstPlayer.getLastPos()) != -1) // being in another player property
            firstPlayerMoneyTransfer -= (int)(propertiesPrices.get(firstPlayer.getLastPos() - 1) * 0.1);

        if (firstPlayer.isTurn()&& secondPlayer.getQuarters().keySet().contains(firstPlayer.getLastPos())) // being in another player quarters
            firstPlayerMoneyTransfer -= (int) (propertiesPrices.get(firstPlayer.getLastPos() - 1) * secondPlayer.getQuarters().get(firstPlayer.getLastPos()) * 0.1 * 0.2);

        if (firstPlayer.isTurn()&& secondPlayer.getDormIndexes().indexOf(firstPlayer.getLastPos()) != -1) // being in another player dorm
            firstPlayerMoneyTransfer -= (int) (propertiesPrices.get(firstPlayer.getLastPos() - 1) * 0.5 * 0.1);

        if (secondPlayer.isTurn()&& firstPlayer.getPropertyIndexes().indexOf(secondPlayer.getLastPos()) != -1) // being in another player property
            secondPlayerMoneyTransfer -= (int)(propertiesPrices.get(secondPlayer.getLastPos() - 1) * 0.1);

        if (secondPlayer.isTurn()&& firstPlayer.getQuarters().keySet().contains(secondPlayer.getLastPos())) // being in another player quarters
            secondPlayerMoneyTransfer -= (int) (propertiesPrices.get(secondPlayer.getLastPos() - 1) * firstPlayer.getQuarters().get(secondPlayer.getLastPos()) * 0.1 * 0.2);

        if (secondPlayer.isTurn()&& firstPlayer.getDormIndexes().indexOf(secondPlayer.getLastPos()) != -1) // being in another player dorm
            secondPlayerMoneyTransfer -= (int) (propertiesPrices.get(secondPlayer.getLastPos() - 1) * 0.5 * 0.1);

        if (firstPlayer.isTurn()) {
            firstPlayer.setLastMoney(firstPlayer.getLastMoney() + firstPlayerMoneyTransfer);
            System.out.println("first player money transfer: " + firstPlayerMoneyTransfer);
            System.out.println("first player money:" + firstPlayer.getLastMoney());
            System.out.println("firstPlayer properties total price: " + new Integer(getPlayerPropertiesPrice(1) + getPlayerQuartersPrice(1) + getPlayerDormPrice(1)));
        }

        if (secondPlayer.isTurn()) {
            secondPlayer.setLastMoney(secondPlayer.getLastMoney() + secondPlayerMoneyTransfer);
            System.out.println("second player money transfer: " + secondPlayerMoneyTransfer);
            System.out.println("second player money:" + secondPlayer.getLastMoney());
            System.out.println("secondPlayer properties total price: " + new Integer(getPlayerPropertiesPrice(2) + getPlayerQuartersPrice(2) + getPlayerDormPrice(2)));
        }
    }

    private void setPlayersPosition() { // setting player positions and also some part of money transfers!
        int firstPlayerDice1 = 0;
        int firstPlayerDice2 = 0;
        int secondPlayerDice1 = 0;
        int secondPlayerDice2 = 0;

        if (firstPlayer.isTurn()) {
            firstPlayerDice1 = Math.abs(random1.nextInt()) % 6 + 1;
            firstPlayerDice2 = Math.abs(random1.nextInt()) % 6 + 1;

        }

        if (secondPlayer.isTurn()) {
            secondPlayerDice1 = Math.abs(random2.nextInt()) % 6 + 1;
            secondPlayerDice2 = Math.abs(random2.nextInt()) % 6 + 1;


        }

        if (firstPlayer.isTurn()&& firstPlayerDice1 == 6&& firstPlayerDice2 == 6) // just setting (incrementing) the double six value
            firstPlayer.setDoubleSix(firstPlayer.getDoubleSix() + 1);
        if (firstPlayer.isTurn()&& (firstPlayerDice1 != 6|| firstPlayerDice2 != 6)) // if it's not a double six then the setDoubleSix method must invoke and set to 0
            firstPlayer.setDoubleSix(0);

        if (secondPlayer.isTurn()&& secondPlayerDice1 == 6&& secondPlayerDice2 == 6) // just setting (incrementing) the double six value
            secondPlayer.setDoubleSix(secondPlayer.getDoubleSix() + 1);
        if (secondPlayer.isTurn()&& (secondPlayerDice1 != 6|| secondPlayerDice2 != 6)) // if it's not a double six then the setDoubleSix method must invoke and set to 0
            secondPlayer.setDoubleSix(0);

        ///////////// position doesn't necessary equal to pos += dice1 + dice2   ------------> alpha-0 and jail

        int firstPlayerPosition = firstPlayer.getLastPos();
        int secondPlayerPosition = secondPlayer.getLastPos();

        if (firstPlayer.getDoubleSix() != 2)
            firstPlayerPosition = firstPlayer.getLastPos() + firstPlayerDice1 + firstPlayerDice2;

        if (firstPlayerPosition >= 37)
            firstPlayerMoneyTransfer += 200;
        firstPlayerPosition %= 37;
        if (firstPlayerPosition == 0)
            ++firstPlayerPosition;
        if (firstPlayer.isTurn()) // at the beginning of turn, gain benefits
            firstPlayerMoneyTransfer += (int) ((getPlayerPropertiesPrice(1) * 0.1) + (getPlayerQuartersPrice(1) * 0.1) + (getPlayerDormPrice(1) * 0.1));

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (secondPlayer.getDoubleSix() != 2)
            secondPlayerPosition = secondPlayer.getLastPos() + secondPlayerDice1 + secondPlayerDice2;

        if (secondPlayerPosition >= 37)
            secondPlayerMoneyTransfer += 200;
        secondPlayerPosition %= 37;
        if (secondPlayerPosition == 0)
            ++secondPlayerPosition;
        if (secondPlayer.isTurn()) // at the beginning of turn, gain benefits
            secondPlayerMoneyTransfer += (int) ((getPlayerPropertiesPrice(2) * 0.1) + (getPlayerQuartersPrice(2) * 0.1) + (getPlayerDormPrice(2) * 0.1));

        if (firstPlayer.isTurn()) {
            firstPlayer.move(firstPlayerDice1, firstPlayerDice2, firstPlayerPosition);  // changing the turn take place in draw animation after moving players
            System.out.println("firstPlayer: "+firstPlayerDice1 + "  " + firstPlayerDice2 + "  " + firstPlayerPosition);
        }

        if (secondPlayer.isTurn()) {
            secondPlayer.move(secondPlayerDice1, secondPlayerDice2, secondPlayerPosition);  // changing the turn take place in draw animation after moving players
            System.out.println("secondPlayer: "+secondPlayerDice1 + "  " + secondPlayerDice2 + "  " + secondPlayerPosition);
        }

    }

    public GameEvent getInformation() {
        GameEvent event = new GameEvent(this, firstPlayer.getName(), firstPlayer.getLastPos(),
                firstPlayer.getLastDice1Face(), firstPlayer.getLastDice2Face(), firstPlayer.isTurn(),
                firstPlayer.getLastMoney(), firstPlayer.getLastDebt(), firstPlayer.getLastProperty(), firstPlayer.getLastDorm(), firstPlayer.isStuck(),
                firstPlayer.getDoubleSix(), firstPlayer.isDoubleSixBan(), firstPlayer.getDormIndexes(), firstPlayer.getPositions(),
                firstPlayer.getDice1Faces(), firstPlayer.getDice2Faces(), firstPlayer.getMoney(),
                firstPlayer.getDebts(), firstPlayer.getPropertyIndexes(), firstPlayer.getQuarters(), secondPlayer.getName(), secondPlayer.getLastPos(),
                secondPlayer.getLastDice1Face(), secondPlayer.getLastDice2Face(), secondPlayer.isTurn(),
                secondPlayer.getLastMoney(), secondPlayer.getLastDebt(), secondPlayer.getLastProperty(), secondPlayer.getLastDorm(),
                secondPlayer.isStuck(), secondPlayer.getDoubleSix(), secondPlayer.isDoubleSixBan(), secondPlayer.getDormIndexes(),
                secondPlayer.getPositions(), secondPlayer.getDice1Faces(), secondPlayer.getDice2Faces(),
                secondPlayer.getMoney(), secondPlayer.getDebts(), secondPlayer.getPropertyIndexes(), secondPlayer.getQuarters());

        return event;

    }

    public ObservableList<Data> getFirstPlayerDiceData() { // using for statistics
        ObservableList<Data> firstPlayerDiceData = FXCollections.observableArrayList();

        PieChart.Data face1 = new PieChart.Data("1", 0);
        PieChart.Data face2 = new PieChart.Data("2", 0);
        PieChart.Data face3 = new PieChart.Data("3", 0);
        PieChart.Data face4 = new PieChart.Data("4", 0);
        PieChart.Data face5 = new PieChart.Data("5", 0);
        PieChart.Data face6 = new PieChart.Data("6", 0);

        for (int i=0; i<firstPlayer.getDice1Faces().size(); ++i) {
            switch (firstPlayer.getDice1Faces().get(i)) {
                case 1:
                    face1.setPieValue(face1.getPieValue() + 1);
                    break;

                case 2:
                    face2.setPieValue(face2.getPieValue() + 1);
                    break;

                case 3:
                    face3.setPieValue(face3.getPieValue() + 1);
                    break;

                case 4:
                    face4.setPieValue(face4.getPieValue() + 1);
                    break;

                case 5:
                    face5.setPieValue(face5.getPieValue() + 1);
                    break;

                case 6:
                    face6.setPieValue(face6.getPieValue() + 1);
                    break;
            }
        }

        for (int i=0; i<firstPlayer.getDice2Faces().size(); ++i) {
            switch (firstPlayer.getDice2Faces().get(i)) {
                case 1:
                    face1.setPieValue(face1.getPieValue() + 1);
                    break;

                case 2:
                    face2.setPieValue(face2.getPieValue() + 1);
                    break;

                case 3:
                    face3.setPieValue(face3.getPieValue() + 1);
                    break;

                case 4:
                    face4.setPieValue(face4.getPieValue() + 1);
                    break;

                case 5:
                    face5.setPieValue(face5.getPieValue() + 1);
                    break;

                case 6:
                    face6.setPieValue(face6.getPieValue() + 1);
                    break;
            }
        }

        firstPlayerDiceData.addAll(face1, face2, face3, face4, face5, face6);

        return firstPlayerDiceData;

    }


    public ObservableList<Data> getSecondPlayerDiceData() { // using for statistics
        ObservableList<Data> secondPlayerDiceData = FXCollections.observableArrayList();

        PieChart.Data face1 = new PieChart.Data("1", 0);
        PieChart.Data face2 = new PieChart.Data("2", 0);
        PieChart.Data face3 = new PieChart.Data("3", 0);
        PieChart.Data face4 = new PieChart.Data("4", 0);
        PieChart.Data face5 = new PieChart.Data("5", 0);
        PieChart.Data face6 = new PieChart.Data("6", 0);

        for (int i=0; i<secondPlayer.getDice1Faces().size(); ++i) {
            switch (secondPlayer.getDice1Faces().get(i)) {
                case 1:
                    face1.setPieValue(face1.getPieValue() + 1);
                    break;

                case 2:
                    face2.setPieValue(face2.getPieValue() + 1);
                    break;

                case 3:
                    face3.setPieValue(face3.getPieValue() + 1);
                    break;

                case 4:
                    face4.setPieValue(face4.getPieValue() + 1);
                    break;

                case 5:
                    face5.setPieValue(face5.getPieValue() + 1);
                    break;

                case 6:
                    face6.setPieValue(face6.getPieValue() + 1);
                    break;
            }
        }

        for (int i=0; i<secondPlayer.getDice2Faces().size(); ++i) {
            switch (secondPlayer.getDice2Faces().get(i)) {
                case 1:
                    face1.setPieValue(face1.getPieValue() + 1);
                    break;

                case 2:
                    face2.setPieValue(face2.getPieValue() + 1);
                    break;

                case 3:
                    face3.setPieValue(face3.getPieValue() + 1);
                    break;

                case 4:
                    face4.setPieValue(face4.getPieValue() + 1);
                    break;

                case 5:
                    face5.setPieValue(face5.getPieValue() + 1);
                    break;

                case 6:
                    face6.setPieValue(face6.getPieValue() + 1);
                    break;
            }
        }

        secondPlayerDiceData.addAll(face1, face2, face3, face4, face5, face6);

        return secondPlayerDiceData;

    }

    public void setInformation(GameEvent event) { // using whenever the game is loaded from a file
        //////////////// here we must set players with the new information loaded from file ///////////////////
        FirstPlayer firstTemp = new FirstPlayer(event.getFirstPlayerName());

        firstTemp.setLastPos(event.getFirstPlayerLastPos());
        firstTemp.setLastDice1Face(event.getFirstPlayerLastDice1Face());
        firstTemp.setLastDice2Face(event.getFirstPlayerLastDice2Face());
        firstTemp.setLastMoney(event.getFirstPlayerLastMoney());
        firstTemp.setLastDebt(event.getFirstPlayerLstDebt());
        firstTemp.setTurn(event.isFirstPlayerTurn());
        firstTemp.setLastProperty(event.getFirstPlayerLastProperty());
        firstTemp.setLastDorm(event.getFirstPlayerLastDorm());
        firstTemp.setStuck(event.isFirstPlayerStuck());
        firstTemp.setDoubleSix(event.getFirstPlayerDoubleSix());
        firstTemp.setDoubleSixBan(event.isFirstPlayerDoubleSixBan());
        firstTemp.setDormIndexes(event.getFirstPlayerDormIndexes());
        firstTemp.setPositions(event.getFirstPlayerPositions());
        firstTemp.setDice1Faces(event.getFirstPlayerDice1Faces());
        firstTemp.setDice2Faces(event.getFirstPlayerDice2Faces());
        firstTemp.setMoney(event.getFirstPlayerMoney());
        firstTemp.setDebts(event.getFirstPlayerDebts());
        firstTemp.setPropertyIndexes(event.getFirstPlayerPropertyIndexes());
        firstTemp.setQuartersLoad(event.getFirstPlayerQuarters());

        SecondPlayer secondTemp = new SecondPlayer(event.getSecondPlayerName());

        secondTemp.setLastPos(event.getSecondPlayerLastPos());
        secondTemp.setLastDice1Face(event.getSecondPlayerLastDice1Face());
        secondTemp.setLastDice2Face(event.getSecondPlayerLastDice2Face());
        secondTemp.setLastMoney(event.getSecondPlayerLastMoney());
        secondTemp.setLastDebt(event.getSecondPlayerLastDebt());
        secondTemp.setTurn(event.isSecondPlayerTurn());
        secondTemp.setLastProperty(event.getSecondPlayerLastProperty());
        secondTemp.setLastDorm(event.getFirstPlayerLastDorm());
        secondTemp.setStuck(event.isSecondPlayerStuck());
        secondTemp.setDoubleSix(event.getSecondPlayerDoubleSix());
        secondTemp.setDoubleSixBan(event.isSecondPlayerDoubleSixBan());
        secondTemp.setDormIndexes(event.getSecondPlayerDormIndexes());
        secondTemp.setPositions(event.getSecondPlayerPositions());
        secondTemp.setDice1Faces(event.getSecondPlayerDice1Faces());
        secondTemp.setDice2Faces(event.getSecondPlayerDice2Faces());
        secondTemp.setMoney(event.getSecondPlayerMoney());
        secondTemp.setDebts(event.getSecondPlayerDebts());
        secondTemp.setPropertyIndexes(event.getSecondPlayerPropertyIndexes());
        secondTemp.setQuartersLoad(event.getSecondPlayerQuarters());

        firstPlayer = firstTemp;
        secondPlayer = secondTemp;

        for (Integer propertyIndex:firstPlayer.getPropertyIndexes())
            DrawMap.drawBoughtProperty(1, propertyIndex);
        for (Integer propertyIndex:secondPlayer.getPropertyIndexes())
            DrawMap.drawBoughtProperty(2, propertyIndex);
        for (Integer quarterPos:firstPlayer.getQuarters().keySet())
            DrawMap.drawBoughtQuarter(quarterPos, firstPlayer.getQuarters().get(quarterPos));
        for (Integer quarterPos:secondPlayer.getQuarters().keySet())
            DrawMap.drawBoughtQuarter(quarterPos, secondPlayer.getQuarters().get(quarterPos));
        for (Integer dormPos:firstPlayer.getDormIndexes())
            DrawMap.drawBoughtDorm(dormPos);
        for (Integer dormPos:secondPlayer.getDormIndexes())
            DrawMap.drawBoughtDorm(dormPos);

        System.out.println(event.getFirstPlayerName() + "   " + event.getSecondPlayerName());
    }

    private void setPropertiesPrice() {
        propertiesPrices.add(0);
        propertiesPrices.add(1000);
        propertiesPrices.add(17000);
        propertiesPrices.add(1200);
        propertiesPrices.add(1000);
        propertiesPrices.add(2000);
        propertiesPrices.add(500);
        propertiesPrices.add(3000);
        propertiesPrices.add(1000);
        propertiesPrices.add(0);
        propertiesPrices.add(4000);
        propertiesPrices.add(1200);
        propertiesPrices.add(1600);
        propertiesPrices.add(10000);
        propertiesPrices.add(8000);
        propertiesPrices.add(12000);
        propertiesPrices.add(8000);
        propertiesPrices.add(5000);
        propertiesPrices.add(0);
        propertiesPrices.add(7000);
        propertiesPrices.add(13000);
        propertiesPrices.add(15000);
        propertiesPrices.add(16000);
        propertiesPrices.add(20000);
        propertiesPrices.add(6000);
        propertiesPrices.add(4500);
        propertiesPrices.add(100);
        propertiesPrices.add(0);
        propertiesPrices.add(100);
        propertiesPrices.add(500);
        propertiesPrices.add(3000);
        propertiesPrices.add(5000);
        propertiesPrices.add(2500);
        propertiesPrices.add(24000);
        propertiesPrices.add(3000);
        propertiesPrices.add(8000);
    }

    private void checkEnd() {
        if ((firstPlayer.getPropertyIndexes().size() + secondPlayer.getPropertyIndexes().size()) == propertiesPrices.size() - 4)
            endSuspension = true;

        else endSuspension = false;
    }

    public Integer getPlayerPropertiesAmount(int playerIndex) {
        if (playerIndex == 1)
            return firstPlayer.getPropertyIndexes().size();

        else if (playerIndex == 2)
            return secondPlayer.getPropertyIndexes().size();

        return 0;
    }

    public int getPlayerQuartersPrice(int playerIndex) {
        int totalPrice = 0;
        if (playerIndex == 1)
            for (Integer quarterIndex : firstPlayer.getQuarters().keySet())
                totalPrice += firstPlayer.getQuarters().get(quarterIndex) * (0.2 * propertiesPrices.get(quarterIndex - 1));

        else if (playerIndex == 2)
            for (Integer quarterIndex : secondPlayer.getQuarters().keySet())
                totalPrice += secondPlayer.getQuarters().get(quarterIndex) * (0.2 * propertiesPrices.get(quarterIndex - 1));

        return totalPrice;
    }

    public int getPlayerDormPrice(int playerIndex) {
        int totalPrice = 0;
        if (playerIndex == 1)
            for (Integer dormIndex : firstPlayer.getDormIndexes())
                totalPrice += (0.5 * propertiesPrices.get(dormIndex - 1));

        else if (playerIndex == 2)
            for (Integer dormIndex : secondPlayer.getDormIndexes())
                totalPrice += (0.5 * propertiesPrices.get(dormIndex - 1));

        return totalPrice;
    }

    public int getPlayerPropertiesPrice(int playerIndex) {
        int totalPrice = 0;
        if (playerIndex == 1)
            for (Integer propertyIndex:firstPlayer.getPropertyIndexes())
                totalPrice += propertiesPrices.get(propertyIndex - 1);

        else if (playerIndex == 2)
            for (Integer propertyIndex:secondPlayer.getPropertyIndexes())
                totalPrice += propertiesPrices.get(propertyIndex - 1);

        return totalPrice;
    }

}
