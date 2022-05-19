package cs5004.marblesolitaire;

/**
 * Use Marbles to represent different situation of the position
 */
public enum Marbles {
  INVALID{
    public String toString() {
      return " ";
    }
  },
  EMPTY{
    public String toString() {
      return "_";
    }
  },
  MARBLE{
    public String toString() {
      return "O";
    }
  }

}
