public class Queue<T> implements TDAQueue<T>{

    List<T> queueList = new List<>();

    /**
     * Limpia la cola. Elimina todos los elementos.
     */
    @Override
    public void clear() {
        queueList.clear();
    }

    /**
     * Remueve y regresa el siguiente elemento en la cola.
     *
     * @return el siguiente en la cola o null si es vacía.
     */
    @Override
    public T dequeue() {
        queueList.remove(queueList.size()-1);
        return this.first();
    }

    /**
     * Agrega un nuevo al final de la cola.
     *
     * @param e el elemento a agregar.
     */
    @Override
    public void enqueue(T e) {
        queueList.add(0,e);
    }

    /**
     * Devuelve el elemento siguiente en la cola, sin eliminarlo.
     *
     * @return el siguiente elemento en la cola o null si es vacía.
     */
    @Override
    public T first() {
        return queueList.get(queueList.size()-1);
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return true si la cola no contiene elementos, false en otro caso.
     */
    @Override
    public boolean isEmpty() {
        return queueList.isEmpty();
    }

    /**
     * Verifica si un elemento está contenido en la lista.
     *
     * @param e el elemento a verificar si está contenido.
     * @return true si el elemento está contenido, false en otro caso.
     */
    public boolean contains(T e) {
        return queueList.contains(e);
    }

    @Override
    public String toString() {
        return queueList.toString();
    }
}