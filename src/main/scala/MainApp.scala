
import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage

object MainApp
{
  def main(args: Array[String]) //usually we would use extends App
  {
    Application.launch(classOf[HelloWorld], args: _*)
  }
}

class HelloWorld extends Application
{
  override def start(primaryStage: Stage)
  {
    primaryStage.setTitle("Hello World!")
    val btn = new Button
    btn.setText("Say 'Hello World'")
    btn.setOnAction((e: ActionEvent) => {
      println("Hello World!")
      println(e)
    })

    val root = new StackPane
    root.getChildren.add(btn)
    primaryStage.setScene(new Scene(root, 300, 250))
    primaryStage.show
  }

}