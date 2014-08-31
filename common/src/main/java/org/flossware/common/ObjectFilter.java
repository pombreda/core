package org.flossware.common;

/**
 *
 * The purpose of this interface is denote the API to filter objects.
 *
 * @author sfloess
 *
 * @param <T> the type of object to filter.
 * @param <V> the value of object to filter.
 */
public interface ObjectFilter<T, V> {
    /**
     * Return true if <code>object</code> meets filter.
     *
     * @param object to compare.
     * @param value to compare to.
     *
     * @return true if filtered or false if not.
     */
    public boolean isFiltered(T object, V value);
}
