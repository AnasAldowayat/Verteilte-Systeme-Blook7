package de.umr.ds.task1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KernelTest {

    @Test
    void convolve() {

        Kernel arr =new Kernel(new double[][]{ {1,0,1},
                                               {0,1,0},
                                               {1,0,1}}  );
        int[][] img = new int[3][3];
        img[0]= new int[]{1, 0, 0};
        img[1]= new int[]{1, 1, 0};
        img[2]= new int[]{1, 1, 1};

        assertEquals("[[4]]", Arrays.deepToString(arr.convolve(img)));

    }

}