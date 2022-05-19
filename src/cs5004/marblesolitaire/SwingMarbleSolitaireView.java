package cs5004.marblesolitaire;

import javax.swing.*;
import java.awt.*;

/**
 * This is a GUI view for the Marble Solitaire
 */
public class SwingMarbleSolitaireView extends JFrame implements MarbleSolitaireView {

  private JLabel showText;
  private JPanel showTable;
  private JButton[] cells;
  private int currPos;
  private int fromPos;
  private int toPos;


  /**
   * Set the initial parameter for a given position of cell
   *
   * @param pos the position of the cell
   */
  private void initializeCells(int pos) {
    cells[pos] = new JButton(" ");
    cells[pos].setPreferredSize(new Dimension(50,50));
    cells[pos].setHorizontalAlignment(JLabel.CENTER);
    cells[pos].setBorder(BorderFactory.createLineBorder(Color.WHITE));
  }

  /**
   * Add 49 cells with different starting items in cell to the panel with the gird layout
   * @return the board panel
   */
  private JPanel addCells() {
    // build new object
    JPanel panel = new JPanel(new GridLayout(7, 7));
    panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    cells = new JButton[49];
    for(int i = 0; i < cells.length; i++) {
      initializeCells(i);
      panel.add(cells[i]);
    }
    return panel;
  }

  /**
   * Initialize the panel
   */
  private void initializePanel() {
    this.showTable = new JPanel();
    this.showTable.setLayout(new FlowLayout());
    // add 9 cells to the blank table
    this.showTable.add(addCells());
    this.showTable.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(showTable);
  }

  /**
   * Initialize the label
   */
  private void initializeLabel() {
    this.showText = new JLabel();
    // set size of text
    this.showText.setFont(new Font("Arial",Font.PLAIN, 20));
    this.showText.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(showText);
  }

  /**
   * Construct a Marble Solitaire GUI View
   *
   * @param title the title of the window
   */
  public SwingMarbleSolitaireView(String title) {
    // set the title
    super(title);

    // set location and size of the board
    this.setLocation(500, 100);
    this.setSize(560, 560);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    // set the label on the top
    initializeLabel();
    initializePanel();

    currPos = 0;
    fromPos = 0;
    toPos = 0;

    makeVisible();
  }


  /**
   * Check if the position is valid
   *
   * @param row the given row
   * @param col the given column
   * @return true if the position is valid, otherwise false
   */
  private boolean ifValidPosition(int row, int col) {
    if(row < 0 || row >= 7 || col < 0 || col >= 7) {
      return false;
    }
    else if(row <= 1 && col <= 1) {
      return false;
    }
    else if(row <= 1 && col >= 5) {
      return false;
    }
    else if(row >= 5 && col <= 1) {
      return false;
    }
    else
      return row < 5 || col < 5;
  }


  @Override
  public void startGame() {
    for(int i = 0; i <cells.length;i++){
      int row = i / 7;
      int col = i % 7;
      if(ifValidPosition(row, col)) {
        cells[i].setFont(new Font("Arial", Font.PLAIN, 30));
        cells[i].setForeground(Color.PINK);
        if(row == 3 && col == 3) {
          cells[i].setText(" ");
        }
        else {
          cells[i].setText("O");
        }
      }
      else {
        cells[i].setFont(new Font("Arial", Font.PLAIN, 40));
        cells[i].setForeground(Color.BLACK);
        cells[i].setText("X");
      }
    }
  }

  @Override
  public void setCellsEmpty(int pos) {
    cells[pos].setText(" ");
  }

  @Override
  public void setCellsMarble(int pos) {
    cells[pos].setText("O");
  }


  @Override
  public void addListenerOfClick(MarbleSolitaireController l) {
    for(int i =0; i < 49 ; i++){
      cells[i].addActionListener(e -> {
        int j = 0;
        while(j < cells.length) {
          if (e.getSource() == cells[j]) {
            if (currPos == 0) {
              fromPos = j;
              currPos++;
            } else if (currPos == 1) {
              toPos = j;
              currPos = 0;
              l.getClick(fromPos, toPos);
            } else {
              break;
            }
          }
          j++;
        }
      });
    }
  }

  @Override
  public void setShowText(String s) {
    showText.setText(s);
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }
}
