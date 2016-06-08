package fr.osb.smartf.agent.dispatcher.exception.types;

import java.io.Serializable;

/**
 * Created by mpaltanea on 07.04.2016.
 */
@SuppressWarnings("serial")
public class TechnicalException extends RuntimeException implements
        Serializable {

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

}
