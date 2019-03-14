# TODOLY

This is a command line application that allows users to manage and track their tasks like adding,
updating, filtering and listing.

## Usage

```bash
git clone https://github.com/erdalzeynep/todoly.git
cd todoly
```

**Run via Java cli:** 

```bash
./gradlew clean build -x test
java -cp build/libs/todoly-1.0-SNAPSHOT.jar todoly.App
```

or 

**Run via gradle:** 

```bash
./gradlew run
```

## Manual

#### List Task

In order to list tasks, you should pick **(1)** from the main menu. 

There will be two options:

**_(1) Show All Tasks :_** Will list all tasks sorted by date.

**_(2) By Project     :_** Will ask you to enter project name and will filter tasks by entered project name.

| Input   	| Type   	| Mandatory 	| Description                            	|
|---------	|--------	|-----------	|----------------------------------------	|
| Project 	| String 	| Yes       	| A field that points to tasks project.  	|



#### Add Task

In order to add a new task, you should pick **(2)** from the main menu. 

| Input    	| Type                	| Mandatory 	| Description                                                                                                    	|
|----------	|---------------------	|-----------	|----------------------------------------------------------------------------------------------------------------	|
| Project  	| String              	| YES       	| A field that points the tasks project.  So the tasks can be filtered by this field  afterward.                 	|
| Title    	| String              	| YES       	| A short description of the task.                                                                               	|
| Due Date 	| String (dd-MM-yyyy) 	| YES       	| The due date of the task. It can not be  older than today. The tasks will be sorted by this field when listing 	|


#### Update and Delete Task

In order to update or delete a task, you should pick **(3)** from the main menu. 

| Input   	| Type   	| Mandatory 	| Description                                    	|
|---------	|--------	|-----------	|-------------------------------------------------- |
| Task ID 	| Integer 	| Yes       	| A unique value that points to one task record.  	|

Task ID is mandatory input for all three actions:

**_(1) Update          :_** Will update the task record with given Task ID.

| Input    	| Type                	| Mandatory 	| Description                                                                                                    	|
|----------	|---------------------	|-----------	|----------------------------------------------------------------------------------------------------------------	|
| Project  	| String              	| YES       	| A field that points the tasks project.  So the tasks can be filtered by this field  afterward.                 	|
| Title    	| String              	| YES       	| A short description of the task.                                                                               	|
| Due Date 	| String (dd-MM-yyyy) 	| YES       	| The due date of the task. It can not be  older than today. The tasks will be sorted by this field when listing 	|

**_(2) Mark as Done    :_** Will update the task status as "Done" with given Task ID.

**_(3) Remove          :_** Will delete the task record with given task ID.

#### Save and Quit

In order yo save the changes and quit the application, you should pick **(4)** from the menu.

It will save the changes you have made and quit the application.