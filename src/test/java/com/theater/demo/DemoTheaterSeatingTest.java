package com.theater.demo;

import org.junit.Test;

public class DemoTheaterSeatingTest {

    @Test
    public void testTheaterSeating() {
        DemoTheaterSeating test = new DemoTheaterSeating();
        test.process("theater_layout.txt");
    }

    @Test(expected = NullPointerException.class)
    public void testTheaterSeatingException() {
        DemoTheaterSeating test = new DemoTheaterSeating();
        test.process("noFile.txt");
    }

    @Test(expected = NumberFormatException.class)
    public void testTheaterSeatingNumberFormat() {
        DemoTheaterSeating test = new DemoTheaterSeating();
        test.process("theater_layout_wrong.txt");
    }

}
