package cs5004.marblesolitaire;

/**
 * This is a GUI View Interface for MarbleSolitaire
 */
public interface MarbleSolitaireView {

  /**
   * Show the start of the new Marble Solitaire game
   */
  void startGame();

  /**
   * Set the given position of cell empty
   *
   * @param pos the position of the cell
   */
  void setCellsEmpty(int pos);

  /**
   * Set the given position of cell marble
   *
   * @param pos the position of the cell
   */
  void setCellsMarble(int pos);

  /**
   * Set the controller as the listener
   * @param l the listener we need in our game
   */
  void addListenerOfClick(MarbleSolitaireController l);

  /**
   * Display game information on the view
   * @param info the information will be displayed
   */
  void setShowText(String info);

  /**
   * Make all the view visible
   */
  void makeVisible();
}
