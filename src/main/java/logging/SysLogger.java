package logging;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SysLogger {

    private Handler file;
    private Logger logger;

    public SysLogger(Handler file, Logger logger) {
        this.file = file;
        this.file.setFormatter(new MyFormatter());
        this.logger = logger;
        logger.setUseParentHandlers(false);
        logger.addHandler(file);
    }


    public void toLogInfo(String text) {
        logger.log(Level.INFO, text);
    }

    public void toLogWarning(String text) {
        logger.log(Level.WARNING, text);
    }

    public void toLogSevere(String text) {
        logger.log(Level.SEVERE, text);
    }

}