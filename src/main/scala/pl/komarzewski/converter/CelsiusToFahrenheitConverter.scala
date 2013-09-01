package pl.komarzewski.converter

class CelsiusToFahrenheitConverter {

  def convertToFahrenheit(valInCelsius: Double): Double = {
    valInCelsius * 9 / 5 + 32
  }

  def convertToCelsius(valInFahrenheit: Double): Double = {
    (valInFahrenheit - 32) * 5 / 9
  }
}

object CelsiusToFahrenheitConverter {
  def apply() = new CelsiusToFahrenheitConverter
}
