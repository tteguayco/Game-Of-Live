package gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Environment extends JPanel {
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color ALIVE_CELL_COLOR = Color.BLACK;
	private static final Color DEAD_CELL_COLOR = Color.WHITE;
	
	private static final double ENVIRONMENT_WIDTH = 80;
	private static final double ENVIRONMENT_HEIGHT = 50;
	
	private boolean paintAliveCells;
	
	private double cellWidth;
	private double cellHeight;
	
	private CellState[][] cells;
	
	public Environment() {
		paintAliveCells = true;
		addMouseListener(new CellsClickListener());
		addMouseMotionListener(new CellsDragListener());
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
		if (cells != null) {
			
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
	}
	
	public void reset() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = CellState.DEAD;
			}
		}
		
		repaint();
	}
	
	private int numberOfNeighborsForCell(int aRow, int aCol) {
		int numberOfNeighbors = 0;
		
		// Up
		if (aRow > 0 && cells[aRow - 1][aCol] == CellState.ALIVE) {
			numberOfNeighbors++;
		}
		
		// Down
		if (aRow < ENVIRONMENT_HEIGHT - 1 && cells[aRow + 1][aCol] == CellState.ALIVE) {
			numberOfNeighbors++;
		}
		
		// Left
		if (aCol > 0 && cells[aRow][aCol - 1] == CellState.ALIVE) {
			numberOfNeighbors++;
		}
		
		// Right
		if (aCol < ENVIRONMENT_WIDTH - 1 && cells[aRow][aCol + 1] == CellState.ALIVE) {
			numberOfNeighbors++;
		}
		
		// Up left
		if (aRow > 0 && aCol > 0 && cells[aRow - 1][aCol - 1] == CellState.ALIVE) {
			numberOfNeighbors++;
		}
		
		// Up right
		if (aRow > 0 && aCol < ENVIRONMENT_WIDTH - 1 && cells[aRow - 1][aCol + 1] == CellState.ALIVE) {
			numberOfNeighbors++;
		}
		
		// Down left
		if (aRow < ENVIRONMENT_HEIGHT - 1 && aCol > 0 && cells[aRow + 1][aCol - 1] == CellState.ALIVE) {
			numberOfNeighbors++;
		}
		
		// Down right
		if (aRow < ENVIRONMENT_HEIGHT - 1 && aCol < ENVIRONMENT_WIDTH - 1 &&
				cells[aRow + 1][aCol + 1] == CellState.ALIVE) {
			numberOfNeighbors++;
		}
		
		return numberOfNeighbors;
	}
	
	private CellState[][] copyEnvironment(CellState[][] environmentToCopy) {
		CellState[][] auxState = 
				new CellState[environmentToCopy.length][environmentToCopy[0].length];
		
		for (int i = 0; i < environmentToCopy.length; i++) {
			for (int j = 0; j < environmentToCopy[i].length; j++) {
				auxState[i][j] = environmentToCopy[i][j];
			}
		}
		
		return auxState;
	}
	
	public void runGeneration() {
		int numberOfNeighbors = 0;
		CellState[][] newState;
		
		// Copy the current state of the environment
		newState = copyEnvironment(cells);
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				numberOfNeighbors = numberOfNeighborsForCell(i, j);
				
				// Underpopulation
				if (cells[i][j] == CellState.ALIVE && numberOfNeighbors < 2) {
					newState[i][j] = CellState.DEAD;
				}
				
				// Lives the next generation
				else if (cells[i][j] == CellState.ALIVE && 
						(numberOfNeighbors == 2 || numberOfNeighbors == 3)) {
					newState[i][j] = CellState.ALIVE;
				}
				
				// Overpopulation
				else if (cells[i][j] == CellState.ALIVE && numberOfNeighbors > 3) {
					newState[i][j] = CellState.DEAD;
				}
				
				// Reproduction
				else if (cells[i][j] == CellState.DEAD && numberOfNeighbors == 3) {
					newState[i][j] = CellState.ALIVE;
				}
			}
		}
		
		cells = copyEnvironment(newState);
		repaint();
	}
	
	private void changeCellState(int clickX, int clickY) {
		int cellRow = (int) (clickY / cellHeight);
		int cellCol = (int) (clickX / cellWidth);
		
		if (paintAliveCells) {
			cells[cellRow][cellCol] = CellState.ALIVE;
		}
		
		else {
			cells[cellRow][cellCol] = CellState.DEAD;
		}
	}
	
	private class CellsClickListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			changeCellState(e.getX(), e.getY());
			repaint();
		}
	}
	
	private class CellsDragListener extends MouseAdapter {
		
		@Override
		public void mouseDragged(MouseEvent e) {
			changeCellState(e.getX(), e.getY());
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

	public boolean isPaintAliveCells() {
		return paintAliveCells;
	}

	public void setPaintAliveCells(boolean paintAliveCells) {
		this.paintAliveCells = paintAliveCells;
	}
}
