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
package org.flossware.wsimport.soap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.soap.SOAPHandler;
import org.flossware.common.IntegrityUtil;

/**
 * Soap utilities.
 *
 * @author Scot P. Floess
 */
public final class SoapUtil {

	/**
	 * Our logger.
	 */
	private static final Logger logger = Logger.getLogger(SoapUtil.class.getName());

	/**
	 * Return the logger.
	 *
	 * @return the logger.
	 */
	protected static Logger getLogger() {
		return logger;
	}

	/**
	 * Create a list of handlers.
	 *
	 * @param handler will be added to a list and returned.
	 *
	 * @return a list of handlers.
	 */
	protected static List<Handler> createHandlerList(final Handler handler) {
		final List<Handler> handlerChain = new ArrayList<>();

		handlerChain.add(handler);

		return handlerChain;
	}

	/**
	 * Create the handler chain for bindingProvider.
	 *
	 * @param binding      set handler on.
	 * @param handlerChain the handler chains.
	 */
	public static void setHandlerChain(final Binding binding, final List<Handler> handlerChain) {
		binding.setHandlerChain(handlerChain);

		getLogger().log(Level.FINE, "Seting binding [{0}]'s handler list to [{1}]", new Object[]{binding, handlerChain});
	}

	/**
	 * Create the handler chain for bindingProvider.
	 *
	 * @param binding set handler on.
	 * @param handler the handler.
	 */
	public static void setHandlerChain(final Binding binding, final SOAPHandler handler) {
		setHandlerChain(binding, createHandlerList(handler));
	}

	/**
	 * Create the handler chain for bindingProvider.
	 *
	 * @param bindingProvider the provider to set handler on.
	 * @param handler         the handler.
	 */
	public static void setHandlerChain(final BindingProvider bindingProvider, final SOAPHandler handler) {
		setHandlerChain(bindingProvider.getBinding(), handler);
	}

	/**
	 * Create the handler chain for bindingProvider.
	 *
	 * @param bindingProvider
	 * @param handler
	 */
	public static void setHandlerChain(final Object bindingProvider, final SOAPHandler handler) {
		setHandlerChain((BindingProvider) bindingProvider, handler);
	}

	/**
	 * Set the url on the request context map.
	 *
	 * @param requestContextMap the context that is used to initialize the message context for
	 *                          request messages.
	 * @param svcUrl            the url to set.
	 */
	public static void setUrl(final Map<String, Object> requestContextMap, final String svcUrl) {
		IntegrityUtil.ensure(requestContextMap, "Must provide a request context map!!");
		IntegrityUtil.ensure(svcUrl, "URL is null or empty [" + svcUrl + "]");

		requestContextMap.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, svcUrl);

		getLogger().log(Level.FINE, "Seting URL to [{0}]", svcUrl);
	}

	/**
	 * Set the URL to call on the web service.
	 *
	 * @param bindingProvider the binding provider for whom we want to set the web service url.
	 * @param svcUrl          is the end poing address.
	 *
	 * @throws IllegalArgumentException if svcUrl is null or blank.
	 */
	public static void setUrl(BindingProvider bindingProvider, String svcUrl) {
		IntegrityUtil.ensure(bindingProvider, "Must have a binding provider!");
		
		setUrl(bindingProvider.getRequestContext(), svcUrl);
	}

	/**
	 * Set the URL to call on the web service.
	 *
	 * @param object is the object for whom we want to set the web service url.
	 * @param svcUrl is the end poing address.
	 *
	 * @throws IllegalArgumentException if svcUrl is null or blank.
	 */
	public static void setUrl(Object object, String svcUrl) {
		setUrl((BindingProvider) object, svcUrl);
	}
}
