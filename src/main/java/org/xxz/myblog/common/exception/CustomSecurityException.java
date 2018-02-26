package org.xxz.myblog.common.exception;

/**
 * @author tt
 */
public class CustomSecurityException extends CustomRuntimeException {

    public CustomSecurityException(ExceptionStatus exStatus) {
        super(exStatus);
    }
}
