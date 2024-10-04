package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int largo;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;
        Nodo(T v) {
             valor = v; 
            }
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        largo = 0;
    }

    public int longitud() {
    return this.largo;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (this.longitud()==0){
            this.ultimo = nuevo;
            this.primero = nuevo;
        } else {
            primero.ant = nuevo;
            nuevo.sig = this.primero;
            this.primero = nuevo;
        }
        this.largo += 1;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (this.longitud()==0){
            this.ultimo = nuevo;
            this.primero = nuevo;
        } else {
            ultimo.sig = nuevo;
            nuevo.ant = this.ultimo;
            this.ultimo = nuevo;
        }
        this.largo += 1;
    }

    public T obtener(int i) {
        Nodo posicion = this.primero;
        int indice = 0;
        while (indice != i){
            posicion = this.primero.sig;
            indice+=1;
        }
        return posicion.valor;
    }

    public void eliminar(int i) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void modificarPosicion(int indice, T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ListaIterador implements Iterador<T> {
    	Nodo primero;

        public boolean haySiguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        
        public boolean hayAnterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
	    throw new UnsupportedOperationException("No implementada aun");
    }

}
