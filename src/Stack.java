import java.util.EmptyStackException;

public class Stack<T> implements TDAStack<T>{

    List<T> stackList = new List<>();

    /**
     * Limpia la pila. Elimina todos los elementos.
     */
    @Override
    public void clear() {
        stackList.clear();
    }

    /**
     * Verifica si la pila está vacía.
     *
     * @return true si la pila no contiene elementos, false en otro caso.
     */
    @Override
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    /**
     * Remueve y regresa el tope de la pila.
     *
     * @return el tope de la pila.
     * @throws EmptyStackExpception si la pila está vacía.
     */
    @Override
    public T pop() throws EmptyStackException {
        if(stackList.isEmpty()){
            throw new EmptyStackException();
        }
        stackList.remove(0);
        return stackList.get(0);
    }

    /**
     * Agrega un nuevo elemento a la pila.
     *
     * @param e el elemento a agregar.
     */
    @Override
    public void push(T e) {
        stackList.add(0,e);
    }

    /**
     * Regresa el tope de la pila.
     *
     * @throws EmptyStackExpception si la pila está vacía.
     */
    @Override
    public T top() throws EmptyStackException {
        return stackList.get(0);
    }

    @Override
    public String toString() {
        return stackList.toString();
    }
}
