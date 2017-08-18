package gameoflife;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;

public class ButtonsPanel extends JPanel {

	private JButton startButton;
	private JButton stepButton;
	private JButton resetButton;
	private JSlider speedSlider;
	
	private JRadioButton aliveCell;
	private JRadioButton deadCell;
	
	public ButtonsPanel() {
		startButton = new JButton("START");
		stepButton = new JButton("STEP");
		resetButton = new JButton("RESET");
		speedSlider = new JSlider();
		aliveCell = new JRadioButton("Alive");
		deadCell = new JRadioButton("Dead");
		
		ButtonGroup group = new ButtonGroup();
		group.add(aliveCell);
		group.add(deadCell);
		
		aliveCell.setSelected(true);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(startButton);
		add(stepButton);
		add(resetButton);
		add(new JSeparator());
		add(new JSeparator());
		add(new JSeparator());
		add(new JLabel("Speed: "));
		add(speedSlider);
		add(new JSeparator());
		add(new JSeparator());
		add(new JSeparator());
		add(new JLabel("Draw cell: "));
		add(aliveCell);
		add(deadCell);
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getStepButton() {
		return stepButton;
	}

	public void setStepButton(JButton stepButton) {
		this.stepButton = stepButton;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}

	public JSlider getSpeedSlider() {
		return speedSlider;
	}

	public void setSpeedSlider(JSlider speedSlider) {
		this.speedSlider = speedSlider;
	}

	public JRadioButton getAliveCell() {
		return aliveCell;
	}

	public void setAliveCell(JRadioButton aliveCell) {
		this.aliveCell = aliveCell;
	}

	public JRadioButton getDeadCell() {
		return deadCell;
	}

	public void setDeadCell(JRadioButton deadCell) {
		this.deadCell = deadCell;
	}
}
