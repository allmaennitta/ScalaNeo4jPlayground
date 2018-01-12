package de.allmaennitta.mindware.conceptmap

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.slf4j.LoggerFactory
import org.springframework.http.{HttpHeaders, HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler}
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
object RestResponseEntityExceptionHandler {
  private val LOG = LoggerFactory.getLogger(classOf[RestResponseEntityExceptionHandler])
}

@ControllerAdvice
class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = Array(classOf[IllegalArgumentException], classOf[IllegalStateException]))
  def handleApiFault(ex: RuntimeException, request: WebRequest): ResponseEntity[String] =
    createErrorResponseEntity("API", ex, HttpStatus.BAD_REQUEST)

  @ExceptionHandler(value = Array(classOf[NullPointerException]))
  def handleServerFault(ex: RuntimeException, request: WebRequest): ResponseEntity[String] =
    createErrorResponseEntity("Server", ex, HttpStatus.INTERNAL_SERVER_ERROR)

  @ExceptionHandler(value = (Array(classOf[Exception])))
  def handleUnknownFault(ex: RuntimeException, request: WebRequest): ResponseEntity[String] =
    createErrorResponseEntity("Unknown", ex, HttpStatus.NOT_IMPLEMENTED)

  private def createErrorResponseEntity(errorType: String, ex: RuntimeException,
                                        status: HttpStatus) = {
    RestResponseEntityExceptionHandler.LOG.error(String.format("%s Fault: %s", errorType, ex.getLocalizedMessage))
    new ResponseEntity[String](String.format("%s Fault at %s", errorType,
      LocalDateTime.now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)), new HttpHeaders, status)
  }
}