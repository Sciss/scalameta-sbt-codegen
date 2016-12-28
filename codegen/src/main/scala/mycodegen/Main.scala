package mycodegen

import java.io.File
import java.nio.file.Files

object Main {
  def main(args: Array[String]): Unit = {
    args.toList match {
      case path :: Nil if path.endsWith(".scala") =>
        val jfile = new File(path)
        jfile.getParentFile.mkdirs()
        // Do scala.meta code gene
        Files.write(
          jfile.toPath,
          """package mycodegen
                   object Generated {
                     val msg = "Hello world!"
                     println(msg)
                   }
                   """.getBytes("UTF-8")
        )
    }
  }
}
