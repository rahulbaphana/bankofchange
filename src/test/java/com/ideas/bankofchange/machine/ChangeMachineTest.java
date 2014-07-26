package com.ideas.bankofchange.machine;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class ChangeMachineTest {

    @Test
    public void shouldCreateAnInstanceOfChangeMachine(){
        ChangeMachine changeMachine = new ChangeMachine(new DenominationService());

        assertNotNull(changeMachine);
    }

    @Test
    public void shouldReturnChangeForTheInputNoteWhenGetMeChangeIsCalled() throws Exception {
        ChangeMachine changeMachine = new ChangeMachine(getDenominationServiceWithAddedDenominations());

        Map<Integer, Integer> changeAmount = changeMachine.getMeChangeFor(100);
        Assert.assertEquals(2, changeAmount.get(50).intValue());

        changeAmount = changeMachine.getMeChangeFor(500);
        Assert.assertEquals(5, changeAmount.get(100).intValue());

        changeAmount = changeMachine.getMeChangeFor(200);
        Assert.assertEquals(3, changeAmount.get(50).intValue());
        Assert.assertEquals(2, changeAmount.get(20).intValue());
        Assert.assertEquals(1, changeAmount.get(10).intValue());

        changeAmount = changeMachine.getMeChangeFor(200);
        Assert.assertEquals(1, changeAmount.get(200).intValue());
    }

    private DenominationService getDenominationServiceWithAddedDenominations() throws Exception {
        return new DenominationService().withADenominationAdded(100, 5).withADenominationAdded(50, 5).withADenominationAdded(20, 5).withADenominationAdded(10, 5);
    }

}
