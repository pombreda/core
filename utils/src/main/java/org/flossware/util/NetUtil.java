package org.flossware.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Network utility class.
 *
 * @author sfloess
 *
 */
public class NetUtil {

    /**
     * Protocol separator.
     */
    public static final String PROTOCOL_SEPARATOR = "://";

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(NetUtil.class.getName());

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Converts <code>rawURL</code> to a URL.
     *
     * @param rawUrl the complete raw URL (including any additional paths).
     *
     * @return a URL.
     *
     * @throws IllegalArgumentException if computing the URL fails.
     */
    public static URL createUrl(final String rawUrl) {
        try {
            return new URL(rawUrl);
        } catch (final MalformedURLException exception) {
            getLogger().log(Level.SEVERE, "Trouble getting protocol and host [{0}]", exception.getMessage());

            throw new IllegalArgumentException("Trouble getting protocol and host!", exception);
        }
    }

    /**
     * Taking the <code>rawURL</code>, will return the URL for just the server.
     *
     * @param rawUrl The complete raw URL (including any additional paths).
     *
     * @return the server's URL including protocol.
     *
     * @throws IllegalArgumentException if computing the URL fails.
     */
    public static String computeHostUrlAsString(final String rawUrl) {
        final URL url = createUrl(rawUrl);

        return StringUtil.concat(url.getProtocol(), PROTOCOL_SEPARATOR, url.getHost());
    }

    /**
     * Using <code>rawUrl</code>, convert to protocol and host version.
     *
     * @param rawUrl the raw URL to convert.
     *
     * @return a URL representation of only protocol and host.
     *
     * @throws MalformedURLException if there is a problem converting to a URL.
     */
    public static URL computeHostUrl(final String rawUrl) throws MalformedURLException {
        return new URL(computeHostUrlAsString(rawUrl));
    }
}
