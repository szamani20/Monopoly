package gui;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.File;
import java.util.Optional;

public class MainBoard { // the main board is controlled from here and the event is sent from here and the drawing classes are made here
    private DrawMap drawMap;
    private DrawComponents drawComponents;
    private DrawAnimations drawAnimations;
    private GameHandle gameHandle;
    private EventHandle eventHandle;
    private Controller controller;
    private DrawEasternComponents drawEasternComponents;
    private static Group group;

    public MainBoard(Stage stage) {
        group = new Group();

        Scene scene = new Scene(group);

        drawMap = new DrawMap(group);
        drawComponents = new DrawComponents(group);
        drawAnimations = new DrawAnimations();
        gameHandle = new GameHandle();
        eventHandle = new EventHandle();
        controller = new Controller();
        drawEasternComponents = new DrawEasternComponents(group);

        eventHandle.setDiceRollListener(() -> {

            if (gameHandle.end)
                return;

            gameHandle.move();
            drawAnimations.draw(group);
        });

        eventHandle.setSaveListener(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MONOPOLY", "*.mon"));
            fileChooser.setTitle("Where To Save?");
            File file = fileChooser.showSaveDialog(stage);

            controller.saveToFile(event, file);

            ///////////// gain information from event
            ///////////// send information to controller
            ///////////// send chosen file to controller using getSelectedFile method
            ///////////// send file from controller to database
        });

