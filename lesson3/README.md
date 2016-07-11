# Lesson 3 - Listviews, Adapters, Debugging

## ListViews
### Concepts Behind ListViews
In order to display a scrollable list of something in Android, you will need to use a `ListView`.  A `ListView` is what you see when you scroll through a list of clickable things in an app, like your friends list in Snapchat or your list of chats in Messenger.

Unfortunately, this is not as simple as just making a `ListView` and attaching an `ArrayList` to it.  You also need an `Adapter`.  There are three levels of displaying scrollable and clickable data in Android: the data source, the `Adapter`, and the `ListView`.

- **Data Source**: This is the actual variable that stores all your data, usually an `ArrayList`.
- **Adapter**: This interprets the data source and tells the `ListView` what each row of the list should look like.
- **ListView**: This handles displaying the full list of rows and having the logic for any onClick listeners.

In our Snapchat example, the data source would be an `ArrayList` of strings of our Snapchat friends' names.  The `Adapter` would handle displaying the name in a `TextView`, and also displaying the little icon to indicate whether you have an unread snap from them.  The `ListView` would handle displaying all your friends and making it scrollable when there's not enough room on the screen for the whole list.  It would also handle the logic for displaying your snap when you click on a friend with an unread snap, or replying when you double-click on a friend.

For simple lists where you just want to display a list of strings in a `TextView`, you can use Google's built-in `ArrayAdapter`.  If you want to make a more complex display, such as including a picture or a button on each row, then you will need to create your own custom `Adapter`.  Just like you made a layout .xml file when creating the view for your fragment, you can make a layout xml file for your `Adapter` to position where each component goes in the row.  For the built-in `ArrayAdapter` you can pass in Google's built-in `simple_list_item_1` as your layout file, which fills the row with a `TextView`.

### Code

Now that you know the concept behind making a `ListView` work, read through [this guide](https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView) to learn how to code it (you can skip the section about row view recycling if you want).

Here is some basic sample code using the built-in `ArrayAdapter` to get you started:
```java
// Make a friends ArrayList
ArrayList<String> friends = new ArrayList<>();
friends.add("David");
friends.add("Bill");

// Use Google's ArrayAdapter, pass in the simple_list_item_1 layout and our list of friends
ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);

// Initialize our ListView, linking it to the friendsListView in this fragment's layout file
ListView listView = (ListView) findViewById(R.id.friendsListView);
// Link this ListView to our adapter
listView.setAdapter(itemsAdapter);
```

## Debugging

## Assignment
Before next class, make a todo app.  You should have:
- A way to add new todo items.
- A display of all the current todo items.
- A way to delete items when the user completes them.
- A way to edit an item.

Stretch goals (optional):
- Use a custom `Adapter`! You could make an icon that changes when the item is completed, or make each item have an inline edit/complete button.
- Have each item have a description that displays when you click on it, maybe in an `AlertDialog` or a different fragment.

The details of how to implement this are up to you.
