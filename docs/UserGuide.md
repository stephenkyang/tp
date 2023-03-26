# User Guide

## Introduction

BudgetBuddy is a one stop Command Line Interface application that helps you to keep track of anything finance related!
You can keep track of your expenses better by creating and editing custom budgets, and also taking note of what deposits
you have
to have a better understanding of your overall spending and saving.

## System requirements

BudgetBuddy is built using Java version 11. Ensure that you are running Java version 11 on your device.

## Installation guide

1. Download the budgetbuddy.jar file under [Releases](https://github.com/AY2223S2-CS2113-W15-3/tp/releases)
2. Copy the file into a folder of your choice. The task list will be saved into this folder. The file name will be
   tasklist.txt
3. Open terminal application and change directory to the selected folder
4. Run the Duke by entering `java -jar duke.jar`

You will be then greeted by Duke as shown below:

```
_______________
Welcome to BudgetBuddy! What can I do for you?
_______________

```

## Features

BudgetBuddy accepts a range of valid commands as shown below.
Use the `help` command to get instructions on how to use BudgetBuddy.
<br/><br/>

#### 1. Budgets

Use the command `budget help` to learn the specific budget related commands

1. You can add a monthly budget:

   `budget add /c (category name) /l (spending limit)`
   <br/><br/>
2. You can modify an existing budget:

   `budget set /c (category) /l (spending limit)`
   <br/><br/>

3. You can remove a monthly budget:

   `budget del /c (category)`
   <br/><br/>
4. You can list all budgets:

   `budget list`
   <br/><br/>
5. You can search for budgets that include a certain keyword:

   `budget find /c (keyword)`
   <br/><br/>

<br/><br/>

#### 2. Expenses

Use the command `Expenses help` to learn the specific budget related commands

1. You can add a monthly budget:

   `budget add /c (category name) /l (spending limit)`
   <br/><br/>
2. You can modify an existing budget:

   `budget set /c (category) /l (spending limit)`
   <br/><br/>

3. You can remove a monthly budget:

   `budget del /c (category)`
   <br/><br/>
4. You can list all budgets:

   `budget list`
   <br/><br/>
5. You can search for budgets that include a certain keyword:

   `budget find /c (keyword)`

<br/><br/>

#### 3. Exiting BudgetBuddy

You can exit the application using the `exit` command.

Upon exiting BudgetBuddy, BudgetBuddy will save the current tasks in the list to a local text file in your device.
These saved tasks will be imported into Duke when it is subsequently launched in the future,
ensuring that you do not lose track of you tasks.
<br/><br/>
