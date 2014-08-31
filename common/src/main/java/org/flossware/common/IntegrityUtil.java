package org.flossware.common;

import java.util.Collection;
import java.util.Map;

/**
 *
 * Utility class ensuring the integrity of objects/params/values are of a
 * correct value (for example not null).
 *
 * @author sfloess
 *
 */
public class IntegrityUtil {

    /**
     * Default error message when a parameter is bad.
     */
    public static final String DEFAULT_ERROR_MSG = "Invalid value";

    /**
     * Default minimum size for an array.
     */
    public static final int DEFAULT_MIN_ARRAY_LENGTH = 1;

    /**
     * Default minimum size for a list.
     */
    public static final int DEFAULT_MIN_LIST_SIZE = 1;

    /**
     * Default minimum size for a map.
     */
    public static final int DEFAULT_MIN_MAP_SIZE = 1;

    /**
     * Checks <code>object</code> to ensure it is not null.
     *
     * @param <V>      the type of object we are ensuring is not null.
     *
     * @param object   The object to inspect to ensure its not null.
     * @param errorMsg The error message within the raised exception if
     *                 <code>object</code> is null.
     *
     * @return object if it is not null.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static <V> V ensure(final V object, final String errorMsg) throws IllegalArgumentException {
        if (null == object) {
            throw new IllegalArgumentException(errorMsg);
        }

        return object;
    }

    /**
     * Checks <code>object</code> to ensure it is not null.
     *
     * @param <V>    the type of object we are ensuring is not null.
     *
     * @param object The object to inspect to ensure its not null.
     *
     * @return object if it is not null.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static <V> V ensure(final V object) throws IllegalArgumentException {
        return IntegrityUtil.ensure(object, DEFAULT_ERROR_MSG);
    }

    /**
     * Checks <code>str</code> to ensure it is not null nor empty.
     *
     * @param str      The string to inspect to ensure its not null nor empty.
     * @param errorMsg The error message within the raised exception if
     *                 <code>str</code> is null or empty.
     *
     * @return str if it is not null or empty.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static String ensure(final String str, final String errorMsg) throws IllegalArgumentException {
        if (null == str || "".equals(str.trim())) {
            throw new IllegalArgumentException(errorMsg);
        }

        return str;
    }

    /**
     * Checks <code>str</code> to ensure it is not null nor empty.
     *
     * @param str The string to inspect to ensure its not null nor empty.
     *
     * @return str if it is not null or empty.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static String ensure(final String str) throws IllegalArgumentException {
        return IntegrityUtil.ensure(str, DEFAULT_ERROR_MSG);
    }

    /**
     * Ensures collection is not empty or null.
     *
     * @param <C>        the collection to examine for a size.
     * @param collection to check for null/empty.
     * @param minSize    the minimum size of the collection.
     * @param errorMsg   the message within the IllegalArgumentException if
     *                   empty or null.
     *
     * @return collection if it is not null and of minimum size minSize.
     *
     * @throws IllegalArgumentException if <code>collection</code> is empty or
     *                                  null.
     */
    public static <C extends Collection<?>> C ensure(final C collection, final int minSize, final String errorMsg) throws IllegalArgumentException {
        IntegrityUtil.ensure((Object) collection, errorMsg);

        if (collection.size() < minSize) {
            throw new IllegalArgumentException(errorMsg);
        }

        for (final Object value : collection) {
            IntegrityUtil.ensure(value, errorMsg);
        }

        return collection;
    }

    /**
     * Ensures list is not empty or null.
     *
     * @param <C>        the collection to examine for a size.
     *
     * @param collection to check for null/empty.
     * @param errorMsg   the message within the IllegalArgumentException if
     *                   empty or null.
     *
     * @return collection if it is not null and contains DEFAULT_MIN_LIST_SIZE
     *         elements.
     *
     * @throws IllegalArgumentException if <code>collection</code> is empty or
     *                                  null.
     */
    public static <C extends Collection<?>> C ensure(final C collection, final String errorMsg) throws IllegalArgumentException {
        return IntegrityUtil.ensure(collection, DEFAULT_MIN_LIST_SIZE, errorMsg);
    }

