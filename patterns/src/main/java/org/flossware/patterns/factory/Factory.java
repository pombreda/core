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
package org.flossware.patterns.factory;

/**
 * Interface for creating and destroying things.
 *
 * @author Scot P. Floess
 *
 * @param <V> the type of value to create.
 * @param <T> the type of arguments to use when creating a value.
 */
public interface Factory<V, T> {

    /**
     * Should a value not be found in the cache, use this method to create the
     * value.
     *
     * @param arguments The values to use when creating.
     *
     * @return a value for caching.
     */
    public V create(T arguments);

    /**
     * Will cleanup a value when no longer needed.
     *
     * @param value the value to cleanup.
     */
    public void destroy(V value);
}
