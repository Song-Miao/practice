package Impatient.ch05

class PlayType {

  def play() = {
    println("in play type method")
  }


}

object PlayType {
  implicit def SilenceType2PlayType(s: SilenceType) = {
    new PlayType
  }
}

class SilenceType {

}
