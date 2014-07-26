package com.ideas.bankofchange.machine;

import java.util.List;
import java.util.Map;

public class AmountValidation {

    public static final int MAXIMUM_ALLOWED_AMOUNT = 1000;

    private void showErrorMessage(String message) throws Exception {
        throw new Exception(message);
    }

    public void checkIfMaxLimitReached(int note, int calculatedNoteCount) throws Exception {
        if (calculatedNoteCount * note > MAXIMUM_ALLOWED_AMOUNT) {
            showErrorMessage("Maximum possible denomination value is "+MAXIMUM_ALLOWED_AMOUNT+" Rs.");
        }
    }

    public void validateIfThereAreAnyDenominationsEnteredIn(Map mapOfNoteCount) throws Exception {
        if(mapOfNoteCount.isEmpty()) {
            showErrorMessage("no denominations");
        }
    }

    public void validateForLeastDenomination(int inputAmount, List<Integer> availableNotes) throws Exception {
        if(availableNotes.get((availableNotes.size()-1)) > inputAmount) {
            showErrorMessage("we don't have denomination of lower value");
        }
    }
}