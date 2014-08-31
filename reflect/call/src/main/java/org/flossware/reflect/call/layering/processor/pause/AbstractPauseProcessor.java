package org.flossware.reflect.call.layering.processor.pause;

import java.util.logging.Level;
import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.layering.processor.AbstractProcessor;

/**
 *
 * Abstract base class for pausing...
 *
 * @author sfloess
 *
 */
public abstract class AbstractPauseProcessor extends AbstractProcessor {
    /**
     * The pause time in millis.
     */
    private final long pause;

    /**
     * Return the pause time.
     *
     * @return the pause time.
     */
    protected long getPause() {
        return pause;
    }

    /**
     * Force a pause.
     */
    protected void doPause() {
        getLogger().log(Level.FINEST, "About to pause for [{0} ms]", getPause());

        try {
            Thread.currentThread().sleep(getPause());
        } catch (final InterruptedException iException) {
            getLogger().log(Level.WARNING, "When pausing, we were interrupted");
        }

        getLogger().log(Level.FINEST, "Pause completed");
    }

    /**
     * Set the pause time.
     *
     * @param pause is the pause time.
     *
     * @throws IllegalArgumentException if pause is < 1.
     */
    public AbstractPauseProcessor(final long pause) {
        IntegrityUtil.ensure(pause, 1);

        this.pause = pause;
    }
}
