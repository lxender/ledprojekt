package app.ledprojekt.screens;

import app.ledprojekt.Main;
import app.ledprojekt.Player;
import app.ledprojekt.layers.Drawable;
import app.ledprojekt.typography.Lettering;
import ledControl.BoardController;
import ledControl.gui.KeyBuffer;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class StartScreen implements Screen {
    private int x = 0;

    private Player[] playersOnDisplay = Main.availablePlayers;
    private int selectedBox = this.playersOnDisplay.length / 2;

    private KeyBuffer buffer = null;
    private Box[] boxes = new Box[0];

    private int currentMessage = 0;
    private ArrayList<Lettering> currentLettering = new ArrayList<>();
    private ArrayList<Lettering> message_1 = new ArrayList<>();
    private ArrayList<Lettering> message_2 = new ArrayList<>();

    private ScreenStates state = ScreenStates.START_MESSAGE;

    public void init(BoardController controller) {
        this.buffer = controller.getKeyBuffer();

        this.initMessage();

        this.initBoxes(controller);
    }
    private void initBoxes(BoardController controller) {
        this.boxes = new Box[this.playersOnDisplay.length];
        for (int i = 0; i < this.boxes.length; i++) {

            int playerWidth = this.playersOnDisplay[i].getModel().getWidthWithZeroRows();
            int playerHeight = this.playersOnDisplay[i].getModel().getHeight();

            if (this.playersOnDisplay[i].getWeapon() != null) {
                playerWidth += this.playersOnDisplay[i].getWeapon().getModel().getWidthWithZeroRows();
            }

            this.boxes[i] = new Box(this.x, (controller.getHeight() / 2) - ((playerHeight + 2) / 2), playerWidth + 2, playerHeight + 2, this.playersOnDisplay[i]);
        }
        this.boxes[this.selectedBox].isSelected = true;
    }
    private void initMessage() {
        int[] letteringColor = new int[]{0, 0, 127, 1};
        int y = 0;
        this.message_1.add(new Lettering("s1", 0, y, letteringColor));
        this.message_1.add(new Lettering("select", 0, y += this.message_1.get(0).getHeight() + 1, letteringColor));
        this.message_1.add(new Lettering("a player", 0, y += this.message_1.get(1).getHeight() + 1, letteringColor));

        this.message_2.addAll(this.message_1);
        this.message_2.remove(0);
        this.message_2.add(0, new Lettering("s2", 0, 0, letteringColor));
    }

    public void update(double delta) {
        if (this.state == ScreenStates.START_MESSAGE) {
            if (this.currentMessage == 0) {
                this.currentLettering = this.message_1;
            } else if(this.currentMessage == 1) {
                this.currentLettering = this.message_2;
            }

            KeyEvent event = this.buffer.pop();
            if (event != null && event.getID() == KeyEvent.KEY_RELEASED) {
                this.currentMessage++;
                this.state = ScreenStates.SELECTION;
            }
        } else if (this.state == ScreenStates.SELECTION) {
            this.updateBoxes();
        }
    }
    public void updateBoxes() {
        this.boxes[this.selectedBox].isSelected = false;

        KeyEvent event = this.buffer.pop();
        if (event != null) {
            if (event.getID() == KeyEvent.KEY_RELEASED) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                    try {
                        Main.players.add(this.playersOnDisplay[this.selectedBox].getClass().getDeclaredConstructor(int.class, int.class).newInstance(0 ,0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (this.currentMessage == 0) {
                        this.state = ScreenStates.START_MESSAGE;
                    } else if(this.currentMessage == 1) {
                        this.state = ScreenStates.START_MESSAGE;
                    } else if (this.currentMessage == 2) {
                        System.out.println(Arrays.toString(Main.players.toArray()));
                        Main.state = Main.States.GAME;
                    }

                } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    this.selectedBox--;
                } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    this.selectedBox++;
                }
            }
        }

        if (this.selectedBox < 0) {
            this.selectedBox = this.boxes.length - 1;
        } else if (this.selectedBox > this.boxes.length - 1) {
            this.selectedBox = 0;
        }

        this.boxes[this.selectedBox].isSelected = true;
    }

    private void drawBoxes(BoardController controller) {
        int summedWidth = 0;

        for (int i = 0; i < this.boxes.length; i++) {
            Box b = this.boxes[i];
            b.x = this.x + summedWidth;
            summedWidth += b.width + 1;

            b.draw(controller);
        }
    }
    public void draw(BoardController controller) {
        controller.resetColors();

        if (this.state == ScreenStates.START_MESSAGE) {
            if (!this.currentLettering.isEmpty()) {
                for (Lettering l : this.currentLettering) {
                    l.draw(controller);
                }
            }
        }

        if (this.state == ScreenStates.SELECTION) {
            if (this.boxes[this.selectedBox].x + this.boxes[this.selectedBox].width > controller.getWidth() / 2 + this.boxes[this.selectedBox].width / 2) {
                this.x--;
            } else if(this.boxes[this.selectedBox].x + this.boxes[this.selectedBox].width < controller.getWidth() / 2 + this.boxes[this.selectedBox].width / 2) {
                this.x++;
            }
            this.drawBoxes(controller);
        }

        controller.updateBoard();
    }



    private class Box implements Drawable {
        private int x;
        private int y;

        private int width;
        private int height;

        private Player player;
        private int initialPlayerX;
        private int initialPlayerY;

        private boolean isSelected = false;

        Box(int x, int y, int width, int height, Player player) {
            this.x = x;
            this.y = y;

            this.width = width;
            this.height = height;

            this.player = player;
            this.initialPlayerX = player.getX();
            this.initialPlayerY = player.getY();

            this.player.removeTrait("Go");
            this.player.removeTrait("Turn");
            this.player.removeTrait("Jump");
            this.player.disableHealthbar();
        }

        public void draw(BoardController controller) {
            int[] color = (this.isSelected) ? new int[]{127, 0, 0} : new int[]{127, 127, 127};

            for (int y = 0; y < this.height; y++) {
                for (int x = 0; x < this.width; x++) {
                    controller.setColor(this.x + x, this.y + y, color);

                    if (y < this.height - 1 && y > 0 &&
                            x < this.width - 1 && x > 0) {
                        controller.setColor(this.x + x, this.y + y, new int[]{0, 0, 0});
                    }
                }
            }

            this.player.setX(this.x + this.initialPlayerX);
            this.player.setY(this.y + this.initialPlayerY);
            if (this.player.getWeapon() != null) {
                this.player.getWeapon().update();
            }
            this.player.draw(controller);
        }
    }

    private enum ScreenStates {
        START_MESSAGE,
        SELECTION
    }
}
