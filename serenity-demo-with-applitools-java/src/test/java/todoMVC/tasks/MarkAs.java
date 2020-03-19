package todoMVC.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import todoMVC.pageobjects.TODOPage;

public class MarkAs {

  public static Performable Completed(String itemDescription) {
    return Task.where(
        "{0} attempts to mark todo #itemDescription as completed",
        Click.on(TODOPage.TODO_ITEM_TOGGLE));
  }
}
