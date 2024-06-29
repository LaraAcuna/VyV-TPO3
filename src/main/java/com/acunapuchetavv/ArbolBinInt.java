/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acunapuchetavv;

public class ArbolBinInt {

    //Se cambió de Private a Public para poder testear
    public NodoArbolInt raiz;

    public boolean insertar(int padre, int elem, char pos) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbolInt(elem);
        } else {
            NodoArbolInt nodoPadre = obtenerNodo(this.raiz, padre);
            if (nodoPadre != null) {
                if (pos == 'i' && nodoPadre.getIzquierdo() == null) {
                    nodoPadre.setIzquierdo(new NodoArbolInt(elem));
                } else if(nodoPadre.getIzquierdo() != null){
                    exito = false;
                } else if (pos == 'd' && nodoPadre.getDerecho() == null) {
                    nodoPadre.setDerecho(new NodoArbolInt(elem));
                }else if(nodoPadre.getDerecho() != null){
                    exito = false;
                }else if(pos != 'i' && pos != 'd'){
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    //Se cambió de Private a Public para poder testear
    public NodoArbolInt obtenerNodo(NodoArbolInt n, int buscado) {
        /* 
        NodoArbolInt resultado = null;
        if (n != null) {
            if (n.getElem() == buscado) {
                resultado = n;
            } else {
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
            }
            if (resultado == null) {
                resultado = obtenerNodo(n.getDerecho(), buscado);
            }
        }
        return resultado;
        */
        return null;
    }

    public boolean esVacio() {
        boolean vacio = true;
        if (this.raiz != null) {
            vacio = false;
        }
        return vacio;
    }

    public int padre(int hijo) {
        int esPadre;
        NodoArbolInt padre = padreRecursivo(hijo, this.raiz);
        if (padre == null) {
            esPadre = -1;
        } else {
            esPadre = padreRecursivo(hijo, this.raiz).getElem();
        }
        return esPadre;
    }

    //Para testear se hizo publico
    public NodoArbolInt padreRecursivo(int elem, NodoArbolInt n) {
        NodoArbolInt retornar = null;
        if (n != null) {
            if (n.getDerecho() != null) {
                if (n.getDerecho().getElem() == elem) {
                    retornar = n;
                } else {
                    retornar = padreRecursivo(elem, n.getDerecho());

                }
            }
            if (n.getIzquierdo() != null) {
                if (n.getIzquierdo().getElem() == elem) {
                    retornar = n;
                }
            }
            if (retornar == null) {
                retornar = padreRecursivo(elem, n.getIzquierdo());
            }
        }
        return retornar;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public int altura() {
        int p;
        p = alturaRecursiva(this.raiz);
        return p;
    }

    private int alturaRecursiva(NodoArbolInt n) {
        int cont = -1;

        if (n != null) {
            int izq = alturaRecursiva(n.getIzquierdo());
            int der = alturaRecursiva(n.getDerecho());
            cont = Math.max(izq, der) + 1;
        }
        return cont;
    }

    public String toString() {
        String cad;
        if (!this.esVacio()) {
            cad = "raiz: " + this.raiz.getElem() + "\n";
            cad += "========================\n";
            cad += stringRecursivo(this.raiz) + "\n";
        } else {
            cad = "Arbol vacío";
        }
        return cad;
    }

    private String stringRecursivo(NodoArbolInt n) {
        String cadena = "";
        if (n != null) {
            if (n.getIzquierdo() != null) {
                cadena += "Nodo: " + n.getElem() + " Hijo Izquierdo: " + n.getIzquierdo().getElem();
            } else {
                cadena += "Nodo: " + n.getElem() + " Hijo Izquierdo: vacio";
            }
            if (n.getDerecho() != null) {
                cadena += " Hijo Derecho: " + n.getDerecho().getElem();
            } else {
                cadena += " Hijo Derecho: vacio ";
            }
            cadena += "\n";
            if (n.getIzquierdo() != null) {
                cadena += stringRecursivo(n.getIzquierdo());
            }
            if (n.getDerecho() != null) {
                cadena += stringRecursivo(n.getDerecho());
            }
        }

        return cadena;
    }

//    public int nivel(int n) {
//        return alturaRecursiva(obtenerNodo(this.raiz, n));
//    }
    public Lista preorden() {
        Lista lista = new Lista();
        preordenAux(this.raiz, 1, lista);
        return lista;
    }

    private void preordenAux(NodoArbolInt nodo, int pos, Lista lista) {

        if (nodo != null) {
            lista.insertar(nodo.getElem(), pos);
            preordenAux(nodo.getIzquierdo(), pos + 1, lista);
            preordenAux(nodo.getDerecho(), lista.longitud() + 1, lista);
        }

    }

    public Lista posOrden() {
        Lista lista = new Lista();
        posOrdenAux(this.raiz, lista);
        return lista;
    }

    private void posOrdenAux(NodoArbolInt nodo, Lista lista1) {
        if (nodo != null) {
            posOrdenAux(nodo.getIzquierdo(), lista1);
            posOrdenAux(nodo.getDerecho(), lista1);
            lista1.insertar(nodo.getElem(), lista1.longitud() + 1);
        }
    }

    public Lista inOrden() {
        Lista lista = new Lista();
        inOrdenAux(this.raiz, lista);
        return lista;
    }

    private void inOrdenAux(NodoArbolInt nodo, Lista lista2) {
        if (nodo != null) {
            inOrdenAux(nodo.getIzquierdo(), lista2);
            lista2.insertar(nodo.getElem(), lista2.longitud() + 1);
            inOrdenAux(nodo.getDerecho(), lista2);
        }
    }

    private int nivel(NodoArbolInt nodo, int elem) {
        int x;

        if (nodo == null) {
            //Caso que no se encuentre el elem;
            x = Integer.MIN_VALUE;
        } else if (nodo.getElem() == elem) {
            //Caso base;
            x = 0;
        } else {
            x = nivel(nodo.getIzquierdo(), elem); //Recorre la izq
            if (x == Integer.MIN_VALUE) {
                x = nivel(nodo.getDerecho(), elem); // Recorre la der
            }
        }
        if (x != Integer.MIN_VALUE) {
            x++;
        }
        return x;

    }

    public int nivel(int elem) {

        return nivel(this.raiz, elem) - 1;
    }

    public boolean verificarPatron(Lista listPatron) {
        boolean res;
        res = verificarRec(this.raiz, listPatron, 1);
        return res;
    }

    private boolean verificarRec(NodoArbolInt a, Lista lista, int pos) {
        boolean aux = false;
        if (pos > lista.longitud()) {

            aux = true;
        } else if (a != null && (a.getElem() == lista.recuperar(pos))) {
            aux = verificarRec(a.getIzquierdo(), lista, pos + 1);
            if (!aux) {
                aux = verificarRec(a.getDerecho(), lista, pos + 1);
            }
        }
        return aux;
    }

    public Lista frontera() {
        Lista lista = new Lista();
        System.out.println(lista);
        int pos = 1;
        fronteraRec(this.raiz, lista, pos);

        return lista;
    }

    private void fronteraRec(NodoArbolInt a, Lista lista2, int pos) {
        if (a != null) {
            if (a.getDerecho() == null && a.getIzquierdo() == null) {
                System.out.println(a.getElem());
                lista2.insertar(a.getElem(), pos);
            } else {

                fronteraRec(a.getDerecho(), lista2, pos);
                fronteraRec(a.getIzquierdo(), lista2, pos);
            }
        }
    }

    public ArbolBinInt clon() {
        ArbolBinInt arbolRec = new ArbolBinInt();
        if (this.esVacio() != true) {
            arbolRec.raiz = clonrecursivo(this.raiz);
        }

        return arbolRec;
    }

    private NodoArbolInt clonrecursivo(NodoArbolInt nodo) {
        NodoArbolInt aux = new NodoArbolInt();
        aux.setElemento(nodo.getElem());
        if (nodo.getIzquierdo() != null) {
            aux.setDerecho(clonrecursivo(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            aux.setIzquierdo(clonrecursivo(nodo.getDerecho()));
        }
        return aux;
    }

}