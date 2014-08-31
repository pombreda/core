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
package org.flossware.patterns.decorator;

import org.flossware.common.AbstractCommonBase;
import org.flossware.common.IntegrityUtil;

/**
 * Abstract base class for decorators.
 *
 * @author Scot P. Floess
 *
 * @param <V> the object we are decorating.
 */
public abstract class AbstractDecorator<V> extends AbstractCommonBase implements Decorator<V> {

    /**
     * Our decoratee.
     */
    private final V decoratee;

    /**
     * Sets the decoratee.
     *
     * @param decoratee the object for whom we add embellishment.
     *
     * @throws IllegalArgumentException if <code>decoratee</code> is null.
     */
    protected AbstractDecorator(final V decoratee) {
        this.decoratee = IntegrityUtil.ensure(decoratee, "Cannot have a null decoratee!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V getDecoratee() {
        return decoratee;
    }
}
