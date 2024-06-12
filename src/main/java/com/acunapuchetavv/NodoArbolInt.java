/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acunapuchetavv;

public class NodoArbolInt {

    private int elemento;
    private NodoArbolInt izquierdo;
    private NodoArbolInt derecho;

    public NodoArbolInt(int elem) {
        this.elemento = elem;
        this.izquierdo = null;
        this.derecho = null;
    }

    public NodoArbolInt() {
        this.elemento = Integer.MAX_VALUE;
        this.izquierdo = null;
        this.derecho = null;
    }

    public void setDerecho(NodoArbolInt der) {
        this.derecho = der;
    }

    public void setIzquierdo(NodoArbolInt izq) {
        this.izquierdo = izq;

    }

    public void setElemento(int elem) {
        this.elemento = elem;
    }

    public NodoArbolInt getIzquierdo() {
        return this.izquierdo;
    }

    public NodoArbolInt getDerecho() {
        return this.derecho;
    }

    public int getElem() {
        return this.elemento;
    }
}
