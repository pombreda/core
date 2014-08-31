package org.flossware.util;

import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import org.flossware.common.AbstractStringifiable;

/**
 *
 * This class will monitor a file for changes. If the file does not exist, no
 * changes can be considered. A change to a file is denoted by its last modified
 * time stamp changing.
 *
 * @author sfloess
 *
 */
public class FileMonitor extends AbstractStringifiable {

    /**
     * The file to monitor.
     */
    private final File file;

    /**
     * Last time file was monitored, its date/time.
     */
    private long lastModified;

    /**
     * Return the timestamp of the last modification of the file being monitor.
     *
     * @return the timestamp of the file last time it was monitored.
     */
    protected long getLastModified() {
        return lastModified;
    }

    /**
     * Set the timestamp for the file being monitored.
     *
     * @param lastModified is the timestamp of the file being monitored.
     */
    protected void setLastModified(final long lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * This constructor sets the file to monitor.
     *
     * @param file the file to monitor.
     */
    public FileMonitor(final File file) {
        this.file = file;
        this.lastModified = 0;
    }

    /**
     * This constructor sets the file to monitor using a file name.
     *
     * @param fileName the name of the file to monitor.
     */
    public FileMonitor(final String fileName) {
        this(new File(fileName));
    }

    /**
     * Return the file to monitor.
     *
     * @return the file to monitor.
     */
    public File getFile() {
        return file;
    }

    /**
     * Return true if the file being monitored exists, or false if not... The
     * file could be deleted - in which case it technically changed, but there
     * is nothing to monitor.
     *
     * @return true if the file exists or false if not.
     */
    public boolean exists() {
        getLogger().log(Level.FINEST, "File, {0}, exists = {1}", new Object[] {getFile(), getFile().exists()});

        return getFile().exists();
    }

    /**
     * Return true if the file changed or false if not. Be aware, if the file
     * does not exists, its as if the file has not changed. Call exists() to see
     * if it no longer exists.
     *
     * @return true if the file changed or false if not.
     */
    public boolean isChanged() {
        boolean retVal = false;

        if (exists()) {
            getLogger().log(Level.FINEST, "File, {0} lastModified on {1} -> {2}", new Object[] {getFile(), new Date(getFile().lastModified()), new Date(getLastModified())});

            retVal = getFile().lastModified() != getLastModified();

            setLastModified(getFile().lastModified());
        }

        return retVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toString(final StringBuilder stringBuilder, final String prefix) {
        stringBuilder.append(prefix).append("file [").append(getFile()).append("] - exists [").append(exists()).append("]");
    }
}
