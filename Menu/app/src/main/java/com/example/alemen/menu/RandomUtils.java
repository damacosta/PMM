package com.example.alemen.menu;

import java.util.Random;

/**
 * Created by alemen on 7/11/17.
 */

public class RandomUtils {

    private static Random r = new Random();

     public static int randomInt(int range) {
     return(r.nextInt(range));
     }

     /** Devuelve un indice aleatorio en el intervalo [0 array.length-1] */
    public static int randomIndex(Object[] array) {
        return(randomInt(array.length));
    }

    /** Devuelve un elemento aleatorio perteneciente a un array
     * se usa genericidad y siempre necesita un valor de retorno.*/
    public static <T> T randomElement(T[] array) {
        return(array[randomIndex(array)]);
    }

    /** devuelve un numero float aleatorio, en el intervalo[0, n) */
    public static float randomFloat(int n) {
        return((float)Math.random()*n);
    }
}
