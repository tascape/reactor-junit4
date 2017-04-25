package org.junit.internal.runners.model;

import java.lang.reflect.InvocationTargetException;
import org.junit.ToBeImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * When invoked, throws the exception from the reflected method, rather than
 * wrapping it in an InvocationTargetException.
 *
 * @author linsong wang
 */
public abstract class ReflectiveCallable {
    private static final Logger LOG = LoggerFactory.getLogger(ReflectiveCallable.class);

    public Object run() throws Throwable {
        try {
            return runReflectiveCall();
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();
            if (t instanceof ToBeImplementedException) {
                LOG.warn(t.getMessage());
            } else {
                LOG.error("{}", e.getCause(), t);
            }
            throw t;
        }
    }

    protected abstract Object runReflectiveCall() throws Throwable;
}
