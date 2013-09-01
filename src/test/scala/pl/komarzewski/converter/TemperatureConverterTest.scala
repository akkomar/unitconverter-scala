package pl.komarzewski.converter

import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.matchers.ShouldMatchers

class TemperatureConverterTest extends FlatSpec with TableDrivenPropertyChecks with ShouldMatchers {
  val temperatures = Table(
    ("Celsius", "Fahrenheit"),
    (300.00, 572.00),
    (20.00, 68.00),
    (10.00, 50.00),
    (0.0, 32.0),
    (-100.00, -148.00),
    (-273.15, -459.67)
    //lower values are meaningless, depending on use case we may want to throw an error in these case
  )

  val converter = new TemperatureConverter

  /**
   * Precision
   */
  val e: Double = 0.01

  "Celsius to Fahrenheit converter" should "properly convert C->F and F->C" in {
    forAll(temperatures) {
      (c, f) =>
        converter.convert(Celsius(c), Fahrenheit()).t should (be >= f - e and be <= f + e)
        converter.convert(Fahrenheit(f), Celsius()).t should (be >= c - e and be <= c + e)
    }
  }
}
