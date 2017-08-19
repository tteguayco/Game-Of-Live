package gameoflife;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private static final String TITLE = "John Conway's Game of Life";
	
	private static final int INITIAL_SPEED = 100;
	private static final double SPEED_FACTOR = 100;
	
	private ButtonsPanel buttonsPanel;
	private Environment environment;
	private Timer timer;
	
	public MainWindow() {
		buttonsPanel = new ButtonsPanel();
		environment = new Environment();
		
		timer = new Timer(INITIAL_SPEED, new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent ae) {
		        runGeneration();
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
				stopExecution();
				buttonsPanel.resetGenerationCount();
			}
		});
		
		buttonsPanel.getStepButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				runGeneration();
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
	
	private void setSliderListener() {
		buttonsPanel.getSpeedSlider().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				timer.setDelay(
						(int) (buttonsPanel.getSpeedSlider().getValue() * SPEED_FACTOR));
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
		setSliderListener();
		setRadioButtonsListeners();
	}
	
	private void stopExecution() {
		if (timer.isRunning()) {
			buttonsPanel.changeStartButtonText();
			timer.stop();
		}
	}
	
	private void runGeneration() {
		environment.runGeneration();
		buttonsPanel.increaseGenerationCount();
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
