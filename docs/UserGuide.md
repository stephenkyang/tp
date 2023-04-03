# User Guide

## Introduction

BudgetBuddy is a one stop Command Line Interface application that helps you to keep track of anything finance related!
You can keep track of your expenses better by creating and editing custom budgets, and also taking note of what deposits
you have to have a better understanding of your overall spending and saving.

## System requirements

BudgetBuddy is built using Java version 11 and only works on Windows, MAC and Linux OS. Ensure that you are running Java
version 11 on your device.

## Installation guide

1. Download the `budgetbuddy.jar` file under [Releases](https://github.com/AY2223S2-CS2113-W15-3/tp/releases)
2. Copy the file into a folder of your choice. Ensure that you do not have `data.json` in the same directory.
3. Launch your command prompt/terminal and navigate to the folder where `budgetbuddy.jar` is located.
4. Run the following command: `java -jar budgetbuddy.jar`

**NOTE: It is not recommended to edit the data.json file unless you know what you are doing!**

**Please change your command line interface's character encoding to UTF-8 if the application displays text such as
????.**

For Windows, if you are using command prompt or powershell, change the code page by typing `chcp 65001` in the prompt.
For Mac, change the Text encoding to `UTF-8` under International section in your Terminal Settings.

You will be then greeted by Budget Buddy as shown below:

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

Here are some features that Budget Buddy offers:

#### Track your expenses

You can keep track any items that you have just spent on BudgetBuddy!

#### Categorize your budget into different names

Instead of keeping your expenses into a single list, split up into different categories (e.g. Transport, Food, Bills)!

#### Keep a list of your deposits

Have extra earnings that you wish to keep track too? Fret not!

#### Show your overall budget result

Summarize your overall budget and see what needs to be improved!

#### Remind your spending

Always have a buddy to remind your spending!

#### Sort and filter deposits and expenses

You can sort deposits and expenses by categories and date too!

#### Persistent Data

Budget Buddy ensures that your data is always with us!

## Commands

###### Commands that have the keyword [optional] means it is not required to key in after!

###### Please input the parameters in correct format so as to prevent abnormal behavior.

### 1. Help

To know what commands are available, simply type in `help`!

```
help
_______________
Budget Buddy helps you to manage your finances better.
Budget Buddy has 4 main categories:
1. Budget
   - Choose how much money you want to allocate to a budget of your specified name
2. Deposit 
   - track how much money you have earned or received
3. Expense
   - track how much money you have spent, and link it to a certain budget of yours
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
   budget set /c (category name) /l (spending limit)
3. You can remove a monthly budget: 
   budget del /c (category name)
4. You can list and check each budget's status for a certain month/year: 
   budget list [optional] /m (month) /y (year in YYYY format)
_______________
```

### 2. Budgets

To add a budget, type the name of the budget you want followed by the spending limit:

The category name must be less than 30 characters.

budget add /c (category name) /l (spending limit)

```
budget add /c food /l 600.00
_______________
Successfully added food with limit of $600.00
There are 2 budget categories.
_______________
```

You can also set and modify that was wrongly inserted:

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

Note that you can only input year from 1000 to current year.

budget list [optional] /m month (1-12) /y year (in YYYY)

```
budget list
_______________
Your budget list for Mar 2023:
1. food      ░░░░░░░░░░░░░░░░░░░░ ($0.00/500.00)
2. transport ░░░░░░░░░░░░░░░░░░░░ ($0.00/500.00)
_______________
```

### 3. Deposits

If you want to create a deposit, use the following command,
(if no date is listed, it is treated as today)

The deposit name must be less than 30 characters, and the date can only be input before today.

deposit add /n (name) /a (amount) [optional] /d DD-MM-YYYY

```
deposit add /n picked up from ground /a 50.00 /d 31-03-2023
_______________
The following deposit has been added:
1. picked up from ground ($50.00) on 31 Mar 2023
_______________
```

To delete the deposit use the this command. The deposit number can be found with `deposit list`

deposit del /n (deposit number)

```
deposit del /n 1
_______________
The following deposit has been removed:
1. picked up from ground ($50.00) on 31 Mar 2023
_______________
```

To list deposits, use this command. The optional parameter are dates from the start to the end range.

deposit list [optional] /f DD-MM-YYYY /t DD-MM-YYYY

```
deposit list
_______________
Here are your deposits for this month:
1. lottery ($3000.00) on 31 Mar 2023
2. bank interest ($5.00) on 31 Mar 2023
_______________
```

To find a deposit given a keyword, use this commmand.

deposit find /n (keyword)

```
deposit find /n bank
_______________
Here are the deposits you searched:
2. bank interest ($5.00) on 31 Mar 2023
_______________
```

To clear all deposits, use this command. Optionally, if you want to clear within a range, use the optional params.

deposit clear [optional] /f DD-MM-YYYY /t DD-MM-YYYY

```
deposit clear
_______________
These are the deposits cleared:
1. lottery ($3000.00) on 31 Mar 2023
2. bank interest ($5.00) on 31 Mar 2023
_______________
```

### 4. Expenses

To add an expense, type the category of the expense followed by the name, amount
and date. (If no date is listed, it is treated as today):

The expense name must be less than 30 characters, and the date can only be input before today.

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
1. [transport] bus ($0.50) on 31 Mar 2023
_______________
```

You can also list the expenses made within a specific start and end date, with an
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

You can find out the stats for the month or year. The format is:

stats show [optional] /m month /y year /v verbose (d for deposit, e for expense)

Note that you can only input year from 1000 to current year.

```
stats show /m 03 /y 2023 /v de
_______________
Your current progress for Mar 2023:
1. food      ████████░░░░░░░░░░░░ ($200.00/500.00)
2. transport ████████████████░░░░ ($402.50/500.00)
 
Deposits
Total deposits: $0.00
 
Expenses
1. [transport] mrt ($2.50) on 01 Mar 2023
2. [food] mac ($200.00) on 31 Mar 2023
3. [transport] taxi ($400.00) on 31 Mar 2023
Total expenses: $602.50
 
Total budget progress: $602.50/1000.00 (+0.00)
Good job! You are on the right track!
_______________
```

### 6. Exiting BudgetBuddy

You can exit the application using the `exit` command.

Upon exiting BudgetBuddy, BudgetBuddy will save the current data in the list to a local text file in your device.
The saved data will be imported into BudgetBuddy when it is subsequently launched in the future,
ensuring that you do not lose track of it.
