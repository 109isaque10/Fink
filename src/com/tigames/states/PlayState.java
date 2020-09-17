package com.tigames.states;

import com.tigames.GamePanel;
import com.tigames.entity.Player;
import com.tigames.graphics.Font;
import com.tigames.graphics.Sprite;
import com.tigames.tiles.TileManager;
import com.tigames.util.KeyHandler;
import com.tigames.util.MouseHandler;
import com.tigames.util.Vector2f;

import java.awt.*;

public class PlayState extends GameState{
    private final Font font;
    private final Player ply;
    private final TileManager tm;
    public static Vector2f map;
    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
        tm = new TileManager("tile/level0.xml");
        font = new Font("font/NewFont.png", 10, 10);
        ply = new Player(new Sprite("entity/Fink.png"), new Vector2f(0 + (GamePanel.width / 2) - 16, 0 + (GamePanel.height / 2) - 16), 64);
    }
    @Override
    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        ply.update();
    }
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        ply.input(mouse, key);
    }
    @Override
    public void render(Graphics2D g) {
        tm.render(g);
        Sprite.drawArray(g, font, GamePanel.oldFrameCount + " FPS", new Vector2f(GamePanel.width - 192, 10), 30, 30, 26, 0);
        ply.render(g);
    }
}
