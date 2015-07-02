package gui;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class DrawAnimations {
    private static ImageView dice1View = null; // for final view
    private static ImageView dice2View = null; // for final view
    private static ImageView firstPlayerView = null; // for final view
    private static ImageView secondPlayerView = null; // for final view
    private static ImageView quarterView;
    private static Image firstPlayerImage = null;
    private static Image secondPlayerImage = null;
    private static PathTransition diceAnimation = null;
    private static PathTransition playerAnimation = null;
    private static FadeTransition jailAnimation = null;
    private static FadeTransition alphaAnimation = null;
    private static PathTransition moneyAnimation = null;
    private static FadeTransition winnerAnimation = null;

    public DrawAnimations() {
        firstPlayerView = new ImageView();
        secondPlayerView = new ImageView();
        quarterView = new ImageView();

        firstPlayerImage = new Image(this.getClass().getResourceAsStream("/se.png"));
        secondPlayerImage = new Image(this.getClass().getResourceAsStream("/ee.png"));

        diceAnimation = new PathTransition();
        playerAnimation = new PathTransition();
        jailAnimation = new FadeTransition();
        alphaAnimation= new FadeTransition();
        moneyAnimation = new PathTransition();
        winnerAnimation = new FadeTransition();

    }

    public void draw(Group group) {
        drawDiceThrowingAnimation(group);
    }

    private void gameEnd(Group group) {
        if (GameHandle.firstPlayer.getLastPos() == 1) {
            System.out.println("Congratulations! first player has won the game");

            GameHandle.end = true;

            Image winnerImage = new Image(this.getClass().getResourceAsStream("/winner.png"));
            ImageView winnerView = new ImageView(winnerImage);

            winnerView.setX(DrawMap.width / 2 - 175);
            winnerView.setY(DrawMap.height / 2 - 350);

            winnerAnimation.setDuration(Duration.seconds(2));
            winnerAnimation.setNode(winnerView);
            winnerAnimation.setFromValue(0.0);
            winnerAnimation.setToValue(1.0);

            winnerAnimation.play();

            group.getChildren().add(winnerView);

            winnerAnimation.setOnFinished(event -> {
                Image playerImage = new Image(this.getClass().getResourceAsStream("/seIcon.png"));
                ImageView playerView = new ImageView(playerImage);

                playerView.setX(DrawMap.width / 2 - 75);
                playerView.setY(DrawMap.height / 2 - 50);

                group.getChildren().add(playerView);

            });
        }
        if (GameHandle.secondPlayer.getLastPos() == 1) {
            System.out.println("Congratulations! second player has won the game");

            GameHandle.end = true;

            Image winnerImage = new Image(this.getClass().getResourceAsStream("/winner.png"));
            ImageView winnerView = new ImageView(winnerImage);

            winnerView.setX(DrawMap.width / 2 - 175);
            winnerView.setY(DrawMap.height / 2 - 350);

            winnerAnimation.setDuration(Duration.seconds(2));
            winnerAnimation.setNode(winnerView);
            winnerAnimation.setFromValue(0.0);
            winnerAnimation.setToValue(1.0);

            winnerAnimation.play();

            group.getChildren().add(winnerView);

            winnerAnimation.setOnFinished(event -> {
                Image playerImage = new Image(this.getClass().getResourceAsStream("/eeIcon.png"));
                ImageView playerView = new ImageView(playerImage);

                playerView.setX(DrawMap.width / 2 - 75);
                playerView.setY(DrawMap.height / 2 - 50);

                group.getChildren().add(playerView);

            });
        }
    }

    private void drawMoneyAnimation(Group group) { // at this time the last animation method which is invoked is moneyAnimation method (this method)
        Text moneyText = new Text();
        if (GameHandle.firstPlayer.isTurn()&& GameHandle.firstPlayer.getMoney().size() >= 2)
            moneyText.setText(String.valueOf(GameHandle.firstPlayer.getMoney().get(GameHandle.firstPlayer.getMoney().size() - 1) -
            GameHandle.firstPlayer.getMoney().get(GameHandle.firstPlayer.getMoney().size() - 2)));

        if (GameHandle.secondPlayer.isTurn()&& GameHandle.secondPlayer.getMoney().size() >= 2)
            moneyText.setText(String.valueOf(GameHandle.secondPlayer.getMoney().get(GameHandle.secondPlayer.getMoney().size() - 1) -
                    GameHandle.secondPlayer.getMoney().get(GameHandle.secondPlayer.getMoney().size() - 2)));

        moneyText.setFont(Font.font("arial", FontWeight.BOLD, 35));

        Path path = new Path();
        path.getElements().add(new MoveTo(DrawMap.leftMargin + 400, DrawMap.height / 2));

        if (Integer.parseInt(moneyText.getText()) >= 0) {
            moneyText.setFill(Color.YELLOW);
            path.getElements().add(new LineTo(DrawMap.leftMargin + 400, DrawMap.height / 2 - 200));
        }
        else if (Integer.parseInt(moneyText.getText()) < 0) {
            moneyText.setFill(Color.RED);
            path.getElements().add(new LineTo(DrawMap.leftMargin + 400, DrawMap.height / 2 + 200));
        }

        moneyAnimation.setDuration(Duration.millis(1000));
        moneyAnimation.setNode(moneyText);
        moneyAnimation.setPath(path);

        FadeTransition fadeMoney = new FadeTransition();
        fadeMoney.setNode(moneyText);
        fadeMoney.setDuration(Duration.millis(1000));
        fadeMoney.setFromValue(1.0);
        fadeMoney.setToValue(0.0);

        group.getChildren().add(moneyText);

        moneyAnimation.play();
        fadeMoney.play();

        moneyAnimation.setOnFinished(event -> {
            group.getChildren().remove(moneyText);

            if (GameHandle.endSuspension)
                gameEnd(group);

            buyProperty();

        });

    }

    private void drawPositionAnimation(Group group) { // for special places like alpha-0 draw some cute animations :D
        // in the special blocks in addition to decreasing money we must avoid changing turns

        if (GameHandle.firstPlayer.isTurn()&& GameHandle.firstPlayer.getDoubleSix() == 2) {
            drawMoneyAnimation(group);

            return;
        }

        if (GameHandle.firstPlayer.isTurn()&& GameHandle.firstPlayer.getDoubleSix() != 2) {
            if (GameHandle.firstPlayer.getLastPos() == 19) { // jail!
                ImageView jailView = new ImageView();
                Image jailImage = new Image(this.getClass().getResourceAsStream("/jail.png"));
                jailView.setImage(jailImage);
                jailView.setFitWidth(100);
                jailView.setFitHeight(100);
                jailView.setX(DrawMap.width / 2 - 50);
                jailView.setY(DrawMap.upMargin + DrawMap.cellHeight + 25);

                jailAnimation.setDuration(Duration.millis(1500));
                jailAnimation.setFromValue(1.0);
                jailAnimation.setToValue(0.0);
                jailAnimation.setNode(jailView);

                group.getChildren().add(jailView);

                jailAnimation.play();

                jailAnimation.setOnFinished(event -> {
                    group.getChildren().remove(jailView); // remove animation last frame remnant

                    GameHandle.firstPlayer.setStuck(true); // set the player stuck boolean to true
                    System.out.println("first player stuck");

                    drawMoneyAnimation(group);

                    return;
                });
            }

            if (GameHandle.firstPlayer.getLastPos() == 28) { // alpha-0!
                ImageView alphaView = new ImageView();
                Image alphaImage = new Image(this.getClass().getResourceAsStream("/alpha.png"));
                alphaView.setImage(alphaImage);
                alphaView.setFitWidth(100);
                alphaView.setFitHeight(100);
                alphaView.setX(DrawMap.width / 2 - 50);
                alphaView.setY(DrawMap.upMargin + DrawMap.cellHeight + 25);

                alphaAnimation.setDuration(Duration.millis(2000));
                alphaAnimation.setFromValue(1.0);
                alphaAnimation.setToValue(0.0);
                alphaAnimation.setNode(alphaView);

                group.getChildren().add(alphaView);

                alphaAnimation.play();

                alphaAnimation.setOnFinished(event -> {
                    group.getChildren().remove(alphaView);
                    GameHandle.firstPlayer.setLastPos(10);

                    GameHandle.firstPlayer.setStuck(true);
                    System.out.println("first player stuck");

                    drawMoneyAnimation(group);
                });
            }

            if (alphaAnimation.getStatus() == Animation.Status.STOPPED&& jailAnimation.getStatus() == Animation.Status.STOPPED) // if player position is not a special block
                drawMoneyAnimation(group); // so even if no animation is invoked we can handle the sequence of animation method calls well using animation getStatus method
            return;
        }

        if (GameHandle.secondPlayer.isTurn()&& GameHandle.secondPlayer.getDoubleSix() == 2) {
            drawMoneyAnimation(group);

            return;
        }

        if (GameHandle.secondPlayer.isTurn()&& GameHandle.secondPlayer.getDoubleSix() != 2) {
            if (GameHandle.secondPlayer.getLastPos() == 19) {
                ImageView jailView = new ImageView();
                Image jailImage = new Image(this.getClass().getResourceAsStream("/jail.png"));
                jailView.setImage(jailImage);
                jailView.setFitWidth(100);
                jailView.setFitHeight(100);
                jailView.setX(DrawMap.width / 2 - 50);
                jailView.setY(DrawMap.upMargin + DrawMap.cellHeight + 25);

                jailAnimation.setDuration(Duration.millis(1500));
                jailAnimation.setFromValue(1.0);
                jailAnimation.setToValue(0.0);
                jailAnimation.setNode(jailView);

                group.getChildren().add(jailView);

                jailAnimation.play();

                jailAnimation.setOnFinished(event -> {
                    group.getChildren().remove(jailView);

                    GameHandle.secondPlayer.setStuck(true);
                    System.out.println("second player stuck");

                    drawMoneyAnimation(group);

                    return; // turn doesn't change because of jail
                });
            }

            if (GameHandle.secondPlayer.getLastPos() == 28) {
                ImageView alphaView = new ImageView();
                Image alphaImage = new Image(this.getClass().getResourceAsStream("/alpha.png"));
                alphaView.setImage(alphaImage);
                alphaView.setFitWidth(100);
                alphaView.setFitHeight(100);
                alphaView.setX(DrawMap.width / 2 - 50);
                alphaView.setY(DrawMap.upMargin + DrawMap.cellHeight + 25);

                alphaAnimation.setDuration(Duration.millis(2000));
                alphaAnimation.setFromValue(1.0);
                alphaAnimation.setToValue(0.0);
                alphaAnimation.setNode(alphaView);

                group.getChildren().add(alphaView);

                alphaAnimation.play();

                alphaAnimation.setOnFinished(event -> {
                    group.getChildren().remove(alphaView);

                    GameHandle.secondPlayer.setLastPos(10);

                    GameHandle.secondPlayer.setStuck(true);
                    System.out.println("second player stuck");

                    //////// turn doesn't change and money decrease by 500`

                    drawMoneyAnimation(group);


                    return;
                });
            }

            if (alphaAnimation.getStatus() == Animation.Status.STOPPED && jailAnimation.getStatus() == Animation.Status.STOPPED) // if player position is not a special block
                drawMoneyAnimation(group);
        }

    }

    private void drawDiceThrowingAnimation(Group group) { // first animation which is called anyway

        /////////////////////////////////////////////////////////////////////////////////////////////////
        ImageView diceView = new ImageView(); // just for animation
        Image diceImage = new Image(this.getClass().getResourceAsStream("/dice.png"));
        diceView.setImage(diceImage);
        diceView.setX(DrawComponents.width / 2 - 64);
        diceView.setY(DrawComponents.height / 2 - 64);

        Path path = new Path();
        path.getElements().add(new MoveTo(DrawComponents.width / 2, DrawComponents.height / 2));
        path.getElements().add(new CubicCurveTo(30, 10, 380, 320,
                DrawComponents.width / 2, DrawComponents.height / 2));

        diceAnimation.setDuration(Duration.millis(1000));
        diceAnimation.setNode(diceView);
        diceAnimation.setPath(path);
        group.getChildren().remove(dice1View);
        group.getChildren().remove(dice2View);
        diceAnimation.play();
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        Image dice1 = null;
        Image dice2 = null;

        if (GameHandle.firstPlayer.isTurn()) {
            switch (GameHandle.firstPlayer.getLastDice1Face()) { // setting the proper dice face base on last dice1 face
                case 1:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice1.png"));
                    break;
                case 2:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice2.png"));
                    break;
                case 3:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice3.png"));
                    break;
                case 4:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice4.png"));
                    break;
                case 5:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice5.png"));
                    break;
                case 6:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice6.png"));
                    break;
            }

            switch (GameHandle.firstPlayer.getLastDice2Face()) { // setting the proper dice face base on last dice2 face
                case 1:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice1.png"));
                    break;
                case 2:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice2.png"));
                    break;
                case 3:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice3.png"));
                    break;
                case 4:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice4.png"));
                    break;
                case 5:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice5.png"));
                    break;
                case 6:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice6.png"));
                    break;
            }
        }

        if (GameHandle.secondPlayer.isTurn()) {
            switch (GameHandle.secondPlayer.getLastDice1Face()) { // setting the proper dice face base on last dice1 face
                case 1:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice1.png"));
                    break;
                case 2:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice2.png"));
                    break;
                case 3:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice3.png"));
                    break;
                case 4:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice4.png"));
                    break;
                case 5:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice5.png"));
                    break;
                case 6:
                    dice1 = new Image(this.getClass().getResourceAsStream("/dice6.png"));
                    break;
            }

            switch (GameHandle.secondPlayer.getLastDice2Face()) { // setting the proper dice face base on last dice2 face
                case 1:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice1.png"));
                    break;
                case 2:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice2.png"));
                    break;
                case 3:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice3.png"));
                    break;
                case 4:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice4.png"));
                    break;
                case 5:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice5.png"));
                    break;
                case 6:
                    dice2 = new Image(this.getClass().getResourceAsStream("/dice6.png"));
                    break;
            }
        }

        dice1View = new ImageView(dice1);
        dice2View = new ImageView(dice2);

        dice1View.setFitWidth(50);
        dice1View.setFitHeight(50);
        dice1View.setX(DrawComponents.width / 2 - 25);
        dice1View.setY(DrawComponents.height / 2 - 25);

        dice2View.setFitWidth(50);
        dice2View.setFitHeight(50);
        dice2View.setX(DrawComponents.width / 2 + 25);
        dice2View.setY(DrawComponents.height / 2 - 25);

        group.getChildren().add(diceView);

        diceAnimation.setOnFinished(event -> { // when animation is finished this lambda will be invoked
            group.getChildren().remove(diceView); // remove the last frame of animation

            group.getChildren().add(dice1View);
            group.getChildren().add(dice2View);

            drawPlayerAnimation(group); // at the end of this animation the next animation will be invoke

        });
    }

    private void drawPlayerAnimation(Group group) { // draw player moving from last position to current position animation
        ImageView p1View = new ImageView(firstPlayerImage); // just for animation
        ImageView p2View = new ImageView(secondPlayerImage); // just for animation
        double x1 = 0; // initial position
        double y1 = 0; // initial position
        double x2 = 0; // final position
        double y2 = 0; // final position

        if (GameHandle.firstPlayer.isTurn()&& GameHandle.firstPlayer.getDoubleSix() == 2) {
            // before that we can draw some animation to show leading the player to jail

            drawPositionAnimation(group); // we run the next animation method here and then close the method
            return; // we do not allow the player to move
        }

        if (GameHandle.secondPlayer.isTurn()&& GameHandle.secondPlayer.getDoubleSix() == 2) {
            // before that we can draw some animation to show leading the player to jail

            drawPositionAnimation(group); // we run the next animation method here and then close the method
            return; // we do not allow the player to move
        }

        firstPlayerView.setFitWidth(50);
        firstPlayerView.setFitHeight(50);
        secondPlayerView.setFitWidth(50);
        secondPlayerView.setFitHeight(50);

        if (GameHandle.firstPlayer.isTurn()) {
            x1 = setX1(1);
            y1 = setY1(1);
            x2 = setX2(1);
            y2 = setY2(1);

            firstPlayerView.setX(x2);
            firstPlayerView.setY(y2);
        }
        if (GameHandle.secondPlayer.isTurn()) {
            x1 = setX1(2);
            y1 = setY1(2);
            x2 = setX2(2);
            y2 = setY2(2);

            secondPlayerView.setX(x2);
            secondPlayerView.setY(y2);
        }

        firstPlayerView.setImage(firstPlayerImage);
        secondPlayerView.setImage(secondPlayerImage);

        Path path = new Path();
        path.getElements().add(new MoveTo(x1 + 25, y1 + 25));
        path.getElements().add(new LineTo(x2 + 25, y2 + 25));

        playerAnimation.setDuration(Duration.millis(1000));
        playerAnimation.setPath(path);

        if (GameHandle.firstPlayer.isTurn()) {
            group.getChildren().remove(firstPlayerView); // remove initial place
            group.getChildren().add(p1View); // adding new imageView
            playerAnimation.setNode(p1View); // adding image to animate
        }

        if (GameHandle.secondPlayer.isTurn()) {
            group.getChildren().remove(secondPlayerView); // remove initial place
            group.getChildren().add(p2View); // adding new imageView
            playerAnimation.setNode(p2View); // adding image to animate
        }

        playerAnimation.play();

        playerAnimation.setOnFinished(event -> {
            if (GameHandle.firstPlayer.isTurn()) {
                group.getChildren().add(firstPlayerView); // draw final position
                group.getChildren().remove(p1View); // remove animation last frame remnant
            }

            if (GameHandle.secondPlayer.isTurn()) {
                group.getChildren().add(secondPlayerView); // draw final position
                group.getChildren().remove(p2View); // remove animation last frame remnant
            }

            drawPositionAnimation(group); // then the next animation will be invoked
        });

    }

    private double setY1(int turn) {
        double y = 0;

        if (turn == 1) {
            int index = GameHandle.firstPlayer.getPositions().size() - 2;
            if (index < 0)
                index = 0;

            if (GameHandle.firstPlayer.getPositions().get(index) < 10)
                y = DrawMap.height - DrawMap.downMargin - GameHandle.firstPlayer.getPositions().get(index) * DrawMap.cellHeight + DrawMap.cellHeight / 2;
            if (GameHandle.firstPlayer.getPositions().get(index) < 19&& GameHandle.firstPlayer.getPositions().get(index) >= 10)
                y = DrawMap.upMargin + DrawMap.cellHeight / 2;
            if (GameHandle.firstPlayer.getPositions().get(index) < 28&& GameHandle.firstPlayer.getPositions().get(index) >= 19)
                y = DrawMap.upMargin + (GameHandle.firstPlayer.getPositions().get(index) % 19) * DrawMap.cellHeight + DrawMap.cellHeight / 2;
            if (GameHandle.firstPlayer.getPositions().get(index) < 37&& GameHandle.firstPlayer.getPositions().get(index) >= 28)
                y = DrawMap.height - DrawMap.downMargin - DrawMap.cellHeight / 2;
        }

        if (turn == 2) {
            int index = GameHandle.secondPlayer.getPositions().size() - 2;
            if (index < 0)
                index = 0;

            if (GameHandle.secondPlayer.getPositions().get(index) < 10)
                y = DrawMap.height - DrawMap.downMargin - GameHandle.secondPlayer.getPositions().get(index) * DrawMap.cellHeight + DrawMap.cellHeight / 2;
            if (GameHandle.secondPlayer.getPositions().get(index) < 19&& GameHandle.secondPlayer.getPositions().get(index) >= 10)
                y = DrawMap.upMargin + DrawMap.cellHeight / 2;
            if (GameHandle.secondPlayer.getPositions().get(index) < 28&& GameHandle.secondPlayer.getPositions().get(index) >= 19)
                y = DrawMap.upMargin + (GameHandle.secondPlayer.getPositions().get(index) % 19) * DrawMap.cellHeight + DrawMap.cellHeight / 2;
            if (GameHandle.secondPlayer.getPositions().get(index) < 37&& GameHandle.secondPlayer.getPositions().get(index) >= 28)
                y = DrawMap.height - DrawMap.downMargin - DrawMap.cellHeight / 2;
        }

        return y;
    }

    private double setX1(int turn) {
        double x = 0;

        if (turn == 1) {
            int index = GameHandle.firstPlayer.getPositions().size() - 2;
            if (index < 0)
                index = 0;

            if (GameHandle.firstPlayer.getPositions().get(index) < 10)
                x = DrawMap.leftMargin + DrawMap.cellWidth / 2;
            if (GameHandle.firstPlayer.getPositions().get(index) < 19&& GameHandle.firstPlayer.getPositions().get(index) >= 10)
                x = DrawMap.leftMargin + (GameHandle.firstPlayer.getPositions().get(index) % 10) * DrawMap.cellWidth + DrawMap.cellWidth / 2;
            if (GameHandle.firstPlayer.getPositions().get(index) < 28&& GameHandle.firstPlayer.getPositions().get(index) >= 19)
                x = DrawMap.width - DrawMap.rightMargin - DrawMap.cellWidth / 2;
            if (GameHandle.firstPlayer.getPositions().get(index) < 37&& GameHandle.firstPlayer.getPositions().get(index) >= 28)
                x = DrawMap.width - DrawMap.rightMargin - (GameHandle.firstPlayer.getPositions().get(index) % 28) * DrawMap.cellWidth - DrawMap.cellWidth / 2;
        }

        if (turn == 2) {
            int index = GameHandle.secondPlayer.getPositions().size() - 2;
            if (index < 0)
                index = 0;

            if (GameHandle.secondPlayer.getPositions().get(index) < 10)
                x = DrawMap.leftMargin + DrawMap.cellWidth / 2;
            if (GameHandle.secondPlayer.getPositions().get(index) < 19&& GameHandle.secondPlayer.getPositions().get(index) >= 10)
                x = DrawMap.leftMargin + (GameHandle.secondPlayer.getPositions().get(index) % 10) * DrawMap.cellWidth + DrawMap.cellWidth / 2;
            if (GameHandle.secondPlayer.getPositions().get(index) < 28&& GameHandle.secondPlayer.getPositions().get(index) >= 19)
                x = DrawMap.width - DrawMap.rightMargin - DrawMap.cellWidth / 2;
            if (GameHandle.secondPlayer.getPositions().get(index) < 37&& GameHandle.secondPlayer.getPositions().get(index) >= 28)
                x = DrawMap.width - DrawMap.rightMargin - (GameHandle.secondPlayer.getPositions().get(index) % 28) * DrawMap.cellWidth - DrawMap.cellWidth / 2;
        }

        return x;
    }

    private double setY2(int turn) {
        double y = 0;

        if (turn == 1) {

            if (GameHandle.firstPlayer.getLastPos() < 10)
                y = DrawMap.height - DrawMap.downMargin - GameHandle.firstPlayer.getLastPos() * DrawMap.cellHeight + DrawMap.cellHeight / 2;
            if (GameHandle.firstPlayer.getLastPos() < 19&& GameHandle.firstPlayer.getLastPos() >= 10)
                y = DrawMap.upMargin + DrawMap.cellHeight / 2;
            if (GameHandle.firstPlayer.getLastPos() < 28&& GameHandle.firstPlayer.getLastPos() >= 19)
                y = DrawMap.upMargin + (GameHandle.firstPlayer.getLastPos() % 19) * DrawMap.cellHeight + DrawMap.cellHeight / 2;
            if (GameHandle.firstPlayer.getLastPos() < 37&& GameHandle.firstPlayer.getLastPos() >= 28)
                y = DrawMap.height - DrawMap.downMargin - DrawMap.cellHeight / 2;
        }

        if (turn == 2) {

            if (GameHandle.secondPlayer.getLastPos() < 10)
                y = DrawMap.height - DrawMap.downMargin - GameHandle.secondPlayer.getLastPos() * DrawMap.cellHeight + DrawMap.cellHeight / 2;
            if (GameHandle.secondPlayer.getLastPos() < 19&& GameHandle.secondPlayer.getLastPos() >= 10)
                y = DrawMap.upMargin + DrawMap.cellHeight / 2;
            if (GameHandle.secondPlayer.getLastPos() < 28&& GameHandle.secondPlayer.getLastPos() >= 19)
                y = DrawMap.upMargin + (GameHandle.secondPlayer.getLastPos() % 19) * DrawMap.cellHeight + DrawMap.cellHeight / 2;
            if (GameHandle.secondPlayer.getLastPos() < 37&& GameHandle.secondPlayer.getLastPos() >= 28)
                y = DrawMap.height - DrawMap.downMargin - DrawMap.cellHeight / 2;
        }

        return y;
    }

    private double setX2(int turn) {
        double x = 0;

        if (turn == 1) {

            if (GameHandle.firstPlayer.getLastPos() < 10)
                x = DrawMap.leftMargin + DrawMap.cellWidth / 2;
            if (GameHandle.firstPlayer.getLastPos() < 19&& GameHandle.firstPlayer.getLastPos() >= 10)
                x = DrawMap.leftMargin + (GameHandle.firstPlayer.getLastPos() % 10) * DrawMap.cellWidth + DrawMap.cellWidth / 2;
            if (GameHandle.firstPlayer.getLastPos() < 28&& GameHandle.firstPlayer.getLastPos() >= 19)
                x = DrawMap.width - DrawMap.rightMargin - DrawMap.cellWidth / 2;
            if (GameHandle.firstPlayer.getLastPos() < 37&& GameHandle.firstPlayer.getLastPos() >= 28)
                x = DrawMap.width - DrawMap.rightMargin - (GameHandle.firstPlayer.getLastPos() % 28) * DrawMap.cellWidth - DrawMap.cellWidth / 2;
        }

        if (turn == 2) {

            if (GameHandle.secondPlayer.getLastPos() < 10)
                x = DrawMap.leftMargin + DrawMap.cellWidth / 2;
            if (GameHandle.secondPlayer.getLastPos() < 19&& GameHandle.secondPlayer.getLastPos() >= 10)
                x = DrawMap.leftMargin + (GameHandle.secondPlayer.getLastPos() % 10) * DrawMap.cellWidth + DrawMap.cellWidth / 2;
            if (GameHandle.secondPlayer.getLastPos() < 28&& GameHandle.secondPlayer.getLastPos() >= 19)
                x = DrawMap.width - DrawMap.rightMargin - DrawMap.cellWidth / 2;
            if (GameHandle.secondPlayer.getLastPos() < 37&& GameHandle.secondPlayer.getLastPos() >= 28)
                x = DrawMap.width - DrawMap.rightMargin - (GameHandle.secondPlayer.getLastPos() % 28) * DrawMap.cellWidth - DrawMap.cellWidth / 2;
        }

        return x;
    }

    private void buyProperty() {
        int playerIndex, playerPos;

        if (GameHandle.firstPlayer.isTurn()) {
            playerIndex = 1;
            playerPos = GameHandle.firstPlayer.getLastPos();
        } else {
            playerIndex = 2;
            playerPos = GameHandle.secondPlayer.getLastPos();
        }

        if (playerIndex == 1 && GameHandle.secondPlayer.getPropertyIndexes().indexOf(playerPos) == -1 && GameHandle.firstPlayer.getPropertyIndexes().indexOf(playerPos) == -1
                && GameHandle.firstPlayer.getLastMoney() >= GameHandle.propertiesPrices.get(playerPos - 1) && playerPos != 19 && playerPos != 28 &&
                playerPos != 10 && playerPos != 1 && GameHandle.firstPlayer.getDoubleSix() != 2) {

            Alert buyPropertyConfirmation = new Alert(Alert.AlertType.CONFIRMATION);

            buyPropertyConfirmation.setContentText("Buy Property?");
            buyPropertyConfirmation.setTitle("Buy Property Confirmation");
            buyPropertyConfirmation.setHeaderText("");

            ImageView propertyView = new ImageView();
            Image propertyImage = new Image(this.getClass().getResourceAsStream("/propertyDeploy.png"));
            propertyView.setImage(propertyImage);

            buyPropertyConfirmation.setGraphic(propertyView);

            Optional<ButtonType> isBought = buyPropertyConfirmation.showAndWait();

            if (isBought.get() == ButtonType.OK) {
                System.out.print(GameHandle.firstPlayer.getLastMoney() + "  " + GameHandle.propertiesPrices.get(playerPos - 1) + "  ");

                GameHandle.firstPlayer.setLastMoney(GameHandle.firstPlayer.getLastMoney() - GameHandle.propertiesPrices.get(playerPos - 1));
                GameHandle.firstPlayer.setLastProperty(playerPos);

                DrawMap.drawBoughtProperty(playerIndex, playerPos);

                System.out.println(GameHandle.firstPlayer.getLastMoney());
            }
        }

        if (playerIndex == 2 && GameHandle.firstPlayer.getPropertyIndexes().indexOf(playerPos) == -1 && GameHandle.secondPlayer.getPropertyIndexes().indexOf(playerPos) == -1
                && GameHandle.secondPlayer.getLastMoney() >= GameHandle.propertiesPrices.get(playerPos - 1) && playerPos != 19 && playerPos != 28 &&
                playerPos != 10 && playerPos != 1 && GameHandle.secondPlayer.getDoubleSix() != 2) {
            Alert buyPropertyConfirmation = new Alert(Alert.AlertType.CONFIRMATION);

            buyPropertyConfirmation.setContentText("Buy Property?");
            buyPropertyConfirmation.setTitle("Buy Property Confirmation");
            buyPropertyConfirmation.setHeaderText("");

            ImageView propertyView = new ImageView();
            Image propertyImage = new Image(this.getClass().getResourceAsStream("/propertyDeploy.png"));
            propertyView.setImage(propertyImage);

            buyPropertyConfirmation.setGraphic(propertyView);

            Optional<ButtonType> isBought = buyPropertyConfirmation.showAndWait();

            if (isBought.get() == ButtonType.OK) {
                System.out.print(GameHandle.secondPlayer.getLastMoney() + "  " + GameHandle.propertiesPrices.get(playerPos - 1) + "  ");

                GameHandle.secondPlayer.setLastMoney(GameHandle.secondPlayer.getLastMoney() - GameHandle.propertiesPrices.get(playerPos - 1));
                GameHandle.secondPlayer.setLastProperty(playerPos);

                DrawMap.drawBoughtProperty(playerIndex, playerPos);

                System.out.println(GameHandle.secondPlayer.getLastMoney());
            }
        }

        buildQuarter();
    }

    private void buildQuarter() {
        int playerIndex, playerPos;

        if (GameHandle.firstPlayer.isTurn()) {
            playerIndex = 1;
            playerPos = GameHandle.firstPlayer.getLastPos();
        }
        else {
            playerIndex = 2;
            playerPos = GameHandle.secondPlayer.getLastPos();
        }

        if (playerIndex == 1&& GameHandle.secondPlayer.getPropertyIndexes().indexOf(playerPos) == -1 && GameHandle.firstPlayer.getPropertyIndexes().indexOf(playerPos) != -1
                && GameHandle.firstPlayer.getLastMoney() >= (0.2 * GameHandle.propertiesPrices.get(playerPos - 1))&& playerPos != 19&& playerPos != 28&&
                playerPos != 10&& playerPos != 1&& GameHandle.firstPlayer.getDoubleSix() != 2) {

            if (GameHandle.firstPlayer.getQuarters().get(playerPos) != null)
                if (GameHandle.firstPlayer.getQuarters().get(playerPos) == 5) {
                    buildDorm();
                    return;
                }

            Stage stage = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group);
            Button okBtn = new Button("OK");
            Image quarterDeployImage = new Image(this.getClass().getResourceAsStream("/quarterDeploy.png"));
            ImageView quarterDeployView = new ImageView(quarterDeployImage);
            ComboBox<Integer> buildQuarterCombo = new ComboBox<>();

            buildQuarterCombo.setLayoutX(20);
            buildQuarterCombo.setLayoutY(20);

            quarterView.setX(20);
            quarterView.setY(100);

            quarterDeployView.setX(180);
            quarterDeployView.setY(20);

            okBtn.setLayoutX(150);
            okBtn.setLayoutY(300);

            int min = 5;

            if (GameHandle.firstPlayer.getQuarters().get(playerPos) != null)
                min = 5 - GameHandle.firstPlayer.getQuarters().get(playerPos);

            if ((int)Math.floor(GameHandle.firstPlayer.getLastMoney() / (0.2 * GameHandle.propertiesPrices.get(playerPos - 1))) < min)
                min = (int) Math.floor(GameHandle.firstPlayer.getLastMoney() / (0.2 * GameHandle.propertiesPrices.get(playerPos - 1)));

            for (int i = 1; i <= min; ++i)
                buildQuarterCombo.getItems().add(i);

            buildQuarterCombo.setOnAction(event -> {
                group.getChildren().remove(quarterView);

                Image quarterImage = new Image(this.getClass().getResourceAsStream("/" + buildQuarterCombo.getValue() + ".png"));
                quarterView.setImage(quarterImage);

                group.getChildren().add(quarterView);
            });

            okBtn.setOnAction(event -> {
                if (GameHandle.firstPlayer.getQuarters().containsKey(playerPos))
                    GameHandle.firstPlayer.setQuarters(playerPos, GameHandle.firstPlayer.getQuarters().get(playerPos) + buildQuarterCombo.getValue());
                else GameHandle.firstPlayer.setQuarters(playerPos, buildQuarterCombo.getValue());

                GameHandle.firstPlayer.setLastMoney(GameHandle.firstPlayer.getLastMoney() - (int) (buildQuarterCombo.getValue() * (0.2 * GameHandle.propertiesPrices.get(playerPos - 1))));

                System.out.print("Bought " + buildQuarterCombo.getValue() + " quarters for " + buildQuarterCombo.getValue() * (0.2 * GameHandle.propertiesPrices.get(playerPos - 1)));
                System.out.println(", " + GameHandle.firstPlayer.getLastMoney());

                DrawMap.drawBoughtQuarter(playerPos, GameHandle.firstPlayer.getQuarters().get(playerPos));

                stage.close();
            });

            group.getChildren().addAll(buildQuarterCombo, okBtn, quarterDeployView);

            stage.setWidth(400);
            stage.setHeight(400);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        }

        if (playerIndex == 2&& GameHandle.firstPlayer.getPropertyIndexes().indexOf(playerPos) == -1 && GameHandle.secondPlayer.getPropertyIndexes().indexOf(playerPos) != -1
                && GameHandle.secondPlayer.getLastMoney() >= (0.2 * GameHandle.propertiesPrices.get(playerPos - 1))&& playerPos != 19&& playerPos != 28&&
                playerPos != 10&& playerPos != 1&& GameHandle.secondPlayer.getDoubleSix() != 2) {

            if (GameHandle.secondPlayer.getQuarters().get(playerPos) != null)
                if (GameHandle.secondPlayer.getQuarters().get(playerPos) == 5) {
                    buildDorm();
                    return;
                }

            Stage stage = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group);
            Button okBtn = new Button("OK");
            Image quarterDeployImage = new Image(this.getClass().getResourceAsStream("/quarterDeploy.png"));
            ImageView quarterDeployView = new ImageView(quarterDeployImage);
            ComboBox<Integer> buildQuarterCombo = new ComboBox<>();

            buildQuarterCombo.setLayoutX(20);
            buildQuarterCombo.setLayoutY(20);

            quarterView.setX(20);
            quarterView.setY(100);

            quarterDeployView.setX(180);
            quarterDeployView.setY(20);

            okBtn.setLayoutX(150);
            okBtn.setLayoutY(300);

            int min = 5;

            if (GameHandle.secondPlayer.getQuarters().get(playerPos) != null)
                min = 5 - GameHandle.secondPlayer.getQuarters().get(playerPos);

            if ((int)Math.floor(GameHandle.secondPlayer.getLastMoney() / (0.2 * GameHandle.propertiesPrices.get(playerPos - 1))) < min)
                min = (int) Math.floor(GameHandle.secondPlayer.getLastMoney() / (0.2 * GameHandle.propertiesPrices.get(playerPos - 1)));

            for (int i = 1; i <= min; ++i)
                buildQuarterCombo.getItems().add(i);

            buildQuarterCombo.setOnAction(event -> {
                group.getChildren().remove(quarterView);

                Image quarterImage = new Image(this.getClass().getResourceAsStream("/" + buildQuarterCombo.getValue() + ".png"));
                quarterView.setImage(quarterImage);

                group.getChildren().add(quarterView);
            });

            okBtn.setOnAction(event -> {
                if (GameHandle.secondPlayer.getQuarters().containsKey(playerPos))
                    GameHandle.secondPlayer.setQuarters(playerPos, GameHandle.secondPlayer.getQuarters().get(playerPos) + buildQuarterCombo.getValue());
                else GameHandle.secondPlayer.setQuarters(playerPos, buildQuarterCombo.getValue());

                GameHandle.secondPlayer.setLastMoney(GameHandle.secondPlayer.getLastMoney() - (int) (buildQuarterCombo.getValue() * (0.2 * GameHandle.propertiesPrices.get(playerPos - 1))));

                System.out.print("Bought " + buildQuarterCombo.getValue() + " quarters for " + buildQuarterCombo.getValue() * (0.2 * GameHandle.propertiesPrices.get(playerPos - 1)));
                System.out.println(", " + GameHandle.secondPlayer.getLastMoney());

                DrawMap.drawBoughtQuarter(playerPos, GameHandle.secondPlayer.getQuarters().get(playerPos));

                stage.close();
            });

            group.getChildren().addAll(buildQuarterCombo, quarterDeployView, okBtn);

            stage.setWidth(400);
            stage.setHeight(400);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
        }

        buildDorm();
    }

    private void buildDorm() {

        int playerIndex, playerPos;

        if (GameHandle.firstPlayer.isTurn()) {
            playerIndex = 1;
            playerPos = GameHandle.firstPlayer.getLastPos();
        }
        else {
            playerIndex = 2;
            playerPos = GameHandle.secondPlayer.getLastPos();
        }

        if (playerIndex == 1&& GameHandle.secondPlayer.getPropertyIndexes().indexOf(playerPos) == -1&& GameHandle.firstPlayer.getPropertyIndexes().indexOf(playerPos) != -1
                && GameHandle.firstPlayer.getLastMoney() >= (0.5 * GameHandle.propertiesPrices.get(playerPos - 1))&& playerPos != 19&& playerPos != 28&&
                playerPos != 10&& playerPos != 1&& GameHandle.firstPlayer.getDoubleSix() != 2) {

            try {

                if (GameHandle.firstPlayer.getQuarters().get(playerPos) != 5) {
                    changeTurn();
                    return;
                }

                if (GameHandle.firstPlayer.getDormIndexes().indexOf(playerPos) != -1) { // has already a dorm in that place
                    changeTurn();
                    return;
                }
            } catch (Exception exception) {
                changeTurn();
                return;
            }

            Stage stage = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group);
            Button okBtn = new Button("OK");
            Image dormImage = new Image(this.getClass().getResourceAsStream("/dormIcon.png"));
            ImageView dormView = new ImageView(dormImage);
            Image dormDeployImage = new Image(this.getClass().getResourceAsStream("/dormDeploy.png"));
            ImageView dormDeployView = new ImageView(dormDeployImage);

            dormView.setX(20);
            dormView.setY(20);

            dormDeployView.setX(250);
            dormDeployView.setY(20);

            okBtn.setLayoutX(200);
            okBtn.setLayoutY(300);

            okBtn.setOnAction(event -> {
                GameHandle.firstPlayer.setLastDorm(playerPos);
                GameHandle.firstPlayer.setLastMoney(GameHandle.firstPlayer.getLastMoney() - (int) (0.5 * GameHandle.propertiesPrices.get(playerPos - 1)));

                System.out.print("Deploy a dorm in " + playerPos + " for " + (0.5 * GameHandle.propertiesPrices.get(playerPos - 1)));
                System.out.println(", " + GameHandle.firstPlayer.getLastMoney());

                DrawMap.drawBoughtDorm(playerPos);

                stage.close();
            });

            group.getChildren().addAll(dormDeployView, dormView, okBtn);

            stage.setWidth(500);
            stage.setHeight(400);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        }

        if (playerIndex == 2&& GameHandle.firstPlayer.getPropertyIndexes().indexOf(playerPos) == -1&& GameHandle.secondPlayer.getPropertyIndexes().indexOf(playerPos) != -1
                && GameHandle.secondPlayer.getLastMoney() >= (0.5 * GameHandle.propertiesPrices.get(playerPos - 1))&& playerPos != 19&& playerPos != 28&&
                playerPos != 10&& playerPos != 1&& GameHandle.secondPlayer.getDoubleSix() != 2) {

            try {

                if (GameHandle.secondPlayer.getQuarters().get(playerPos) != 5) {
                    changeTurn();
                    return;
                }

                if (GameHandle.secondPlayer.getDormIndexes().indexOf(playerPos) != -1) { // has already a dorm in that place
                    changeTurn();
                    return;
                }
            } catch (Exception exception) {
                changeTurn();
                return;
            }

            Stage stage = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group);
            Button okBtn = new Button("OK");
            Image dormImage = new Image(this.getClass().getResourceAsStream("/dormIcon.png"));
            ImageView dormView = new ImageView(dormImage);
            Image dormDeployImage = new Image(this.getClass().getResourceAsStream("/dormDeploy.png"));
            ImageView dormDeployView = new ImageView(dormDeployImage);

            dormView.setX(20);
            dormView.setY(20);

            dormDeployView.setX(250);
            dormDeployView.setY(20);

            okBtn.setLayoutX(200);
            okBtn.setLayoutY(300);

            okBtn.setOnAction(event -> {
                GameHandle.secondPlayer.setLastDorm(playerPos);
                GameHandle.secondPlayer.setLastMoney(GameHandle.secondPlayer.getLastMoney() - (int) (0.5 * GameHandle.propertiesPrices.get(playerPos - 1)));

                System.out.print("Deploy a dorm in " + playerPos + " for " + (0.5 * GameHandle.propertiesPrices.get(playerPos - 1)));
                System.out.println(", " + GameHandle.secondPlayer.getLastMoney());

                DrawMap.drawBoughtDorm(playerPos);

                stage.close();
            });

            group.getChildren().addAll(dormDeployView, dormView, okBtn);

            stage.setWidth(500);
            stage.setHeight(400);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
        }

        changeTurn();

    }

    private void changeTurn() {
        if (GameHandle.firstPlayer.isTurn()) {
            if (GameHandle.firstPlayer.getDoubleSix() == 2) {
                System.out.println("sorry! two double six in sequence for first player, leading to jail :(");
                GameHandle.firstPlayer.setDoubleSix(0);
                GameHandle.firstPlayer.setDoubleSixBan(true);
                GameHandle.firstPlayer.setLastPos(19);
            }

            if (GameHandle.secondPlayer.isStuck()) {
                System.out.println("sorry! second player stuck, turn doesn't change");
                GameHandle.secondPlayer.setStuck(false);
                return;
            }

            if (GameHandle.firstPlayer.getDoubleSix() == 1) {
                System.out.println("congratulations! first player got a double six, turn doesn't change");
                return;
            }

            if (GameHandle.secondPlayer.isDoubleSixBan()) {
                System.out.println("sorry! second player is double six ban, turn doesn't change");
                GameHandle.secondPlayer.setDoubleSixBan(false);
                return;
            }

            System.out.println("turn changed to second");
            GameHandle.firstPlayer.setTurn(false);
            GameHandle.secondPlayer.setTurn(true);
            return;
        }

        if (GameHandle.secondPlayer.isTurn()) {
            if (GameHandle.secondPlayer.getDoubleSix() == 2) {
                System.out.println("sorry! two double six in sequence for second player, leading to jail :(");
                GameHandle.secondPlayer.setDoubleSix(0);
                GameHandle.secondPlayer.setDoubleSixBan(true);
                GameHandle.secondPlayer.setLastPos(19);
            }

            if (GameHandle.firstPlayer.isStuck()) {
                System.out.println("sorry! second player stuck, turn doesn't change");
                GameHandle.firstPlayer.setStuck(false);
                return;
            }

            if (GameHandle.secondPlayer.getDoubleSix() == 1) {
                System.out.println("congratulations! second player got a double six, turn doesn't change");
                return;
            }

            if (GameHandle.firstPlayer.isDoubleSixBan()) {
                System.out.println("sorry! first player is double six ban, turn doesn't change");
                GameHandle.firstPlayer.setDoubleSixBan(false);
                return;
            }

            System.out.println("turn changed to first");
            GameHandle.secondPlayer.setTurn(false);
            GameHandle.firstPlayer.setTurn(true);
            return;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////// by setting these values below before the dices thrown and comparing these values to the new values which
    ///////////////////// are set in the players by gameHandle after these values are set, we can draw some animation based on the
    ///////////////////// differences between previous and current situation
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
