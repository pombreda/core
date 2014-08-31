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
package org.flossware.common.operation;

/**
 * If needed, this interface can be used by classes to decouple preparing an
 * object for use.
 *
 * @author Scot P. Floess
 *
 * @param <V> the type of object to prepare.
 */
public interface Prepare<V> {

    /**
     * Prepare <code>toPropare</code> for use. The returned object is the
     * "prepared" version.
     *
     * @param toPrepare the object to prepare.
     *
     * @return a prepared object.
     */
    V prepare(V toPrepare);
}
