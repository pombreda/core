package org.flossware.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.flossware.util.FileUtil;
import org.flossware.util.IOUtil;

/**
 *
 * Acts as a decorator using a file.
 *
 * @author sfloess
 *
 */
public final class FilePropertiesMgr extends AbstractPropertiesMgr {

    protected final InputStreamPropertiesMgr inputStreamPropertiesMgr;

    /**
     * Return the input stream properties manager.
     *
     * @return the input stream properties manager.
     */
    protected InputStreamPropertiesMgr getInputStreamPropertiesMgr() {
        return inputStreamPropertiesMgr;
    }

    @Override
    public Properties getProperties() {
        return getInputStreamPropertiesMgr().getProperties();
    }

    public FilePropertiesMgr(final File file) {
        final FileInputStream fis = FileUtil.getFileInputStream(file);

        inputStreamPropertiesMgr = new InputStreamPropertiesMgr(fis);

        IOUtil.close(fis);
    }

    public FilePropertiesMgr(final String fileName) {
        this(new File(fileName));
    }
}
