/*
 * Copyright (C) 2014 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.util;

import java.util.logging.Logger;
import org.flossware.common.IntegrityUtil;

/**
 * A string utility class.
 *
 * @author Scot P. Floess
 */
public class StringUtil {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(StringUtil.class.getName());

    /**
     * Default separator.
     */
    private static final String DEFAULT_SEPARATOR = "";

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Return true if a separator can be appended or false if not. To append,
     * the index must be less than or equal to the array's length - 2.
     *
     * @param index the place within the array we are processing.
     * @param objs  the array of objects being processed.
     *
     * @return true if we can append a separator or false if not.
     */
    private static boolean isSeparatorAppendable(final int index, final Object... objs) {
        return index <= objs.length - 2;
    }

    /**
     * Concat objects together and return the toString of the concatenation.
     *
     * @param separator the separator to use between concatenation.
     * @param objs      the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static String concatWithSeparator(final String separator, Object... objs) {
        IntegrityUtil.ensure(objs, "Must have a list of objects to concat!");

        final StringBuilder sb = new StringBuilder();

        for (int index = 0; index < objs.length; index++) {
            sb.append(objs[index]);

            if (isSeparatorAppendable(index, objs.length)) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    /**
     * Concat objects together and return the toString of the concatenation.
     *
     * @param objs the objects to concatenate.
     *
     * @return the string representation of the concatenation.
     */
    public static String concat(Object... objs) {
        return concatWithSeparator(DEFAULT_SEPARATOR, objs);
    }
}
