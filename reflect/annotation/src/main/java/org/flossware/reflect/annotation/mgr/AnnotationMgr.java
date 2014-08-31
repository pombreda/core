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
package org.flossware.reflect.annotation.mgr;

import java.lang.reflect.AccessibleObject;
import java.util.List;

/**
 * API for dealing with annotation retrieval on accessible objects.
 *
 * @author Scot P. Floess
 *
 * @param <V> the type of accessible object to find containing annotations.
 */
public interface AnnotationMgr<V extends AccessibleObject> {

    /**
     * Find all accessible objects who have <code>annotation</code> as an
     * annotation.
     *
     * @param klass      the class to search.
     * @param annotation the annotation to search for on <code>klass</code>
     *
     * @return a list of accessible objects that have <code>annotation</code> as
     *         the annotation.
     */
    List<V> find(Class klass, Class annotation);
}
