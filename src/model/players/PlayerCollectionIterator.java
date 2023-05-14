package model.players;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class allows the usage of the iterator for the PlayerCollection
 */
public class PlayerCollectionIterator<T> implements Iterator<T> {

    /**
     * A generic private ArrayList.
     */
    private ArrayList<T> list = null;

    /**
     * int used for the index of the list.
     */
    private int current = 0;

    /**
     * Assigns the parameter list to the private global list.
     * @param list the provided list to use.
     */
    public PlayerCollectionIterator(ArrayList<T> list) {
        this.list = list;
    }

    /**
     * Checks if there is a next element.
     * @return if there is a next element in the list.
     */
    @Override
    public boolean hasNext() {
        return this.current < this.list.size();
    }

    /**
     * Gets the next item in the list.
     * @return the next item in the list.
     */
    @Override
    public T next() {
        return this.list.get(this.current++);
    }
   
}
