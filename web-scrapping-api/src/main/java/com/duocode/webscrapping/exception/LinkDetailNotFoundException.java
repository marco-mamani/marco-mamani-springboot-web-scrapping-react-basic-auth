package com.duocode.webscrapping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LinkDetailNotFoundException extends RuntimeException {

   public LinkDetailNotFoundException(String message) {
      super(message);
   }
}
