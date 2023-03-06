package com.example.gestioncartes;

import java.util.Objects;

public class Carte {
    private int id;
    private String recto;
    private String verso;

    public Carte() {
    }

    public Carte(String recto, String verso) {
        this.recto = recto;
        this.verso = verso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecto() {
        return recto;
    }

    public void setRecto(String recto) {
        this.recto = recto;
    }

    public String getVerso() {
        return verso;
    }

    public void setVerso(String verso) {
        this.verso = verso;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", recto='" + recto + '\'' +
                ", verso='" + verso + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (!(o instanceof Carte carte)) return false;
        return getRecto().equalsIgnoreCase(this.getRecto()) && getVerso().equalsIgnoreCase(this.getVerso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecto(), getVerso());
    }
}
