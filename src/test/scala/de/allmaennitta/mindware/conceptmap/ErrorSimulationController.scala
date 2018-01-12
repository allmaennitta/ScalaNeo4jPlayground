package de.allmaennitta.mindware.conceptmap

import javax.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = Array("/error"))
class ErrorSimulationController {

  @RequestMapping(value = Array("/nullpointer"))
  def handleRequestThrowingNullpointer(response: HttpServletResponse) =
    throw new NullPointerException("Simulating a Nullpointer " + "Exception...")

  @RequestMapping(value = Array("/illegal_argument"))
  def handleRequestThrowingIllegalArgumentException(response: HttpServletResponse) =
    throw new IllegalArgumentException("Simulating an Illegal Argument Exception...")

  @RequestMapping(value = Array("/unsupported_operation"))
  def handleRequestThrowingUnsupportedOperationException(response: HttpServletResponse) =
    throw new UnsupportedOperationException("Simulating an Unsupported Operation Exception...")
}