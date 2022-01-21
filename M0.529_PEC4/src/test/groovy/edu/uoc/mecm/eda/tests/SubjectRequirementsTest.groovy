package edu.uoc.mecm.eda.tests

import edu.uoc.mecm.eda.pac4.exercise1.SubjectRequirements
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Spock Class that tests SubjectRequirements class methods
 *
 * BEWARE - This class is written in the Groovy language, which is slightly different from Java
 *
 * @author Carles Pairot Gavaldà
 */
class SubjectRequirementsTest extends Specification {

    @Shared
    String[] subjects = ["PR I", "PR II", "EDA", "LOG", "ILO", "DUBD", "IBD"]

    @Unroll
    def "addSubjectRequirementsExceptionTest()" () {
        when: "expect SubjectRequirements to manage exceptions properly"
            SubjectRequirements sr = new SubjectRequirements (subjects)
            sr.addRequirement ("PR II", "PR I")
            sr.addRequirement ("EDA", "PR II")
            sr.addRequirement ("LOG", "ILO")
            sr.addRequirement ("DUBD", "IBD")
            sr.addRequirement (subject1, subject2)
            sr.addRequirement ("DUBD", "EDA")
            sr.addRequirement ("DUBD", "LOG")
        then:
            thrown (expectedException)

        // We test the method with these values
        where:
            subject1        | subject2     || expectedException
            "DUBD"          | "IBD"        || IllegalArgumentException
            null            | "IBD"        || IllegalArgumentException
            "IBD"           | null         || IllegalArgumentException
            "TORR"          | "IBD"        || IllegalArgumentException
            "ILO"           | "TORR"       || IllegalArgumentException
    }

    @Unroll
    def "getSubjectRequirementsExceptionTest()" () {
        when: "expect SubjectRequirements to manage exceptions properly"
            SubjectRequirements sr = new SubjectRequirements (subjects)
            sr.addRequirement ("PR II", "PR I")
            sr.addRequirement ("EDA", "PR II")
            sr.addRequirement ("LOG", "ILO")
            sr.addRequirement ("DUBD", "IBD")
            sr.addRequirement ("DUBD", "EDA")
            sr.addRequirement ("DUBD", "LOG")
            sr.getSubjectRequirements (subject)
        then:
            thrown (expectedException)

        // We test the method with these values
        where:
            subject    || expectedException
            null       || IllegalArgumentException
            "TORR"     || IllegalArgumentException
    }


    @Unroll
    def "getPassedSubjectsExceptionTest()" () {
        when: "expect SubjectRequirements to manage exceptions properly"
            SubjectRequirements sr = new SubjectRequirements (subjects)
            sr.addRequirement ("EDA", "PR II")
            sr.addRequirement ("LOG", "ILO")
            sr.addRequirement ("DUBD", "IBD")
            sr.addRequirement ("DUBD", "EDA")
            sr.addRequirement ("DUBD", "LOG")
            sr.getPassedSubjects (subject)
        then:
            thrown (expectedException)

        // We test the method with these values
        where:
            subject    || expectedException
            null       || IllegalArgumentException
            "TORR"     || IllegalArgumentException
    }

    @Unroll
    def "getSubjectRequirementsTest()" () {
        expect: "expect SubjectRequirements to perform properly"
            SubjectRequirements sr = new SubjectRequirements (subjects)
            sr.addRequirement ("PR II", "PR I")
            sr.addRequirement ("EDA", "PR II")
            sr.addRequirement ("LOG", "ILO")
            sr.addRequirement ("DUBD", "IBD")
            sr.addRequirement ("DUBD", "EDA")
            sr.addRequirement ("DUBD", "LOG")
            expectedResult == sr.getSubjectRequirements (subject)

        // We test the method with these values
        where:
            subject    || expectedResult
            "PR II"    || ["PR I"]
            "EDA"      || ["PR II"]
            "ILO"      || []
            "LOG"      || ["ILO"]
            "DUBD"     || ["EDA","IBD", "LOG"]
    }

    @Unroll
    def "getPassedSubjectsTest()" () {
        expect: "expect SubjectRequirements to perform properly"
            SubjectRequirements sr = new SubjectRequirements (subjects)
            sr.addRequirement ("PR II", "PR I")
            sr.addRequirement ("EDA", "PR II")
            sr.addRequirement ("LOG", "ILO")
            sr.addRequirement ("DUBD", "IBD")
            sr.addRequirement ("DUBD", "EDA")
            sr.addRequirement ("DUBD", "LOG")
            expectedResult == sr.getPassedSubjects (subject)

        // We test the method with these values
        where:
            subject    || expectedResult
            "PR II"    || ["PR I"]
            "EDA"      || ["PR I", "PR II"]
            "ILO"      || []
            "LOG"      || ["ILO"]
            "DUBD"     || ["EDA", "IBD", "ILO", "LOG", "PR I", "PR II"]
    }
}