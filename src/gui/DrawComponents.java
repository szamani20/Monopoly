package gui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DrawComponents {
    public static final int width = 1500;
    public static final int height = 1000;

    public static final int leftMargin = 150;
    public static final int rightMargin = 150;

    public static final int upMargin = 5;
    public static final int downMargin = 30;

    public static final int aboutUsWidth = 300;
    public static final int aboutUsHeight = 100;

    public static final int saveWidth = 200;
    public static final int saveHeight = 100;

    public static final int statisticsWidth = 300;
    public static final int statisticsHeight = 100;

    public static final int loadWidth = 200;
    public static final int loadHeight = 100;

    public static final int closeWidth = 200;
    public static final int closeHeight = 100;

    public static final int firstPlayerInfWidth = 400;
    public static final int firstPlayerInfHeight = 180;

    public static final int rollWidth = 300;
    public static final int rollHeight = 180;

    public static final int secondPlayerInfWidth = 400;
    public static final int secondPlayerInfHeight = 180;

    public static final int easternComponentsWidth = 180;
    public static final int easternComponentsHeight = 60;


    public DrawComponents(Group group) {

        drawNorthernComponents(group);
        drawSouthernComponents(group);
        // because of complex lyrics animation in the eastern part of scene a separate class will handle it
        drawWesternComponents(group);

    }

    private void drawWesternComponents(Group group) {
        int counter = 0;
        int upMargin = 200;
        int margin = 50;

        ImageView playView = new ImageView();
        Image playImage = new Image(this.getClass().getResourceAsStream("/play.png"));

        playView.setImage(playImage);
        playView.setX(5);
        playView.setY(upMargin + counter * easternComponentsHeight + counter * margin);
        group.getChildren().add(playView);
        ++counter;

        ImageView pauseView = new ImageView();
        Image pauseImage = new Image(this.getClass().getResourceAsStream("/pause.png"));

        pauseView.setImage(pauseImage);
        pauseView.setX(5);
        pauseView.setY(upMargin + counter * easternComponentsHeight + counter * margin);
        group.getChildren().add(pauseView);
        ++counter;

        ImageView stopView = new ImageView();
        Image stopImage = new Image(this.getClass().getResourceAsStream("/stop.png"));

        stopView.setImage(stopImage);
        stopView.setX(5);
        stopView.setY(upMargin + counter * easternComponentsHeight + counter * margin);
        group.getChildren().add(stopView);
        ++counter;

        ImageView nextView = new ImageView();
        Image nextImage = new Image(this.getClass().getResourceAsStream("/next.png"));

        nextView.setImage(nextImage);
        nextView.setX(5);
        nextView.setY(upMargin + counter * easternComponentsHeight + counter * margin);
        group.getChildren().add(nextView);
        ++counter;

        ImageView previousView = new ImageView();
        Image previousImage = new Image(this.getClass().getResourceAsStream("/previous.png"));

        previousView.setImage(previousImage);
        previousView.setX(5);
        previousView.setY(upMargin + counter * easternComponentsHeight + counter * margin);
        group.getChildren().add(previousView);
        ++counter;


    }

    private void drawSouthernComponents(Group group) {
        ImageView rollView = new ImageView();
        Image rollImage = new Image(this.getClass().getResourceAsStream("/roll.png"));

        rollView.setImage(rollImage);
        rollView.setX(width / 2 - rollWidth / 2);
        rollView.setY(height - downMargin - rollHeight);

        group.getChildren().add(rollView);
    }

    private void drawNorthernComponents(Group group) {
        ImageView aboutUsView = new ImageView();
        Image aboutUsImage = new Image(this.getClass().getResourceAsStream("/aboutUs.png"));

        aboutUsView.setImage(aboutUsImage);
        aboutUsView.setX(leftMargin);
        aboutUsView.setY(upMargin);

        group.getChildren().add(aboutUsView);


        ImageView saveView = new ImageView();
        Image saveImage = new Image(this.getClass().getResourceAsStream("/save.png"));

        saveView.setImage(saveImage);
        saveView.setX(leftMargin + aboutUsWidth);
        saveView.setY(upMargin);

        group.getChildren().add(saveView);


        ImageView statisticsView = new ImageView();
        Image statisticsImage = new Image(this.getClass().getResourceAsStream("/statistics.png"));

        statisticsView.setImage(statisticsImage);
        statisticsView.setX(leftMargin + aboutUsWidth + saveWidth);
        statisticsView.setY(upMargin);

        group.getChildren().add(statisticsView);


        ImageView loadView = new ImageView();
        Image loadImage = new Image(this.getClass().getResourceAsStream("/load.png"));

        loadView.setImage(loadImage);
        loadView.setX(leftMargin + aboutUsWidth + saveWidth + statisticsWidth);
        loadView.setY(upMargin);

        group.getChildren().add(loadView);


        ImageView closeView = new ImageView();
        Image closeImage = new Image(this.getClass().getResourceAsStream("/close.png"));

        closeView.setImage(closeImage);
        closeView.setX(leftMargin + aboutUsWidth + saveWidth + statisticsWidth + loadWidth);
        closeView.setY(upMargin);

        group.getChildren().add(closeView);
    }

}
