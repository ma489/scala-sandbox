package example

import scalaz.Writer
import scalaz._
import Scalaz._

object WriterExample {

  type LogMessage = String
  type Log = List[LogMessage]
  type Result = Int
  type ResultWriter = Writer[Log, Result]

  def main(args: Array[String]): Unit = {

    // Scala's for-comprehensions are equivalent to Haskell's do-notation
    val x: ResultWriter = for {
        y <- foo(1)
        z <- foo(2)
      } yield {
        y + z
      }

    val (log: Log, result: Result) = x.run

    println(result)
    println(log)
  }

  private def foo(someArg: Int): ResultWriter = {
    val log: Log = List(s"Called bar with: $someArg")
    val result: Result = someArg * 2
    result.set(log)
  }

}
