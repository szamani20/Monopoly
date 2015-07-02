package controller;

import database.Database;
import database.GameInformation;
import gui.GameEvent;

import java.io.File;

public class Controller {
    private Database database;

    public Controller() {
        database = new Database();
    }

    public void saveToFile(GameEvent event, File file) { // give file from fileChooser which is sent through MainBoard and the game information as an EventObject
        GameInformation gameInformation = setGameInformation(event);
        database.saveToFile(gameInformation, file);
    }

    public GameInformation setGameInformation(GameEvent event) {
        return new GameInformation(event.getFirstPlayerName(), event.getFirstPlayerLastPos(),
                event.getFirstPlayerLastDice1Face(), event.getFirstPlayerLastDice2Face(),
                event.isFirstPlayerTurn(), event.getFirstPlayerLastMoney(), event.getFirstPlayerLstDebt(), event.getFirstPlayerLastProperty(),
                event.getFirstPlayerLastDorm(), event.isFirstPlayerStuck(), event.getFirstPlayerDoubleSix(), event.isFirstPlayerDoubleSixBan(),
                event.getFirstPlayerDormIndexes(), event.getFirstPlayerPositions(), event.getFirstPlayerDice1Faces(), event.getFirstPlayerDice2Faces(),
                event.getFirstPlayerMoney(), event.getFirstPlayerDebts(), event.getFirstPlayerPropertyIndexes(), event.getFirstPlayerQuarters(), event.getSecondPlayerName(),
                event.getSecondPlayerLastPos(), event.getSecondPlayerLastDice1Face(), event.getSecondPlayerLastDice2Face(),
                event.isSecondPlayerTurn(), event.getSecondPlayerLastMoney(), event.getSecondPlayerLastDebt(),
                event.getSecondPlayerLastProperty(), event.getSecondPlayerLastDorm(), event.isFirstPlayerStuck(), event.getFirstPlayerDoubleSix(),
                event.isSecondPlayerDoubleSixBan(), event.getSecondPlayerDormIndexes(),  event.getSecondPlayerPositions(), event.getSecondPlayerDice1Faces(),
                event.getSecondPlayerDice2Faces(), event.getSecondPlayerMoney(), event.getSecondPlayerDebts(), event.getSecondPlayerPropertyIndexes(), event.getSecondPlayerQuarters());

    }

    public GameEvent loadFromFile(File file) {
        GameInformation gameInformation = database.loadFromFile(file);

        return new GameEvent(this, gameInformation.getFirstPlayerName(), gameInformation.getFirstPlayerLastPos(),
                gameInformation.getFirstPlayerLastDice1Face(), gameInformation.getFirstPlayerLastDice2Face(),
                gameInformation.isFirstPlayerTurn(), gameInformation.getFirstPlayerLastMoney(),
                gameInformation.getFirstPlayerLstDebt(), gameInformation.getFirstPlayerLastProperty(), gameInformation.getFirstPlayerLastDorm(), gameInformation.isFirstPlayerStuck(),
                gameInformation.getFirstPlayerDoubleSix(), gameInformation.isFirstPlayerDoubleSixBan(), gameInformation.getFirstPlayerDormIndexes(),  gameInformation.getFirstPlayerPositions(),
                gameInformation.getFirstPlayerDice1Faces(), gameInformation.getFirstPlayerDice2Faces(),
                gameInformation.getFirstPlayerMoney(), gameInformation.getFirstPlayerDebts(), gameInformation.getFirstPlayerPropertyIndexes(), gameInformation.getFirstPlayerQuarters(),
                gameInformation.getSecondPlayerName() ,gameInformation.getSecondPlayerLastPos(),
                gameInformation.getSecondPlayerLastDice1Face(), gameInformation.getSecondPlayerLastDice2Face(),
                gameInformation.isSecondPlayerTurn(), gameInformation.getSecondPlayerLastMoney(),
                gameInformation.getSecondPlayerLastDebt(), gameInformation.getSecondPlayerLastProperty(), gameInformation.getFirstPlayerLastDorm(), gameInformation.isSecondPlayerStuck(),
                gameInformation.getSecondPlayerDoubleSix(), gameInformation.isSecondPlayerDoubleSixBan(), gameInformation.getFirstPlayerDormIndexes(), gameInformation.getSecondPlayerPositions(),
                gameInformation.getSecondPlayerDice1Faces(), gameInformation.getSecondPlayerDice2Faces(),
                gameInformation.getSecondPlayerMoney(), gameInformation.getSecondPlayerDebts(), gameInformation.getSecondPlayerPropertyIndexes(), gameInformation.getSecondPlayerQuarters());
    }


















































}
