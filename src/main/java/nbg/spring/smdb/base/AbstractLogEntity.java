package nbg.spring.smdb.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public abstract class AbstractLogEntity {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    private void init() {
        logger.trace("Loaded {}.", getClass().getName());
    }
}
