import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.{Insets, Pos}
import javafx.scene.Scene
import javafx.scene.control.{Button, Label, Separator, TextField}
import javafx.scene.layout._
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.Stage

object PizzaStore {
  def main(args: Array[String]) {
    Application.launch(classOf[PizzaStore], args: _*)
  }
}

class PizzaStore extends Application  {
  override def start(stage: Stage) {
    val borderPane = new MainBorderPane
    val scene = new Scene(borderPane, 900, 1200)
//    scene.getStylesheets.add(getClass.getResource("pizza.css").toExternalForm) // need pizza.css file in resrouces!!
    stage.setScene(scene)
    stage.setTitle("Val's browser")
    stage.show
  }
}

class MainBorderPane extends BorderPane {

  var url = "http://ss.com"

  // others need to be able to get this to add an action to it
  val placeOrderButton = new Button
  val cancelOrderButton = new Button
  cancelOrderButton.setText("Go To Address")
  cancelOrderButton.setOnAction((e: ActionEvent) => {
    url = textField.getText
    println(s"Going to $url")
    webEngine.load(url)
    //here you would call your database function or something to actually clear an order
  })

  val toggleButton = new Button
  toggleButton.setText("Toggle ON")
  toggleButton.setOnAction((e: ActionEvent) => {
    println("Toggling", toggleButton.getText)
    //generally you would store your toggle in some variable but we can get the state from the button as well
    toggleButton.setText(if (toggleButton.getText == "Toggle ON") "Toggle OFF" else "Toggle ON")
    //here you would call your database function or something to actually clear an order
  })



  val alsPizzaLabel = new Label("Val's quick browser like app")
  //label.setId("fancytext")   //css approach
  alsPizzaLabel.setFont(new Font("Arial", 24))
  alsPizzaLabel.setTextFill(Color.TOMATO)

  placeOrderButton.setText("Go Home")
  placeOrderButton.setOnAction((e: ActionEvent) => {
    println(s"Going home t $url")
    webEngine.load(url)
  })
  placeOrderButton.setFont(new Font("Arial", 20))

  val textField = new TextField()
  textField.setText(url)

  // a spacer to push the visible elements up a little
  val spacer = new Region
  spacer.setPrefHeight(10)

  import javafx.collections.FXCollections
  import javafx.collections.ObservableList
  import javafx.scene.control.ListView

  val list = new ListView[String]
  val items: ObservableList[String] = FXCollections.observableArrayList("Single", "Double", "Deluxe", "Family Pizza")
  list.setItems(items)
  list.setPrefWidth(80)
  list.setPrefHeight(60)

  list.setOnScrollTo(n => {
    println("Scrolled to", n)
  })

  list.setOnMouseClicked(e => {
    println("Mouse Clicked", e.toString)
    println("Mouse Clicked", e.getTarget.toString) //then we would hav eto cast it to text
  })

  import javafx.scene.web.WebEngine
  import javafx.scene.web.WebView

  val browser = new WebView
  browser.setPrefWidth(800)
  browser.setPrefHeight(600)
  val webEngine: WebEngine = browser.getEngine
//  webEngine.load("http://ss.com")
  webEngine.load(url)

  val vbox = new VBox
  vbox.setSpacing(40)
  vbox.setAlignment(Pos.CENTER)
//  vbox.getChildren.addAll(alsPizzaLabel, placeOrderButton, spacer, cancelOrderButton, toggleButton, list, browser)
  vbox.getChildren.addAll(alsPizzaLabel, placeOrderButton, cancelOrderButton, textField, browser)

  this.setCenter(vbox)

}