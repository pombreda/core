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

/**
 * Utility class for tests.
 *
 * @author Scot P. Floess
 */
public class TestUtil {

	/**
	 * Generates a unique string with the string's prefix being prefix and suffix being suffix.
	 *
	 * @param prefix the prefix.
	 * @param suffix the suffix.
	 *
	 * @return a unique string containing prefix as prefix and suffix as suffix.
	 */
	public static final String generateUniqueStr(final String prefix, final String suffix) {
		return prefix + System.currentTimeMillis() + suffix;
	}

	/**
	 * Generate a unique string with prefix as the prefix.
	 *
	 * @param prefix the prefix.
	 *
	 * @return a unique string containing prefix as prefix.
	 */
	public static final String generateUniqueStr(final String prefix) {
		return generateUniqueStr(prefix, "");
	}

	/**
	 * Generate a unique string.
	 *
	 * @return a unique string.
	 */
	public static final String generateUniqueStr() {
		return generateUniqueStr("");
	}
}
