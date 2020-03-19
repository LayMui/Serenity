package todoMVC.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://todomvc.com/")
public class TODOPage extends PageObject {

  public static final Target TODO_FIELD =
      Target.the("new todo").locatedBy("//input[@class='new-todo']");

  public static final Target TODO_LIST(String item) {
    String locator = String.format("//div[contains(@class, 'view') and contains(., \'%s\')]", item);
    return Target.the("new todo displayed").locatedBy(locator);
  }

  public static final Target TODO_ITEM_TOGGLE =
      Target.the("new todo check as completed").locatedBy("css:.todo-list li.todo");

  public static final Target TODO_ITEM_STRIKEOFF(String item) {
    String locator = String.format("//label[contains(text(), \'%s\')]", item);
    return Target.the("new todo check as strike off").locatedBy(locator);
  }
}
