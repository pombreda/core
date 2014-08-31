/**
 * This package is useful when you want to simplify all wsimport generated
 * services to one interface with a simple getPort() call. The niceness here is
 * one doesn't need to know QNames, actual methods, etc. Simply doing this will
 * suffice:
 *
 * final WebService<SomePort> service = new
 * GenericWebService(SomeService.class); final SomePort port =
 * servcie.getPort();
 *
 * port.someCallOne(); service.getPort().someCallTwo();
 */
package org.flossware.wsimport.service;
