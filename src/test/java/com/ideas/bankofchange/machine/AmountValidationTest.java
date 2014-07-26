package com.ideas.bankofchange.machine;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;

public class AmountValidationTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void shouldNotifyBankerThatTheNoteAmountHasCrossed1000RupeesLimit() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("Maximum possible denomination value is "+ AmountValidation.MAXIMUM_ALLOWED_AMOUNT+" Rs.");
        new AmountValidation().checkIfMaxLimitReached(100, 11);
    }

    @Test
    public void shouldNotifyUserAskingForChangeThatNoChangeAvailableWhenNoDenominationsAreAddedByBanker() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("no denominations");
        new AmountValidation().validateIfThereAreAnyDenominationsEnteredIn(Collections.emptyMap());
    }

    @Test
    public void shouldNotifyUserAskingForChangeThatNoChangeAvailableWhenInputNoteAmountIsLessThanMinimumDenominations() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("we don't have denomination of lower value");
        new AmountValidation().validateForLeastDenomination(5, Arrays.asList(100, 50, 20, 10));
    }

}