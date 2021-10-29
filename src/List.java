import java.util.Iterator;

public class List<T> implements TDAList<T>{

    private Node head;
    private int size;

    /**
     * Inserta un nuevo elemento <i>e</i> en la posición <i>i</i>.
     *
     * @param i la posición donde agregar el elemento.
     * @param e el elemento a insertar.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException {
        if(i < 0 || i > size){
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(e);

        if(head == null){//Si la lista es vacia
            head = newNode;
        }else if(i == 0){//Si se va agregar al inicio
            newNode.setNext(head);
            head = newNode;
        }else{//Cuando se agrega en cualquier otra posición
            Node iterator = head;
            for(int j = 0; j < i; j++){
                iterator = iterator.getNext();
            }
            newNode.setNext(iterator.getNext());
            iterator.setNext(newNode);
        }
        size++;
    }

    /**
     * Limpia la lista. Elimina todos los elementos.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Verifica si un elemento está contenido en la lista.
     *
     * @param e el elemento a verificar si está contenido.
     * @return true si el elemento está contenid, false en otro caso.
     */
    @Override
    public boolean contains(T e) {
        Node aux = head;
        if(isEmpty()){
            return false;
        }

        for(int i = 0; i < size; i++){
            if(aux.getElement().equals(e)){
                return true;
            }
            aux = aux.getNext();
        }

        return false;
    }

    /**
     * Obtiene el elemento en la posición <i>i</i>.
     *
     * @param i el índice a obtener elemento.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public T get(int i) throws IndexOutOfBoundsException {
        if( i < 0 || i > size){
            throw new IndexOutOfBoundsException();
        }else if(isEmpty()){
            return null;
        }else if(i == 0){
            return head.getElement();
        }else{
            Node aux = head;
            for(int j = 0; j < i-1; j++){
                aux = aux.getNext();
            }
            return aux.getElement();
        }
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista no contiene elementos, false en otro caso.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Elimina el elemento en la posición <i>i</i>.
     *
     * @param i el índice del elemento a eliminar.
     * @return el elemento eliminado.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public T remove(int i) throws IndexOutOfBoundsException {
        if(i < 0 || i > size ){
            throw new IndexOutOfBoundsException();
        }
        // Eliminar la cabeza
        if(i == 0){
            T element = head.getElement();
            if(size == 1){
                head = null;
            }else{
                head = head.getNext();
            }
            size--;
            return element;
        }else{
            Node aux = head;
            for(int j = 0; j < i-1; j++){
                aux = aux.getNext();
            }

            aux.setNext(aux.getNext().getNext());

            size--;
            return aux.getElement();
        }
    }

    /**
     * Regresa la cantidad de elementos contenidos en la lista.
     *
     * @return la cantidad de elementos contenidos.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Revierte los elementos de la lista. Esto es, da la reversa de la lista.
     */
    @Override
    public void revert() {
    }

    /**
     * Da la mitad derecha o izquierda de una lista.
     *
     * @param side la mitad que recortar de la lista original.
     *             true - mitad derecha.
     *             false - mitad izquierda.
     * @return una nueva lista con la mitad de los elementos.
     */
    @Override
    public TDAList cut(boolean side) {
        return null;
    }

    /**
     * Da un iterador para la lista.
     *
     * @return un iterador para la estructura.
     */
    @Override
    public Iterator listIterador() {
        return null;
    }


    public class Node{

        T element;
        Node next;

        public Node(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    @Override
    public String toString() {
        if(!isEmpty()) {
            String result = "";
            Node aux = head;
            while (aux != null) {
                result += aux.getElement() + ", ";
                aux = aux.getNext();
            }
            return result.substring(0, result.length() - 2);
        }
        return "La lista es vacía";
    }
}
