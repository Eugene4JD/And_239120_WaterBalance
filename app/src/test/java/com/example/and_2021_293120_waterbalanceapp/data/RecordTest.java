package com.example.and_2021_293120_waterbalanceapp.data;

import junit.framework.TestCase;

public class RecordTest extends TestCase {
    private Record record;

    @Override
    protected void setUp() throws Exception {
        this.record = new Record(10,9);
    }

    public void testGetGoal() {

        assertEquals(10.0,record.getGoal());
    }

    public void testGetProgress() {
        assertEquals(9.0,record.getProgress());
    }

    public void testGetTimeStamp() {
        assertNotNull(record.getTimeStamp());
    }

    public void testSetGoal() {
        record.setGoal(11);
        assertEquals(11.0,record.getGoal());
    }

    public void testSetProgress() {
        record.setProgress(12);
        assertEquals(12.0,record.getProgress());
    }

    public void testSetTimeStamp() {
        record.setTimeStamp("ABOBA");
        assertEquals("ABOBA",record.getTimeStamp());
    }
}