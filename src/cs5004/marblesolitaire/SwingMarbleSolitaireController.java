package cs5004.marblesolitaire;

/**
 * This class represents a controller for the Marble Solitaire Game, which implements the Marble
 * Solitaire controller interface with two field m represents Marble Solitaire Model and v
 * represents Marble Solitaire view
 */
public class SwingMarbleSolitaireController implements MarbleSolitaireController {

  private MarbleSolitaireModel m;
  private MarbleSolitaireView v;

  /**
   * Construct a Marble Solitaire Game controller, and initialize it with given Marble Solitaire
   * model and view
   *
   * @param v the Marble Solitaire view
   * @param m the Marble Solitaire model
   */
  public SwingMarbleSolitaireController(MarbleSolitaireView v, MarbleSolitaireModel m) {
    this.m = m;
    this.v = v;
    v.addListenerOfClick(this);
  }

  @Override
  public void playGame() {
    v.startGame();
    v.setShowText("Start Score: 32");
    v.makeVisible();
  }

  @Override
  public void getClick(int fromPos, int toPos) {
    int fromRow = fromPos / 7;
    int fromCol = fromPos % 7;
    int toRow = toPos / 7;
    int toCol = toPos % 7;

    if (m.isGameOver()) {
      v.setShowText("Game is over!");
    } else if (!m.isGameOver()) {
      try {
        m.move(fromRow, fromCol, toRow, toCol);
        int middleRow = (fromRow + toRow) / 2;
        int middleCol = (fromCol + toCol) / 2;
        int middlePos = middleRow * 7 + middleCol;
        v.setCellsEmpty(fromPos);
        v.setCellsEmpty(middlePos);
        v.setCellsMarble(toPos);
        v.setShowText("Score:" + m.getScore());
        // if game over after move
        if (m.isGameOver()) {
          v.setShowText("Game is over! Final score is: " + m.getScore());
        }
      }
      catch (IllegalArgumentException ignored) {
      }



    }


  }
}
