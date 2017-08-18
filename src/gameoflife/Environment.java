package gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Environment extends JPanel {
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color ALIVE_CELL_COLOR = Color.BLUE;
	private static final Color DEAD_CELL_COLOR = Color.WHITE;
	
	private static final double ENVIRONMENT_WIDTH = 80;
	private static final double ENVIRONMENT_HEIGHT = 50;
	
	private double cellWidth;
	private double cellHeight;
	
	private CellState[][] cells;
	
	public Environment() {
		addMouseListener(new CellsListener());
	}
	
	public void setUpEnviroment() {
		calculateCellDimensions();
		initializeCells();
		repaint();
	}
	
	private void calculateCellDimensions() {
		cellWidth = getWidth() / ENVIRONMENT_WIDTH;
		cellHeight = getHeight() / ENVIRONMENT_HEIGHT;
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
		
		// Alive cells
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j] == CellState.ALIVE) {
					g.setColor(ALIVE_CELL_COLOR);
				} else {
					g.setColor(DEAD_CELL_COLOR);
				}
				
				g.fillRect((int) (j * cellWidth), 
						(int) (i * cellHeight), 
						(int) (cellWidth) + 1, 
						(int) (cellHeight) + 1);
			}
		}
		
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
	
	private void changeCellState(int i, int j) {
		if (cells[i][j] == CellState.ALIVE) {
			cells[i][j] = CellState.DEAD;
		} 
		
		else if (cells[i][j] == CellState.DEAD) {
			cells[i][j] = CellState.ALIVE;	
		}
	}
	
	private class CellsListener extends MouseAdapter {
		@Override
	    public void mouseClicked(MouseEvent e) {
			int cellRow = (int) (e.getY() / cellHeight);
			int cellCol = (int) (e.getX() / cellWidth);
			
			changeCellState(cellRow, cellCol);
			repaint();
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
