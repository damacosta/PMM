package com.example.alemen.nombresp;

import android.media.MediaPlayer;
import android.provider.MediaStore;

/**
 * Created by alemen on 20/10/17.
 */

public class Titular {

    private String titulo;
    private String subtitulo;
    private int imagen;
    private int MediaPlayer;

    public Titular(String titulo, String subtitulo, int imagen,int MediaPlayer){
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.imagen = imagen;
        this.MediaPlayer = MediaPlayer;
    }

    public void setTitulo (String titulo) {this.titulo = titulo;}
    public void setSubtitulo (String subtitulo) {this.subtitulo = subtitulo;}
    public void setImagen (int imagen) {this.imagen = imagen;}
    public void MediaPlayer (int MediaPlayer) {this.MediaPlayer = MediaPlayer;}


    public String getTitulo(){return titulo;}
    public String getSubtitulo(){return subtitulo;}
    public int getImagen(){return imagen;}
    public int getMediaPlayer(){return MediaPlayer;}

    public String toString() {
        return (titulo + ", " + subtitulo);
    }

}