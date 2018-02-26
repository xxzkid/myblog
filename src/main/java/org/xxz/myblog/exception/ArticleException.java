package org.xxz.myblog.exception;

import org.xxz.myblog.common.exception.CustomRuntimeException;
import org.xxz.myblog.common.exception.ExceptionStatus;

/**
 * @author tt
 */
public class ArticleException extends CustomRuntimeException {

    public ArticleException(ExceptionStatus exStatus) {
        super(exStatus);
    }
}
