package todoMVC.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import todoMVC.pageobjects.TODOPage;

public class AddTodo {

  public static Performable forItem(String itemDescription) {
    return Task.where(
            "{0} attempts to add todo #itemDescription",
            Click.on(TODOPage.TODO_FIELD),
            Enter.theValue(itemDescription).into(TODOPage.TODO_FIELD).thenHit(Keys.ENTER))
        .with("item")
        .of(itemDescription);
  }
}
