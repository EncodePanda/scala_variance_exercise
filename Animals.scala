class Animal(val name: String)
class Wolf(override val name: String) extends Animal(name)
class Dog(override val name: String) extends Wolf(name)



object Aniamls extends App {

  var animals: Set[Animal] = Set.empty[Animal]
  var wolfs: Set[Wolf] = Set(new Wolf("White"), new Wolf("Black"), new Wolf("Gray"))
  var dogs: Set[Dog] = Set(new Dog("Reksio"), new Dog("Szarik"))

  /*
   [error]  found   : Set[Wolf]
   [error]  required: Set[Animal]
   [error] Note: Wolf <: Animal, but trait Set is invariant in type A.
   */
  // animals = wolfs

  def tame[A <: Animal](animals: Set[A]) = {
    animals.foreach(a => println(s"taming ${a.name}"))
  }

  tame(animals)
  tame(wolfs)

  def tameWolfable[A <: Wolf](animals: Set[A]) = {
    animals.foreach(a => println(s"taming wolf-a-like ${a.name}"))
  }

  tameWolfable(wolfs)
  tameWolfable(dogs)
  //  tameWolfable(animals)

  def copy[T, A >: T](from: Set[T], to: Set[A]) = {}

  copy(dogs, animals)
  copy(dogs, wolfs)
//  copy(wolfs, dogs)

  sealed trait Maybe[+A] 
  case class Just[A](get: A) extends Maybe[A]
  case object Nth extends Maybe[Nothing]

  val exists: Maybe[String] = Just("Hello")
  val doesNot: Maybe[String] = Nth



}
