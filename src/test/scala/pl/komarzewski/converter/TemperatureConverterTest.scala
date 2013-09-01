package pl.komarzewski.converter

import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.matchers.ShouldMatchers

class TemperatureConverterTest extends FlatSpec with TableDrivenPropertyChecks with ShouldMatchers {
  val temperatures = Table(
    ("Celsius", "Fahrenheit", "Kelvin"),
    (300.00, 572.00, 573.15),
    (20.00, 68.00, 293.15),
    (10.00, 50.00, 283.15),
    (0.0, 32.0, 273.15),
    (-100.00, -148.00, 173.15),
    (-273.15, -459.67, 0.00)
    //lower values are meaningless, depending on use case we may want to throw an error in these case
  )

  val converter = new TemperatureConverter

  /**
   * Precision
   */
  val e: Double = 0.01

  "Temperature converter" should "properly convert C->F and F->C" in {
    forAll(temperatures) {
      (c, f, k) =>
        converter.convert(Celsius(c), Fahrenheit()).t should (be >= f - e and be <= f + e)
        converter.convert(Fahrenheit(f), Celsius()).t should (be >= c - e and be <= c + e)
    }
  }

  "Temperature converter" should "properly convert C->K and K->C" in {
    forAll(temperatures) {
      (c, f, k) =>
        converter.convert(Celsius(c), Kelvin()).t should (be >= k - e and be <= k + e)
        converter.convert(Kelvin(k), Celsius()).t should (be >= c - e and be <= c + e)
    }
  }

  "Temperature converter" should "properly convert F->K and K->F" in {
    forAll(temperatures) {
      (c, f, k) =>
        converter.convert(Fahrenheit(f), Kelvin()).t should (be >= k - e and be <= k + e)
        converter.convert(Kelvin(k), Fahrenheit()).t should (be >= f - e and be <= f + e)
    }
  }
}
