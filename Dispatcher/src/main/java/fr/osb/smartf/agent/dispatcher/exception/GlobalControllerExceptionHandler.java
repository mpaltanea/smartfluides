package fr.osb.smartf.agent.dispatcher.exception;

import fr.osb.smartf.agent.dispatcher.exception.dto.ExceptionResponse;
import fr.osb.smartf.agent.dispatcher.exception.types.BusinessException;
import fr.osb.smartf.agent.dispatcher.exception.types.TechnicalException;
import fr.osb.smartf.agent.dispatcher.exception.types.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mpaltanea on 07.04.2016.
 */

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ExceptionResponse handleValidationExceptions(ValidationException e,
                                                        HttpServletRequest request) {
        LOGGER.error(e.getLocalizedMessage(), e);
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(TechnicalException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleTechnicalException(TechnicalException e,
                                                      HttpServletRequest request) {
        return new ExceptionResponse(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleBusinessException(BusinessException e,
                                                     HttpServletRequest request) {
        return new ExceptionResponse(e.getMessage());
    }
}
