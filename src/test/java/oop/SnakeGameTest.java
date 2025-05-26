package oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class SnakeGameTest {

  @Test
  void testMoveWithinBounds() {
    int[][] food = {{1, 2}, {0, 1}};
    SnakeGame game = new SnakeGame(3, 3, food);

    assertEquals(0, game.move("R")); // Move right
    assertEquals(0, game.move("D")); // Move down
    assertEquals(1, game.move("R")); // Move right and eat food
  }

  @Test
  void testMoveOutOfBounds() {
    int[][] food = {};
    SnakeGame game = new SnakeGame(3, 3, food);

    assertEquals(0, game.move("R")); // Move right
    assertEquals(0, game.move("R")); // Move right
    assertEquals(-1, game.move("R")); // Move right out of bounds
  }

  @Test
  void testMoveIntoItself() {
    int[][] food = {{0, 1}, {0, 2}, {0, 3}, {1, 3}, {1, 2}};
    SnakeGame game = new SnakeGame(4, 4, food);

    assertEquals(1, game.move("R")); // Eat food
    assertEquals(2, game.move("R")); // Eat food
    assertEquals(3, game.move("R")); // Eat food
    assertEquals(4, game.move("D")); // Valid Move
    assertEquals(5, game.move("L")); // Valid Move
    assertEquals(-1, game.move("U"));// Move left into itself
  }

  @Test
  void testEatAllFood() {
    int[][] food = {{0, 1}, {0, 2}};
    SnakeGame game = new SnakeGame(3, 3, food);

    assertEquals(1, game.move("R")); // Eat first food
    assertEquals(2, game.move("R")); // Eat second food
    assertEquals(2, game.move("D")); // Move down without food
  }

  @Test
  void testNoFood() {
    int[][] food = {};
    SnakeGame game = new SnakeGame(3, 3, food);

    assertEquals(0, game.move("R")); // Move right
    assertEquals(0, game.move("D")); // Move down
    assertEquals(0, game.move("L")); // Move left
  }
}