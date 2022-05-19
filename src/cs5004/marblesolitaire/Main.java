package cs5004.marblesolitaire;

/**
 * Run a Marble Solitaire game interactively on the console.
 */
public class Main {
  /**
   * Run a Marble Solitaire game interactively on the console.
   * 
   * @param args not used
   */
  public static void main(String[] args) {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    MarbleSolitaireView v = new SwingMarbleSolitaireView("Marble Solitaire");
    MarbleSolitaireController c = new SwingMarbleSolitaireController(v, m);
    c.playGame();
  }
}
