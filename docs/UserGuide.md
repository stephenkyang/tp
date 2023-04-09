# User Guide

## Introduction

BudgetBuddy is a one-stop Command Line Interface application that helps you to keep track of anything finance related!
You can keep track of your expenses better by creating and editing custom budgets, and also taking note of what deposits
you have to have a better understanding of your overall spending and saving.

## Features

Here are some features that Budget Buddy offers:

#### Track your expenses
- You can keep track any items that you have just spent on BudgetBuddy!

#### Categorize your budget into different names
- Instead of keeping your expenses into a single list, split up into different categories (e.g. Transport, Food, Bills)!

#### Keep a list of your deposits
- Have extra earnings that you wish to keep track too? Fret not!

#### Show your overall budget result
- Summarize your overall budget and see what needs to be improved!

#### Remind your spending
- Always have a buddy to remind your spending!

#### Sort and filter deposits and expenses
- You can sort deposits and expenses by categories and date too!

#### Persistent Data
- Budget Buddy ensures that your data is always with us!

## Installation guide

BudgetBuddy is built using Java version 11 and only works on **Windows, MAC and Linux OS**. Please ensure that you are running **Java version 11** on your device.

1. Download the `budgetbuddy.jar` file under [Releases](https://github.com/AY2223S2-CS2113-W15-3/tp/releases)
2. Copy the file into a folder of your choice. Ensure that you do not have `data.json` in the same directory, as this will be used to store the data of this application!
3. Launch your command prompt/terminal and navigate to the folder where `budgetbuddy.jar` is located.
4. Run the following command: `java -jar budgetbuddy.jar`

After launching the application for the first time, you will be greeted by the application as shown:
```
Could not locate data.json file. A file will be automatically created after an action!
_______________

 █▀▀█ █   █ █▀▀▄ █▀▀▀ █▀▀ ▀▀█▀▀ 　  █▀▀█ █  █ █▀▀▄ █▀▀▄ █  █ 
 █▀▀▄ █   █ █  █ █ ▀█ █▀▀   █   　  █▀▀▄ █  █ █  █ █  █ █▄▄█ 
 █▄▄█  ▀▀▀  ▀▀▀  ▀▀▀▀ ▀▀▀   ▀   　  █▄▄█  ▀▀▀ ▀▀▀  ▀▀▀  ▄▄▄█
Welcome to BudgetBuddy! What can I do for you?
_______________

```

**NOTE: It is not recommended to edit the data.json file unless you know what you are doing!**

If you encounter the greeting message displaying as "????", please change your command line/terminal's character encoding to UTF-8.
- For Windows, if you are using command prompt or powershell, change the code page by typing `chcp 65001` in the prompt.
- For Mac, change the Text encoding to `UTF-8` under international section in your Terminal Settings.
- For others, please find a setting that can change the encoding to `UTF-8`. Else if it doesn't work, please test it on another computer.

## Commands

For every command that you want to execute, the input of the command must be strictly in this format as follows:
> `(command) (action) (parameters) [optional parameters]`

The following points apply to the rest of the commands below.
- Command, action and parameter(s) must be keyed in in the input.
- Some commands have optional parameters `[optional]`, which it is not required to key in.
- The command and action names are not case sensitive, but the parameters and value of parameters are.
- It is possible to rearrange the order of parameters.
- For values that accept double (or decimal), the application will only take the first 2 decimal places.
- Value of the parameter must not contain any slash (`/`).
- Please input the parameters in the correct format so as to prevent abnormal behavior.

### 1. Help

To know what commands are available, simply type in `help`.

Command: `help`
```
help
_______________
Budget Buddy helps you to manage your finances better.
Budget Buddy is split into 4 main classifications:
1. Budget
   - Choose how much money you want to allocate to a budget of your specified name
2. Deposit 
   - Track how much additional earnings you have made
3. Expense
   - Track how much money you have spent, and link it to a certain budget of yours
4. Stats 
   - shows the progress on your overall budget, deposit and expenses
To learn the specific commands for each category, input "(category) help"
_______________
```

You can also know what are the actions for a specific category (e.g. budget).

Command: `budget help`
```
budget help
_______________
1. You can add a monthly budget: 
   budget add /c (category name) /a (spending limit)
2. You can modify an existing budget: 
   budget set /c (category name) /a (spending limit)
3. You can remove a monthly budget: 
   budget del /c (category name)
4. You can list and check each budget's status for a certain month/year: 
   budget list [optional] /m (month) /y (year in YYYY format)
_______________
```

### 2. Budgets

#### Add Budget
To add a budget, type the name of the budget you want followed by the spending limit: <br>
You are not allowed to add duplicate budgets, and the name of the budget must be less than 30 characters.<br>
The maximum number of budgets you can add is 99.

Command: `budget add /c (category name) /a (spending limit)`

```
budget add /c food /a 600.00
_______________
Successfully added food with limit of $600.00
There are 2 budget categories.
_______________
```

#### Set Budget
You can also set and modify that was wrongly inserted:

Command: `budget set /c (category) /a (spending limit)`

```
budget set /c food /a 500.00
_______________
Successfully modified food to have a limit of $500.00
There are 2 budget categories.
_______________
```

#### Delete Budget
You can delete a budget that you no longer need.<br>
Take note that **expenses that are linked to that budget will be deleted too.**

Command: `budget del /c (category)`

```
budget del /c transport
_______________
Successfully deleted budget transport with 2 related expenses.
There are 1 budget categories.
_______________
```

#### List Budget
You can list down the budgets that you've have added, which will also show each budget's progress for the current month.<br>
Alternatively, you may also show the progress for a certain month & year.<br>
Take note that you can only input a month & year which is before today, and the year must be between 1000 to current year.

Command: `budget list [optional] /m month (1-12) /y year (in YYYY)`

```
budget list
_______________
Your budget list for Apr 2023:
  1. food      ██████████░░░░░░░░░░ ($250.00/500.00)
  2. transport ░░░░░░░░░░░░░░░░░░░░ ($0.00/500.00)
_______________
```

### 3. Deposits

If you want to create a deposit, use the following command,
(if no date is listed, it is treated as today)

The deposit name must be less than 30 characters, and the date can only be input before today.

deposit add /n (name) /a (amount) [optional] /d DD-MM-YYYY

The parameters are the following:

    name: String
    amount: Integer
    date: datetime in the form of DD-MM-YYY

```
deposit add /n picked up from ground /a 50.00 /d 31-03-2023
_______________
The following deposit has been added:
1. picked up from ground ($50.00) on 31 Mar 2023
_______________
```

To delete the deposit use the this command. The deposit number can be found with `deposit list`

deposit del /n (deposit number)

The parameters are the following:

    deposit number: Integer
```
deposit del /n 1
_______________
The following deposit has been removed:
1. picked up from ground ($50.00) on 31 Mar 2023
_______________
```

To list deposits, use this command. The optional parameter are dates from the start to the end range.

deposit list [optional] /f DD-MM-YYYY /t DD-MM-YYYY

The parameters are the following:

    start date: datetime in the form of DD-MM-YYY
    end date: datetime in the form of DD-MM-YYY

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

The parameters are the following:

    keyword: String

```
deposit find /n bank
_______________
Here are the deposits you searched:
2. bank interest ($5.00) on 31 Mar 2023
_______________
```

To clear all deposits, use this command. Optionally, if you want to clear within a range, use the optional params.

deposit clear [optional] /f DD-MM-YYYY /t DD-MM-YYYY

The parameters are the following:

    (optional) start date: datetime in the form of DD-MM-YYY
    (optional) end date: datetime in the form of DD-MM-YYY

```
deposit clear
_______________
These are the deposits cleared:
1. lottery ($3000.00) on 31 Mar 2023
2. bank interest ($5.00) on 31 Mar 2023
_______________
```

### 4. Expenses

#### Add Expense
To add an expense, type the category of the budget followed by the name, amount and date (optional).<br>
The category of budget must exist in the list of budgets and the name of the expense must be less than 30 characters.<br>
If no date is listed, it is treated as today.<br>
However if you would like to input a date, the date must be before today.

Command: `expense add /c (category name) /n (name) /a (amount) [optional] /d DD-MM-YYYY`
```
expense add /c transport /n mrt /a 2.50 /d 01-03-2023
_______________
The following expense has been added:
Expense No 2. [transport] mrt ($2.50) on 01 Mar 2023
_______________
```

#### Delete Expense
If you want to remove a specific expense, you can remove by its expense number.

Command: `expense del /n (expense number)`
```
expense del /n 2
_______________
The following expense has been removed:
Expense No 2. [transport] mrt ($2.50) on 01 Mar 2023
_______________
```

#### List Expense
You can list down the expenses that you've added, which will show the previous expenses and current expenses for this month:<br>
Alternatively, you may also list down by a specific category, or date range.<br>
If either from or to date is not entered, it will show the expenses that are from/until that date.<br>
Take note that you can only input a month & year which is before today, and the year must be between 1000 to current year.

Command: `expense list [optional] /c (category name) /f DD-MM-YYYY /t DD-MM-YYYY`
```
expense list /c food
_______________
Here are your expenses for this month for food:
Expense No 1. [food] kfc ($250.00) on 09 Apr 2023
Expense No 2. [food] salmon don ($7.00) on 09 Apr 2023
_______________
```

#### Find Expense
You can find expenses by the expense name.<br>
If you want to find expenses by category, use `expense list`.

Command: `expense find /n (keyword)`
```
expense find /n kfc
_______________
Here are the expenses you searched:
Expense No 1. [food] kfc ($250.00) on 09 Apr 2023
Expense No 3. [food] kfc chicken ($10.00) on 01 Apr 2023
_______________
 
```

#### Clear Expenses
You can delete multiple expenses at once.<br>
Alternatively if you want to delete specific expenses, you can delete by the category name, or a date range.<br>
If either from or to date is not entered, it will show the expenses that are from/until that date.<br>
Take note that you can only input a month & year which is before today, and the year must be between 1000 to current year.

**WARNING: Entering `expense clear` will delete all expenses permanently. This action is irreversible!**

Command: `expense clear [optional] /c (category name) /f DD-MM-YYYY /t DD-MM-YYYY`
```
expense clear /c food
_______________
These are the expenses cleared:
Expense No 1. [food] kfc ($250.00) on 09 Apr 2023
Expense No 2. [food] salmon don ($7.00) on 09 Apr 2023
Expense No 3. [food] kfc chicken ($10.00) on 01 Apr 2023
_______________
```

### 5. Stats

#### Show Stats
Finally, after adding all your budgets, deposits and expenses, you can display your entire budget progress for the current month.<br>
Alternatively, you may display your progress on a certain month/year.<br>
If you wish to view all your deposits, expenses or both, you can also input the verbose option.<br>
Take note that you can only input a month & year which is before today, and the year must be between 1000 to current year.

The message of the overall budget is determined by:<br>
total expenses / total budget limit + total deposits

If total expenses is more than total budget limit+deposits, the following message will be displayed:
> Oh no! You seem to be spending too much!
<br>Else, the message will display:
> Good job! You are on the right track!

Command: `stats show [optional] /m month (1-12) /y year (in YYYY) /v (d for deposit, e for expense, de for both)`
```
stats show /m 04 /y 2023 /v de
_______________
Your current progress for Apr 2023:
  1. food      ████████████████████ ($1550.00/500.00)
  2. transport █████░░░░░░░░░░░░░░░ ($122.50/500.00)

Deposits
Deposit No 1. lottery ($50.00) on 09 Apr 2023
Deposit No 2. money from ground ($10.00) on 09 Apr 2023
Total deposits: $60.00

Expenses
Expense No 1. [transport] mrt ($2.50) on 08 Apr 2023
Expense No 2. [transport] taxi ($120.00) on 09 Apr 2023
Expense No 3. [food] kfc ($250.00) on 09 Apr 2023
Expense No 4. [food] mcdonalds ($200.00) on 03 Apr 2023
Expense No 5. [food] burger king ($100.00) on 04 Apr 2023
Expense No 6. [food] longjohnsilver ($1000.00) on 09 Apr 2023
Total expenses: $1672.50

Total budget progress: $1672.50/1000.00 (+60.00)
Oh no! You seem to be spending too much!
_______________
```

### 6. Exit

You can exit the application using the `exit` command.<br>
Upon exiting BudgetBuddy, BudgetBuddy will save the current data in the list to a local text file in your device.
The saved data will be imported into BudgetBuddy when it is subsequently launched in the future,
ensuring that you do not lose track of it.
