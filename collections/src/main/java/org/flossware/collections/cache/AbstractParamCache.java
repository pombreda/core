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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import org.flossware.common.AbstractCommonBase;
import org.flossware.common.IntegrityUtil;

/**
 * Abstract base class for caches.
 *
 * @author Scot P. Floess
 *
 * @param <K> the key.
 * @param <V> the type to cache.
 * @param <T> the type(s) to use to create the value if not cached.
 */
public abstract class AbstractParamCache<K, V, T> extends AbstractCommonBase implements ParamCache<K, V, T> {

    /**
     * This is our actual cache.
     */
    private final ConcurrentMap<K, V> map;

    /**
     * Default constructor.
     */
    protected AbstractParamCache() {
        this.map = new ConcurrentHashMap<>();
    }

    /**
     * Return the actual cache.
     *
     * @return the actual cache.
     */
    protected ConcurrentMap<K, V> getMap() {
        return map;
    }

    /**
     * Debug the key and parameters.
     *
     * @param msg        the message to emit.
     * @param key        the key.
     * @param parameters the parameters.
     */
    protected void debug(final String msg, final K key, final Object... parameters) {
        getLogger().log(Level.FINE, "Key [{0}] is " + msg + " - we have value these values", key);
        for (final Object param : parameters) {
            getLogger().log(Level.FINE, "   Value [{0}]", param);
        }
    }

    /**
     * Create a value based on parameters.
     *
     * @param key   the key to use when creating.
     * @param param the seed data to use when creating a value.
     *
     * @return the created value.
     */
    protected abstract V create(K key, T param);

    /**
     * Will destroy a value. Most implementations likely do not need this level
     * of functionality. Override to do the needful.
     *
     * @param key   the key that refers to value.
     * @param value the value to destroy.
     */
    protected void destroy(final K key, final V value) {
        getLogger().log(Level.FINE, "Requested to destroy [{0}] -> [{1}] - nothing to do", new Object[] {key, value});
    }

    /**
     * If oldValue is not null, return it otherwise return the new value.
     *
     * @param oldValue will be returned if its not null.
     * @param newValue will be returned if oldValue is null.
     *
     * @return oldValue if not null or newValue if oldValue is null.
     */
    protected V getNonNull(final V oldValue, V newValue) {
        return (null != oldValue ? oldValue : newValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(final K key, final V value) {
        getLogger().log(Level.FINE, "Attempting to add [{0}] -> [{1}]", new Object[] {key, value});

        IntegrityUtil.ensure(key, "Cannot have a null key!");
        IntegrityUtil.ensure(value, "Must have a value to add!");

        return getNonNull(getMap().putIfAbsent(key, value), value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(final K key, final T param) {
        getLogger().log(Level.FINE, "Attempting to retrieve [{0}]", key);

        IntegrityUtil.ensure(key, "Cannot have a null key!");
        IntegrityUtil.ensure(param, "Must have parameters!");

        final V retVal = getMap().get(key);
        if (null != retVal) {
            debug("cached", key, param);

            return retVal;
        }

        return put(key, create(key, param));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final K key) {
        getLogger().log(Level.FINE, "Requested to remove [{0}]", key);

        destroy(IntegrityUtil.ensure(key, "Cannot have a null key!"), getMap().remove(key));
    }
}
