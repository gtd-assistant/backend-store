package io.alvarogarcia7.petprojects.gtdassistant.backend.card;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandlers {

    @ExceptionHandler(SlangPayloadException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleSlangWords() {
        Map<String, String> userInformation = new HashMap<>();
        userInformation.put("message", "cannot use slang words in this context");
        return userInformation;
    }
}
