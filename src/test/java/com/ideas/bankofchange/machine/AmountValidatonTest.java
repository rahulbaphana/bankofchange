package com.ideas.bankofchange.machine;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AmountValidatonTest {

    @Test
    public void shouldNotifyBankerThatTheNoteAmountHasCrossed1000RupeesLimit(){

        Exception ex = null;

        try {
            new AmountValidaton().checkIfMaxLimitReached(100, 11);
        } catch (Exception e) {
            ex=e;
        }

        assertEquals("Maximum possible denomination value is "+ AmountValidaton.MAXIMUM_ALLOWED_AMOUNT+" Rs.", ex.getMessage().trim());

    }

    @Test
    public void shouldNotifyUserAskingForChangeThatNoChangeAvailableWhenNoDenominationsAreAddedByBanker() throws Exception {
        Exception ex = null;

        try {
            new AmountValidaton().validateIfThereAreAnyDenominationsEnteredIn(Collections.emptyMap());
        } catch (Exception e) {
            ex=e;
        }

        assertEquals("no denominations", ex.getMessage().trim());
    }

    @Test
    public void shouldNotifyUserAskingForChangeThatNoChangeAvailableWhenInputNoteAmountIsLessThanMinimumDenominations() throws Exception {
        Exception ex = null;

        try {
            new AmountValidaton().validateForLeastDinomination(5, Arrays.asList(100, 50, 20, 10));
        } catch (Exception e) {
            ex=e;
        }

        assertEquals("we don't have denomination of lower value", ex.getMessage().trim());
    }

}