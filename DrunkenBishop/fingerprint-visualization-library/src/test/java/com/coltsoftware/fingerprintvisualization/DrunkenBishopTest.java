package com.coltsoftware.fingerprintvisualization;

import static org.junit.Assert.*;
import org.junit.Test;

public final class DrunkenBishopTest {

    @Test
    public void example_from_paper_works() {
        String s = new DrunkenBishop("fc:94:b0:c1:e5:b0:98:7c:58:43:99:76:97:ee:9f:b7").render();

        s= s.replaceAll(System.lineSeparator(),"\n");

        assertEquals(
                "+-----------------+\n" +
                "|       .=o.  .   |\n" +
                "|     . *+*. o    |\n" +
                "|      =.*..o     |\n" +
                "|       o + ..    |\n" +
                "|        S o.     |\n" +
                "|         o  .    |\n" +
                "|          .  . . |\n" +
                "|              o .|\n" +
                "|               E.|\n" +
                "+-----------------+\n", s);
    }

}