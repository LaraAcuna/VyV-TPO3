package com.acunapuchetavv;



public class Lista {

    private Nodo cabecera;
//Constructor

    public Lista() {
        this.cabecera = null;
    }

    public int longitud() {

        int contador = 1;
        Nodo nuevoNodo = this.cabecera;
        if (nuevoNodo == null) {
            contador = 0;
        } else {
            while (nuevoNodo.getEnlace() != null) {
                nuevoNodo = nuevoNodo.getEnlace();
                contador++;

            }

        }
        return contador;

    }

    public boolean insertar(Object elem, int pos) {
        boolean exito = true;

        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else if (pos == 1) {
            Nodo nuevo = new Nodo(elem, this.cabecera);
            this.cabecera = nuevo;
        } else {
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos - 1) {
                aux = aux.getEnlace();
                i++;
            }
            Nodo nuevo = new Nodo(elem, aux.getEnlace());
            aux.setEnlace(nuevo);
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;
        if (this.cabecera == null) {
            exito = false;
        } else if (pos == 1) {
            this.cabecera = this.cabecera.getEnlace();
        } else {
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos - 1) {
                aux = aux.getEnlace();
                i++;
            }
            aux.setEnlace(aux.getEnlace().getEnlace());
        }
        return exito;
    }

    public int localizar(Object elem) {
        int pos = 1;
        boolean sigueBuscando = true;
        Nodo aux = this.cabecera;

        while (sigueBuscando && aux != null) {
            if (elem.equals(aux.getElemento())) {
                sigueBuscando = false;
            } else {
                aux = aux.getEnlace();
                pos++;
            }
        }
        if (sigueBuscando == true) {
            pos = -1;
        }
        return pos;
    }

    public int recuperar(int pos) {
        int i = 1, elem = Integer.MAX_VALUE;
        Nodo aux = this.cabecera;
        boolean seguir = true;

        if (pos > 0 && pos <= this.longitud()) {
            while (seguir && i <= this.longitud()) {
                if (i == pos) {
                    elem = (int) aux.getElemento();
                    seguir = false;
                } else {
                    aux = aux.getEnlace();
                    i++;
                }
            }
        }
        return elem;
    }

    public Lista clonarLista() {
        Lista clon = new Lista();
        if (this.cabecera == null) {
            clon.cabecera = null;
        } else {
            Nodo aux1 = this.cabecera;
            Nodo aux2;
            clon.cabecera = new Nodo(aux1.getElemento());
            aux2 = clon.cabecera;
            aux1 = aux1.getEnlace();
            while (aux1 != null) {
                Nodo nodoNuevo = new Nodo(aux1.getElemento());
                aux2.setEnlace(nodoNuevo);
                aux2 = nodoNuevo;
                aux1 = aux1.getEnlace();
            }
        }
        return clon;
    }

    public boolean esVacia() {
        boolean vacia = false;
        if (this.cabecera == null) {
            vacia = true;
        }
        return vacia;
    }

    public void vaciar() {
        this.cabecera = null;
    }

    public String toString() {
        String cad = "";

        if (this.cabecera == null) {
            cad = "la cadena es vacía";
        } else {
            cad = "[";
            Nodo aux = this.cabecera;
            while (aux != null) {
                cad += aux.getElemento();
                aux = aux.getEnlace();
                if (aux != null) {
                    cad += ",";
                }

            }
            cad += "]";
        }
        return cad;
    }

    public void insertarPromedio() {
        int i, suma = 0, div = 0, promedio;
        Nodo aux = this.cabecera;

        while (aux.getEnlace() != null) {
            suma = (int) aux.getElemento() + suma;
            div = div + 1;
            aux = aux.getEnlace();

        }
        suma = (int) aux.getElemento() + suma;
        div = div + 1;
        promedio = suma / div;
        if (aux.getEnlace() == null) {
            Nodo aux2 = new Nodo(promedio);
            aux.setEnlace(aux2);
        }

    }

    public void eliminarRepetido(int x) {
        Nodo aux = this.cabecera;
        while (aux.getEnlace() != null) {

            if (this.cabecera.getElemento().equals(x)) {
                this.cabecera = aux.getEnlace();
                aux = aux.getEnlace();
            } else if (aux.getEnlace().getElemento().equals(x)) {
                aux.setEnlace(aux.getEnlace().getEnlace());
            } else {
                aux = aux.getEnlace();
            }

        }
        if (this.cabecera.getEnlace() == null) {
            if (this.cabecera.getElemento().equals(x)) {
                this.cabecera = null;

            }
        }
    }
}
