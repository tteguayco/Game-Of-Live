package gameoflife;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame {
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;
	
	private static final String TITLE = "John Conway's Game of Life";
	
	private static final double INITIAL_SPEED = 100;
	
	private ButtonsPanel buttonsPanel;
	private Environment environment;
	private Timer timer;
	private double speed;
	
	public MainWindow() {
		buttonsPanel = new ButtonsPanel();
		environment = new Environment();
		speed = INITIAL_SPEED;
		
		timer = new Timer((int) speed, new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ae) {
		        environment.runGeneration();
		    }
		});
		
		setTitle(TITLE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setVisible(true);
	    
	    setLayout(new BorderLayout());
	    add(buttonsPanel, BorderLayout.NORTH);
	    add(environment, BorderLayout.CENTER);
	    
	    setListeners();
	}
	
	private void setButtonsListeners() {
		buttonsPanel.getResetButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				environment.reset();
			}
		});
		
		buttonsPanel.getStepButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				environment.runGeneration();
			}
		});
		
		buttonsPanel.getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonsPanel.changeStartButtonText();
				
				if (!timer.isRunning()) {
					timer.start();
				}
				
				else {
					timer.stop();
				}
			}
		});
	}
	
	private void setRadioButtonsListeners() {
		buttonsPanel.getAliveCell().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				environment.setPaintAliveCells(true);
			}
		});
		
		buttonsPanel.getDeadCell().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				environment.setPaintAliveCells(false);
			}
		});
	}
	
	private void setListeners() {
		setButtonsListeners();
		setRadioButtonsListeners();
	}
	
	public void setUpConfigurations() {
		environment.setUpEnviroment();
	}
	
	public static void main(String[] args) {
		
		try {
			// Set the OS' default LAF
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("Slider.paintValue", false);
			
			MainWindow mainWindow = new MainWindow();
			mainWindow.setVisible(true);
			mainWindow.setUpConfigurations();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
