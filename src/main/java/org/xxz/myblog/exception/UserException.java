package org.xxz.myblog.exception;

import org.xxz.myblog.common.exception.CustomRuntimeException;
import org.xxz.myblog.common.exception.ExceptionStatus;

/**
 * @author tt
 */
public class UserException extends CustomRuntimeException {

    public UserException(ExceptionStatus exStatus) {
        super(exStatus);
    }
}
