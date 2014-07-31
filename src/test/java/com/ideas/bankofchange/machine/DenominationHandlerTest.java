package com.ideas.bankofchange.machine;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DenominationHandlerTest {

    @Test
    public void shouldReturnZeroAsNoteCountWhenTheNoteDoesNotExistInChangeMachine(){
        DenominationHandler denominationHandler = new DenominationHandler();

        assertEquals(0, denominationHandler.getDenominationCountFor(100));
    }

    @Test
    public void shouldAllowToAddNotesToMachineForVariousDenominations() throws Exception {
        DenominationHandler denominationHandler = new DenominationHandler().withADenominationAdded(100, 10).withADenominationAdded(50, 20);

        assertEquals(10, denominationHandler.getDenominationCountFor(100));
        assertEquals(20, denominationHandler.getDenominationCountFor(50));
    }

    @Test
    public void shouldUpdateNoteCountToMachineWhenExistingDenominationIsAdded() throws Exception {
        DenominationHandler denominationHandler = new DenominationHandler().withADenominationAdded(100, 5).withADenominationAdded(100, 5);

        assertEquals(10, denominationHandler.getDenominationCountFor(100));
    }

    @Test
    public void shouldReturnOutOfStockNotesWhenGetOutOfStockMethodCalled() throws Exception {
        DenominationHandler denominationHandler = new DenominationHandler().withADenominationAdded(100, 5).withADenominationAdded(50, 2).withADenominationAdded(20, 1);

        denominationHandler.deductDenominationFrom(100, 5);

        assertEquals("[100]", denominationHandler.getNotesOutOfStock().toString());
    }

}