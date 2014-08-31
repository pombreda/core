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

/**
 * This interface defines the decorator pattern interface:
 * <ul>
 * <li>
 * Attach additional responsibilities to an object dynamically. Decorators
 * provide a flexible alternative to subclassing for extending functionality.
 * </li>
 * </ul>
 * Design Patterns - Elements of Reusable Object-Oriented Software p175.
 *
 * @author Scot P. Floess
 *
 * @param <V> the object we are decorating.
 */
public interface Decorator<V> {

    /**
     * Return the object for whom we add embellishment.
     *
     * @return the object for whom we add embellishment.
     */
    V getDecoratee();
}
