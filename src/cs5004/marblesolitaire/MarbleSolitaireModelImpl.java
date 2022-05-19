package cs5004.marblesolitaire;

/**
 * This class represents a MarbleSolitaireModelImpl. It has three fields: a 2d-array stores marbles,
 * an integer score that represents the number of marbles and an integer armThickness to represents
 * the arm thickness of the board
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {

  private Marbles[][] board;
  private int score;
  private final int armThickness;

  /**
   * Constructs a MarbleSolitaireModelImp object and initializes it with setting armThickness to 3
   * and set the board with empty slot in the center and armThickness
   */
  public MarbleSolitaireModelImpl() {
    this(3,3,3);
  }

  /**
   * Constructs a MarbleSolitaireModelImp object and initializes it with setting armThickness to 3
   * and set the board with empty slot in the take in sRow sCol and armThickness
   *
   * @param sRow the given row
   * @param sCol the given column
   * @throws IllegalArgumentException if sRow or sCol is not valid
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  /**
   * Constructs a MarbleSolitaireModelImp object and initializes it with setting armThickness to
   * take in argument and set the board with empty slot in the center and the take in armThickness
   *
   * @param armThickness the armThickness that takes in
   * @throws IllegalArgumentException if armThickness is not a positive odd number
   */
  public MarbleSolitaireModelImpl(int armThickness) throws IllegalArgumentException {
    this(armThickness, armThickness, armThickness);
  }

  /**
   * Constructs a MarbleSolitaireModelImp object and initializes it with setting armThickness to
   * take in argument and set the board with empty slot at the given place and the take in
   * armThickness
   *
   * @param armThickness the armThickness that takes in
   * @param row the given row
   * @param col the given column
   * @throws IllegalArgumentException if armThickness is not a positive odd number or the takes in
   * row or column is valid
   */
  public MarbleSolitaireModelImpl(int armThickness, int row, int col)
      throws IllegalArgumentException {
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("The arm thickness should be a positive odd number.");
    }
    if (!isValidPosition(armThickness, row, col)) {
      throw new IllegalArgumentException
          (String.format("Invalid empty cell position (%d,%d)", row, col));
    }
    this.armThickness = armThickness;
    setBoard(this.armThickness, row, col);
  }


  /**
   * Set the board to the starter situation
   *
   * @param n the arm thickness of the board
   * @param row the row of the marble
   * @param col the column of the marble
   */
  private void setBoard(int n, int row, int col) {
    int length = 2 * n + 1;
    int weight = 2 * n + 1;
    this.board = new Marbles[length][weight];
    score = 0;
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        // set take in cell to empty slot with "_"
        if (i == row && j == col) {
          this.board[i][j] = Marbles.EMPTY;
        }
        // set invalid cell to empty string
        else if (!isValidPosition(n, i, j)) {
          this.board[i][j] = Marbles.INVALID;
        }
        // set valid cell except empty slot to "o"
        else {
          this.board[i][j] = Marbles.MARBLE;
          score++;
        }
      }
    }
  }

  /**
   * Check if the position is valid
   *
   * @param n the arm thickness
   * @param row the given row
   * @param col the given column
   * @return true if the position is valid, otherwise false
   */
  private boolean isValidPosition(int n, int row, int col) {
    if (row < 0 || row >= 2 * n + 1 || col < 0 || col >= 2 * n + 1) {
      return false;
    } else if (row <= (n - 1) / 2 && col <= (n - 1) / 2) {
      return false;
    } else if (row <= (n - 1) / 2 && col >= (3 * n + 1) / 2) {
      return false;
    } else if (row >= (3 * n + 1) / 2 && col <= (n - 1) / 2) {
      return false;
    } else
      return row < (3 * n + 1) / 2 || col < (3 * n + 1) / 2;
  }

  /**
   * Get the arm thickness of the board
   *
   * @return the arm thickness of the board
   */
  private int getThickness() {
    return this.armThickness;
  }


  /**
   * Check if a marble has a valid move
   *
   * @param row the row of the given marble
   * @param col the column of the given marble
   * @return true if the given position have a valid move, otherwise false
   */
  private boolean hasValidMove(int row, int col) {
    int n = getThickness();
    if (isValidPosition(n, row, col) && board[row][col].equals(Marbles.MARBLE)) {
      if (isValidPosition(n, row - 2, col) && board[row - 1][col].equals(Marbles.MARBLE)
          && board[row - 2][col].equals(Marbles.EMPTY)) {
        return true;
      }
      if (isValidPosition(n, row + 2, col) && board[row + 1][col].equals(Marbles.MARBLE)
          && board[row + 2][col].equals(Marbles.EMPTY)) {
        return true;
      }
      if (isValidPosition(n, row, col - 2) && board[row][col - 1].equals(Marbles.MARBLE)
          && board[row][col - 2].equals(Marbles.EMPTY)) {
        return true;
      }
      return isValidPosition(n, row, col + 2) && board[row][col + 1].equals(Marbles.MARBLE)
          && board[row][col + 2].equals(Marbles.EMPTY);
    }
    return false;
  }

  /**
   * Check if the given from position is legal to move to the to position
   *
   * @param fromRow the given from row
   * @param fromCol the given from column
   * @param toRow the given to row
   * @param toCol the given to column
   * @return true if the from position is illegal to move to the to position
   */
  private boolean isIllegalMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (!board[toRow][toCol].equals(Marbles.EMPTY)) {
      return true;
    }
    if (fromRow == toRow) {
      if (fromCol - toCol == 2 && board[fromRow][fromCol - 1].equals(Marbles.MARBLE)) {
        return false;
      }
      return fromCol - toCol != -2 || !board[fromRow][fromCol + 1].equals(Marbles.MARBLE);
    } else if (fromCol == toCol) {
      if (fromRow - toRow == 2 && board[fromRow - 1][fromCol].equals(Marbles.MARBLE)) {
        return false;
      }
      return fromRow - toRow != -2 || !board[fromRow + 1][fromCol].equals(Marbles.MARBLE);
    }
    return true;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
      throws IllegalArgumentException {
    if (!isValidPosition(armThickness, fromRow, fromCol)) {
      throw new IllegalArgumentException("From position is not valid!");
    }
    if (!isValidPosition(armThickness, toRow, toCol)) {
      throw new IllegalArgumentException("To position is not valid!");
    }
    if (!hasValidMove(fromRow, fromCol)) {
      throw new IllegalArgumentException("From position does not have valid move!");
    }
    if (isIllegalMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("This is not a legal move!");
    }
    board[fromRow][fromCol] = Marbles.EMPTY;
    int middleRow = (fromRow + toRow) / 2;
    int middleCol = (fromCol + toCol) / 2;
    board[middleRow][middleCol] = Marbles.EMPTY;
    board[toRow][toCol] = Marbles.MARBLE;
    score--;
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (hasValidMove(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String getGameState() {
    StringBuilder gameState = new StringBuilder();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (i == board.length - 1 && j == board[i].length - 1) {
          gameState.append(board[i][j].toString());
        } else if (j == board[i].length - 1) {
          gameState.append(board[i][j].toString()).append("\n");
        } else {
          gameState.append(board[i][j].toString()).append(" ");
        }
      }
    }
    return gameState.toString();
  }

  @Override
  public int getScore() {
    return score;
  }



}
