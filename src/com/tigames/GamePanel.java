package com.tigames;

import com.tigames.states.GameStateManager;
import com.tigames.util.KeyHandler;
import com.tigames.util.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{
	private static final long serialVersionUID = -8526571498853989792L;
	public static int width, height;
	public static int oldFrameCount;
	private Thread tOne;
	private BufferedImage img;
	private Graphics2D g;
	private boolean running = false;
	private MouseHandler mouse;
	private KeyHandler key;
	private GameStateManager gsm;
	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
	}
	@Override
	public void addNotify() {
		super.addNotify();
		if (tOne == null) {
			tOne = new Thread(this, "GameThread");
			tOne.start();
		}
	}
	public void init(){
		running = true;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();
		mouse = new MouseHandler(this);
		key = new KeyHandler(this);
		gsm = new GameStateManager();
	}
	@Override
	public void run() {
		init();
		final double GAME_HERTZ = 60;
		final double TBU = 1000000000 / GAME_HERTZ; // Time before update
		final int MUBR = 5; // Must update before render
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime;
		final double TARGET_FPS = 60;
		final double TTBR = 1000000000 / TARGET_FPS; // Total time before render
		int frameCount = 0;
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		oldFrameCount = 0;
		while(running){
			double now = System.nanoTime();
			int updateCount = 0;
			while((now - lastUpdateTime) > TBU && (updateCount < MUBR)){
				update();
				input(mouse, key);
				lastUpdateTime += TBU;
				updateCount++;
			}
			if(now - lastUpdateTime > TBU)
				lastUpdateTime = now - TBU;
			input(mouse, key);
			render();
			draw();
			lastRenderTime = now;
			frameCount++;
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if(thisSecond > lastSecondTime){
				if(frameCount != oldFrameCount){
					System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
					oldFrameCount = frameCount;
				}
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			while(now - lastRenderTime < TTBR && now - lastUpdateTime < TBU){
				Thread.yield();
				try{
					Thread.sleep(1);
				}catch (Exception e){
					System.out.println("Error yielding thread :(");
				}
				now = System.nanoTime();
			}
		}
	}
	public void update(){ gsm.update(); }
	public void input(MouseHandler mouse, KeyHandler key){
		gsm.input(mouse, key);
	}
	public void render(){
		if(g!=null){
			g.setColor(new Color(33, 30, 39));
			g.fillRect(0, 0, width, height);
			gsm.render(g);
		}
	}
	public void draw(){
		Graphics g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(img, 0, 0, width, height, null);
		g2.dispose();
	}
}
