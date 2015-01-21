package com.coltsoftware.fingerprintvisualization;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class DrunkenBishopTest {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    public void example_from_paper_works() {
        String s = new DrunkenBishop.Builder().hexString("fc:94:b0:c1:e5:b0:98:7c:58:43:99:76:97:ee:9f:b7").build().render();

        assertEquals(
                "+-----------------+" + LINE_SEPARATOR +
                "|       .=o.  .   |" + LINE_SEPARATOR +
                "|     . *+*. o    |" + LINE_SEPARATOR +
                "|      =.*..o     |" + LINE_SEPARATOR +
                "|       o + ..    |" + LINE_SEPARATOR +
                "|        S o.     |" + LINE_SEPARATOR +
                "|         o  .    |" + LINE_SEPARATOR +
                "|          .  . . |" + LINE_SEPARATOR +
                "|              o .|" + LINE_SEPARATOR +
                "|               E.|" + LINE_SEPARATOR +
                "+-----------------+" + LINE_SEPARATOR, s);
    }

}