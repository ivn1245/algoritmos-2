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

    public void eliminar(T elem) {
        if (this.pertenece(elem)){
            this.raiz = eliminarRecursivo(this.raiz, elem);
        }
    }

    private Nodo eliminarRecursivo(Nodo nodo, T elem) {
        if (nodo == null) {
            return null;
        }
        int comparacion = elem.compareTo(nodo.valor);
        if (comparacion < 0) {
            nodo.menor = eliminarRecursivo(nodo.menor, elem);
        } else if (comparacion > 0) {
            nodo.mayor = eliminarRecursivo(nodo.mayor, elem);
        } else {
            if (nodo.menor == null && nodo.mayor == null) {
                this.longitud--;
                return null;
            }
            if (nodo.menor == null) {
                this.longitud--;
                return nodo.mayor;
            } else if (nodo.mayor == null) {
                this.longitud--;
                return nodo.menor;
            }
            Nodo sucesor = encontrarMinimo(nodo.mayor);
            nodo.valor = sucesor.valor;  
            nodo.mayor = eliminarRecursivo(nodo.mayor, sucesor.valor);  
        }
        return nodo;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.menor != null) {
            nodo = nodo.menor;
        }
        return nodo;
    }

    public String toString(){
        StringBuffer evento = new StringBuffer();
        Iterador iterador = this.iterador();
        evento.append("{");
        if (iterador.haySiguiente()!=false){
            evento.append(iterador.siguiente());
            while (iterador.haySiguiente()){
                evento.append(",");
                evento.append(iterador.siguiente().toString());
            }
        }
        evento.append("}");
        return evento.toString();
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo actual;

        public boolean haySiguiente() {
            boolean hay = false;            
            while (actual.padre!=null){
                if (actual.padre.mayor!=actual){
                    hay=true;
                }
            }
            if (actual.mayor == null){
                hay=false;
            }
            return hay;
        }
    
        public T siguiente() {
            if (actual.mayor!=null){
                actual = encontrarMinimo(actual.mayor);
            } else if (actual.mayor==null) {
                while (actual.padre.mayor==actual){
                    actual=actual.padre;
                }
                actual=actual.padre;
            }
            return actual.valor;
        }
    }

    public Iterador<T> iterador() {
        ABB_Iterador nuevo = new ABB_Iterador();
        Nodo minimo = new Nodo(null);
        Nodo comienzo = new Nodo(null);
        nuevo.actual = comienzo;
        if (this.raiz!=null){
            minimo = encontrarMinimo(this.raiz);
            nuevo.actual.padre = minimo;
        }
        return nuevo;
    }

}