    /**
     * Ensures list is not empty or null.
     *
     * @param <C>        the collection to examine for a size.
     * @param collection to check for null/empty.
     * @param minSize    the minimum size of the list.
     *
     * @return collection if it is not null and contains minSize elements.
     *
     * @throws IllegalArgumentException if <code>collection</code> is empty or
     *                                  null.
     */
    public static <C extends Collection<?>> C ensure(final C collection, final int minSize) throws IllegalArgumentException {
        return IntegrityUtil.ensure(collection, minSize, DEFAULT_ERROR_MSG);
    }

    /**
     * Ensures list is not empty or null.
     *
     * @param <C>        the collection to examine for a size.
     * @param collection to check for null/empty.
     *
     * @return collection if it is not null and contains DEFAULT_MIN_LIST_SIZE
     *         elements.
     *
     * @throws IllegalArgumentException if <code>collection</code> is empty or
     *                                  null.
     */
    public static <C extends Collection<?>> C ensure(final C collection) throws IllegalArgumentException {
        return IntegrityUtil.ensure(collection, DEFAULT_MIN_LIST_SIZE);
    }

    /**
     * Ensures map is not empty or null.
     *
     * @param map      to check for null/empty.
     * @param minSize  the minimum size of the map.
     * @param errorMsg the message within the IllegalArgumentException if empty
     *                 or null.
     *
     * @return map if it is not null and contains minSize elements.
     *
     * @throws IllegalArgumentException if <code>map</code> is empty or null.
     */
    public static Map<?, ?> ensure(final Map<?, ?> map, final int minSize, final String errorMsg) throws IllegalArgumentException {
        IntegrityUtil.ensure((Object) map, errorMsg);

        if (map.size() < minSize) {
            throw new IllegalArgumentException(errorMsg);
        }

        return map;
    }

    /**
     * Ensures map is not empty or null.
     *
     * @param map      to check for null/empty.
     * @param errorMsg the message within the IllegalArgumentException if empty
     *                 or null.
     *
     * @return map if it is not null and does not contain DEFAULT_MIN_MAP_SIZE
     *         elements.
     *
     * @throws IllegalArgumentException if <code>map</code> is empty or null.
     */
    public static Map<?, ?> ensure(final Map<?, ?> map, final String errorMsg) throws IllegalArgumentException {
        return IntegrityUtil.ensure(map, DEFAULT_MIN_MAP_SIZE, errorMsg);
    }

    /**
     * Ensures map is not empty or null.
     *
     * @param map     to check for null/empty.
     * @param minSize the minimum size of the list.
     *
     * @return map if it is not null and does not contain minSize elements.
     *
     * @throws IllegalArgumentException if <code>map</code> is empty or null.
     */
    public static Map<?, ?> ensure(final Map<?, ?> map, final int minSize) throws IllegalArgumentException {
        return IntegrityUtil.ensure(map, minSize, DEFAULT_ERROR_MSG);
    }

    /**
     * Ensures map is not empty or null.
     *
     * @param map to check for null/empty.
     *
     * @return map if it is not null and does not contain minSize elements.
     *
     * @throws IllegalArgumentException if <code>map</code> is empty or null.
     */
    public static Map<?, ?> ensure(final Map<?, ?> map) throws IllegalArgumentException {
        return IntegrityUtil.ensure(map, DEFAULT_MIN_MAP_SIZE);
    }

    /**
     * Ensure a value is no less than a minimum.
     *
     * @param value    is the value to compare.
     * @param minValue is the minimum that value can be.
     * @param errorMsg is the message within the IllegalArgumentException if
     *                 value less than minValue.
     *
     * @return value if value is greater than or equal to minValue.
     *
     * @throws IllegalArgumentException if value is less than minValue.
     */
    public static long ensure(final long value, final long minValue, final String errorMsg) throws IllegalArgumentException {
        if (value < minValue) {
            throw new IllegalArgumentException(errorMsg);
        }

        return value;
    }

