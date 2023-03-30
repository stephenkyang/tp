# User Guide

## Introduction

BudgetBuddy is a one stop Command Line Interface application that helps you to keep track of anything finance related!
You can keep track of your expenses better by creating and editing custom budgets, and also taking note of what deposits
you have to have a better understanding of your overall spending and saving.

## System requirements

BudgetBuddy is built using Java version 11. Ensure that you are running Java version 11 on your device.

## Installation guide

1. Download the `budgetbuddy.jar` file under [Releases](https://github.com/AY2223S2-CS2113-W15-3/tp/releases)
2. Copy the file into a folder of your choice. Ensure that you do not have `data.json` in the same directory.
3. Launch your command prompt/terminal and navigate to the folder where `budgetbuddy.jar` is located.
4. If you are using **command prompt or powershell in Windows**, change the code page to UTF-8 by typing `chcp 65001`.
5. Run the following command: `java -jar duke.jar`

You will be then greeted by Duke as shown below:

```
Could not locate data.json file. A file will be automatically created after an action!
_______________

 █▀▀█ █   █ █▀▀▄ █▀▀▀ █▀▀ ▀▀█▀▀ 　  █▀▀█ █  █ █▀▀▄ █▀▀▄ █  █ 
 █▀▀▄ █   █ █  █ █ ▀█ █▀▀   █   　  █▀▀▄ █  █ █  █ █  █ █▄▄█ 
 █▄▄█  ▀▀▀  ▀▀▀  ▀▀▀▀ ▀▀▀   ▀   　  █▄▄█  ▀▀▀ ▀▀▀  ▀▀▀  ▄▄▄█
Welcome to BudgetBuddy! What can I do for you?
_______________

```

## Features



## Commands

###### Commands that have the keyword [optional] means it is not required to key in after!

### 1. Help

To know what commands are available, simply type in `help`!

```
help
_______________
Budget Buddy helps you to manage your finances better.
Budget Buddy has 3 main categories:
1. Budget
   - Choose how much money you want to allocate to a budget of your specified name
2. Expense
   - track how much money you have spent, and link it to a certain budget of yours
3. Deposit 
   - track how much money you have earned or received
4. Stats 
   - shows the progress on spending so far as well as the total number of deposits
To learn the specific commands for each category, input "(category) help"
_______________
```

You can also know what are the actions for a specific category (e.g. budget)!
```
budget help
_______________
1. You can add a monthly budget: 
   budget add /c (category name) /l (spending limit)
2. You can modify an existing budget: 
   budget set /c (category) /l (spending limit)
3. You can remove a monthly budget: 
   budget del /c (category)
4. You can list all budgets: 
   budget list
5. You can search for budgets that include a certain keyword: 
   budget find /c (keyword)
_______________
```

### 2. Budgets

To add a budget, type the name of the budget you want followed by the spending limit:
budget add /c (category name) /l (spending limit)
```
budget add /c food /l 600.00
_______________
Successfully added food with limit of $600.00
There are 2 budget categories.
_______________
```

You can also set a modify that was wrongly inserted:
budget set /c (category) /l spending limit
```
budget set /c food /l 500.00
_______________
Successfully modified food to have a limit of $500.00
There are 2 budget categories.
_______________
```

If you want to remove a certain budget,
budget del /c (category)
```
budget del /c transport
_______________
Successfully deleted transport.
There are 1 budget categories.
_______________
```

Finally, list down the budgets that show each progress.
```
budget list [optional] /m 
_______________
Your budget list for Mar 2023:
1. food      ░░░░░░░░░░░░░░░░░░░░ ($0.00/500.00)
2. transport ░░░░░░░░░░░░░░░░░░░░ ($0.00/500.00)
_______________
```

### 3. Deposits



### 4. Expenses

To add an expense, type the category of the expense followed by the name, amount
and date.
(If no date is listed, it is treated as today) : 
expense add /c (category name) /n (name) /a (amount) [optional] /d DD-MM-YYYY
```
expense add /c transport /n mrt /a 2.50 /d 01-03-2023
_______________
The following expense has been added:
1. [transport] mrt ($2.50) on 01 Mar 2023
_______________
```

If you want to remove a specific expense:
expense del /n (expense number)
```
expense del /n 2
_______________
The following expense has been removed:
2. [transport] bus ($0.50) on 31 Mar 2023
_______________
```

You can alo list the expenses made within a specific start and end date, with an
optional modifier for the category of the expenses in that timeframe: 
expense list [optional] /c (category name) /f DD-MM-YYYY /t DD-MM-YYYY
```
expense list /f 01-02-2023 /t 28-02-2023
_______________
Here are your expenses from 01 Feb 2023 to 28 Feb 2023 for all:
3. [food] kfc ($30.00) on 01 Feb 2023
_______________
```

If you want to find a specific expense: 
expense find /n (keyword)
```
expense find /n mrt
_______________
Here are the expenses you searched:
1. [transport] mrt ($2.50) on 01 Mar 2023
_______________
 
```

If you want to delete expenses in a certain timeframe, with an optional modifier
for the category of expenses: 
expense clear [optional] /c (category name) /f DD-MM-YYYY /t DD-MM-YYYY
```
expense clear /c food
_______________
These are the expenses cleared:
3. [food] kfc ($30.00) on 01 Feb 2023
_______________
```


### 5. Stats



### 6. Exiting BudgetBuddy

You can exit the application using the `exit` command.

Upon exiting BudgetBuddy, BudgetBuddy will save the current tasks in the list to a local text file in your device.
These saved tasks will be imported into Duke when it is subsequently launched in the future,
ensuring that you do not lose track of you tasks.