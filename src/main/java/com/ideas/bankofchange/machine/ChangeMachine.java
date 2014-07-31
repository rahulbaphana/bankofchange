package com.ideas.bankofchange.machine;

import java.util.*;

public class ChangeMachine {
    private DenominationHandler denominationHandler;

    public ChangeMachine(final DenominationHandler denominationHandler) {
        this.denominationHandler = denominationHandler;
    }

    public Map<Integer, Integer> getMeChangeFor(int inputAmount) throws Exception {
        int amountSum = 0;
        Map<Integer, Integer> changeAmount = new HashMap<Integer, Integer>();
        for (Integer currencyNote : denominationHandler.getAvailableNotesFor(inputAmount)) {
            if (!areEqual(inputAmount, currencyNote)) {
                amountSum = getUpdatedAmountSumFor(inputAmount, amountSum, changeAmount, currencyNote, denominationHandler.getDenominationCountFor(currencyNote));
            }
        }
        return returnChangeAmount(inputAmount, changeAmount, amountSum);
    }

    private int getUpdatedAmountSumFor(int inputAmount, int sum, Map<Integer, Integer> changeAmount, Integer note, int noteCount) throws Exception {
        for (int i = 1; i <= noteCount; i++) {
            if (additionOf(sum, note) <= inputAmount) {
                sum = getSumWith(sum, note, changeAmount);
            }
        }
        return sum;
    }

    private boolean areEqual(int inputAmount, Integer note) {
        return note == inputAmount;
    }

    private int getSumWith(int sum, Integer note, Map<Integer, Integer> changeAmount) throws Exception {
        denominationHandler.deductDenominationFrom(note, 1);
        denominationHandler.addDenominationToMap(changeAmount, note, 1);
        return additionOf(sum, note);
    }

    private int additionOf(int sum, int note) {
        return sum + note;
    }

    private Map<Integer, Integer> returnChangeAmount(int inputAmount, Map<Integer, Integer> changeAmount, Integer sum) {
        if (sum != inputAmount) {
            changeAmount.clear();
            changeAmount.put(inputAmount, 1);
        }
        return changeAmount;
    }

}
