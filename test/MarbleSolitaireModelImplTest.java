import static org.junit.Assert.*;

import cs5004.marblesolitaire.MarbleSolitaireModel;
import cs5004.marblesolitaire.MarbleSolitaireModelImpl;
import org.junit.Test;
import org.junit.Before;

/**
 * A JUnit test class for the MarbleSolitaireModelImpl class
 */
public class MarbleSolitaireModelImplTest {
  private MarbleSolitaireModel m1;
  private MarbleSolitaireModel m2;
  private MarbleSolitaireModel m3;
  private MarbleSolitaireModel m4;

  /** Setting up the objects we need for our test. */
  @Before
  public void setUp() throws Exception {
    m1 = new MarbleSolitaireModelImpl();
    m2 = new MarbleSolitaireModelImpl(2,3);
    m3 = new MarbleSolitaireModelImpl(5);
    m4 = new MarbleSolitaireModelImpl(7,5,5);
  }

  /**
   * Test the if exception being thrown when illegal arguments occur in the constructor
   * takes illegal row or column or both
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInValidRowConstructor() {
    new MarbleSolitaireModelImpl(-1,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidColumnConstructor() {
    new MarbleSolitaireModelImpl(3,-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidPositionConstructor() {
    new MarbleSolitaireModelImpl(0,0);
  }

  /**
   * Test the if exception being thrown when illegal arguments occur in the constructor
   * takes illegal arm thickness
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeThickness() {
    new MarbleSolitaireModelImpl(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvenThickness() {
    new MarbleSolitaireModelImpl(2);
  }

  /**
   * Test the if exception being thrown when illegal arguments occur in the constructor
   * takes illegal row or column or arm thickness
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeThicknessWithRowAndCol() {
    new MarbleSolitaireModelImpl(-2,3,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvenThicknessWithRowAndCol() {
    new MarbleSolitaireModelImpl(2,2,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRowWithThreeArguments() {
    new MarbleSolitaireModelImpl(3,7,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColumnWithThreeArguments() {
    new MarbleSolitaireModelImpl(3,3,7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPositionWithThreeArguments() {
    new MarbleSolitaireModelImpl(3,0,0);
  }


  /** Test the method of move for non-argument constructor */
  @Test
  public void testMoveWithFirstConstructor() {
    m1.move(3,1,3,3);
    assertEquals(m1.getGameState(),
        "    O O O    \n"
            + "    O O O    \n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O    \n"
            + "    O O O    ");
  }

  /** Test the method of move for row and col arguments taken constructor */
  @Test
  public void testMoveWithSecondConstructor() {
    m2.move(2,1,2,3);
    assertEquals(m2.getGameState(),
        "    O O O    \n"
            + "    O O O    \n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O    \n"
            + "    O O O    ");
  }

  /** Test the method of gameState for arm thickness argument taken constructor */
  @Test
  public void testMoveWithThirdConstructor() {
    m3.move(3,5,5,5);
    assertEquals(m3.getGameState(),
        "      O O O O O      \n"
            + "      O O O O O      \n"
            + "      O O O O O      \n"
            + "O O O O O _ O O O O O\n"
            + "O O O O O _ O O O O O\n"
            + "O O O O O O O O O O O\n"
            + "O O O O O O O O O O O\n"
            + "O O O O O O O O O O O\n"
            + "      O O O O O      \n"
            + "      O O O O O      \n"
            + "      O O O O O      ");
  }

  /** Test the method of gameState for arm thickness, row and col arguments taken constructor */
  @Test
  public void testMoveWithFourthConstructor() {
    m4.move(7,5,5,5);
    assertEquals(m4.getGameState(),
        "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O\n"
            + "O O O O O _ O O O O O O O O O\n"
            + "O O O O O _ O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O\n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        ");
  }


  /**
   * Test if exceptions are thrown when Arguments for Move is not valid
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFromPosition() {
    m1.move(0,0,3,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidToPosition() {
    m1.move(1,3,0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFromPositionHasNotValidMove() {
    m1.move(2,3,3,3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveIsIllegal() {
    m1.move(2,3,3,3);
  }


  /** Test the method of isGameOver when game is not over */
  @Test
  public void testIsGameOveFalse() {
    m1.move(3,1,3,3);
    assertFalse(m1.isGameOver());
  }

  /** Test the method of isGameOver when game is over */
  @Test
  public void testIsGameOveTrue() {
    m1.move(3,1,3,3);
    m1.move(3,4,3,2);
    m1.move(3,6,3,4);
    m1.move(1,3,3,3);
    m1.move(4,3,2,3);
    m1.move(6,3,4,3);
    assertEquals(m1.getGameState(),
        "    O O O    \n"
        + "    O _ O    \n"
        + "O O O O O O O\n"
        + "O _ O _ O _ _\n"
        + "O O O O O O O\n"
        + "    O _ O    \n"
        + "    O _ O    ");
    assertTrue(m1.isGameOver());
  }

  /** Test the method of gameState for non-argument constructor */
  @Test
  public void getGameStateForNonArgument() {
    assertEquals(m1.getGameState(),
        "    O O O    \n"
            + "    O O O    \n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O    \n"
            + "    O O O    ");
  }

  /** Test the method of gameState for row and col arguments taken constructor */
  @Test
  public void getGameStateForRowAndColTaken() {
    assertEquals(m2.getGameState(),
        "    O O O    \n"
            + "    O O O    \n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O    \n"
            + "    O O O    ");
    m2.move(0,3,2,3);
    assertEquals(m2.getGameState(),
        "    O _ O    \n"
        + "    O _ O    \n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "    O O O    \n"
        + "    O O O    ");
  }


  /** Test the method of gameState for arm thickness argument taken constructor */
  @Test
  public void getGameStateForThicknessTaken() {
    assertEquals(m3.getGameState(),
        "      O O O O O      \n"
            + "      O O O O O      \n"
            + "      O O O O O      \n"
            + "O O O O O O O O O O O\n"
            + "O O O O O O O O O O O\n"
            + "O O O O O _ O O O O O\n"
            + "O O O O O O O O O O O\n"
            + "O O O O O O O O O O O\n"
            + "      O O O O O      \n"
            + "      O O O O O      \n"
            + "      O O O O O      ");
  }

  /** Test the method of gameState for arm thickness, row and col arguments taken constructor */
  @Test
  public void getGameStateForThicknessAndRowAndColTaken() {
    assertEquals(m4.getGameState(),
        "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "O O O O O O O O O O O O O O O\n"
            + "O O O O O _ O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O O O\n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        \n"
            + "        O O O O O O O        ");
  }


  /** Test the method of getScore */
  @Test
  public void getScore() {
    assertEquals(m1.getScore(),32);
    m1.move(3,1,3,3);
    assertEquals(m1.getScore(),31);
    assertEquals(m3.getScore(),84);
    m3.move(5,3,5,5);
    assertEquals(m3.getScore(),83);
  }
}