package gui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class DrawMap {
    public static final int width = 1500;
    public static final int height = 1000;

    public static final int leftMargin = 200;
    public static final int upMargin = 100;
    public static final int rightMargin = 200;
    public static final int downMargin = 200;

    public static final int cellWidth = 110;
    public static final int cellHeight = 70;

    private static List<ImageView> propertyViews;
    private static List<Integer> propertyPositions;

    private static List<ImageView> quarterViews;
    private static List<Integer> quarterPositions;

    private static List<ImageView> dormViews;
    private static List<Integer> dormPositions;

    public DrawMap(Group group) {

        propertyViews = new ArrayList<>();
        propertyPositions = new ArrayList<>();
        quarterViews = new ArrayList<>();
        quarterPositions = new ArrayList<>();
        dormViews = new ArrayList<>();
        dormPositions = new ArrayList<>();

        drawIcon(group);

        drawLeftSide(group);
        drawUpSide(group);
        drawRightSide(group);
        drawDownSide(group);
    }

    private void drawIcon(Group group) {
        Image seImage = new Image(this.getClass().getResourceAsStream("/seIcon.png"));
        ImageView seView = new ImageView(seImage);
        seView.setFitWidth(150);
        seView.setFitHeight(180);

        Image eeImage = new Image(this.getClass().getResourceAsStream("/eeIcon.png"));
        ImageView eeView = new ImageView(eeImage);
        eeView.setFitWidth(150);
        eeView.setFitHeight(180);
        eeView.setX(width - 150);


        group.getChildren().add(seView);
        group.getChildren().add(eeView);
    }

    private void drawDownSide(Group group) {
        int counter = 1;

        Rectangle rectangle1 = new Rectangle(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight, cellWidth, cellHeight);
        Text text1 = new Text(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight + 20, "AlphaZero");
        rectangle1.setArcWidth(15);
        rectangle1.setArcHeight(15);
        rectangle1.setFill(Color.INDIANRED);
        group.getChildren().add(rectangle1);
        group.getChildren().add(text1);
        ++counter;

        Rectangle rectangle2 = new Rectangle(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight, cellWidth, cellHeight);
        Text text2 = new Text(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight + 20, "Alpha-2 100$");
        rectangle2.setArcWidth(15);
        rectangle2.setArcHeight(15);
        rectangle2.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle2);
        group.getChildren().add(text2);
        ++counter;

        Rectangle rectangle3 = new Rectangle(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight, cellWidth, cellHeight);
        Text text3 = new Text(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight + 20, "Park 500$");
        rectangle3.setArcWidth(15);
        rectangle3.setArcHeight(15);
        rectangle3.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle3);
        group.getChildren().add(text3);
        ++counter;

        Rectangle rectangle4 = new Rectangle(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight, cellWidth, cellHeight);
        Text text4 = new Text(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight + 20, "Bank 3000$");
        rectangle4.setArcWidth(15);
        rectangle4.setArcHeight(15);
        rectangle4.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle4);
        group.getChildren().add(text4);
        ++counter;

        Rectangle rectangle5 = new Rectangle(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight, cellWidth, cellHeight);
        Text text5 = new Text(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight + 20, "Jaber 5000$");
        rectangle5.setArcWidth(15);
        rectangle5.setArcHeight(15);
        rectangle5.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle5);
        group.getChildren().add(text5);
        ++counter;

        Rectangle rectangle6 = new Rectangle(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight, cellWidth, cellHeight);
        Text text6 = new Text(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight + 20, "B14 2500$");
        rectangle6.setArcWidth(15);
        rectangle6.setArcHeight(15);
        rectangle6.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle6);
        group.getChildren().add(text6);
        ++counter;

        Rectangle rectangle7 = new Rectangle(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight, cellWidth, cellHeight);
        Text text7 = new Text(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight + 20, "SpacePort 24000$");
        text7.setFont(Font.font(12));
        rectangle7.setArcWidth(15);
        rectangle7.setArcHeight(15);
        rectangle7.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle7);
        group.getChildren().add(text7);
        ++counter;

        Rectangle rectangle8 = new Rectangle(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight, cellWidth, cellHeight);
        Text text8 = new Text(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight + 20, "Boofe 3000$");
        rectangle8.setArcWidth(15);
        rectangle8.setArcHeight(15);
        rectangle8.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle8);
        group.getChildren().add(text8);
        ++counter;

        Rectangle rectangle9 = new Rectangle(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight, cellWidth, cellHeight);
        Text text9 = new Text(width - rightMargin - cellWidth * counter,
                height - downMargin - cellHeight + 20, "Loop 8000$");
        rectangle9.setArcWidth(15);
        rectangle9.setArcHeight(15);
        rectangle9.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle9);
        group.getChildren().add(text9);
        ++counter;
    }

    private void drawRightSide(Group group) {
        int counter = 0;

        Rectangle rectangle1 = new Rectangle(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter, cellWidth, cellHeight);
        Text text1 = new Text(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter + 20, "Jail");
        rectangle1.setArcWidth(15);
        rectangle1.setArcHeight(15);
        rectangle1.setFill(Color.YELLOW);
        group.getChildren().add(rectangle1);
        group.getChildren().add(text1);
        ++counter;

        Rectangle rectangle2 = new Rectangle(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter, cellWidth, cellHeight);
        Text text2 = new Text(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter + 20, "B11 7000$");
        rectangle2.setArcWidth(15);
        rectangle2.setArcHeight(15);
        rectangle2.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle2);
        group.getChildren().add(text2);
        ++counter;

        Rectangle rectangle3 = new Rectangle(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter, cellWidth, cellHeight);
        Text text3 = new Text(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter + 20, "Sinister 13000$");
        rectangle3.setArcWidth(15);
        rectangle3.setArcHeight(15);
        rectangle3.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle3);
        group.getChildren().add(text3);
        ++counter;

        Rectangle rectangle4 = new Rectangle(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter, cellWidth, cellHeight);
        Text text4 = new Text(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter + 20, "Abtalebi 15000$");
        rectangle4.setArcWidth(15);
        rectangle4.setArcHeight(15);
        rectangle4.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle4);
        group.getChildren().add(text4);
        ++counter;

        Rectangle rectangle5 = new Rectangle(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter, cellWidth, cellHeight);
        Text text5 = new Text(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter + 20, "Ebnes 16000$");
        rectangle5.setArcWidth(15);
        rectangle5.setArcHeight(15);
        rectangle5.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle5);
        group.getChildren().add(text5);
        ++counter;

        Rectangle rectangle6 = new Rectangle(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter, cellWidth, cellHeight);
        Text text6 = new Text(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter + 20, "TechCenter 20000$");
        text6.setFont(Font.font(12));
        rectangle6.setArcWidth(15);
        rectangle6.setArcHeight(15);
        rectangle6.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle6);
        group.getChildren().add(text6);
        ++counter;

        Rectangle rectangle7 = new Rectangle(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter, cellWidth, cellHeight);
        Text text7 = new Text(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter + 20, "Aida 6000$");
        rectangle7.setArcWidth(15);
        rectangle7.setArcHeight(15);
        rectangle7.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle7);
        group.getChildren().add(text7);
        ++counter;

        Rectangle rectangle8 = new Rectangle(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter, cellWidth, cellHeight);
        Text text8 = new Text(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter + 20, "Kanon 4500$");
        rectangle8.setArcWidth(15);
        rectangle8.setArcHeight(15);
        rectangle8.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle8);
        group.getChildren().add(text8);
        ++counter;

        Rectangle rectangle9 = new Rectangle(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter, cellWidth, cellHeight);
        Text text9 = new Text(width - rightMargin - cellWidth,
                upMargin + cellHeight * counter + 20, "Alpha-1 100$");
        rectangle9.setArcWidth(15);
        rectangle9.setArcHeight(15);
        rectangle9.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle9);
        group.getChildren().add(text9);
        ++counter;
    }

    private void drawUpSide(Group group) {
        int counter = 0;

        Rectangle rectangle1 = new Rectangle(leftMargin + cellWidth * counter, upMargin,
                cellWidth, cellHeight);
        Text text1 = new Text(leftMargin + cellWidth * counter, upMargin + 20, "Infirmary");
        rectangle1.setArcWidth(15);
        rectangle1.setArcHeight(15);
        rectangle1.setFill(Color.RED);
        group.getChildren().add(rectangle1);
        group.getChildren().add(text1);
        ++counter;

        Rectangle rectangle2 = new Rectangle(leftMargin + cellWidth * counter, upMargin,
                cellWidth, cellHeight);
        Text text2 = new Text(leftMargin + cellWidth * counter, upMargin + 20, "Hoze 4000$");
        rectangle2.setArcWidth(15);
        rectangle2.setArcHeight(15);
        rectangle2.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle2);
        group.getChildren().add(text2);
        ++counter;

        Rectangle rectangle3 = new Rectangle(leftMargin + cellWidth * counter, upMargin,
                cellWidth, cellHeight);
        Text text3 = new Text(leftMargin + cellWidth * counter, upMargin + 20, "B8 1200$");
        rectangle3.setArcWidth(15);
        rectangle3.setArcHeight(15);
        rectangle3.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle3);
        group.getChildren().add(text3);
        ++counter;

        Rectangle rectangle4 = new Rectangle(leftMargin + cellWidth * counter, upMargin,
                cellWidth, cellHeight);
        Text text4 = new Text(leftMargin + cellWidth * counter, upMargin + 20, "B9 1600$");
        rectangle4.setArcWidth(15);
        rectangle4.setArcHeight(15);
        rectangle4.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle4);
        group.getChildren().add(text4);
        ++counter;

        Rectangle rectangle5 = new Rectangle(leftMargin + cellWidth * counter, upMargin,
                cellWidth, cellHeight);
        Text text5 = new Text(leftMargin + cellWidth * counter, upMargin + 20, "Aquarium 10000$");
        text5.setFont(Font.font(12));
        rectangle5.setArcWidth(15);
        rectangle5.setArcHeight(15);
        rectangle5.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle5);
        group.getChildren().add(text5);
        ++counter;

        Rectangle rectangle6 = new Rectangle(leftMargin + cellWidth * counter, upMargin,
                cellWidth, cellHeight);
        Text text6 = new Text(leftMargin + cellWidth * counter, upMargin + 20, "B10 8000$");
        rectangle6.setArcWidth(15);
        rectangle6.setArcHeight(15);
        rectangle6.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle6);
        group.getChildren().add(text6);
        ++counter;

        Rectangle rectangle7 = new Rectangle(leftMargin + cellWidth * counter, upMargin,
                cellWidth, cellHeight);
        Text text7 = new Text(leftMargin + cellWidth * counter, upMargin + 20, "Halls 12000$");
        rectangle7.setArcWidth(15);
        rectangle7.setArcHeight(15);
        rectangle7.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle7);
        group.getChildren().add(text7);
        ++counter;

        Rectangle rectangle8 = new Rectangle(leftMargin + cellWidth * counter, upMargin,
                cellWidth, cellHeight);
        Text text8 = new Text(leftMargin + cellWidth * counter, upMargin + 20, "Library 8000$");
        rectangle8.setArcWidth(15);
        rectangle8.setArcHeight(15);
        rectangle8.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle8);
        group.getChildren().add(text8);
        ++counter;

        Rectangle rectangle9 = new Rectangle(leftMargin + cellWidth * counter, upMargin,
                cellWidth, cellHeight);
        Text text9 = new Text(leftMargin + cellWidth * counter, upMargin + 20, "Gym 5000$");
        rectangle9.setArcWidth(15);
        rectangle9.setArcHeight(15);
        rectangle9.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle9);
        group.getChildren().add(text9);
        ++counter;

    }

    private void drawLeftSide(Group group) {
        int counter = 1;

        Rectangle rectangle1 = new Rectangle(leftMargin, height - downMargin - cellHeight * counter,
                cellWidth, cellHeight);
        Text text1 = new Text(leftMargin, height - downMargin - cellHeight * counter + 20, "Start");
        rectangle1.setArcHeight(15);
        rectangle1.setArcWidth(15);
        rectangle1.setFill(Color.GREEN);
        group.getChildren().add(rectangle1);
        group.getChildren().add(text1);
        ++counter;

        Rectangle rectangle2 = new Rectangle(leftMargin, height - downMargin - cellHeight * counter,
                cellWidth, cellHeight);
        Text text2 = new Text(leftMargin, height - downMargin - cellHeight * counter + 20, "B1 1000$");
        rectangle2.setArcHeight(15);
        rectangle2.setArcWidth(15);
        rectangle2.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle2);
        group.getChildren().add(text2);
        ++counter;

        Rectangle rectangle3 = new Rectangle(leftMargin, height - downMargin - cellHeight * counter,
                cellWidth, cellHeight);
        Text text3 = new Text(leftMargin, height - downMargin - cellHeight * counter + 20, "Abdos 17000$");
        rectangle3.setArcHeight(15);
        rectangle3.setArcWidth(15);
        rectangle3.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle3);
        group.getChildren().add(text3);
        ++counter;

        Rectangle rectangle4 = new Rectangle(leftMargin, height - downMargin - cellHeight * counter,
                cellWidth, cellHeight);
        Text text4 = new Text(leftMargin, height - downMargin - cellHeight * counter + 20, "B3 1200$");
        rectangle4.setArcHeight(15);
        rectangle4.setArcWidth(15);
        rectangle4.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle4);
        group.getChildren().add(text4);
        ++counter;

        Rectangle rectangle5 = new Rectangle(leftMargin, height - downMargin - cellHeight * counter,
                cellWidth, cellHeight);
        Text text5 = new Text(leftMargin, height - downMargin - cellHeight * counter + 20, "DogDooni 1000$");
        rectangle5.setArcHeight(15);
        rectangle5.setArcWidth(15);
        rectangle5.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle5);
        group.getChildren().add(text5);
        ++counter;

        Rectangle rectangle6 = new Rectangle(leftMargin, height - downMargin - cellHeight * counter,
                cellWidth, cellHeight);
        Text text6 = new Text(leftMargin, height - downMargin - cellHeight * counter + 20, "Jakooz 2000$");
        rectangle6.setArcHeight(15);
        rectangle6.setArcWidth(15);
        rectangle6.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle6);
        group.getChildren().add(text6);
        ++counter;

        Rectangle rectangle7 = new Rectangle(leftMargin, height - downMargin - cellHeight * counter,
                cellWidth, cellHeight);
        Text text7 = new Text(leftMargin, height - downMargin - cellHeight * counter + 20, "B5 500$");
        rectangle7.setArcHeight(15);
        rectangle7.setArcWidth(15);
        rectangle7.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle7);
        group.getChildren().add(text7);
        ++counter;

        Rectangle rectangle8 = new Rectangle(leftMargin, height - downMargin - cellHeight * counter,
                cellWidth, cellHeight);
        Text text8 = new Text(leftMargin, height - downMargin - cellHeight * counter + 20, "B6 3000$");
        rectangle8.setArcHeight(15);
        rectangle8.setArcWidth(15);
        rectangle8.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle8);
        group.getChildren().add(text8);
        ++counter;

        Rectangle rectangle9 = new Rectangle(leftMargin, height - downMargin - cellHeight * counter,
                cellWidth, cellHeight);
        Text text9 = new Text(leftMargin, height - downMargin - cellHeight * counter + 20, "B7 1000$");
        rectangle9.setArcHeight(15);
        rectangle9.setArcWidth(15);
        rectangle9.setFill(Color.LIGHTBLUE);
        group.getChildren().add(rectangle9);
        group.getChildren().add(text9);
        ++counter;

    }

    public static void drawBoughtProperty(int playerIndex, int propertyPos) {

        ImageView propertyView = new ImageView();
        Image propertyImage = null;

        if (playerIndex == 1)
            propertyImage = new Image(System.class.getResourceAsStream("/seProperty.png"));
        if (playerIndex == 2)
            propertyImage = new Image(System.class.getResourceAsStream("/eeProperty.png"));

        propertyView.setImage(propertyImage);
        propertyView.setLayoutX(10);
        propertyView.setLayoutY(10);

        if (propertyPos < 10) {
            propertyView.setX(leftMargin);
            propertyView.setY(height - downMargin - propertyPos * cellHeight + 10);
        }
        else if (propertyPos < 19) {
            propertyView.setX(leftMargin + (propertyPos - 10) * cellWidth);
            propertyView.setY(upMargin + 10);
        }
        else if (propertyPos < 28) {
            propertyView.setX(width - rightMargin - cellWidth);
            propertyView.setY(upMargin + (propertyPos - 19) * cellHeight + 10);
        }

        else if (propertyPos < 37) {
            propertyView.setX(width - rightMargin - (propertyPos - 27) * cellWidth);
            propertyView.setY(height - downMargin - cellHeight + 10);
        }

        propertyViews.add(propertyView);
        propertyPositions.add(propertyPos);

        MainBoard.getGroup().getChildren().add(propertyViews.get(propertyViews.size() - 1));
    }

    public static void drawBoughtQuarter(int quarterPos, int amount) {
        ImageView quarterView = new ImageView();
        Image quarterImage = null;

        switch (amount) {
            case 1:
                quarterImage = new Image(System.class.getResourceAsStream("/quarter1.png"));
                break;
            case 2:
                quarterImage = new Image(System.class.getResourceAsStream("/quarter2.png"));
                break;
            case 3:
                quarterImage = new Image(System.class.getResourceAsStream("/quarter3.png"));
                break;
            case 4:
                quarterImage = new Image(System.class.getResourceAsStream("/quarter4.png"));
                break;
            case 5:
                quarterImage = new Image(System.class.getResourceAsStream("/quarter5.png"));
                break;
        }

        quarterView.setImage(quarterImage);

        if (quarterPos < 10) {
            quarterView.setX(leftMargin);
            quarterView.setY(height - downMargin - quarterPos * cellHeight + 35);
        }

        else if (quarterPos < 19) {
            quarterView.setX(leftMargin + (quarterPos - 10) * cellWidth);
            quarterView.setY(upMargin + 35);
        }

        else if (quarterPos < 28) {
            quarterView.setX(width - rightMargin - cellWidth);
            quarterView.setY(upMargin + (quarterPos - 19) * cellHeight + 35);
        }

        else if (quarterPos < 37) {
            quarterView.setX(width - rightMargin - (quarterPos - 27) * cellWidth);
            quarterView.setY(height - downMargin - cellHeight + 35);
        }

        if (quarterPositions.indexOf(quarterPos) != -1) {
            MainBoard.getGroup().getChildren().remove(quarterViews.get(quarterPositions.indexOf(quarterPos)));
            quarterPositions.remove(new Integer(quarterPos));
        }

        quarterViews.add(quarterView);
        quarterPositions.add(quarterPos);

        MainBoard.getGroup().getChildren().add(quarterViews.get(quarterViews.size() - 1));
    }

    public static void drawBoughtDorm(int dormPos) {
        Image dormImage = new Image(System.class.getResourceAsStream("/dorm.png"));
        ImageView dormView = new ImageView(dormImage);

        if (dormPos < 10) {
            dormView.setX(leftMargin + 55);
            dormView.setY(height - downMargin - dormPos * cellHeight + 35);
        }

        else if (dormPos < 19) {
            dormView.setX(leftMargin + (dormPos - 10) * cellWidth + 55);
            dormView.setY(upMargin + 35);
        }

        else if (dormPos < 28) {
            dormView.setX(width - rightMargin - cellWidth + 55);
            dormView.setY(upMargin + (dormPos - 19) * cellHeight + 35);
        }

        else if (dormPos < 37) {
            dormView.setX(width - rightMargin - (dormPos - 27) * cellWidth + 55);
            dormView.setY(height - downMargin - cellHeight + 35);
        }

        dormViews.add(dormView);
        dormPositions.add(dormPos);

        MainBoard.getGroup().getChildren().add(dormViews.get(dormViews.size() - 1));
    }

}
