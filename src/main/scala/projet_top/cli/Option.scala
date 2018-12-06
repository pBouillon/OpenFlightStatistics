package projet_top.cli

object Option extends Enumeration {
  type Option = Int

  // basic operations
  val Quit    = 0
  val Help    = 6

  // subject-specific questions
  val Quest1  = 1
  val Quest2  = 2
  val Quest3  = 3
  val Quest4  = 4
  val Quest5  = 5
  val Quest6  = 6
  val Quest7  = 7
}