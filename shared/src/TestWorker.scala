package worker

import org.scalajs.dom
import org.scalajs.dom.MessageEvent
import scala.concurrent.{ExecutionContext, Future}
import typings.std.global.postMessage

object TestWorker:
  @main
  def main(): Unit =
    println("Hello from worker")
