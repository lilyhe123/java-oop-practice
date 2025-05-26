package oop;

import java.util.*;
/**
 * 5. Design Snake Game 
 * Design a Snake game that is played on a device with screen size height x 
 * width. Play the game online if you are not familiar with the game. 
 *  
 * The snake is initially positioned at the top left corner (0, 0) with a length 
 * of 1 unit. 
 *  
 * You are given an array food where food[i] = (ri, ci) is the row and column 
 * position of a piece of food that the snake can eat. When a snake eats a piece 
 * of food, its length and the game's score both increase by 1. 
 *  
 * Each piece of food appears one by one on the screen, meaning the second piece 
 * of food will not appear until the snake eats the first piece of food. 
 *  
 * When a piece of food appears on the screen, it is guaranteed that it will not 
 * appear on a block occupied by the snake. 
 *  
 * The game is over if the snake goes out of bounds (hits a wall) or if its head 
 * occupies a space that its body occupies after moving (i.e. a snake of length 
 * 4 cannot run into itself). 
 *  
 * Implement the SnakeGame class: 
 *  
 * SnakeGame(int width, int height, int[][] food) Initializes the object with a 
 * screen of size height x width and the positions of the food. 
 * int move(String direction) Returns the score of the game after applying one 
 * direction move by the snake. If the game is over, return -1. 
 * -----------------------------
 * 
 * For every move, we need to calculate the next position, 
 * whether the game is over?
 *   the next position is out of range, 
 *   or the snake runs into itself. 
 * If game is not over, can the snake eat a food resulting in increasing its length.
 * 
 * To check whether the snake runs into itself, we need to store the history
 * positions of the same size of the snake's size. To make the check with constant
 * time, we need a HashSet. We also need to reserve the adding order, so we can
 * remove the oldest element when needed. LinkedHashSet is the right choice.
 */
class SnakeGame {
  private final int rows, cols;
  private final int[][] food;
  private int index = 0;// food index
  private int row = 0, col = 0;
  private int size = 1;
  private LinkedHashSet<Point> set;

  public SnakeGame(int width, int height, int[][] food) {
      rows = height;
      cols = width;
      this.food = food;
      set = new LinkedHashSet<>();
      set.addFirst(new Point(0,0));
  }

  /**
   * calculate next location
  */
  public int move(String direction) {
    switch(direction) {
      case "L":
        col --;
        break;
      case "R":
        col ++;
        break;
      case "U":
        row --;
        break;
      case "D":
        row ++;
        break;
    }

    if (outOfRange(row, col)) return -1;
    Point tail = set.getLast();
    Point head = new Point(row, col);
    // check whether the snake run into itself
    if (size > 4 && !head.equals(tail) && set.contains(head)) {
      return -1;
    }
    // whether eat the food
    if (index < food.length && row == food[index][0] && col == food[index][1]) {
      index ++;
      size ++;
    } else {
      set.removeLast();
    }
    set.addFirst(head);
    return size-1;
  }
  private boolean outOfRange(int row, int col) {
    return (row < 0 || row >= rows || col < 0 || col >= cols);
  }

  public static void main(String... args) {
    int[][] food = {{0, 1}, {0, 2}, {0, 3}, {1, 3}, {1, 2}};
    SnakeGame game = new SnakeGame(4, 4, food);
    System.out.println(game.move("R"));
    System.out.println(game.move("R"));
    System.out.println(game.move("R"));
    System.out.println(game.move("D"));
    System.out.println(game.move("L"));
    System.out.println(game.move("U"));
  }
}
class Point {
  int x, y;
  public Point(int x, int y) {
     this.x = x;
     this.y = y;
  }
  @Override
  public boolean equals(Object obj) {
    if (obj.getClass() != this.getClass()) return false;
    Point other = (Point) obj;
    if(other.x == this.x && other.y == this.y) return true;
    return false;
  }
  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
