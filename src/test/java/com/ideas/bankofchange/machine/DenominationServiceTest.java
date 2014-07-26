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

}