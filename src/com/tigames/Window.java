package com.tigames;

import javax.swing.JFrame;

public class Window extends JFrame{
	private static final long serialVersionUID = -5661979980346817210L;
	public Window() {
		//Properties
		setTitle("Fink");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Obrigatory
		setContentPane(new GamePanel(1280,720));
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
