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
package org.flossware.wsimport.service.decorator;

import javax.xml.namespace.QName;
import org.flossware.patterns.decorator.AbstractDecorator;
import org.flossware.wsimport.service.WebService;

/**
 * Abstract base class of a web service decorator.
 *
 * @author Scot P. Floess
 */
public abstract class AbstractWebServiceDecorator<P> extends AbstractDecorator<WebService<P>> implements WebServiceDecorator<P> {

    /**
     * Assigns the object for whom we add embellishment.
     *
     * @param decoratee the object for whom we will embellish.
     */
    protected AbstractWebServiceDecorator(final WebService<P> decoratee) {
        super(decoratee);
    }

    /**
     * {@inheritDoc}
     */
    public QName getQName() {
        return getDecoratee().getQName();
    }

    /**
     * {@inheritDoc}
     */
    public String getPortName() {
        return getDecoratee().getPortName();
    }
}
