# Lesson 2 - Android Basics
### Your first Android application!
Today in class we'll walk you through making your first Android application. Found in the lesson2 folder is a copy of the application that we will create in class.

## Create a Blank App
To begin with, we'll create a default app and walk you through the different components.

1. Create a new project in Android Studio
2. Name it something akin to "Lesson2HW" and save it in your forked repo
3. Click "Basic Activity"
4. Check "Use a Fragment"

Great, you just created your very first app!

## Explore Around
Android Studio just created a bunch of files and boilerplate code to get you started. Before we write any code, let's explore the different files that were just created.

There are generally three different types of files that you'll need to know about in Android Studio:

1. **Java files** are where your backend logic goes.
2. **XML Layout files** are where you define the frontend layout of your screens.
3. **Configuration files** are used by Android Studio to configure your app.

You'll be writing most of your code in Java files.
### 1. Java Files
When you interact with an app on your phone, there are different "screens" that you can go to. Snapchat, for example has a "friends list" screen, a "picture-taking" screen, a "settings" screen, and so on.  The settings screen displays different things and needs completely different logic than the picture-taking screen.  Android separates these "screens" into different files called **activities** and **fragments**.

#### [Activities](https://developer.android.com/guide/components/activities.html) (ex. MainActivity.java)

Activities contain one or many fragments inside of them.  It handles the passing of information between fragments.  Most simple apps (like the ones you'll be making in the beginning of this class) will only need one activity.

#### [Fragments](https://developer.android.com/guide/components/fragments.html) (ex. MainActivityFragment.java)
Fragments are basically what we described as "screens" in our Snapchat example. Each fragment does a separate task and shows different components to the user. If you were making Snapchat, you might have a fragment for each swipeable screen. Fragments are useful for:
1. Modularity - dividing complex activity code across fragments for better organization and maintenance.
2. Reusability - placing behavior or UI parts into fragments that can be shared across multiple activities.
3. Adaptability - representing sections of a UI as different fragments and utilizing different layouts depending on screen orientation and size.

### 2. [Layout XML](https://developer.android.com/guide/topics/ui/declaring-layout.html) files (ex. fragment_main.xml)
XML files are basically the CSS/HTML of the Android world. They define where each component goes in your activities and fragments. Each activity and fragment will have its own XML file that it can communicate with.

### 3. Configuration Files
#### [Manifest](https://developer.android.com/guide/topics/manifest/manifest-intro.html) (AndroidManifest.xml)
The Manifest file defines settings about your app and what permissions it needs.  Whenever you download an app from the Play Store, it will show you what permissions the app needs (like internet, data storage, GPS location) so that users can be knowledgable about what their apps are using. These permissions are taken from the app's manifest file.

#### [Gradle file](https://developer.android.com/studio/build/index.html) (build.grade (Module: app))
Gradle is the thing that builds your app, like a compiler. Each time you launch your app to your phone to test it, gradle will run and compile your code.

#### strings.xml
Android coding conventions dictate that every user-facing string should be stored in this file instead of hardcoded.  This is so every string in your app can be easily translated in one file.

# Android Studio Overview
Android Studio is an IDE, not a text editor. This means that Android Studio is very smart and you can use it to navigate and write code quickly.  Here's an overview of some of our favorite features:

- **Auto-saving:** No need to ever press the save button, AS saves as soon as you type.
- **Auto-compiling**: AS does some compiling in real-time, which means you'll know immediately if you've misspelled a variable or didn't pass the right parameters to a function.
- **Smart Autocomplete:** AS will only make autocomplete suggestions that are legal and make sense in the context of the code.

## Layout of Android Studio
If you don't see the project view pane on the left, press `Alt+1` to make it appear.  Here you can see the directory structure of the different files in your app.  The java files are in `app > java > [DOMAIN]` and the xml layout files are in `app > res > layout`.  Double-clicking on files opens them, and you can have multiple tabs of files open.

When you have a layout xml file open, AS gives you two different ways to edit the layout.  At the bottom you should see a "Design" tab and a "Text" tab.  The Design tab gives you a drag-and-drop GUI to easily add components onto the screen. The pane on the left shows you all the possible components that you can add, and you can drag them onto the screen to place them where you want.  If you want more manual fine-tuning you can click on the Text tab, where you can edit the raw xml that produces the layout.

When you want to run your app, press the green play icon in the top toolbar.  A window will appear, where you can select to run an emulator or use a physical phone.  If you plugged in your android phone and enabled usb debugging mode, you should see your phone pop up in this list.  Select it, press OK, and your app should appear on your phone after it installs.

# Let's get to work!

1. Go to your XML for this fragment
2. Drag a button onto the view or type it in and suggest-complete
3. Give it an id
4. Go to the fragment java file
5. Assign that button as a variable

Also, fragments and activities communicate with their XML files through lookupbyid().

  * Note that the XML file does not make the button - it only describes the design of the button. You declare the button in the fragment/activity, then assign the XML id to your button to describe the look of the button. XML is necessary if you want your button to actually appear on screen. This is true of all other elements as well, not just buttons (what about grid layouts and such?)

6. Make an onclicklistener

7. Have the button log something in android studio

## Tips and tricks
* Pressing CTRL+Q will give you info for a class or method that you’ve highlighted, if you see something and don’t know what it is

* Android best practices

## Extra Resources
[Android Developer guide](https://developer.android.com/develop/index.html) <-- you should 100% be using this

## Assignment

Part 1: Understanding high level design

Draw on the whiteboards how you’d use fragments and activities to make Venmo, Snapchat, Yikyak, or some other popular app that one of us have used before. Get it checked off by one of us

Part 2: Building on top of the app we showed you

Have a textview display things

Get an edittext to work

Play around with different layouts types?

Have text input and button. When you click button, text input gets cleared/textview somewhere else changes to have textinput text
