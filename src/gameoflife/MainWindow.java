package gameoflife;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainWindow extends JFrame {
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;
	
	private static final String TITLE = "John Conway's Game of Life";
	
	private ButtonsPanel buttonsPanel;
	private Environment environment;
	
	public MainWindow()
	{
		buttonsPanel = new ButtonsPanel();
		
		setTitle(TITLE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setVisible(true);
	    
	    setLayout(new BorderLayout());
	    add(buttonsPanel, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}
