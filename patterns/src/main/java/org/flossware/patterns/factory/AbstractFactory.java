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

import java.util.logging.Level;
import org.flossware.common.AbstractCommonBase;
import org.flossware.patterns.factory.Factory;

/**
 * Abstract base class for factories.
 *
 * @author Scot P. Floess
 *
 * @param <V> the type of value to create.
 * @param <T> the type of arguments to use when creating a value.
 */
public abstract class AbstractFactory<V, T> extends AbstractCommonBase implements Factory<V, T> {

    /**
     * Default constructor.
     */
    protected AbstractFactory() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy(final V value) {
        getLogger().log(Level.FINE, "Requested to destroy [{0}]", value);
    }
}
