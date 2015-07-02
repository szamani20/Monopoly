package gui;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DrawEasternComponents { // because of complexity a separate class do the job for this part of scene
    private static Text text;
    private static TranslateTransition tt;
    private static ScrollPane scrollPane;

    private static String first = "Celine Dion, My Heart Will Go On\n\n\n\n" +
            "Every night in my dreams\n" +
            "I see you, I feel you\n" +
            "That is how I know you, go on\n" +
            "\n\n\n\n" +
            "Far across the distance\n" +
            "And spaces between us\n" +
            "You have come to show you, go on\n" +
            "\n\n\n\n" +
            "Near, far, wherever you are\n" +
            "I believe that the heart does go on\n" +
            "Once more you open the door\n" +
            "And you're here in my heart and my heart will go on and on\n" +
            "\n\n\n\n\n\n\n\n" +
            "Love can touch us one time\n" +
            "And last for a lifetime\n" +
            "And never let go till we're gone\n" +
            "\n\n\n\n\n\n" +
            "Love was when I loved you\n" +
            "One true time I hold you\n" +
            "In my life we'll always go on\n" +
            "\n\n\n\n\n\n\n\n" +
            "Near, far, wherever you are\n" +
            "I believe that the heart does go on\n\n\n\n" +
            "Once more you open the door\n" +
            "And you're here in my heart\n" +
            "And my heart will go on and on\n" +
            "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
            "You're here, there's nothing I fear\n" +
            "And I know that my heart will go on\n" +
            "We'll stay forever this way\n" +
            "You are safe in my heart and my heart will go on and on\n" +
            "\n" +
            "\n" +
            "\n";
    private static String second = "Katy Perry, Firework\n\n\n" +
            "Do you ever feel\n" +
            "Like a plastic bag\n" +
            "Drifting through the wind\n" +
            "Wanting to start again\n" +
            "\n" +
            "Do you ever feel\n" +
            "Feel so paper-thin\n" +
            "Like a house of cards\n" +
            "One blow from caving in\n" +
            "\n" +
            "Do you ever feel\n" +
            "Already buried deep\n" +
            "Six feet under\n" +
            "Screams but no one seems to hear a thing\n" +
            "\n" +
            "Do you know that there's\n" +
            "Still a chance for you\n" +
            "'Cause there's a spark in you\n" +
            "You just gotta\n" +
            "\n" +
            "Ignite the light\n" +
            "And let it shine\n" +
            "Just own the night\n" +
            "Like the Fourth of July\n" +
            "\n\n\n\n\n\n\n\n" +
            "'Cause baby, you're a firework\n" +
            "Come on show them what you're worth\n" +
            "Make them go, \"Oh, oh, oh\"\n" +
            "As you shoot across the sky\n" +
            "\n\n\n\n\n\n\n\n" +
            "Baby, you're a firework\n" +
            "Come on let your colors burst\n" +
            "Make them go, \"Oh, oh, oh\"\n" +
            "You're gonna leave them all in awe\n" +
            "\n\n\n\n\n\n\n" +
            "You don't have to feel\n" +
            "Like a waste of space\n" +
            "You're original\n" +
            "Cannot be replaced\n" +
            "\n" +
            "If you only knew\n" +
            "What the future holds\n" +
            "After a hurricane\n" +
            "Comes a rainbow\n" +
            "\n" +
            "Maybe the reason why\n" +
            "All the doors are closed\n" +
            "So you could open one\n" +
            "That leads you to the perfect road\n" +
            "\n" +
            "Like a lightning bolt\n" +
            "Your heart will glow\n" +
            "And when it's time you know\n" +
            "You just gotta\n" +
            "\n" +
            "Ignite the light\n" +
            "And let it shine\n" +
            "Just own the night\n" +
            "Like the Fourth of July\n" +
            "\n\n\n\n\n\n" +
            "'Cause baby, you're a firework\n" +
            "Come on show them what you're worth\n" +
            "Make them go, \"Oh, oh, oh\"\n" +
            "As you shoot across the sky\n" +
            "\n\n\n\n\n\n" +
            "Baby, you're a firework\n" +
            "Come on let your colors burst\n" +
            "Make them go, \"Oh, oh, oh\"\n" +
            "You're gonna leave them all in awe\n" +
            "\n\n\n\n\n\n\n\n" +
            "Boom, boom, boom\n" +
            "Even brighter than the moon, moon, moon\n" +
            "It's always been inside of you, you, you\n" +
            "And now it's time to let it through\n" +
            "\n\n\n\n\n\n\n\n\n" +
            "'Cause baby, you're a firework\n" +
            "Come on show them what you're worth\n" +
            "Make them go, \"Oh, oh, oh\"\n" +
            "As you shoot across the sky\n" +
            "\n\n\n\n\n\n\n" +
            "Baby, you're a firework\n" +
            "Come on let your colors burst\n" +
            "Make them go, \"Oh, oh, oh\"\n" +
            "You're gonna leave them all in awe\n" +
            "\n\n\n\n\n\n\n\n\n\n\n\n" +
            "Boom, boom, boom\n" +
            "Even brighter than the moon, moon, moon\n" +
            "Boom, boom, boom\n" +
            "Even brighter than the moon, moon, moon";
    private static String third = "Katy Perry, Roar\n\n\n\n" +
            "I used to bite my tongue and hold my breath\n" +
            "Scared to rock the boat and make a mess\n" +
            "So I sat quietly, agreed politely\n" +
            "I guess that I forgot I had a choice\n" +
            "I let you push me past the breaking point\n" +
            "I stood for nothing, so I fell for everything\n" +
            "\n" +
            "You held me down, but I got up (hey!)\n" +
            "Already brushing off the dust\n" +
            "You hear my voice, your hear that sound\n" +
            "Like thunder, gonna shake your ground\n" +
            "You held me down, but I got up\n" +
            "Get ready 'cause I've had enough\n" +
            "I see it all, I see it now\n" +
            "\n\n\n\n\n" +
            "I got the eye of the tiger, a fighter\n" +
            "Dancing through the fire\n" +
            "'Cause I am the champion, and you're gonna hear me roar\n" +
            "Louder, louder than a lion\n" +
            "'Cause I am a champion, and you're gonna hear me roar!\n" +
            "\n\n\n\n\n\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "You're gonna hear me roar!\n" +
            "\n\n\n\n\n\n\n\n" +
            "Now I'm floating like a butterfly\n" +
            "Stinging like a bee I earned my stripes\n" +
            "I went from zero, to my own hero\n" +
            "\n" +
            "You held me down, but I got up (hey!)\n" +
            "Already brushing off the dust\n" +
            "You hear my voice, your hear that sound\n" +
            "Like thunder, gonna shake the ground\n" +
            "You held me down, but I got up\n" +
            "Get ready 'cause I've had enough\n" +
            "I see it all, I see it now\n" +
            "\n\n\n\n\n\n\n" +
            "I got the eye of the tiger, a fighter\n" +
            "Dancing through the fire\n" +
            "'Cause I am the champion, and you're gonna hear me roar\n" +
            "Louder, louder than a lion\n" +
            "'Cause I am a champion, and you're gonna hear me roar!\n" +
            "\n\n\n\n\n\n\n\n\n\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "You're gonna hear me roar!\n" +
            "\n\n\n\n\n\n\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "You're gonna hear me roar!\n" +
            "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
            "Roar, roar, roar, roar, roar!\n" +
            "\n\n\n\n\n\n" +
            "I got the eye of the tiger, a fighter\n" +
            "Dancing through the fire\n" +
            "'Cause I am the champion, and you're gonna hear me roar\n" +
            "Louder, louder than a lion\n" +
            "'Cause I am a champion, and you're gonna hear me roar!\n" +
            "\n\n\n\n\n\n\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "You're gonna hear me roar!\n" +
            "\n\n\n\n\n\n\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "Oh oh oh oh oh oh oh oh\n" +
            "You're gonna hear me roar!\n" +
            "\n" +
            "\n" +
            "\n";

    public DrawEasternComponents(Group group) {
        text = new Text();
        tt = new TranslateTransition();
        scrollPane = new ScrollPane();

        scrollPane.setPrefWidth(DrawMap.rightMargin - 50);
        scrollPane.setPrefHeight(DrawMap.height - DrawComponents.upMargin - DrawComponents.downMargin - 100);
        scrollPane.setLayoutX(DrawMap.width - DrawMap.rightMargin + 20);
        scrollPane.setLayoutY(DrawMap.upMargin + 100);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);
        scrollPane.setContent(text);
        scrollPane.setStyle("-fx-background-color: transparent;");

        group.getChildren().add(scrollPane);
    }

    public void drawLyrics(Group group, int id, int type) {

        switch (type) {
            case 1:
                text.setText(first);
                tt.setDuration(javafx.util.Duration.seconds(200));
                tt.setToY(-4000);
                System.out.println("my heart will go on");
                break;
            case 2:
                text.setText(second);
                tt.setDuration(javafx.util.Duration.seconds(239));
                tt.setToY(-11500);
                System.out.println("firework");
                break;
            case 3:
                text.setText(third);
                tt.setDuration(javafx.util.Duration.seconds(223));
                tt.setToY(-11500);
                System.out.println("roar");
                break;
        }

        text.setWrappingWidth(150);
        text.setFill(Color.RED);
        text.setFont(Font.font("arial", FontWeight.BOLD, 30));

        tt.setNode(text);
        tt.setFromY(DrawMap.height - DrawMap.downMargin);
        tt.setInterpolator(Interpolator.LINEAR);

        if (id == 1) {
            tt.play();
        }

        if (id == 2) {
            tt.pause();
        }

        if (id == 3) {
            tt.stop();
        }

        if (id == 4) {
            tt.stop();
            tt.play();
        }

        if (id == 5) {
            tt.stop();
            tt.play();
        }

        tt.setOnFinished(event -> {
            if (type != 1)
                group.getChildren().remove(scrollPane);
        });
    }
}
