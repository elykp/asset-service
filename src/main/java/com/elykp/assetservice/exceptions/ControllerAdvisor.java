package com.elykp.assetservice.exceptions;

import com.elykp.assetservice.storage.StorageException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    return buildBody(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), request);
  }

  @ExceptionHandler(FileSizeLimitExceededException.class)
  @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
  public ResponseEntity<ApiException> handleMultipartException(FileSizeLimitExceededException ex,
      WebRequest request) {
    return buildBody(HttpStatus.PAYLOAD_TOO_LARGE, ex.getMessage(), request);
  }

  @ExceptionHandler(StorageException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ApiException> handleStorageException(StorageException ex,
      WebRequest request) {
    return buildBody(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
  }

  private ResponseEntity buildBody(HttpStatus status, String message, WebRequest request) {
    return new ResponseEntity(new ApiException(status, message, request), status);
  }
}
