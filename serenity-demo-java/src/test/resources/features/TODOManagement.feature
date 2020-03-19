Feature: TODO Management
  In order to remember the tasks I want to do
  As a todoMVC user
  I want to manage my todo list

  Background:
    Given James is at the todoMVC url page

  @smoke @todo
  Scenario Outline: Add tasks to the todo list
  In order to remember the list tasks to do for this week
  As a todoMVC user James
  James wants to add tasks to the todo list and marked as active
    When James add task "<task>" to the list
    Then James is able to see the active task "<task>" displayed

    Examples:
      | task |
      | Return the BDD In Action to NLB Orchard Branch |


   @test @todo
  Scenario Outline: Mark task as completed in the todo list
  In order to mark the task as completed
  As a todoMVC user James
  James wants to mark the task in the todo list as completed
    When James mark task "<task>" in the list as completed
    Then James is able to see the task "<task>" strike off

    Examples:
      | task |
      | Return the BDD In Action to NLB Orchard Branch |

