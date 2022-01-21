package edu.uoc.mecm.eda.tests

import edu.uoc.mecm.eda.pac4.exercise4.SudokuSolver
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock Class that tests Sudoku class methods
 *
 * BEWARE - This class is written in the Groovy language, which is slightly different from Java
 *
 * @author Carles Pairot Gavaldà
 */
class SudokuTest extends Specification {

    @Shared
    String board1Filename = "src/test/resources/sudoku1.txt"

    @Shared
    String board2Filename = "src/test/resources/sudoku2.txt"

    @Shared
    String board3Filename = "src/test/resources/sudoku3.txt"

    @Shared
    String board4Filename = "src/test/resources/sudoku4.txt"

    @Shared
    String board5Filename = "src/test/resources/sudoku5.txt"

    @Shared
    String board6Filename = "src/test/resources/sudoku6.txt"

    @Shared
    String board1Solution   =  "9 6 4 8 5 2 3 1 7\n" +
                               "3 8 2 7 1 6 4 5 9\n" +
                               "1 5 7 9 3 4 2 8 6\n" +
                               "7 9 3 5 6 1 8 2 4\n" +
                               "2 4 8 3 9 7 5 6 1\n" +
                               "5 1 6 4 2 8 7 9 3\n" +
                               "6 3 1 2 7 5 9 4 8\n" +
                               "8 2 9 1 4 3 6 7 5\n" +
                               "4 7 5 6 8 9 1 3 2\n"

    @Shared
    String board2Solution =   "7 3 5 9 6 2 1 8 4\n" +
                              "1 9 8 4 3 5 7 6 2\n" +
                              "2 6 4 1 8 7 3 5 9\n" +
                              "9 7 3 6 5 4 8 2 1\n" +
                              "5 8 1 7 2 3 9 4 6\n" +
                              "4 2 6 8 9 1 5 3 7\n" +
                              "8 4 7 3 1 6 2 9 5\n" +
                              "6 5 9 2 7 8 4 1 3\n" +
                              "3 1 2 5 4 9 6 7 8\n"
    @Shared
    String board3Solution =   "5 8 9 2 7 4 3 1 6\n" +
                              "2 1 7 6 5 3 8 4 9\n" +
                              "4 6 3 1 9 8 5 2 7\n" +
                              "7 2 5 8 1 9 6 3 4\n" +
                              "8 9 6 4 3 7 1 5 2\n" +
                              "1 3 4 5 6 2 7 9 8\n" +
                              "6 5 8 9 4 1 2 7 3\n" +
                              "3 4 2 7 8 5 9 6 1\n" +
                              "9 7 1 3 2 6 4 8 5\n"

    @Unroll
    def "sudokuExceptionTest()" () {
        when: "expect SudokuSolver to manage exceptions properly"
            SudokuSolver game = new SudokuSolver (new File (boardFilename))
            game.solveSudoku()
        then:
            thrown (expectedException)

        // We test the method with these values
        where:
            boardFilename     || expectedException
            "./notfound.txt"  || FileNotFoundException
            board5Filename    || IllegalArgumentException
            board6Filename    || IllegalArgumentException
    }

    @Unroll
    def "sudokuCanItBeSolvedTest()" (String boardFilename, boolean result) {
        expect: "solve Sudoku puzzle correctly"
            SudokuSolver game = new SudokuSolver (new File (boardFilename))

            game.solveSudoku() == result

        // We test the method with these values
        where:
            boardFilename     || result
            board1Filename    || true
            board2Filename    || true
            board3Filename    || true
            board4Filename    || false
    }

    @Unroll
    def "sudokuSolverTest()" (String boardFilename, String result) {
        expect: "solve Sudoku puzzle correctly"
        SudokuSolver game = new SudokuSolver (new File (boardFilename))

        game.solveSudoku()
        game.getSolution().equalsIgnoreCase(result)

        // We test the method with these values
        where:
            boardFilename     || result
            board1Filename    || board1Solution
            board2Filename    || board2Solution
            board3Filename    || board3Solution
    }
}