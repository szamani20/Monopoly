package gui;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class EventHandle { // all of the event such as MouseEvent and KeyEvent are handled through the processEvent in this class
    private DiceRollListener diceRollListener;
    private SaveListener saveListener;
    private LoadListener loadListener;
    private AboutUsListener aboutUsListener;
    private SaveConnection saveConnection;
    private LoadConnection loadConnection;
    private StatisticsListener statisticsListener;
    private LyricsListener lyricsListener;
    private CloseListener closeListener;

    private URL url1 = getClass().getResource("/hkh.mp3"); //// hamin k has  ------> i love you
    private Media media1 = new Media(url1.toString());

    private URL url2 = getClass().getResource("/smb.mp3"); //// sia mese barf  -------> firework
    private Media media2 = new Media(url2.toString());

    private URL url3 = getClass().getResource("/sns.mp3"); //// kalameye oboor  --------> roar
    private Media media3 = new Media(url3.toString());

    private MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
    private MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
    private MediaPlayer mediaPlayer3 = new MediaPlayer(media3);

    public EventHandle() {
    }

    public void processEvent(MouseEvent e) { // this method is invoked whenever an event (Specially a MouseEvent) happened on the scene, this method handle the event based on the mouseButton and it's coordinates
        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= DrawComponents.leftMargin&&
                e.getX() <= DrawComponents.leftMargin + DrawComponents.aboutUsWidth&&
                e.getY() >= DrawComponents.upMargin&& e.getY() <= DrawComponents.upMargin + DrawComponents.aboutUsHeight)
            aboutUsOccur();

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= DrawComponents.leftMargin + DrawComponents.aboutUsWidth&&
                e.getX() <= DrawComponents.leftMargin + DrawComponents.aboutUsWidth + DrawComponents.saveWidth&&
                e.getY() >= DrawComponents.upMargin&& e.getY() <= DrawComponents.upMargin + DrawComponents.saveHeight)
            saveOccur();

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= DrawComponents.leftMargin + DrawComponents.aboutUsWidth + DrawComponents.saveWidth&&
                e.getX() <= DrawComponents.leftMargin + DrawComponents.aboutUsWidth + DrawComponents.saveWidth + DrawComponents.statisticsWidth&&
                e.getY() >= DrawComponents.upMargin&& e.getY() <= DrawComponents.upMargin + DrawComponents.statisticsHeight)
            statisticsOccur();

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= DrawComponents.width - DrawComponents.rightMargin - DrawComponents.closeWidth - DrawComponents.loadWidth&&
                e.getX() <= DrawComponents.width - DrawComponents.rightMargin - DrawComponents.closeWidth&& e.getY() >= DrawComponents.upMargin&&
                e.getY() <= DrawComponents.upMargin + DrawComponents.loadHeight)
            loadOccur();

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= DrawComponents.width - DrawComponents.rightMargin - DrawComponents.closeWidth&&
                e.getX() <= DrawComponents.width - DrawComponents.rightMargin&& e.getY() >= DrawComponents.upMargin&&
                e.getY() <= DrawComponents.upMargin + DrawComponents.closeHeight)
            closeOccur();

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= DrawComponents.width / 2 - DrawComponents.rollWidth / 2&&
                e.getX() <= DrawComponents.width / 2 + DrawComponents.rollWidth / 2&& e.getY() <= DrawComponents.height&&
                e.getY() >= DrawComponents.height - DrawComponents.rollHeight)
            rollOccur();

        int upMargin = 200;
        int margin = 50;

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= 0&& e.getX() <= DrawComponents.easternComponentsWidth&&
                e.getY() >= upMargin&& e.getY() <= upMargin+ DrawComponents.easternComponentsHeight)
            media(1); // play

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= 0&& e.getX() <= DrawComponents.easternComponentsWidth&&
                e.getY() >= upMargin + DrawComponents.easternComponentsHeight + margin&&
                e.getY() <= upMargin + DrawComponents.easternComponentsHeight + margin + DrawComponents.easternComponentsHeight)
            media(2); // pause

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= 0&& e.getX() <= DrawComponents.easternComponentsWidth&&
                e.getY() >= upMargin + 2 * DrawComponents.easternComponentsHeight + 2 * margin&&
                e.getY() <= upMargin + 2 * DrawComponents.easternComponentsHeight + 2 * margin + DrawComponents.easternComponentsHeight) {
            media(3); // stop
        }

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= 0&& e.getX() <= DrawComponents.easternComponentsWidth&&
                e.getY() >= upMargin + 3 * DrawComponents.easternComponentsHeight + 3 * margin&&
                e.getY() <= upMargin + 3 * DrawComponents.easternComponentsHeight + 3 * margin + DrawComponents.easternComponentsHeight)
            media(4); // next

        if (e.getButton() == MouseButton.PRIMARY&& e.getX() >= 0&& e.getX() <= DrawComponents.easternComponentsWidth&&
                e.getY() >= upMargin + 4 * DrawComponents.easternComponentsHeight + 4 * margin&&
                e.getY() <= upMargin + 4 * DrawComponents.easternComponentsHeight + 4 * margin + DrawComponents.easternComponentsHeight)
            media(5); // previous


    }

    private void media(int id) { // there are 5 keys to handle the mediaPlayer, this method handle them

        if (id == 1) {
            if (!mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)&&
                    !mediaPlayer2.getStatus().equals(MediaPlayer.Status.PLAYING)&&
                    !mediaPlayer3.getStatus().equals(MediaPlayer.Status.PLAYING)&&
                    !mediaPlayer1.getStatus().equals(MediaPlayer.Status.PAUSED)&&
                    !mediaPlayer2.getStatus().equals(MediaPlayer.Status.PAUSED)&&
                    !mediaPlayer3.getStatus().equals(MediaPlayer.Status.PAUSED)) {
                mediaPlayer1.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 1);
            }

            if (mediaPlayer1.getStatus().equals(MediaPlayer.Status.PAUSED)) {
                mediaPlayer1.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 1);
            }

            if (mediaPlayer2.getStatus().equals(MediaPlayer.Status.PAUSED)) {
                mediaPlayer2.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 2);
            }

            if (mediaPlayer3.getStatus().equals(MediaPlayer.Status.PAUSED)) {
                mediaPlayer3.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 3);
            }
        }

        if (id == 2) {
            if (mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer1.pause();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 1);
            }

            if (mediaPlayer2.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer2.pause();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 2);
            }

            if (mediaPlayer3.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer3.pause();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 3);
            }
        }

        if (id == 3) {
            if (mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer1.stop();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 1);
            }

            if (mediaPlayer2.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer2.stop();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 2);
            }

            if (mediaPlayer3.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer3.stop();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 3);
            }
        }

        if (id == 4) {
            if (mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer1.stop();
                mediaPlayer2.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 2);
            }

            if (mediaPlayer2.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer2.stop();
                mediaPlayer3.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 3);
            }

            if (mediaPlayer3.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer3.stop();
                mediaPlayer1.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 1);
            }
        }

        if (id == 5) {
            if (mediaPlayer1.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer1.stop();
                mediaPlayer3.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 3);
            }

            if (mediaPlayer2.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer2.stop();
                mediaPlayer1.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 1);
            }

            if (mediaPlayer3.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                mediaPlayer3.stop();
                mediaPlayer2.play();

                if (lyricsListener != null)
                    lyricsListener.LyricsOccur(id, 2);
            }
        }

    }

    private void saveOccur() {
        GameEvent event = null;

        if (saveConnection != null)
            event = saveConnection.connection();

        if (saveListener != null)
            saveListener.saveEventOccur(event);
    }

    private void loadOccur() {
        GameEvent event = null;

        if (loadListener != null)
            event = loadListener.loadFromFile();

        ///////////// refactoring game state using information gained at previous step

        if (loadConnection != null)
            loadConnection.connection(event);

    }

    private void statisticsOccur() {
        if (statisticsListener != null)
            statisticsListener.statisticsOccur();
    }

    private void aboutUsOccur() {
        if (aboutUsListener != null)
            aboutUsListener.aboutUsOccur();
    }

    private void closeOccur() {
        if (closeListener != null)
            closeListener.closeOccur();
    }

    private void rollOccur() {
        if (diceRollListener != null)
            diceRollListener.diceRollOccur();
    }

    public void setDiceRollListener(DiceRollListener diceRollListener) {
        this.diceRollListener = diceRollListener;
    }

    public void setSaveListener(SaveListener saveListener) {
        this.saveListener = saveListener;
    }

    public void setLoadListener(LoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public void setAboutUsListener(AboutUsListener aboutUsListener) {
        this.aboutUsListener = aboutUsListener;
    }

    public void setSaveConnection(SaveConnection saveConnection) {
        this.saveConnection = saveConnection;
    }

    public void setLoadConnection(LoadConnection loadConnection) {
        this.loadConnection = loadConnection;
    }

    public void setStatisticsListener(StatisticsListener statisticsListener) {
        this.statisticsListener = statisticsListener;
    }

    public void setLyricsListener(LyricsListener lyricsListener) {
        this.lyricsListener = lyricsListener;
    }

    public void setCloseListener(CloseListener closeListener) {
        this.closeListener = closeListener;
    }

}
