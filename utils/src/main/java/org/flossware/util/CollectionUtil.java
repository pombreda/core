/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.flossware.util;

import org.flossware.common.ObjectFilter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * Utility class for collections.
 *
 * @author sfloess
 *
 */
public class CollectionUtil {
    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(CollectionUtil.class.getName());

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Create copied collection.
     *
     * @param <V> the type of data to copy.
     * @param toCopy the collection to copy.
     *
     * @return a new copied collection.
     */
    public static <V> List<V> createCopy(final Collection<V> toCopy) {
        final List<V> retVal = new LinkedList<>();

        retVal.addAll(toCopy);

        return retVal;
    }

    /**
     * Take a collection and sort it, returning a new collection of the sort.
     *
     * @param <V> the type to sort.
     *
     * @param toSort the collection to sort - will not be affected.
     * @param comparator will perform comparisons for sort order.
     *
     * @return a newly sorted collection.
     */
    public static <V> Collection<V> sort(final Collection<V> toSort, final Comparator<V> comparator) {
        final List<V> retVal = createCopy(toSort);

        Collections.sort(retVal, comparator);

        return retVal;
    }

    /**
     * See if a collection meets a filter.
     *
     * @param <T> the type within the collection to search.
     * @param <V> the value to search for within the collection.
     *
     * @param toSeek the collection to iterate over to see if it contains a value.
     * @param filter the filter to apply to each object.
     *
     * @return true if the collection meets the filter or false if not.
     */
    public static <T, V> boolean contains(final Collection<T> toSeek, final ObjectFilter filter, final V value) {
        for (final T obj : toSeek) {
            if (filter.isFiltered(obj, value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Find a value from a collection using a filter.
     *
     * @param <T> the type within the collection to search.
     * @param <V> the value to search for within the collection.
     *
     * @param toSeek the collection to iterate over to see if it contains a value.
     * @param filter the filter to apply to each object.
     * @param value is the value to search for.
     * @param defaultIfNotFound the value to return if <code>value</code> is not found.
     *
     * @return the item from the collection that contains value or defaultValue if not found.
     */
    public static <T, V> T find(final Collection<T> toSeek, final ObjectFilter<T, V> filter, final V value, final T defaultIfNotFound) {
        for (final T obj : toSeek) {
            if (filter.isFiltered(obj, value)) {
                return obj;
            }
        }

        return defaultIfNotFound;
    }

    /**
     * Find a value from a collection using a filter.
     *
     * @param <T> the type within the collection to search.
     * @param <V> the value to search for within the collection.
     *
     * @param toSeek the collection to iterate over to see if it contains a value.
     * @param filter the filter to apply to each object.
     * @param value is the value to search for.
     *
     * @return the item from the collection that contains value or null if not found.
     */
    public static <T, V> T find(final Collection<T> toSeek, final ObjectFilter<T, V> filter, final V value) {
        return find(toSeek, filter, value, null);
    }
}
