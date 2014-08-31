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
package org.flossware.wsimport.service;

import javax.xml.namespace.QName;

/**
 * Represents a generic web service.
 *
 * @author Scot P. Floess
 *
 * @param <P> the type of web end point.
 */
public interface WebService<P> {

    /**
     * Return the Web Service's QName
     *
     * @return the web service's QName.
     */
    QName getQName();

    /**
     * Return the port's name. This is the name attribute of the Webendpoint
     * annotation.
     *
     * @return the port's name.
     */
    String getPortName();

    /**
     * Return a usable web end point port.
     *
     * @return a usable web end point port.
     */
    P getPort();
}
