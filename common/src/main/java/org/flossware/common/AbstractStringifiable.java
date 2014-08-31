package org.flossware.common;

/**
 *
 * Default abstract base class of stringifiables.
 *
 * @author sfloess
 *
 */
public abstract class AbstractStringifiable extends AbstractCommonBase implements Stringifiable {

    public static final String LINE_SEPARATOR_PROPERTY = "line.separator";
    public static final String LINE_SEPARATOR_STRING = System.getProperty(LINE_SEPARATOR_PROPERTY);

    /**
     * Default constructor.
     */
    protected AbstractStringifiable() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toString(final StringBuilder stringBuilder) {
        toString(stringBuilder, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(final String prefix) {
        final StringBuilder sb = new StringBuilder();

        toString(sb, prefix);

        return sb.toString();
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representation of self.
     */
    @Override
    public String toString() {
        return toString("");
    }
}
