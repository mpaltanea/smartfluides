package fr.osb.smartf.agent.worker.exception.types;

import java.io.Serializable;

/**
 * Created by mpaltanea on 07.04.2016.
 */
@SuppressWarnings("serial")
public class ValidationException extends RuntimeException implements
        Serializable {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}