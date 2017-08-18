package gameoflife;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ButtonsPanel extends JPanel {

	private JButton startButton;
	private JButton stepButton;
	private JButton resetButton;
	private JSlider speedSlider;
	
	public ButtonsPanel()
	{
		startButton = new JButton("START");
		stepButton = new JButton("STEP");
		resetButton = new JButton("RESET");
		speedSlider = new JSlider();
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(startButton);
		add(stepButton);
		add(resetButton);
		add(speedSlider);
	}
}
