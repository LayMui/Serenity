package todoMVC.stepdefinitions;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import todoMVC.pageobjects.TODOPage;
import todoMVC.tasks.AddTodo;
import todoMVC.tasks.MarkAs;

public class TODOManagementStepDefinitions {

  private final Logger log = LoggerFactory.getLogger(TODOManagementStepDefinitions.class);

  TODOPage todoPage;

  @Before(value = "@todo")
  public void setTheStage() {
    OnStage.setTheStage(new OnlineCast());
  }

  @After(value = "@todo")
  public void drawTheCurtain() {
    OnStage.drawTheCurtain();
  }

  @Given("^(.*) is at the todoMVC url page$")
  public void jamesIsAtTheTodoMVCUrlPage(String actor) {
    theActorCalled(actor).attemptsTo(Open.browserOn().the(todoPage));
  }

  @When("^James add task \"([^\"]*)\" to the list$")
  public void jamesAddTaskToTheList(String task) {
    theActorInTheSpotlight()
        .should(
            eventually(
                seeThat(
                    WebElementQuestion.the(TODOPage.TODO_FIELD),
                    WebElementStateMatchers.isVisible())));
    theActorInTheSpotlight().attemptsTo(AddTodo.forItem(task));
  }

  @Then("^James is able to see the active task \"([^\"]*)\" displayed$")
  public void jamesIsAbleToSeeTheActiveTaskDisplayed(String task) {
    theActorInTheSpotlight()
        .should(
            eventually(
                seeThat(
                    WebElementQuestion.the(TODOPage.TODO_LIST(task)),
                    WebElementStateMatchers.isVisible())));
  }

  @When("^James mark task \"([^\"]*)\" in the list as completed$")
  public void jamesMarkTaskInTheListAsCompleted(String task) {
    jamesAddTaskToTheList(task);
    jamesIsAbleToSeeTheActiveTaskDisplayed(task);

    theActorInTheSpotlight()
        .should(
            eventually(
                seeThat(
                    WebElementQuestion.the(TODOPage.TODO_ITEM_TOGGLE),
                    WebElementStateMatchers.isVisible())));
    theActorInTheSpotlight().attemptsTo(MarkAs.Completed(task));
  }

  @Then("^James is able to see the task \"([^\"]*)\" strike off$")
  public void jamesIsAbleToSeeTheTaskStrikeOff(String task) {

    theActorInTheSpotlight()
        .should(
            eventually(
                seeThat(
                    WebElementQuestion.the(TODOPage.TODO_ITEM_STRIKEOFF(task)),
                    WebElementStateMatchers.isVisible())));
  }
}
