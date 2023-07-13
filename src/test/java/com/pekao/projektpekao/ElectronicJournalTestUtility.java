package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournalParams;

public class ElectronicJournalTestUtility {

    public static ElectronicJournal createElectronicJournalEntryWithEventType(ElectronicJournal.EventType eventType) {
        return ElectronicJournal.builder()
                .eventType(eventType)
                .name("Testowa nazwa")
                .buildNewEntity();
    }

    public static ElectronicJournalParams createElectronicJournalParamsEntryWithEventType(ElectronicJournal.EventType eventType) {
        return ElectronicJournalParams.builder()
                .eventType(eventType)
                .name("Testowa nazwa")
                .build();
    }
}
