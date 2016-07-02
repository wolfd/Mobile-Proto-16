# Lesson 7 - APIs
Today we'll be learning about some of Google's pre-existing APIs built for Android, and a few others you will probably find useful

## What's an API anyway?
You've probably heard the term thrown around all the time and might have no idea what it means - no worries! An API stands for an application program interface. Think of it as a way for one application to easily communicate with another. For example, maybe you have a blog and want it to show your Twitter feed on the side. In that case, you'd need to use Twitter's API to provide a way for your blog to access Twitter's data. This example is specific to web APIs, but you'll be using a lot of Android APIs in order to make an app (e.g., an API for your app to communicate with the camera). The Android platform has a framework API that apps use to interact with the underlying Android system. We'll talk about a few of those next!

## Android APIs
Android has a *ton* of APIs built in; they're useful for using hardware sensors, accessing storage, handling user input, and setting config info.. However, you'll have to be careful with which version of Android you're using as APIs will differ depending on that. The APIs are for using hardware sensors, accessing storage, handling user input, and setting config info.

Check the sidebar in [this link](https://developer.android.com/about/versions/marshmallow/android-6.0.html) out to look at the different APIs for versions 4.1 to 6.0. [This link](https://developer.android.com/reference/android/package-summary.html) provides you with a detailed list of every Android-built API available. There are way too many to list so we won't go through them in detail, but if you've seen your phone do it, you'll find how to do it there.

## [Amazon S3](https://aws.amazon.com/s3/)
Amazon Web Services (AWS) provides something called Amazon Simple Storage Service, AKA Amazon S3. As the name implies, it's a cloud storage service. Now, instead of locally storing data on your phone you can use S3 and save it on the cloud! This is great for applications that need to share or store a lot of data.

Follow [these instructions](https://docs.aws.amazon.com/mobile/sdkforandroid/developerguide/s3transferutility.html) to set up your own S3 bucket (a cloud storage container). If you're interested in learning more about S3 past what the link above says, then check out AWS's [documentation](https://docs.aws.amazon.com/sdkfornet1/latest/apidocs/html/N_Amazon_S3_Transfer.htm).

## Libraries
Since we talked about APIs, we'll introduce some key libraries as well.
    * [Volley](https://developer.android.com/training/volley/index.html) - You'll need this to use HTTP. Alternatively, you can use [Retrofit](https://square.github.io/retrofit/)
    * [Gson](https://github.com/google/gson) - For when you need to parse JSON
    * [Picasso](https://square.github.io/picasso/) - Makes displaying images simpler

## Assignment
Instead of using local storage for the app you've made in previous classes, switch to using S3.