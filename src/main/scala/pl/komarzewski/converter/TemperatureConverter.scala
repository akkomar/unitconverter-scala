package pl.komarzewski.converter

class TemperatureConverter {
  def convert(from: TemperatureUnitWithValue, to: TemperatureUnitWithValue): TemperatureUnitWithValue =
    (from, to) match {
      case (Celsius(c), Fahrenheit(_)) => Fahrenheit(c * 9 / 5 + 32)
      case (Fahrenheit(f), Celsius(_)) => Celsius((f - 32) * 5 / 9)
    }
}

object TemperatureConverter {
  def apply() = new TemperatureConverter
}

sealed trait TemperatureUnitWithValue {
  /**
   * Temperature value
   */
  val t: Double
}

case class Celsius(t: Double = 0) extends TemperatureUnitWithValue

case class Fahrenheit(t: Double = 32) extends TemperatureUnitWithValue

