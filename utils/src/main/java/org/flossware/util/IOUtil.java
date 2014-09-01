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

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * IO utility class.
 *
 * @author Scot P. Floess
 */
public class IOUtil {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

    /**
     * Default constructor not allowed.
     */
    private IOUtil() {
    }

    /**
     * Return the logger.
     */
    protected static Logger getLogger() {
        return logger;
    }

    /**
     * Attempts to close a closeable. If any trouble arises closing, an error is
     * logged.
     *
     * @param closeable the thing to close.
     */
    public static void close(final Closeable closeable) {
        if (null == closeable) {
            return;
        }

        try {
            closeable.close();
        } catch (final IOException ioException) {
            getLogger().log(Level.SEVERE, "Trouble closing", ioException);
        }
    }

    /**
     * Attempts to close inputStream. If any trouble arise closing, no error is
     * logged.
     *
     * @param closeable the thing to close.
     *
     * @throws IllegalArgumentException if inputStream is null.
     */
    public static void closeQuieryly(final Closeable closeable) {
        if (null == closeable) {
            return;
        }

        try {
            closeable.close();
        } catch (final IOException ioException) {
        }
    }
}
