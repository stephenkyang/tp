# Developer Guide

## Introduction

Budget Buddy aims to help those who want a Command Line Interface App that is a one-stop solution to keep track of their
expenses, help them with budgeting and also be help them to visualise what they have spent.

Overspending and the lack of savings is a significant problem found in most fresh graduates. We want to create tools
necessary for them so that they can realize their actual spending and achieve financial freedom in the future.

## Setting up the project

## Design

### General Overview

Below is an architectural diagram that describes the overview of how BudgetBuddy works.

![ArchitectureDiagram.png](images/ArchitectureDiagram.png)

When the user initialises Budget Buddy, the logic of Budget Buddy will check if there is any pre-existing stored data.
This persistence is done by using Gson read stored data into classes that will be used in
Budget Buddy. As the user interacts with the UI, the logic will change the data of Budget Buddy, which is then written
in Json format to update the storage. This process continues until the user exits Budget Buddy.

### Main Component

![MainSequence.png](images/MainSequence.png)

**This diagram only shows the main overview of the application. You can refer to other components for more information.**

In the main class, the main method first calls startApplication() which initalizes the Log Manager for logging (disabled in production stage) and Ui object for printing output messages. After initialized, it will then attempt to
import the user's data from `data.json`. If there is no file, it will initalize a blank data. If importing fails, the
application will print an error message and terminate. Once done, it will greet the user and display the budget progress (for returning users).

The application then runs an infinite loop to take in and execute commands, until when the inputs `exit` to exit the
application. After which, it will output a bye message and exits the application safely.

### Command Component

![Command.png](images/Command.png)

**There are some variables and methods that are not shown in the class diagram for easier readability.**

The command consists of three components: Enum CommandEnum, Abstract class Command and Class CommandParser. Each of them
plays a role in retrieving the commands input by the user and redirecting to the correct components.

For every command that the user wants to execute, the input of the command must be in this format as follows:
> `(command) (action) (parameters) [optional parameters]`

**Command and action names are not case sensitive, but values are.**
**Also, it is possible to rearrange the order of parameters.**

An example of a command can be seen in the user guide.

For each parameter, the type of value must either be: `integer`, `double`, `date`, or `string`, depending on the parameter's specification.

- integer: Input must be a valid integer.
- double: Input must be a valid double that is positive, must not contain alphabet and at most 2 decimal places.
- date: Input must be a valid date in the format `DD-MM-YYYY`, and year must be in 4 digits.
- string: Input can be in any alphanumeric but at most 30 characters.

Note that the value must not contain any slash (`/`), as it interferes with the parameter format.

#### CommandEnum

The Enum class CommandEnum contains all the commands that are available in the application, as shown in the diagram in Command Component. These are the commands that can only be input in the command line.

#### Command

The abstract class Command extends to several command classes (e.g. `BudgetCommand`, `DepositCommand`, `ExpenseCommand`) and contains abstract (execute() and isExit()) and normal methods.

Each child (extended) class has a list of actions for its command and the list of required & optional parameters for each action. These list of actions and parameters are to be used in the CommandParser class, where it validates the input given by the user. For commands such as Help and Exit, no actions and parameters are required.

In the execute() method, the method is executed from main, where it will go to the action class (eg `BudgetAction`) and run the requested action such as `add` along with the required & optional parameters.
After execute() is done, isExit() controls the termination of the application by returning a boolean.

#### CommandParser

![CommandParserSequence.png](images/CommandParserSequence.png)

