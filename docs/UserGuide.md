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
Use the ```help``` command to get instructions on how to use BudgetBuddy.
<br/><br/>

#### 1. Add Todo

Use the command `todo [task name]` to add a new task to your list

example: `todo buy food`

```
added:  buy food
```

<br/><br/>

#### 2. Add Deadline

Use the command `Deadline [task name] /[due_date]` to add a new task to your list

example: `deadline buy milk /tuesday10pm`

```
Got it. I've added " buy milk "
with a deadline of: tuesday10pm
```

<br/><br/>

#### 3. Add Event

Use the command `event [task name] /start_time /end_time` to add a new task to your list

example: `event foodie cooking show /wed3pm /wed5pm`

```
Got it. I've added " foodie cooking show "
with a start time of: wed3pm 
and an ending time of: wed5pm
```

<br/><br/>

#### 4. List tasks

Use the command `list` to list out all the tasks

Expected result:

```
1.[T][ ]buy food
2.[D][ ] buy milk  (by: tuesday10pm)
3.[E][ ] foodie cooking show (from: wed3pm  to: wed5pm)
There are currently 3 task(s) in the list
```

The letters "T" , "D" and "E" represent Todos, Deadlines and Events respectively.
The due dates, start and end times will also be shown next to the task name.
<br/><br/>

#### 5. Mark tasks

You can mark tasks as completed by using the `mark [task_number]` command.
The task number is the corresponding number of the task in the `list` output.

For example: `mark 2`

```
Nice! I've marked this task as done:
[X]   buy milk
```

<br/><br/>

#### 6. Unmark tasks

You can unmark tasks as completed by using the `unmark [task_number]` command.
The task number is the corresponding number of the task in the `list` output.

For example: `unmark 2`

```
OK, I've marked this task as not done yet:
[ ]    buy milk 
```

<br/><br/>

#### 7. Delete tasks

You can delete tasks as completed by using the `delete [task_number]` command.
The task number is the corresponding number of the task in the `list` output.

For example: `delete 2`

```
Noted. I've removed this task:
2.[D][ ] buy milk  (by: tuesday10pm)
```

<br/><br/>

#### 8. Search tasks

You can search for tasks that have a name that includes a keyword determined by the user.
This is done by using the `find` keyword.
For example:

For example: `find food`

```
There are 2 matching tasks in your list
1.[T][ ]buy food
2.[E][ ] foodie cooking show (from: wed3pm  to: wed5pm)
```

Tasks with names that include the keyword will be listed out, even if the keyword is a substring of a word in the name.
<br/><br/>

#### 9. Exiting Duke

You can exit the application using the `bye` command.

Upon exiting Duke, Duke will save the current tasks in the list to a local text file in your device.
These saved tasks will be imported into Duke when it is subsequently launched in the future,
ensuring that you do not lose track of you tasks.
<br/><br/>
