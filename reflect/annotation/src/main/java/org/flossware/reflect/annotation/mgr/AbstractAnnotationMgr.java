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
import java.util.ArrayList;
import java.util.List;
import org.flossware.common.AbstractCommonBase;
import org.flossware.common.IntegrityUtil;

/**
 * Abstract base class for annotation caching.
 *
 * @author Scot P. Floess
 *
 * @param <V> the type of accessible object to find containing annotations.
 */
public abstract class AbstractAnnotationMgr<V extends AccessibleObject> extends AbstractCommonBase implements AnnotationMgr<V> {

    /**
     * Default constructor.
     */
    protected AbstractAnnotationMgr() {
    }

    /**
     * Using klass, return the accessible objects.
     *
     * @param klass the class to return the accessible objects.
     *
     * @return the accessible objects for searching.
     */
    protected abstract V[] getAccessibleObjects(Class klass);

    /**
     * Find something in a class that contains an annotation.
     *
     * @param accessibleObjects the things to search upon.
     * @param annotation        the annotation desired.
     *
     * @return a collection of accessible objects that have
     *         <code>annotation</code> as an annotation.
     */
    protected List<V> findWithAnnotation(final V[] accessibleObjects, final Class annotation) {
        IntegrityUtil.ensure(annotation, "Cannot have a null annotation to search!");
        IntegrityUtil.ensure(accessibleObjects, "Cannot have a null accessible object!");

        final List<V> retVal = new ArrayList<>();

        for (final V obj : accessibleObjects) {
            if (null != obj.getAnnotation(annotation)) {
                retVal.add(obj);
                break;
            }
        }

        return retVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<V> find(Class klass, Class annotation) {
        IntegrityUtil.ensure(klass, "Cannot have a null class to search!");

        return findWithAnnotation(getAccessibleObjects(klass), annotation);
    }
}
