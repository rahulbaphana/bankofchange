package com.ideas.bankofchange.machine;

import java.util.*;

public class ChangeMachine {
    private DenominationService denominationService = new DenominationService();

    public ChangeMachine(final DenominationService denominationService) {
        this.denominationService = denominationService;
    }

    public Map<Integer, Integer> getMeChangeFor(int inputAmount) throws Exception {
        int amountSum = 0;
        Map<Integer, Integer> changeAmount = new HashMap<Integer, Integer>();
        for (Integer note : denominationService.getAvailableNotesFor(inputAmount)) {
            if (!areEqual(inputAmount, note)) {
                amountSum = getUpdatedAmountSumFor(inputAmount, amountSum, changeAmount, note, denominationService.getDenominationCountFor(note));
            }

        }

        return returnChangeIfPossible(inputAmount, changeAmount, amountSum);
    }

    private int getUpdatedAmountSumFor(int inputAmount, int sum, Map<Integer, Integer> changeAmount, Integer note, int noteCount) throws Exception {
        for (int i = 1; i <= noteCount; i++) {
            if (additionOf(sum, note) <= inputAmount) {
                sum = getSumWithAdditionTo(sum, note, changeAmount);
            }
        }
        return sum;
    }

    private boolean areEqual(int inputAmount, Integer note) {
        return note == inputAmount;
    }

    private int getSumWithAdditionTo(int sum, Integer note, Map<Integer, Integer> changeAmount) throws Exception {
        denominationService.deductDenominationFrom(note, 1);
        denominationService.addDenominationToMap(changeAmount, note, 1);
        return additionOf(sum, note);
    }

    private int additionOf(int sum, int note) {
        return sum + note;
    }

    private Map<Integer, Integer> returnChangeIfPossible(int inputAmount, Map<Integer, Integer> changeAmount, Integer sum) {
        if (sum != inputAmount) {
            changeAmount = new HashMap<Integer, Integer>();
            changeAmount.put(inputAmount, 1);
            return changeAmount;
        }
        return changeAmount;
    }

}
