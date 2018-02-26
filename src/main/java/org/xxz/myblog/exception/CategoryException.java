package org.xxz.myblog.exception;

import org.xxz.myblog.common.exception.CustomRuntimeException;
import org.xxz.myblog.common.exception.ExceptionStatus;

/**
 * @author tt
 */
public class CategoryException extends CustomRuntimeException {

    public CategoryException(ExceptionStatus exStatus) {
        super(exStatus);
    }
}
