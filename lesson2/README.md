# Lesson 2 - Android Basics
### Your first Android application!
Today in class we'll walk you through making your first Android application. Found in the lesson2 folder is a copy of the application that we will create in class.

### 1. Make your app
To begin with, we'll create a default app and walk you through the different components.

1. Create a new project in Android Studio
2. Name it something akin to "Lesson2HW" and save it in your forked repo
3. Click "Blank Activity"
4. Check "Use a Fragment"

Great, now you have an Android app! Now, what does this all mean?

### 2. [Activities](https://developer.android.com/guide/components/activities.html) and [Fragments](https://developer.android.com/guide/components/fragments.html)

#### Activities
------

An activity is an application component that provides a screen that users can interact with in order to do something, such as dial the phone, take a photo, send an email, or view a map. Each activity is given a window in which to draw its user interface. The window typically fills the screen, but may be smaller than the screen and float on top of other windows.

Each activity can then start another activity in order to perform different actions. You generally have the main activity which launches other activities (or, your application *could* just be a single activity). Each time a new activity starts, the previous activity is stopped, but the system preserves the activity in a stack (the "back stack"). When a new activity starts, it is pushed onto the back stack and takes user focus.

Note that an activity can hold multiple "fragments," which is very useful. You can also switch between fragments in an activity
It handles passing information between fragments

#### Fragments
------

Fragments are what go hand in hand with activities. They were introduced in Android 3.0 (API level 11), primarily to support more dynamic and flexible UI designs on large screens, such as tablets, but are still useful for smaller screens as well. Think of them as sub activities for when you want to make activities more modular.

You can have multiple fragments, where usually each fragment does a separate task. For example, if you were making Snapchat, you might have a fragment for each swipeable screen. Fragments are useful for:

1. Modularity - dividing complex activity code across fragments for better organization and maintenance.

2. Reusability - placing behavior or UI parts into fragments that can be shared across multiple activities.

3. Adaptability - representing sections of a UI as different fragments and utilizing different layouts depending on screen orientation and size.

![alt text][fragment]

With the Snapchat example however, you could also have each swipeable screen be an activity. Now, this sounds confusing. Why have single fragments that you switch through in one activity instead of just having multiple activities? Well, the biggest reason is for when you're designing an app to be used on both phones and tablets.

![alt text][activityfrag]

Also, fragments and activities communicate with their XML files through lookupbyid(). What does this mean?

[fragment]: https://cdn2.raywenderlich.com/wp-content/uploads/2015/10/android_fragments_d001_why_fragments.png "fragments are very cool, I promise"
[activityfrag]: https://camo.githubusercontent.com/b768afff0888fcb8cbe1704b0609b53110276969/687474703a2f2f646576656c6f7065722e616e64726f69642e636f6d2f696d616765732f66756e64616d656e74616c732f667261676d656e74732e706e67 "see how useful fragments are?"

### 3. [XML](https://developer.android.com/guide/topics/ui/declaring-layout.html) files
Think of it like the CSS/HTML of Android if you don’t know what XML is. XML files define the layouts of your activities/fragments (each activity and fragment will have its own .xml). These are pretty self explanatory, so we won't go very in-depth.
The “Design” tab is useful for looking at possible elements you can add. Not so useful for accurate placement of elements, but go ahead and drag and drop them if you don’t want to type them in.

#### colors.xml and strings.xml
These files hold information for various colors and strings in your code, as the name implies. They're good for modularity and translations so you don’t hardcode stuff.

*Note*: In your java code, if you do type a string in, a little pop up will show up and you can press some shortcut to put it in your strings.xml

### 4. [Manifest](https://developer.android.com/guide/topics/manifest/manifest-intro.html)
It defines settings about your app and what permissions it needs

### 5. [Gradle](https://developer.android.com/studio/build/index.html)
See the file called `build.gradle(Module: app)`? That's your gradle file. Gradle is the thing that builds your app, like a compiler. Your app will break if there’s something wrong with this. Also, remember to recompile whenever you change it (it might take a while).

### 6. Let's get to work!
1. Go to your XML for this fragment

2. Drag a button onto the view or type it in and suggest-complete

3. Give it an id

4. Go to the fragment java file

5. Assign that button as a variable

  * Note that the XML file does not make the button - it only describes the design of the button. You declare the button in the fragment/activity, then assign the XML id to your button to describe the look of the button. XML is necessary if you want your button to actually appear on screen. This is true of all other elements as well, not just buttons (what about grid layouts and such?)

6. Make an onclicklistener

7. Have the button log something in android studio

### 7. Tips and tricks
* Pressing CTRL+Q will give you info for a class or method that you’ve highlighted, if you see something and don’t know what it is

* Android best practices

### Extra Resources
[Android Developer guide](https://developer.android.com/develop/index.html) <-- you should 100% be using this

### Assignment

Part 1: Understanding high level design

Draw on the whiteboards how you’d use fragments and activities to make Venmo, Snapchat, Yikyak, or some other popular app that one of us have used before. Get it checked off by one of us

Part 2: Building on top of the app we showed you

Have a textview display things

Get an edittext to work

Play around with different layouts types?

Have text input and button. When you click button, text input gets cleared/textview somewhere else changes to have textinput text
