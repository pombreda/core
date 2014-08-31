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

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Exception utility class.
 *
 * @author Scot P. Floess
 */
public class ExceptionUtil {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(ExceptionUtil.class.getName());

    /**
     * Return the logger.
     */
    protected static Logger getLogger() {
        return logger;
    }

    /**
     * Return true if throwable or any of its root causes is an IOException.
     *
     * @param throwable to examine if being an IOException or any root causes is
     *                  an IOException.
     *
     * @return if throwable or its root causes is an IOException, or false if
     *         not.
     */
    public static boolean containsIOException(final Throwable throwable) {
        if (null == throwable) {
            return false;
        }

        if (throwable instanceof IOException) {
            return true;
        }

        return containsIOException(throwable.getCause());
    }

    /**
     * Not allowed.
     */
    private ExceptionUtil() {
    }
}
