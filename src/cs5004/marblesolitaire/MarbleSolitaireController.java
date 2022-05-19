package cs5004.marblesolitaire;

/**
 * This is a controller interface for MarbleSolitaire
 */
public interface MarbleSolitaireController {

  /**
   * Start play the Marble Solitaire game
   */
  void playGame();

  /**
   * Move marbles from the fromPos to the toPos
   *
   * @param fromPos the source position of the marble
   * @param toPos the destination position of the marble
   */
  void getClick(int fromPos, int toPos);
}
