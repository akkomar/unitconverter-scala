package pl.komarzewski.converter

sealed trait TemperatureUnitWithValue {
  /**
   * Temperature value
   */
  val t: Double
}

case class Kelvin(t: Double = 273.15) extends TemperatureUnitWithValue

case class Celsius(t: Double = 0) extends TemperatureUnitWithValue

case class Fahrenheit(t: Double = 32) extends TemperatureUnitWithValue

//This model allows adding new temperature units, but TemperatureConverter.convert method starts to be unintelligible
//better way would be to define only conversions from/to Kelvin and use them during other ones.

class TemperatureConverter {
  def convert(from: TemperatureUnitWithValue, to: TemperatureUnitWithValue): TemperatureUnitWithValue =
    (from, to) match {
      case (Celsius(c), Fahrenheit(_)) => Fahrenheit(c * 9 / 5 + 32)
      case (Celsius(c), Kelvin(_)) => Kelvin(c + 273.15)
      case (Fahrenheit(f), Celsius(_)) => Celsius((f - 32) * 5 / 9)
      case (Fahrenheit(f), Kelvin(_)) => Kelvin((f - 32) * 5 / 9 + 273.15)
      case (Kelvin(k), Celsius(_)) => Celsius(k - 273.15)
      case (Kelvin(k), Fahrenheit(_)) => Fahrenheit((k - 273.15) * 9 / 5 + 32)
    }
}

object TemperatureConverter {
  def apply() = new TemperatureConverter
}


