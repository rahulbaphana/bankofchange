package com.ideas.bankofchange.machine;

import java.util.*;

public class DenominationService {
    private AmountValidation amountValidation = new AmountValidation();
    private Map<Integer, Integer> mapOfNoteCount = new HashMap<Integer, Integer>();
    private Set<Integer> notesOutOfStock = new TreeSet<Integer>();

    public DenominationService withADenominationAdded(int note, int count) throws Exception {
        addDenominationToMap(mapOfNoteCount, note, count);
        return this;
    }

    public int getDenominationCountFor(int note) {
        return mapOfNoteCount.containsKey(note) ? mapOfNoteCount.get(note) : 0;
    }

    public void addDenominationToMap(Map<Integer, Integer> noteCount, int note, int count) throws Exception {
        amountValidation.checkIfMaxLimitReached(note, noteCount.containsKey(note) ? (noteCount.get(note) + count) : count);
        noteCount.put(note, noteCount.containsKey(note) ? (noteCount.get(note) + count) : count);
    }

    public void deductDenominationFrom(int note, int count){
        if(mapOfNoteCount.containsKey(note)) {
            updateCountOfNote(note, mapOfNoteCount.get(note) - count);
        }
    }

    private void updateCountOfNote(int note, int calculatedCount) {
        if(calculatedCount > 0){
            mapOfNoteCount.put(note, calculatedCount);
        }else{
            outOfNote(note);
        }
    }

    public Collection<? extends Integer> getAvailableDenominations() {
        return mapOfNoteCount.keySet();
    }

    public Set<Integer> getNotesOutOfStock() {
        return notesOutOfStock;
    }

    public List<Integer> getOrderedListOFAvailableNotes() {
        List<Integer> availableNotes = new ArrayList<Integer>();
        availableNotes.addAll(getAvailableDenominations());
        orderTheListInDescending(availableNotes);
        return availableNotes;
    }

    public List<Integer> getAvailableNotesFor(int inputAmount) throws Exception {
        amountValidation.validateIfThereAreAnyDenominationsEnteredIn(mapOfNoteCount);
        List<Integer> availableNotes = getOrderedListOFAvailableNotes();
        amountValidation.validateForLeastDenomination(inputAmount, availableNotes);
        return availableNotes;
    }

    private void outOfNote(int note) {
        mapOfNoteCount.remove(note);
        notesOutOfStock.add(note);
    }

    private void orderTheListInDescending(List<Integer> availableNotes) {
        Collections.sort(availableNotes);
        Collections.reverse(availableNotes);
    }
}