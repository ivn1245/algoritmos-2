package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo raiz;
    private int longitud;

    private class Nodo {
            Nodo mayor;
            Nodo menor;
            Nodo padre;
            T valor;
            public Nodo(T v){
                this.valor=v;
                this.mayor=null;
                this.menor=null;
                this.padre=null;
            }
        // Crear Constructor del nodo
    }

    public ABB() {
        this.raiz=null;
        this.longitud=0;
    }

    public int cardinal() {
        return this.longitud;
    }

    public T minimo(){
        Nodo guia = this.raiz;
        while (guia.menor != null){
            guia = guia.menor;
        }
        return guia.valor;
    }

    public T maximo(){
        Nodo guia = this.raiz;
        while (guia.mayor != null){
            guia = guia.mayor;
        }
        return guia.valor;
    }

    public void insertar(T elem){
        Nodo guia = this.raiz;
        Nodo nuevo = new Nodo(elem);
        if (this.raiz==null){
            raiz = nuevo;
            this.longitud++;
        }else if (!this.pertenece(elem)){
            while (true){
                if (guia.valor.compareTo(elem)>0){
                    if (guia.menor!=null){
                        guia = guia.menor;

                    } else {
                        guia.menor = nuevo;
                        nuevo.padre = guia;
                        break;
                    }
                } else {
                    if (guia.mayor!=null){
                        guia=guia.mayor;
                    } else {
                        guia.mayor = nuevo;
                        nuevo.padre = guia;
                        break;
                    }
                }
            }
        
        this.longitud++;
        }
    
    }

    public boolean pertenece(T elem){
        Nodo guia = this.raiz;
        boolean pertenece = false;
        if (this.raiz!=null){
            while(true){
                int comparacion = guia.valor.compareTo(elem);
                if (comparacion == 0){
                    pertenece = true;
                    break;
                } else if (comparacion < 0){
                    if (guia.mayor==null){
                        break;
                    } else {
                    guia = guia.mayor;
                    }
                } else {
                    if (guia.menor==null){
                        break;
                    } else{
                        guia = guia.menor;
                    }
                }
            }
        }
        return pertenece;
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
