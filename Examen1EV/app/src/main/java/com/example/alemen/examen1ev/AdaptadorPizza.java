package com.example.alemen.examen1ev;

/**
 * Created by alemen on 17/11/17.
 */

public class AdaptadorPizza {
    private String nombre;
    private String ingrediente;
    private double precio;

    public AdaptadorPizza(String nombre , String ingrediente, double precio){
        this.nombre = nombre;
        this.ingrediente = ingrediente;
        this.precio = precio;
    }
    public String getNombre(){
        return nombre;
    }
    public String getIngrediente(){
        return ingrediente;
    }
    public double getPrecio(){
        return precio;
    }
}