The `CommandParser` class is responsible for validating the input given by the user, which in return will pass a Command object (containing the user's requested action) that can be executed.

Flow of the Command Parser:

1. From the main function, it calls CommandParser.parse() with the input from user.
2. Command name from the input is retrieved, returning a CommandEnum
3. Object command is created and initialized by constructor, based on the CommandEnum
4. List of available actions for that command is retrieved. **Commands such as Help and Exit do not have any actions or parameters, which the command will be returned immediately.**
5. Action from the input is checked from the list of actions to see if it valid. Then, it will set the action in command object.
6. Based on the action, it will retrieve the required and optional parameters from input. Then, it will set the parameters in the command object.
7. Object command is returned to the main, which it will be executed.

## Product scope

### Item Component

The main 4 classes of BudgetBuddy are the `budget` , `deposit`, `expense` and `stats` class. Users are able to add, store and visualise the date relate to each of these classes. Each of these classes are modelled as an `Item`.

![Item.png](images/Item.png)

### Budget Component

#### BudgetCommand Class

The `BudgetCommand` class contains methods that are related to the `Budget` function. Users are able to create
new budgets, which are stored in a budget list. The category word from the user's input is taken from the first word of
the users input,
and the second word of the users input is the action word. The first word is processed through the `CommandParser`
class. If the
Command word is "budget", it will be processed into the `BudgetCommand` class. The action word is then processed into
the `BudgetAction` class.
The class diagram below shows how the `BudgetCommand` parent class is implemented, as well as its extended classes.

![BudgetCommand.png](images/BudgetCommand.png)

#### BudgetCommand Sequence

Once a string `input` from the user has been deemed as a `Budget` command as explained in the `CommandParser` sequence
diagram above,
the `input` will be passed into the `BudgetCommand` class. This class determines what budget commands to carry out to
update the `data` stored in the `Budget` and `Expense` array lists, as shown in the sequence diagram below.

![BudgetCommandSequence.png](images/BudgetCommandSequence.png)

### Deposit Component

### DepositCommand Class

The `DepositCommand` class contains methods that relate to the execution of the deposit functionality in BudgetBuddy.
Users can
create new deposits with a timestamp (if necessary). These deposits are stored in a deposit list and users can delete
deposits, find
deposits using keywords, and list all deposits.

The user's input is split by the parser in the `CommandParser` class and is redirected to the 'DepositCommand' class if
the first word
is "deposit." Based on the second word, a method in `DepositAction` class is called corresponding to the command
requested by the user.

Attached below is how the `DepositCommand` class is implemented along with its relation with the other `Data` classes
and the abstract
`Command` class.

![DepositCommand.png](images/DepositCommand.png)

### DepositCommand Sequence

![img.png](images/DepositCommandSequence.png)

### Design & Implementation of the Deposit Feature

Like all other functionalities of BudgetBuddy, the deposit feature is heavily modularized and designed with an OOP lens.
Because of this,
the parsing of user input, the parsers for each feature, and each feature's actions are all in separate classes.

On a high level, the deposit feature starts with `CommandParser` taking in the input and choosing which `Command` class
to execute from.
This happens with all user input in BudgetBuddy. Then if the first word is "deposit," the `execute` function of
the `DepositCommand` class
will run, creating a new `DepositAction` class. The `execute` function will run a method corresponding to what the user
inputs. The design
of this three class system is meant to modularize the different aspects of the internal logic so future problems would
be encapsulated in a specific location.

### Expense Component

### ExpenseCommand Class

The `ExpenseCommand` class contains methods that relate to the execution of the expense functionality in BudgetBuddy.
Users can
create new expense entries with a timestamp (if necessary). These expenses are then stored in an expense list and users
can delete existing
expenses, find expenses using keywords, and list all expenses according to their categories.

The user input is split by the parser in the `CommandParser` class and is redirected to the `ExpenseCommand` class if
the first word is
"expense". Based on the second command word, a method in `CommandAction` class is called corresponding to the command
entered by the user.

Attached below is how the `ExpenseCommand` class is implemented along with its relation with the other `Data` classes
and the abstract
`Command` class.

![ExpenseCommand.png](images/ExpenseCommand.png)

### ExpenseCommand Sequence

Once a string `input` from the user has been deemed as an `Expense` command, the `input` will be passed into the
`ExpenseCommand` class. This class determines what expense commands to carry out to update the `data` stored in the
`Budget` and `Expense` array lists, as shown in the sequence diagram below.

![img.png](images/ExpenseCommandSequence.png)

### Design & Implementation of the Expense Feature

The expense feature is similarly modularized and designed with an OOP lens. As such, its functionalities have been
separated into different
classes such as the parsing of user input, and the parsing and execution of each of its features.

On a high level, the expense feature starts with `CommandParser` taking in the input and choosing which `Command` class
to execute from.
This happens with all user input in BudgetBuddy. Then, if the first word is "expense", the `execute` function of
the `ExpenseCommand` class
will run, creating a new `ExpenseAction` class. The `execute` function will run a method corresponding to what the user
inputs. The design
of this three class system is meant to modularize the different aspects of the internal logic so future problems would
be encapsulated in
a specific location.

### Stats Component

### StatsCommand Class

The 'StatsCommand' class contains methods that are related to the execution of the stats functionality in BudgetBuddy.
Users can
use this command to view all the details of their expenses, budgets and deposits in the current month. Through this
feature, users will know
their current progress and if their expenses have exceeded their budget.

![StatsCommand.png](images/StatsCommand.png)

### Design & Implementation of the Stats Feature

The stats feature, just like all other features, also is designed and implemented to incorporate good OOP. Therefore,
there are
separate classes for each part of the Stats Feature, which includes StatsAction, StatsUIResponse and StatsCommand.

![img.png](images/StatsCommandSequence.png)

### Others

#### Exception Component

For invalid commands and application errors (such as file), BudgetBuddy will display an error message which requires the user to rectify his/her input or do something, including restarting the application.

#### File IO Component

The input and output of a file is handled using Gson, a third-party plugin that can help to read/write .json file in the Json format. The data file `data.json` (default) is created under the directory that the user is running at after an action is executed in the application. If the application finds the file to be blank, or the format of json is corrupted, it prompts the user to manually delete the file before starting the application.
**Therefore, It is not recommended for users to edit the file manually.**

## Appendix A: User Stories

| Version | As a ...       | I want to ...                                                                   | So that I can ...                                                            |
|---------|----------------|---------------------------------------------------------------------------------|------------------------------------------------------------------------------|
| v1.0    | quick user     | enter my inputs immediately rather than clicking and typing                     | have a convenient way of managing my budget                                  |
| v1.0    | new user       | know how to setup my application                                                | use the application and insert my financial data                             |
| v1.0    | forgetful user | get all the commands I can input                                                | know what are the commands available                                         |
| v1.0    | forgetful user | know the syntax for each action                                                 | know how to perform an action                                                |
| v1.0    | careless user  | receive an error message when entering a wrong command                          | rectify my errors in the next input                                          |
| v1.0    | user           | add my monthly budget for a category (eg. transportation, utilities, food etc.) | have an organised view of my overall budget                                  |
| v1.0    | user           | list down my budget for each category                                           | tell if my salary/earnings is sufficient for the total amount of all budgets |
| v1.0    | user           | modify my budget for a category                                                 | allocate more/less budget to a category                                      |
| v1.0    | user           | delete a budget category                                                        | remove a budget category that I wrote the category name wrongly              |
| v1.0    | user           | add in additional deposit from bank interest/investments                        | increase my savings                                                          |
| v1.0    | user           | remove my additional deposits that I added                                      | delete wrong inputs                                                          |
| v1.0    | user           | list down all my additional deposits                                            | tell how much additional deposits I have made in total                       |
| v1.0    | user           | add an expense for an item I have spent on                                      | keep track of what I have spent on and how much I have spent                 |
| v1.0    | user           | set an expense to a category                                                    | organise my expense history and deduct from the selected category            |
| v1.0    | user           | delete an expense                                                               | delete wrong inputs                                                          |
| v1.0    | user           | view my expense history                                                         | see how much I have spent overall                                            |
| v1.0    | user           | view the overall statistics                                                     | know what is my progress on spending and saving for the month                |
| v1.0    | user           | load my deposit, budget and expense data                                        | retain all my information when I launch the application again                | 
| v1.0    | user           | save my deposit, budget and expense data                                        | retain all my information when I launch the application again                |
| v1.0    | leaving user   | know if I exited the application safely                                         | ensure that there are no errors in the application                           |
| v2.0    | careless user  | get all the actions of a command I can input                                    | know what actions are available                                              |
| v2.0    | user           | show the current progress of my budget                                          | check if I have overspent a category                                         |
| v2.0    | user           | show my budget results for a certain month/year                                 | check my past budget results                                                 |
| v2.0    | user           | find deposit(s) based on name                                                   | recall how much I made from a deposit                                        |
| v2.0    | user           | list down my deposits and/or expenses at a date range                           | check my past deposits/expenses that I've added                              |
| v2.0    | user           | list down my expenses based on category                                         | know what I've spent for a specific category                                 | 
| v2.0    | user           | clear deposits and/or expenses at a date range (or all)                         | delete quickly without doing one by one                                      |
| v2.0    | user           | clear expenses based on category                                                | delete quickly without doing one by one                                      |
| v2.0    | user           | display more stats information such as deposits and expenses                    | have a brief overview of a month’s spending                                  |
| v2.0    | user           | view the overall statistics for a certain month/year                            | check my past results                                                        |
| v2.0    | returning user | be reminded of my budget progress when I launch the app                         | realise my progress and minimise my spending                                 |

## Appendix B: Non-Functional Requirements

1. Works on any common operating systems (Windows, Mac OS, Linux) with Java 11 installed.
2. A user with average typing speed should take up to 7 seconds for the longest command (expense add).
3. Data of the application is still preserved when migrating from one computer to another.

## Appendix C: Glossary

* *Budget* - A category of a budget (e.g. Transport, Food, Electricity) with the maximum spending budget indicated by
  the user.
* *Deposit* - Additional earnings (e.g. Lottery, Bank interest) that is made by the user
* *Expense* - An item that the user has spent on, which must be related to a Budget.
* *Stats* - Statistics of savings and expenditure

## Appendix D: Instructions for manual testing

### Launch

Before starting, ensure that you have Java 11 installed on your operating system.

1. Download the latest [file](https://github.com/AY2223S2-CS2113-W15-3/tp/releases) and place in your preferred directory.
2. Launch a command prompt or terminal and navigate to the directory where the file is located.
3. Run the command `java -jar budgetbuddy.jar`
4. Expected Outcome:
    - Budget Buddy will display a greeting message.
    - For first time users, it will display a warning message showing that the data file is empty.
    - For existing users with valid data file, it will show the progress for all budgets.

If you encounter the greeting message displaying as "????", please change your command line/terminal's character encoding to UTF-8.

- For Windows, if you are using command prompt or powershell, change the code page by typing `chcp 65001` in the prompt.
- For Mac, change the Text encoding to `UTF-8` under international section in your Terminal Settings.
- For others, please find a setting that can change the encoding to `UTF-8`. Else if it doesn't work, please test it on another computer.

### Test Cases

Note that for the test cases listed for Budget, Deposit, Expense and Stats Commands, any wrong input is similiar (not the same) to what it has been listed in General Commands. **Each test case is independent from each other and should be start as a first-time user (delete the data file after each case!)**

#### General Commands

1. Invalid command

- Type `asd`
- Expected output: An error message `Invalid command. Use help for the list of commands.` is shown.

2. Valid command with invalid action

- Type `budget asd`
- Expected output: An error message `Invalid action for budget. Use these actions: add, set, del, list, help` is shown.
- Note that the actions listed depends on the valid command entered.

3. Valid command and action with missing required parameters

- Type `budget add`
- Expected output: An error message `Invalid parameters. Example: budget add /c transport /a 3000.00` is shown.
- Note that the parameters listed depends on the valid command and action entered.

4. Valid command and action with invalid required parameters

- Type `budget add /c transport /a asd`
- Expected output: An error message `Parameter /a: Input must be a valid positive number, with at most 2 decimal places. Invalid parameters. Example: budget add /c transport /a 3000.00` is shown.
- Note that the message of the invalid parameter depends on the type of parameter set by the application.

5. Valid command and action with invalid optional parameters

- Type `deposit list /f a`
- Expected output: An error message `Parameter /f: Input must be in a valid date in this format "DD-MM-YYYY". Invalid parameters. Example: deposit list [/f] 01-01-2023 [/t] 01-03-2023` is shown.
- Note that the message of the invalid parameter depends on the type of parameter set by the application.

6. Valid command and action with valid required parameters but invalid optional parameters

- Type `deposit add /n lottery /a 3000.00 /d a`
- Expected output: An error message `Parameter /d: Input must be in a valid date in this format "DD-MM-YYYY". Invalid parameters. Example: deposit add /n lottery /a 3000.00 [/d] 23-03-2023` is shown.
- Note that the message of the invalid parameter depends on the type of parameter set by the application.

#### Budget Commands

#### Add Budget

1. New Budget (Case 1)

- Type `budget add /c transport /a 1000.00`
- Expected output: A message saying that it has been successfully added with name of budget category and amount shown. Number of budget categories that you have is also shown.

2. Existing Budget (Case 2)

- Type `budget add /c transport /a 1.00`
- Type `budget add /c transport /a 2.00`
- Expected output: A message saying that the budget name is already in use.

#### Delete Budget

1. Delete Budget with no expenses (Case 1)

- Type `budget add /c transport /a 1.00`
- Type `budget del /c transport`
- Expected output: A message saying that the budget is deleted with the name shown and 0 related expenses. No. of budget categories remaining is also shown.

2. Delete Budget with 1 expense (Case 2)

- Type `budget add /c transport /a 1.00`
- Type `expense add /c transport /n mrt /a 1.00`
- Type `budget del /c transport`
- Expected output: A message saying that the budget is deleted with the name shown and 1 related expenses. No. of budget categories remaining is also shown.

3. Delete non-existing Budget (case 3)

- Type `budget del /c transport`
- Expected output: A message saying that the budget does not exist.

#### List Budget

1. List (Case 1)

- Type `budget add /c transport /a 1.00`
- Type `budget list`
- Expected output: A message that shows the current month, year and budget progress, consisting the name, progress bar, total expenses and budget amount.

2. List with only month (before today's month) (Case 2)

- Type `budget add /c transport /a 1.00`
- Type `budget list /m 1`
- Expected output: A message that shows the month (January) in current year and budget progress, consisting the name, progress bar, total expenses and budget amount.

3. List with only year (Case 3)

- Type `budget add /c transport /a 1.00`
- Type `budget list /y 2023`
- Expected output: A message that shows the current month, year 2023 and budget progress, consisting the name, progress bar, total expenses and budget amount.

4. List with month and year (before today's month and year) (case 4)

- Type `budget add /c transport /a 1.00`
- Type `budget list /m 1 /y 2023`
- Expected output: A message that shows the month (January), year 2023 and budget progress, consisting the name, progress bar, total expenses and budget amount.

#### Help Budget

1. Help (Case 1)

- Type `budget help`
- Expected output: A message that shows all the available actions.

#### Deposit Commands

#### Add Deposit

1. New Deposit (today's date) (Case 1)

- Type `deposit add /n lottery /a 3000.00`
- Expected output: A message saying that it has been successfully added with deposit no, name, amount and today's date of deposit shown.

2. New Deposit (date before today) (Case 2)

- Type `deposit add /n lottery /a 3000.00 /d 01-01-2023`
- Expected output: A message saying that it has been successfully added with deposit no, name, amount and date of deposit shown.

#### Delete Deposit

1. Delete Deposit (Case 1)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit del /n 1`
- Expected output: A message saying that it has been successfully deleted with deposit no, name, amount and date of deposit shown.

2. Delete non-existing Deposit (Case 2)

- Type `deposit del /n 1`
- Expected output: A message saying that it the number is not valid.

#### Find Deposit

1. Find Deposit (Case 1)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit find /n lottery`
- Expected output: A message that shows the list of deposits found based on the name.

#### Clear Deposit

1. Clear All Deposit (Case 1)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit clear`
- Expected output: A message that shows all the deposits cleared.

2. Clear All Deposit from a specified date (Case 2)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit clear /f <A date that is before today in DD-MM-YYYY>`
- Expected output: A message that shows all the deposits cleared from that specified date.

3. Clear All Deposit until a specified date (Case 3)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit clear /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the deposits cleared until that specified date.

4. Clear All Deposit on a date range (Case 4)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit clear /f <A date that is today in DD-MM-YYYY> /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the deposits cleared on that date range.

#### List Deposit

1. List All Deposit (Case 1)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit list`
- Expected output: A message that shows all the added deposits.

2. List All Deposit from a specified date (Case 2)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit list /f <A date that is before today in DD-MM-YYYY>`
- Expected output: A message that shows all the added deposits from that specified date.

3. List All Deposit until a specified date (Case 3)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit list /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the added deposits until that specified date.

4. List All Deposit on a date range (Case 4)

- Type `deposit add /n lottery /a 3000.00`
- Type `deposit list /f <A date that is today in DD-MM-YYYY> /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the added deposits on that date range.

#### Help Deposit

1. Help (Case 1)

- Type `deposit help`
- Expected output: A message that shows all the available actions.

#### Expense Commmands

#### Add Expense

1. New Expense (today's date) (Case 1)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Expected output: A message saying that it has been successfully added with expense no, category of the budget, name, amount and today's date of expense shown.

2. New Expense (date before today) (Case 2)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00 /d 01-01-2023`
- Expected output: A message saying that it has been successfully added with expense no, category of the budget, name, amount and date of expense shown.

#### Delete Expense

1. Delete Expense (Case 1)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense del /n 1`
- Expected output: A message saying that it has been successfully deleted with expense no, category of the budget, name, amount and date of expense shown.

2. Delete non-existing Expense (Case 2)

- Type `expense del /n 1`
- Expected output: A message saying that it the number is not valid.

#### Find Expense

1. Find Expense (Case 1)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense find /n mrt`
- Expected output: A message that shows the list of expenses found based on the name.

#### Clear Expense

1. Clear All Expense (Case 1)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense clear`
- Expected output: A message that shows all the expenses cleared.

2. Clear All Expense on specified budget category name (Case 2)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense clear /c transport`
- Expected output: A message that shows all the expenses cleared on the specified budget category name.

3. Clear All Expense from a specified date (Case 3)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense clear /f <A date that is before today in DD-MM-YYYY>`
- Expected output: A message that shows all the expenses cleared from that specified date.

4. Clear All Expense until a specified date (Case 4)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense clear /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the deposits cleared until that specified date.

5. Clear All Expense on a date range (Case 5)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense clear /f <A date that is today in DD-MM-YYYY> /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the expenses cleared on that date range.

6. Clear All Expense on specified budget category name and a date range (Case 6)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense clear /c transport /f <A date that is today in DD-MM-YYYY> /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the expenses cleared on the specified budget category name and date range.

#### List Expense

1. List All Expense (Case 1)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense list`
- Expected output: A message that shows all the added expenses.

2. List All Expense on specified budget category name (Case 2)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense list /c transport`
- Expected output: A message that shows all the added expenses on the specified budget category name.

3. List All Expense from a specified date (Case 3)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense list /f <A date that is before today in DD-MM-YYYY>`
- Expected output: A message that shows all the added expenses from that specified date.

4. List All Expense until a specified date (Case 4)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense list /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the added expenses until that specified date.

5. List All Expense on a date range (Case 5)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense list /f <A date that is today in DD-MM-YYYY> /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the added expenses on that date range.

6. List All Expense on specified budget category name and a date range (Case 6)

- Type `budget add /c transport /a 3000.00`
- Type `expense add /c transport /n mrt 1.00`
- Type `expense list /c transport /f <A date that is today in DD-MM-YYYY> /t <A date that is today in DD-MM-YYYY>`
- Expected output: A message that shows all the added expenses on the specified budget category name and date range.

#### Help Expense

1. Help (Case 1)

- Type `expense help`
- Expected output: A message that shows all the available actions.

#### Stats Commands

#### Show Stats

1. Show statistics (Case 1)

- Type `budget add /c transport /a 3000.0`
- Type `deposit add /n lottery /a 3000.0`
- Type `expense add /c transport /n mrt /a 1.00`
- Type `stats show`
- Expected output: A message that shows the budget progress, total amount of deposit and expense, and overall progress for current month and year.

2. Show statistics with month (Case 2)

- Type `budget add /c transport /a 3000.0`
- Type `deposit add /n lottery /a 3000.0`
- Type `expense add /c transport /n mrt /a 1.00`
- Type `stats show /m 1`
- Expected output: A message that shows the budget progress, total amount of deposit and expense, and overall progress for month January and current year.

3. Show statistics with year (Case 3)

- Type `budget add /c transport /a 3000.0`
- Type `deposit add /n lottery /a 3000.0`
- Type `expense add /c transport /n mrt /a 1.00`
- Type `stats show /y 2023`
- Expected output: A message that shows the budget progress, total amount of deposit and expense, and overall progress for current month and year 2023.

4. Show statistics with month and year (Case 4)

- Type `budget add /c transport /a 3000.0`
- Type `deposit add /n lottery /a 3000.0`
- Type `expense add /c transport /n mrt /a 1.00`
- Type `stats show /m 1 /y 2023`
- Expected output: A message that shows the budget progress, total amount of deposit and expense, and overall progress for month January and year 2023.

5. Show statistics with all deposits (Case 5)

- Type `budget add /c transport /a 3000.0`
- Type `deposit add /n lottery /a 3000.0`
- Type `expense add /c transport /n mrt /a 1.00`
- Type `stats show /v d`
- Expected output: A message that shows the budget progress, list of deposits, total amount of deposit and expense, and overall progress for current month and year.

6. Show statistics with all expenses (Case 6)

- Type `budget add /c transport /a 3000.0`
- Type `deposit add /n lottery /a 3000.0`
- Type `expense add /c transport /n mrt /a 1.00`
- Type `stats show /v e`
- Expected output: A message that shows the budget progress, list of expenses, total amount of deposit and expense, and overall progress for current month and year.

7. Show statistics with all deposits and expenses (Case 7)

- Type `budget add /c transport /a 3000.0`
- Type `deposit add /n lottery /a 3000.0`
- Type `expense add /c transport /n mrt /a 1.00`
- Type `stats show /v de`
- Expected output: A message that shows the budget progress, list of deposits, list of expenses, total amount of deposit and expense, and overall progress for current month and year.

#### Help Stats

1. Help (Case 1)

- Type `stats help`
- Expected output: A message that shows all the available actions.

#### Help Command

1. Help (Case 1)

- Type `help`
- Expected output: A message that shows all the available commands.

#### Exit Command

1. Exit (Case 1)

- Type `exit`
- Expected output: Exits the application with a bye message.

### File Testing

In the application, the data file will be updated/created (if it doesn't exist) whenever commands `budget`, `deposit`, `expense` are executed successfully.

- Deleting the data file and relaunching the application will result in a new data.
- If the data file is empty (still exists), or the contents of the data file could not be Json parsed, the application will detect the file as corrupted, and the user will need to delete the file and relaunch the application.

## Appendix E: Acknowledgements

[Gson: Java serialization/deserialization library](https://github.com/google/gson)