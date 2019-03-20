# Grain Chain Test
What this application do: 

In Login Activity user can post username and password credentials to a Web Service established by Grain Chain with the respond get the user name and save it to the Shared Preferences and move to Tab Activity finishing the Login Activity.

In the Tab Activity show two Fragments with a View Pager Add and Search.

In the Search Fragment the user saved in Shared Preferences is shown in the upper left screen. It has a RecyclerView with 10 preset Cardview elements, each one inside contains contact information of people such as profile picture, name, last name, age and phone number. These elements can be filtered by name through a search engine in the upper part of the Activity located below of the user name. When doing LongClick on an element the application asks if it is desired to eliminate that element, in case it is decided to eliminate the element, it disappear from the list.

In the Add Fragment contains a form to create new contacts, when the contact is created removes data in the form and it can be viewed in the Search Fragment RecyclerView.

When backpressed is clicked asks user if they want to leave app 

## Getting Started
Clone the project open it with Android Studio sync files and run the project.

### Prerequisites
You need to install Android Studio

### Design Patterns
This project uses MVP
Model View Presenter

## Authors
* **Luis Vargas** - *Initial work* - [Luis Vargas](https://bitbucket.org/LuisVargasVic/)