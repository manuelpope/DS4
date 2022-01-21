package edu.uoc.mecm.eda.tests

import edu.uoc.mecm.eda.pac4.exercise3.AmongVous
import edu.uoc.mecm.eda.pac4.exercise3.Position
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock Class that tests AmongVous class methods
 *
 * BEWARE - This class is written in the Groovy language, which is slightly different from Java
 *
 * @author Carles Pairot Gavaldà
 */
class AmongVousTest extends Specification {

    @Shared
    String board1Filename = "src/test/resources/board1.txt"

    @Shared
    String board2Filename = "src/test/resources/board2.txt"

    @Shared
    String board3Filename = "src/test/resources/board3.txt"

    @Shared
    String board4Filename = "src/test/resources/board4.txt"

    @Shared
    String board5Filename = "src/test/resources/sudoku5.txt"

    @Shared
    String board6Filename = "src/test/resources/board6.txt"

    @Shared
    String board1Solution   = "__P___\n" +
                              "__XXX_\n" +
                              "______\n" +
                              "_X__X_\n" +
                              "_XS·XP\n" +
                              "_XX·__\n" +
                              "__P·__\n"
    @Shared
    String board2Solution =   "__P___X___\n" +
                              "_____X__P_\n" +
                              "XXXXX_X___\n" +
                              "S______X_P\n"

    @Shared
    String board3Solution =   "·······\n" +
                              "·XXXXXP\n" +
                              "·X···XP\n" +
                              "·X·X·XX\n" +
                              "···XSXP\n"

    @Shared
    String board4Solution =   "______________________________________·····_______\n" +
                              "______________________________·········XXX·_______\n" +
                              "___________________XXXXXXXXXX_·XXXXXXXXXP··XXXXXXX\n" +
                              "_XXXXX________________________·________XXXX_______\n" +
                              "__PXXX________________________········____________\n" +
                              "_XXXXX_________________XXXXXXXXXXXXXX·____________\n" +
                              "______________________________XXXXX__·____________\n" +
                              "___________XXXXXXXXXXXXXX_______······________XXXX\n" +
                              "________________________________·XXXXXXXXXXXXXX___\n" +
                              "________________________________·_________________\n" +
                              "________________________________·_________________\n" +
                              "________________________________·_________________\n" +
                              "________________________________·················S\n"

    @Unroll
    def "amongVousExceptionTest()" () {
        when: "expect AmongVous to manage exceptions properly"
            new AmongVous (new File (filename))

        then:
            thrown (expectedException)

        // We test the method with these values
        where:
            filename        || expectedException
            "LOLOLO.txt"    || FileNotFoundException
            board5Filename  || IllegalArgumentException
            board6Filename  || IllegalArgumentException
    }

    @Unroll
    def "amongVousDistanceTest()" () {
        expect: "calculate Among Vous distance correctly"
        AmongVous game = new AmongVous (new File (boardFilename))
            List<Position> path = game.distanceToHumanPlayer()
            (path.size() - 1) == result

        // We test the method with these values
        where:
            boardFilename     || result
            board1Filename    || 4
            board2Filename    || -1
            board3Filename    || 19
            board4Filename    || 57
    }

    @Unroll
    def "amongVousTest()" () {
        expect: "play Among Vous correctly"
            AmongVous game = new AmongVous (new File (boardFilename))
            List<Position> path = game.distanceToHumanPlayer()
            game.getPath (path) == result

        // We test the method with these values
        where:
            boardFilename     || result
            board1Filename    || board1Solution
            board2Filename    || board2Solution
            board3Filename    || board3Solution
            board4Filename    || board4Solution
    }
}