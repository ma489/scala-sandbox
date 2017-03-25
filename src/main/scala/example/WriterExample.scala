package example

import scalaz.Writer
import scalaz._
import Scalaz._

object WriterExample {

  type LogMessage = String
  type Log = List[String]
  type Result = Int
  type ResultWriter = Writer[Log, Result]

  def main(args: Array[String]): Unit = {

    val x = for {
        y <- foo(1)
        z <- foo(2)
      } yield {
        y + z
      }

    val (log, result) = x.run.swap

    println(result)
    println(log)
  }

  private def foo(someArg: Int): ResultWriter = {
    val log = List(s"Called bar with: $someArg")
    val result = someArg * 2
    result.set(log)
  }

}
