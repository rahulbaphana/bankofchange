package com.ideas.bankofchange.machine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DenominationServiceTest {

    @Test
    public void shouldReturnZeroAsNoteCountWhenTheNoteDoesNotExistInChangeMachine(){
        DenominationService denominationService = new DenominationService();

        assertEquals(0, denominationService.getDenominationCountFor(100));
    }

    @Test
    public void shouldAllowToAddNotesToMachineForVariousDenominations() throws Exception {
        DenominationService denominationService = new DenominationService();

        denominationService.withADenominationAdded(100, 10);
        denominationService.withADenominationAdded(50, 20);

        assertEquals(10, denominationService.getDenominationCountFor(100));
        assertEquals(20, denominationService.getDenominationCountFor(50));
    }

    @Test
    public void shouldUpdateNoteCountToMachineWhenExistingDenominationIsAdded() throws Exception {
        DenominationService denominationService = new DenominationService();

        denominationService.withADenominationAdded(100, 5);
        denominationService.withADenominationAdded(100, 5);

        assertEquals(10, denominationService.getDenominationCountFor(100));
    }

    @Test
    public void shouldReturnOutOfStockNotesWhenGetOutOfStockMethodCalled() throws Exception {
        DenominationService denominationService = new DenominationService();

        denominationService.withADenominationAdded(100, 5);
        denominationService.withADenominationAdded(50, 2);
        denominationService.withADenominationAdded(20, 1);
        denominationService.deductDenominationFrom(100, 5);

        assertEquals("[100]", denominationService.getNotesOutOfStock().toString());
    }

    @Test
    public void shouldNotifyBankerThatTheNoteAmountHasCrossed1000RupeesLimit(){

        Exception ex = null;
        DenominationService denominationService = new DenominationService();

        try {
            denominationService.withADenominationAdded(100, 11);
        } catch (Exception e) {
            ex=e;
            e.printStackTrace();
        }

        assertEquals("Maximum possible denomination value is 1000 Rs.", ex.getMessage().trim());

    }

    @Test
    public void shouldNotifyUserAskingForChangeThatNoChangeAvailableWhenNoDenominationsAreAddedByBanker() throws Exception {
        Exception ex = null;
        DenominationService denominationService = new DenominationService();

        try {
            denominationService.getAvailableNotesFor(100);
        } catch (Exception e) {
            ex=e;
            e.printStackTrace();
        }

        assertEquals("no denominations", ex.getMessage().trim());
    }

    @Test
    public void shouldNotifyUserAskingForChangeThatNoChangeAvailableWhenInputNoteAmountIsLessThanMinimumDenominations() throws Exception {
        Exception ex = null;
        DenominationService denominationService = new DenominationService();

        denominationService.withADenominationAdded(100, 5);
        denominationService.withADenominationAdded(50, 5);
        denominationService.withADenominationAdded(20, 5);
        denominationService.withADenominationAdded(10, 5);

        try {
            denominationService.getAvailableNotesFor(5);
        } catch (Exception e) {
            ex=e;
            e.printStackTrace();
        }

        assertEquals("we don't have denomination of lower value", ex.getMessage().trim());
    }
}