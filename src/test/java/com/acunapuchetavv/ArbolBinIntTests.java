package com.acunapuchetavv;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class ArbolBinIntTests {

    @Test
    @DisplayName("Spy vs Mock - Un mock debería dar falso porque no tiene los métodos implementados")
    public void spyvsmockMock(){
        ArbolBinInt mock = mock();
        Assertions.assertFalse(mock.esVacio());
    }

    @Test
    @DisplayName("Spy vs Mock - Un Spy debería dar true porque los metodos SI están implementados")
    public void spyvsmockSpy(){
        ArbolBinInt spy = spy(ArbolBinInt.class);
        Assertions.assertTrue(spy.esVacio());
    }

    @Test
    @DisplayName("Insertar debería dar TRUE - obtenerNodo Mockeado pero no se llama, se inserta el nodo en la raiz")
    public void insertarDaTrueSinMock(){
        
        //Creo un nodo normalmente
        NodoArbolInt nodoUno = new NodoArbolInt(1);
        //Creo un SPY del Arbol Binario
        ArbolBinInt arbolBinarioSpy = spy(ArbolBinInt.class);

        /*
        * Mockeo el Método obtenerNodo para que, cuando se invoque obtenerNodo(<CualquierNodo>, 1)
        * devuelva el nodoUno que se creó al principio.
        * Para cualquier otro parámetro que no sean esos dos, el comportamiento esta INDEFINIDO,
        * como si no existiera el método
        */
        when(arbolBinarioSpy.obtenerNodo(any(NodoArbolInt.class), Mockito.eq(1)))
            .thenReturn(nodoUno);

        /*
        * Ejecuto el método insertar del Spy de arbolBinario.
        */
        Boolean pudoInsertar = arbolBinarioSpy.insertar(1, 2, 'i');

        //Verificamos que se pudo insertar
        Assertions.assertTrue(pudoInsertar);
        //verificar que nunca se haya llamado el método que mockeamos: obtenerNodo
        /*
         * Nunca se llama porque el arbolBinario no tiene nodos! el primer insert es la raiz. Ver el código de
         * ArbolBinInt como referencia
         */
        verify(arbolBinarioSpy, never()).obtenerNodo(any(NodoArbolInt.class), Mockito.eq(1));
    }


    @Test
    @DisplayName("Insertar debería dar TRUE - obtenerNodo Mockeado")
    public void insertarDaTrueConMock(){
        
        /*
         * Creo dos nodos normalmente
         */
        NodoArbolInt nodoUno = new NodoArbolInt(1);
        NodoArbolInt nodoDos = new NodoArbolInt(2);
        /*
         * Creo un tercer nodo pero esta vez MOCKEADO. Más adelante se explica el porque
         */
        NodoArbolInt nodoTresMock = mock();

        /*
         * Le inserto al nodo uno, a la izquierda el nodo dos y a la derecha el nodo tres
         */
        nodoUno.setIzquierdo(nodoDos);
        nodoUno.setDerecho(nodoTresMock);

        //Creo un SPY del Arbol Binario
        ArbolBinInt arbolBinarioSpy = spy(ArbolBinInt.class);
        //Le hardcodeo como raiz el nodo 1, esto a efectos de testing
        arbolBinarioSpy.raiz = nodoUno;

         /*
        * Mockeo el Método obtenerNodo para que, cuando se invoque obtenerNodo(<CualquierNodo>, 3)
        * devuelva el nodoTresMock que se creó al principio.
        * Para cualquier otro parámetro que no sean esos dos, el comportamiento esta INDEFINIDO,
        * como si no existiera el método
        */
        when(arbolBinarioSpy.obtenerNodo(any(NodoArbolInt.class), Mockito.eq(3)))
            .thenReturn(nodoTresMock);

        /*
        * Ejecuto el método insertar del Spy de arbolBinario. Nótese que dentro de insertar se llama
        * al método obtenerNodo, pero en vez de usar al real, se usa al Mockeado.
        * para CONFIRMAR esto, hacemos como si el método obtenerNodo no estuviera implementado aún,
        * por lo que en la clase ArbolBinInt comentamos su interior.
        */
        Boolean pudoInsertar = arbolBinarioSpy.insertar(3, 6, 'i');

        /*
        * Esta vez si puede insertar, es decir, exito da true. Porque, ya que tiene una raiz asignada
        * pasa al ELSE, allí SE INVOCA OBTENERNODO, pero ESTA MOCKEADO para que cuando se llame a
        * obtenerNodo(...., 3) devuelva nodoTresMock
        */
        Assertions.assertTrue(pudoInsertar);

        //Verificamos que el metodo obtenerNodo se haya llamado exactamente una vez
        verify(arbolBinarioSpy, times(1)).obtenerNodo(any(NodoArbolInt.class), Mockito.eq(3));
        //Verificamos que el nodoTresMock haya llamado a setIzquierdo exactamente una vez
        verify(nodoTresMock, times(1)).setIzquierdo(any(NodoArbolInt.class));
        //Verificamos que el nodoTresMock NUNCA haya llamado a setDerecho
        verify(nodoTresMock, never()).setDerecho(any(NodoArbolInt.class));
        /*
         * Y esto es cierto porque cuando llamamos a insertar le indicamos la posición 'i'
         * IMPORTANTE! Se hizo un mock de nodoTres y no de nodoUno/Dos porque para el método
         * verify SOLO podemos verificar mocks o spies, no metodos u objetos reales
         */
    }

    @Test
    @DisplayName("Insertar debería dar FALSE - obtenerNodo Mockeado")
    public void insertarDaFalse(){
        
        NodoArbolInt nodoUno = new NodoArbolInt(1);
        NodoArbolInt nodoDos = new NodoArbolInt(2);
        NodoArbolInt nodoTres = new NodoArbolInt(3);

        nodoUno.setIzquierdo(nodoDos);
        nodoUno.setDerecho(nodoTres);

        ArbolBinInt spy = spy(ArbolBinInt.class);
        spy.raiz = nodoUno;
        when(spy.obtenerNodo(any(NodoArbolInt.class), anyInt()))
           .thenReturn(null);

        Boolean pudoInsertar = spy.insertar(4, 6, 'i');
        Assertions.assertFalse(pudoInsertar);
        
    }

    
    @Test
    @DisplayName("Insertar debería dar FALSE, se encuentra el nodo pero la posición es invalida")
    public void insertarDaFalsePosicionLlena(){
        
        NodoArbolInt nodoUno = new NodoArbolInt(1);
        NodoArbolInt nodoDos = new NodoArbolInt(2);
        NodoArbolInt nodoTres = new NodoArbolInt(3);


        nodoUno.setIzquierdo(nodoDos);
        nodoUno.setDerecho(nodoTres);

        ArbolBinInt spy = spy(ArbolBinInt.class);
        spy.raiz = nodoUno;

        when(spy.obtenerNodo(any(NodoArbolInt.class), Mockito.eq(2)))
            .thenReturn(nodoDos);

        Boolean pudoInsertar = spy.insertar(2, 6, 'a');
        Assertions.assertFalse(pudoInsertar);
    }

    @Test
    @DisplayName("padre debería encontrar el padra del nodo 5 de la estructura dada, que es 2. Además se comprueba que las llamadas a PadreRecursivo sean correctas")
    public void padre(){
        //Creamos el Spy
        ArbolBinInt ArbolBinarioSpy = spy(ArbolBinInt.class);
        //Armamos el arbol de forma manual a efectos de usar los nodos posteriormente
        NodoArbolInt nodoRaiz = new NodoArbolInt(1);
        NodoArbolInt nodoDos = new NodoArbolInt(2);
        NodoArbolInt nodoTres = new NodoArbolInt(3);
        NodoArbolInt nodoCuatro = new NodoArbolInt(4);
        NodoArbolInt nodoCinco = new NodoArbolInt(5);
        NodoArbolInt nodoSeis = new NodoArbolInt(6);
        NodoArbolInt nodoSiete = new NodoArbolInt(7);
        nodoRaiz.setIzquierdo(nodoDos);
        nodoRaiz.setDerecho(nodoTres);
        nodoDos.setIzquierdo(nodoCuatro);
        nodoDos.setDerecho(nodoCinco);
        nodoTres.setIzquierdo(nodoSeis);
        nodoTres.setDerecho(nodoSiete);
        ArbolBinarioSpy.raiz = nodoRaiz;

        //Testear metodo padreRecursivo
        int padreDeCinco = ArbolBinarioSpy.padre(5);
        //Se verifica que efectivamente el padre sea 2
        Assertions.assertEquals(2, padreDeCinco);
        //Se verifica la cantidad de llamadas del método recursivo
        verify(ArbolBinarioSpy, times(7)).padreRecursivo(anyInt(), any()); //Para mejora
        //verify(ArbolBinarioSpy, times(14)).padreRecursivo(anyInt(), any()); Para original (donde nos dimos cuenta de que llamaba de forma innecesaria!!)
        //Se verifica el orden de esos llamados y con los parámetros correctos
        InOrder inOrder = inOrder(ArbolBinarioSpy);
        inOrder.verify(ArbolBinarioSpy).padreRecursivo(5, nodoRaiz);
        inOrder.verify(ArbolBinarioSpy).padreRecursivo(5, nodoTres);
        inOrder.verify(ArbolBinarioSpy).padreRecursivo(5, nodoSiete);
        inOrder.verify(ArbolBinarioSpy).padreRecursivo(5, null);
        inOrder.verify(ArbolBinarioSpy).padreRecursivo(5, nodoSeis);
        inOrder.verify(ArbolBinarioSpy).padreRecursivo(5, null);
        inOrder.verify(ArbolBinarioSpy).padreRecursivo(5, nodoDos);

        

    }

}