    /**
     * Ensure a value is no less than a minimum.
     *
     * @param value    is the value to compare.
     * @param minValue is the minimum that value can be.
     *
     * @return value if value is greater than or equal to minValue.
     *
     * @throws IllegalArgumentException if value is less than minValue.
     */
    public static long ensure(final long value, final long minValue) throws IllegalArgumentException {
        return IntegrityUtil.ensure(value, minValue, DEFAULT_ERROR_MSG);
    }

    /**
     * Ensure a value is no less than a minimum.
     *
     * @param value    is the value to compare.
     * @param minValue is the minimum that value can be.
     * @param errorMsg is the message within the IllegalArgumentException if
     *                 value less than minValue.
     *
     * @return value if value is greater than or equal to minValue.
     *
     * @throws IllegalArgumentException if value is less than minValue.
     */
    public static int ensure(final int value, final int minValue, final String errorMsg) throws IllegalArgumentException {
        if (value < minValue) {
            throw new IllegalArgumentException(errorMsg);
        }

        return value;
    }

    /**
     * Ensure a value is no less than a minimum.
     *
     * @param value    is the value to compare.
     * @param minValue is the minimum that value can be.
     *
     * @return value if value is greater than or equal to minValue.
     *
     * @throws IllegalArgumentException if value is less than minValue.
     */
    public static int ensure(final int value, final int minValue) throws IllegalArgumentException {
        return IntegrityUtil.ensure(value, minValue, DEFAULT_ERROR_MSG);
    }

    /**
     * Ensure an array has something in it.
     *
     * @param <V>       the type of array.
     * @param values    represents the array to ensure there are value or has at
     *                  least one element.
     * @param minLength the minimum number of elements in the array.
     * @param errorMsg  is the message of the IllegalArgumentException if
     *                  raised.
     *
     * @return values if not null, contains at least one element and each
     *         element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have
     *                                  at least 1 element.
     */
    public static <V> V[] ensure(final V[] values, final int minLength, final String errorMsg) throws IllegalArgumentException {
        IntegrityUtil.ensure((Object) values, errorMsg);

        if (values.length < minLength) {
            throw new IllegalArgumentException(errorMsg);
        }

        for (final V value : values) {
            IntegrityUtil.ensure(value, errorMsg);
        }

        return values;
    }

    /**
     * Ensure an array has something in it.
     *
     * @param <V>       the type of array.
     * @param values    represents the array to ensure there are value or has at
     *                  least one element.
     * @param minLength the minimum number of elements in the array.
     *
     * @return values if not null, contains at least one element and each
     *         element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have
     *                                  at least 1 element.
     */
    public static <V> V[] ensure(final V[] values, final int minLength) throws IllegalArgumentException {
        return IntegrityUtil.ensure(values, minLength, DEFAULT_ERROR_MSG);
    }

    /**
     * Ensure an array has something in it.
     *
     * @param values   represents the array to ensure there are value or has at
     *                 least one element.
     * @param errorMsg is the message of the IllegalArgumentException if raised.
     *
     * @return values if not null, contains at least one element and each
     *         element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have
     *                                  at least 1 element.
     */
    public static <V> V[] ensure(final V[] values, final String errorMsg) throws IllegalArgumentException {
        return IntegrityUtil.ensure(values, DEFAULT_MIN_ARRAY_LENGTH, errorMsg);
    }

    /**
     * Ensure an array has something in it.
     *
     * @param values represents the array to ensure there are value or has at
     *               least one element.
     *
     * @return values if not null, contains at least one element and each
     *         element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have
     *                                  at least 1 element.
     */
    public static <V> V[] ensure(final V[] values) throws IllegalArgumentException {
        return IntegrityUtil.ensure(values, DEFAULT_ERROR_MSG);
    }
}
