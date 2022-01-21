package edu.uoc.mecm.eda.pac4.exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that implements rules for SubjectRequirements for UOC
 *
 * @author Carles Pairot Gavaldà
 */
public class SubjectRequirements {

    private final List<String> subjects;
    private final int len;
    private final int[][] matrix;

    /**
     * Constructor for initializing a SubjectRequirements instance
     *
     * @param subjects Array of subjects this course contains
     */
    public SubjectRequirements(String[] subjects) {
        this.subjects = Arrays.asList(subjects);
        this.len = subjects.length;
        this.matrix = new int[len][len];
    }

    /**
     * Method to add a required subject to another
     *
     * @param parentSubject The parent subject to add this requirement to
     * @param subject       The subject added as requirement
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void addRequirement(String parentSubject, String subject) throws IllegalArgumentException {

        if (!this.subjects.contains(parentSubject) || !this.subjects.contains(subject)) {

            throw new IllegalArgumentException("not a valid arguments, subjects");
        }
        if (this.matrix[this.subjects.indexOf(parentSubject)][this.subjects.indexOf(subject)] > 0) {

            throw new IllegalArgumentException("not a valid arguments, subjects");
        }
        int indexParent = this.subjects.indexOf(parentSubject);
        int indexSubject = this.subjects.indexOf(subject);
        this.matrix[indexParent][indexSubject] = 1;


    }

    /**
     * Return all possible subjects that have as requirement the input subject
     *
     * @param subject The input subject
     * @return All possible subjects that have as requirement the input subject
     * @throws IllegalArgumentException the illegal argument exception
     */
    public String[] getSubjectRequirements(String subject) throws IllegalArgumentException {
        if (!this.subjects.contains(subject)) {

            throw new IllegalArgumentException("not a valid arguments, subjects");
        }
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < this.len; i++) {
            if (this.matrix[this.subjects.indexOf(subject)][i] > 0) {
                stringList.add(this.subjects.get(i));
            }

        }


        String[] stockArr = new String[stringList.size()];
        stockArr = stringList.toArray(stockArr);
        Arrays.sort(stockArr);
// Se ordenan para no tener el problema en el test ,en el test esta el cambio igualmente.
        return stockArr;
    }

    /**
     * Get a list of all subjects that need to be passed for a specified subject
     *
     * @param subject Input subject
     * @return List of all subjects that need to be passed for a specified subject
     */
    public List<String> getPassedSubjects(String subject) {

        if (!this.subjects.contains(subject)) {

            throw new IllegalArgumentException("not a valid arguments, subjects");
        }

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < this.len; i++) {
            if (this.matrix[this.subjects.indexOf(subject)][i] > 0) {
                stringList.add(this.subjects.get(i));
            }

        }
        ArrayList<String> aux = new ArrayList<>(stringList);


        while (!aux.isEmpty()) {

            String s = aux.get(aux.size() - 1);
            for (int i = 0; i < this.len; i++) {
                if (this.matrix[this.subjects.indexOf(s)][i] > 0) {
                    stringList.add(this.subjects.get(i));
                    aux.add(this.subjects.get(i));
                }


            }

            aux.remove(s);
        }

        String[] stockArr = new String[stringList.size()];
        stockArr = stringList.toArray(stockArr);
        Arrays.sort(stockArr);
// Se ordenan para no tener el problema en el test ,en el test esta el cambio igualmente.
        return Arrays.asList(stockArr);
    }
}
