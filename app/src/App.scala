package app

import org.scalajs.dom
import org.scalajs.dom.{Worker, WorkerType}
import org.scalajs.dom.html.Div
import scala.concurrent.ExecutionContext
import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import slinky.core.facade.ReactElement
import slinky.web.ReactDOMClient
import slinky.web.html.*

object App :
    @main
    def main(): Unit = println("Hello")