        eventHandle.setLoadListener(() -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MONOPOLY", "*.mon"));
            fileChooser.setTitle("Where To Load?");
            File file = fileChooser.showOpenDialog(stage);

            return controller.loadFromFile(file);
            ////////////// sending file to database through controller

        });

        eventHandle.setAboutUsListener(() -> {
            Stage aboutUsStage = new Stage();
            Group aboutUsGroup = new Group();
            Scene aboutUsScene = new Scene(aboutUsGroup);

            ImageView aboutUsView = new ImageView();
            Image aboutUsImage = new Image(MainBoard.this.getClass().getResourceAsStream("/me.png"));
            aboutUsView.setImage(aboutUsImage);
            aboutUsGroup.getChildren().add(aboutUsView);

            aboutUsStage.setScene(aboutUsScene);
            aboutUsStage.setTitle("ABOUT US");
            aboutUsStage.setWidth(900);
            aboutUsStage.setHeight(500);
            aboutUsStage.setResizable(false);
            aboutUsStage.show();
        });

        eventHandle.setStatisticsListener(() -> {

            Pagination pagination = new Pagination(2, 0);
            pagination.setPageFactory(this::getPage);

            Stage statisticsStage = new Stage();
            Group statisticsGroup = new Group();
            Scene statisticsScene = new Scene(statisticsGroup);

            statisticsGroup.getChildren().addAll(pagination);

            statisticsStage.setResizable(false);
            statisticsStage.setTitle("Statistics");
            statisticsStage.setScene(statisticsScene);
            statisticsStage.setWidth(900);
            statisticsStage.setHeight(500);
            statisticsStage.show();

        });

        eventHandle.setCloseListener(() -> {
            if (closeGame())
                stage.close();
        });

        stage.setOnCloseRequest(e -> {
            e.consume();

            if (closeGame())
                stage.close();
        });

        eventHandle.setLyricsListener((id, type) -> drawEasternComponents.drawLyrics(group, id, type));

        eventHandle.setSaveConnection(gameHandle::getInformation);

        eventHandle.setLoadConnection(gameHandle::setInformation);

        scene.setOnMouseClicked(eventHandle::processEvent);

        stage.setScene(scene);
        stage.setWidth(1500);
        stage.setHeight(1000);

        stage.setResizable(false);

        stage.show();
    }

    private boolean closeGame() {
        Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION);

        closeConfirmation.setContentText("Exit Without Saving?");
        closeConfirmation.setTitle("Exit Confirmation");
        closeConfirmation.setHeaderText("");

        ImageView exitView = new ImageView();
        Image exitImage = new Image(this.getClass().getResourceAsStream("/continue.png"));
        exitView.setImage(exitImage);

        closeConfirmation.setGraphic(exitView);

        Optional<ButtonType> isClose = closeConfirmation.showAndWait();

        if (isClose.get() == ButtonType.OK)
            return true;
        else return false;
    }

    private HBox getPage(Integer pageIndex) { // return a layout, it's called for each page in pagination
        if (pageIndex == 0) {
            ObservableList<Data> firstPlayerDiceData = gameHandle.getFirstPlayerDiceData();
            PieChart firstPlayerDiceChart = new PieChart();
            firstPlayerDiceChart.setData(firstPlayerDiceData);
            firstPlayerDiceChart.setTitle("CE");
            firstPlayerDiceChart.relocate(100, 100);

            ObservableList<Data> secondPlayerDiceData = gameHandle.getSecondPlayerDiceData();
            PieChart secondPlayerDiceChart = new PieChart();
            secondPlayerDiceChart.setData(secondPlayerDiceData);
            secondPlayerDiceChart.setTitle("EE");
            secondPlayerDiceChart.relocate(300, 100);

            firstPlayerDiceChart.relocate(10, 10);
            secondPlayerDiceChart.relocate(400, 10);

            HBox hBox = new HBox();
            FadeTransition f = new FadeTransition();
            f.setNode(hBox);
            f.setDuration(Duration.seconds(2));
            f.setFromValue(0.0);
            f.setToValue(1.0);
            f.play();

            hBox.getChildren().addAll(firstPlayerDiceChart, secondPlayerDiceChart);
            return hBox;
        }

        else if (pageIndex == 1) {
            CategoryAxis playerAxis = new CategoryAxis();
            NumberAxis priceAxis = new NumberAxis();
            NumberAxis amountAxis = new NumberAxis();
            BarChart<String, Number> priceChart = new BarChart<>(playerAxis, priceAxis);
            BarChart<String, Number> amountChart = new BarChart<>(playerAxis, amountAxis);

            priceChart.setTitle("Properties Total Price");
            amountChart.setTitle("Properties Total Amount");
            priceAxis.setLabel("Total Price");
            amountAxis.setLabel("Total Amount");

            XYChart.Series firstPlayerPriceSeries = new XYChart.Series();
            firstPlayerPriceSeries.setName("CE");
            firstPlayerPriceSeries.getData().addAll(new XYChart.Data("CE", gameHandle.getPlayerPropertiesPrice(1)));

            XYChart.Series secondPlayerPriceSeries = new XYChart.Series();
            secondPlayerPriceSeries.setName("EE");
            secondPlayerPriceSeries.getData().addAll(new XYChart.Data("EE", gameHandle.getPlayerPropertiesPrice(2)));

            XYChart.Series firstPlayerAmountSeries = new XYChart.Series();
            firstPlayerAmountSeries.setName("CE");
            firstPlayerAmountSeries.getData().addAll(new XYChart.Data("CE", gameHandle.getPlayerPropertiesAmount(1)));

            XYChart.Series secondPlayerAmountSeries = new XYChart.Series();
            secondPlayerAmountSeries.setName("EE");
            secondPlayerAmountSeries.getData().addAll(new XYChart.Data("EE", gameHandle.getPlayerPropertiesAmount(2)));

            priceChart.getData().addAll(firstPlayerPriceSeries, secondPlayerPriceSeries);
            amountChart.getData().addAll(firstPlayerAmountSeries, secondPlayerAmountSeries);

            HBox hBox = new HBox();
            FadeTransition f = new FadeTransition();
            f.setNode(hBox);
            f.setDuration(Duration.seconds(2));
            f.setFromValue(0.0);
            f.setToValue(1.0);
            f.play();

            hBox.getChildren().addAll(priceChart, amountChart);
            return hBox;
        }

        return null;
    }

    public static Group getGroup() {
        return group;
    }
}
