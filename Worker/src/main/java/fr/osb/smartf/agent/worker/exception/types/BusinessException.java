package fr.osb.smartf.agent.worker.exception.types;

import java.io.Serializable;

/**
 * Created by mpaltanea on 07.04.2016.
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException implements
        Serializable {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
