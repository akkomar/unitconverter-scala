package pl.komarzewski.converter

import scala.swing._
import scala.swing.GridBagPanel._
import scala.swing.event.EditDone

object SwingTemperatureConverter extends SimpleSwingApplication {
  val converter = TemperatureConverter()

  def top = new MainFrame {
    title = "Temperature converter"

    val celsiusInput = new TextField(4)
    val fahrenheitInput = new TextField(4)
    val kelvinInput = new TextField(4)

    val fahrenheitLabel = new Label()
    val celsiusLabel = new Label()
    val fahrenheitLabel2 = new Label()

    contents = new GridBagPanel {
      def addTemperatureConversionRow(row: Int, c: Constraints, inputField: TextField,
                                      inputLabelText: String, outputLabel: Label, outputLabelText: String): Unit = {
        c.fill = Fill.Horizontal
        c.gridx = 0
        c.gridy = row
        layout(inputField) = c

        val l = new Label(inputLabelText)
        c.fill = Fill.Horizontal
        c.weightx = 0.5
        c.gridx = 1
        c.gridy = row
        layout(l) = c

        c.fill = Fill.Horizontal
        c.ipadx = 40
        c.weightx = 0.5
        c.gridx = 2
        c.gridy = row
        layout(outputLabel) = c

        val l2 = new Label(outputLabelText)
        c.fill = Fill.Horizontal
        c.weightx = 0.5
        c.gridx = 3
        c.gridy = row
        layout(l2) = c
      }

      val c = new Constraints
      val shouldFill = true
      if (shouldFill) {
        c.fill = Fill.Horizontal
      }
      c.weightx = 0.5

      addTemperatureConversionRow(0, c, celsiusInput, "°C = ", fahrenheitLabel, "°F")
      addTemperatureConversionRow(1, c, fahrenheitInput, "°F = ", celsiusLabel, "°C")
      addTemperatureConversionRow(2, c, kelvinInput, "K = ", fahrenheitLabel2, "°F")

      border = Swing.EmptyBorder(15, 10, 10, 10)
    }

    minimumSize = new Dimension(400, 10)
    centerOnScreen()

    listenTo(celsiusInput, fahrenheitInput, kelvinInput)
    reactions += {
      case EditDone(`fahrenheitInput`) =>
        val f = fahrenheitInput.text.toDouble
        val celsius = converter.convert(Fahrenheit(f), Celsius()).t
        celsiusLabel.text = f"$celsius%2.2f"
      case EditDone(`celsiusInput`) =>
        val c = celsiusInput.text.toDouble
        val fahrenheit = converter.convert(Celsius(c), Fahrenheit()).t
        fahrenheitLabel.text = f"$fahrenheit%2.2f"
      case EditDone(`kelvinInput`) =>
        val k = kelvinInput.text.toDouble
        val fahrenheit = converter.convert(Kelvin(k), Fahrenheit()).t
        fahrenheitLabel2.text = f"$fahrenheit%2.2f"
    }
  }
}
