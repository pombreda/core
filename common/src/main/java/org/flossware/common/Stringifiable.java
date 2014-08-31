package org.flossware.common;

/**
 *
 * API to convert an object to a string and makes it pretty.
 *
 * @author sfloess
 *
 */
public interface Stringifiable {
    /**
     * Using <code>stringBuilder</code>, compute the string representation of self whose
     * prefix for computation is <code>prefix</code>.
     *
     * @param stringBuilder will have the string representation of self appended.
     * @param prefix is the prefix to be first appended prior to self's string representation.
     */
    void toString(StringBuilder stringBuilder, String prefix);

    /**
     * Using <code>stringBuilder</code>, compute the string representation of self.
     *
     * @param stringBuilder will have the string representation of self appended.
     */
    void toString(StringBuilder stringBuilder);

    /**
     * Using <code>prefix</code> return the string representation of self.
     *
     * @param prefix is the prefix to emit when return the string representation of self.
     *
     * @return the string representation of self.
     */
    String toString(String prefix);
}
