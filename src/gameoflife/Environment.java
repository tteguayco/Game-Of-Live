package gameoflife;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Environment extends JPanel {
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	
	private static final double ENVIRONMENT_WIDTH = 80;
	private static final double ENVIRONMENT_HEIGHT = 50;
	
	private double cellWidth;
	private double cellHeight;
	
	private CellState[][] cells;
	
	public Environment() {
		
	}
	
	public void setUpEnviroment() {
		calculateCellDimensions();
		initializeCells();
		repaint();
	}
	
	private void calculateCellDimensions() {
		cellWidth = getWidth() / ENVIRONMENT_WIDTH;
		cellHeight = getHeight() / ENVIRONMENT_HEIGHT;
		
		//System.out.println(cellWidth);
		//System.out.println(cellHeight);
	}
	
	private void initializeCells() {
		cells = new CellState[(int) ENVIRONMENT_HEIGHT][(int) ENVIRONMENT_WIDTH];
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = CellState.DEAD;
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		
		// Horizontal lines
		for (int i = 0; i < ENVIRONMENT_HEIGHT; i++) {
			g.drawLine(0, 
					(int) (i * cellHeight), 
					(int) getWidth(), 
					(int) (i * cellHeight));
		}
		
		// Vertical lines
		for (int i = 0; i < ENVIRONMENT_WIDTH; i++) {
			g.drawLine((int) (i * cellWidth), 
					0, 
					(int) (i * cellWidth), 
					(int) (ENVIRONMENT_WIDTH * cellHeight));
		}
	}

	public double getCellWidth() {
		return cellWidth;
	}

	public void setCellWidth(double cellWidth) {
		this.cellWidth = cellWidth;
	}

	public double getCellHeight() {
		return cellHeight;
	}

	public void setCellHeight(double cellHeight) {
		this.cellHeight = cellHeight;
	}
}
