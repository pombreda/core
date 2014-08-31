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
package org.flossware.collections.cache;

/**
 * Abstract base class for caches.
 *
 * @author Scot P. Floess
 *
 * @param <K> the key.
 * @param <V> the type to cache.
 */
public abstract class AbstractCache<K, V> extends AbstractParamCache<K, V, K> implements Cache<K, V> {

    /**
     * Default constructor.
     */
    protected AbstractCache() {
    }

    /**
     * Based upon key, create a value.
     *
     * @param key the key to use when creating a value
     *
     * @return the value created
     */
    protected abstract V create(K key);

    /**
     * {@inheritDoc}
     */
    @Override
    protected V create(final K key, final K param) {
        return create(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(final K key) {
        return get(key, key);
    }
}
