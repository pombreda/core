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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;
import org.flossware.common.IntegrityUtil;

/**
 * A file utility class.
 *
 * @author Scot P. Floess
 */
public class FileUtil {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

    /**
     * Default constructor not allowed.
     */
    private FileUtil() {
    }

    /**
     * Return the logger.
     */
    protected static Logger getLogger() {
        return logger;
    }

    /**
     * Return a file input stream for file.
     *
     * @param file the file for whom we desire a file input stream.
     *
     * @return a file input stream.
     *
     * @throws IllegalArgumentException if file is null.
     * @throws FileException            if there is any problem creating the
     *                                  file input stream.
     */
    public static FileInputStream getFileInputStream(final File file) {
        IntegrityUtil.ensure(file, "Cannot have a null file!");

        try {
            return new FileInputStream(file);
        } catch (final FileNotFoundException fileNotFoundException) {
            throw new FileException(fileNotFoundException);
        }
    }

    /**
     * Return a file input stream for file.
     *
     * @param fileName the name of the file for whom we desire a file input
     *                 stream.
     *
     * @return a file input stream.
     *
     * @throws IllegalArgumentException if fileName is null or empty.
     * @throws FileException            if there is any problem creating the
     *                                  file input stream.
     */
    public static FileInputStream getFileInputStream(final String fileName) {
        IntegrityUtil.ensure(fileName, "Cannot have a null or empty file name!");

        return getFileInputStream(new File(fileName));
    }

}
