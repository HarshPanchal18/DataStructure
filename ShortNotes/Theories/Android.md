# Android

## What is AndroidManifest.xml? | `<manifest>` element

The AndroidManifest file is the first point the Android system checks when an application is loaded and receives detailed information about the application being loaded. That is, an Android mobile application cannot be launched without a Manifest file (because the Android system will not recognize it). The Android system needs to have some information first so that the downloaded application can work properly.

The following list shows the key parts that the Android system needs to know in order for an application to run smoothly:

* package name of the application being downloaded (each application has its own package name)
* components intended for the application to work without problems — activities, background services, providers, etc.
* all kinds of permissions that will be required from the system or other entities for the application to work without problems
* the minimum API level at which the application can be launched

Android uses an extensible markup language known as XML for its configuration files. The reason for this is to enhance the structural organisation of configuration data, making the written language easily understandable for both computers and humans. This is why XML is referred to as a ‘markup’ language — we tag our data with labels, kind of like stickers, so we can easily find what we need.

Key attributes:

* `package`

The unique package name used by the application. This name usually takes the form “com.example.yourappname”. This naming convention, known as the “reverse domain name” is used in Android development by developers to ensure uniqueness and clarity.

* `android:versionCode`
Version code that indicates the version of the application which is not shown to users

* `android:installLocation`
Used to indicate whether the application should be installed into internal or external storage.

`"internalOnly" (default value)` : When we set the value of this attribute to internalOnly, we are telling the Android system that this app can only be installed in internal storage. That is, if the device’s internal memory is full, then this application will not be able to be installed on the device (even if there is free space in the external memory).

`"preferExternal"` : As the name suggests, the application is meant to be moved to external memory, but it can be moved to internal memory if the external memory is full.

`"auto"` : The application is initially meant to be installed to internal storage but is installed to external storage if internal storage is full.

* `android:sharedUserId` Used to store the application’s unique user ID

_Note:_ The Android operating system is a multi-user Linux system. That is, each application on Android is a different user and has a unique user ID. If the sharedUserId attribute is not set, the Android operating system sets it by default.

* `android:sharedUserLabel`
Intended to hold a string resource used to make the application’s unique user ID readable by users. This is usually visible in the user interface (for example, when sorting applications in the phone’s settings, etc.).
_Note:_ The sharedUserId and sharedUserLabel attributes are deprecated after API 29 and will most likely be completely removed at some future date.

```xml
<manifest xmlns:android="<http://schemas.android.com/apk/res/android>"
     package="..."
     android:versionCode=...
     android:versionName=...
     android:installationLocation=[auto, internalOnly, preferExternal]
     android:sharedUserId=...
     android:sharedUserLabel=...>
</manifest>
```

### Sub-elements and their attributes of `<manifest>`

1. `<uses-sdk>`

`android:minSdkVersion` : Is used to indicate the minimum SDK version the application can run on

`android:targetSdkVersion` : Is used to indicate the SDK version the application is tested against

`android:maxSdkVersion` : Is used to indicate the maximum SDK version the application can run on (optional)

2. `<uses-permission>:`
It is used to declare the system permissions that the user would grant to the application for seamless use of the application. For example, if an app wants phone numbers, then the user must give permission to the app so that the app can get the phone numbers. Otherwise, the permission wouldn’t be granted, and the application might not work properly.

`android:name`
It is used to specify the name of the requested permission. For example, if the application uses the phone’s camera, then the manifest file should have the following permission:
`<uses-permission android:name="android.permission.CAMERA"/>`

3. `<uses-feature>:` Sometimes, it happens that the application we create cannot work without a specific function. For example, let’s say the app can’t run without a camera. In this case, by using `<uses-feature>`, we can inform Google that this application should be filtered in the Google Play Store and not appear in front of the user at all on devices without a camera.

`android:name` : It is used to specify the name of the required function.

`android:required` It is used to indicate whether the function specified in the “name” attribute is required. It accepts the boolean values true or false:

* "true" (default) : Setting this value means that this application cannot run without the function specified in the “name” attribute.
* "false" : Setting this value means that this application uses the function specified in the “name” attribute but is designed to work without this function if it is not present.

```xml
<uses-feature
    android:name="android.hardware.camera.any"
    android:required="true"/>
```

4. `<permission>:` It is used to set a security permission, limiting access to specific components or functions of this or other applications. In other words, securing specific components or features within an application to restrict access.

Consider an application named `MyContacts`, where a custom permission, `ACCESS_PRIVATE_CONTACTS` is created to control access to its contact list. When another application, for instance, `ContactList,` needs access to the contact list in `MyContacts`, it sends a permission request using `<uses-permission>` in its manifest file. In response, Android displays a window for the user to accept the permission. If granted, the contact list from “MyContacts” can be integrated into the “ContactList” application using Intents.

* `android:name` : It is responsible for defining the unique name of a custom permission within the Android application.

* `android:label` : It is used to provide a clear and user-friendly name for a custom permission. This name is displayed to users when they are prompted to grant or manage permissions for the application.

* `android:icon` : An icon representing the permission

* `android:description` : It is used to set a detailed explanation of the custom permission, written in easily understandable language for users. This description, often more comprehensive than the label, is presented when users are asked to grant permission to the application, aiding them in making informed decisions about data access.

* `android:permissionGroup` : To organize permissions effectively, this attribute allows us to assign the custom permission to a specific permission group. This grouping enhances the user experience by presenting related permissions together when users are managing app permissions.

* `android:protectionLevel` : It is a crucial attribute for categorizing custom permissions based on their potential risk levels. This attribute guides the Android system in determining appropriate procedures for granting or restricting access to these permissions.
Its value can be composed of 5 base permission types, optionally combined with zero or more additional flags:

* normal (default) : This permission type poses the lowest risk, granting access to isolated application-level functions without significant security implications. Permissions set with this level are automatically granted by the system, even without user intervention.
* dangerous: This permission type involves higher risk, potentially providing access to sensitive user data or device control capabilities. Due to this risk, dangerous permissions require explicit user approval before being granted.
* signature: Permissions of this type are automatically granted to other applications that are signed with the same certificate as the application declaring the permission. This level ensures permission sharing within a trusted group of apps.
* knownSigner: Similar to signature, this type grants permissions to apps signed with certificates that have been explicitly designated as trusted. It offers a controlled mechanism for permission sharing beyond strict certificate matching.

```xml
<permission
  android:name="com.example.myapp.permission.ACESS_PRIVATE_CONTACTS"
  android:label="Access contacts info"
  android:description="@string/description_access_private_contacts"
  android:icon="@drawable/ic_access_private_contacts"
  android:protectionLevel="dangerous"/>
```

5. `<permission-group>` : It serves as a dedicated space for defining logical groupings of related permissions. While this element doesn’t directly influence app functionality or create permissions itself, it introduces a valuable organizational structure, enabling permissions to be categorized and presented cohesively.

* `android:name` It is a unique identifier for the permission group. This name acts as a reference point, allowing us to assign relevant permissions to the group using their permissionGroup attribute.

* `android:label` It is used to provide a clear and user-friendly name for the permission group. This name is displayed to users when they are prompted to grant or manage permissions group for the application.

* `android:description` It is used to set a detailed explanation of the permission group, written in easily understandable language for users. This description, often more comprehensive than the label, is presented when users are asked to grant permissions to the application, aiding them in making informed decisions about data access.

* `android:icon` An icon representing the permission group

```xml
<manifest
    xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myapp">

    <permission-group
        android:name="com.example.myapp.MY_PERMISSIONS_GROUP"
        android:description="@string/permission_group_description" />

    <permission android:name="com.example.myapp.PERMISSION_1"
        ...
        android:permissionGroup="com.example.myapp.MY_PERMISSIONS_GROUP" />

    <permission android:name="com.example.myapp.PERMISSION_2"
        ...
        android:permissionGroup="com.example.myapp.MY_PERMISSIONS_GROUP" />

</manifest>
```

6. `<queries>` : It plays a crucial role in signaling your application's potential interactions with other apps on the device. This element enables you to specify which external apps your app might engage with, either by directly naming their package names, defining intent requests, or declaring interest in content providers offered by other apps.

> Imagine a file explorer app called “FileExplorer” that enables users to manage various files. When a user attempts to open a PDF file within this app, FileExplorer needs to communicate with the Android system to discover any installed apps capable of handling PDF viewing.

> To achieve this, the developer should define an `<intent>` element within the `<queries>` tag of this app’s manifest file. This intent acts as a precise request, indicating the desired action to be performed. To initiate PDF viewing, we specify android.intent.action.VIEW as the action within the `<action>` sub-element.

```xml
<queries>
    <intent>
        <action android:name="android.intent.action.VIEW" />
    </intent>
</queries>
```

Next, we fill in the intent’s details using the `<data>` sub-element. This involves providing the file type, "application/pdf" for PDF files, along with any other information, ensuring a comprehensive and informative request.

```xml
<queries>
    <intent>
        <action android:name="android.intent.action.VIEW" />
        <data android:scheme="content" />
        <data android:scheme="file" />
        <data android:mimeType="application/pdf" />
        <data android:pathPattern=".*\\.pdf" />
    </intent>
</queries>
```

Upon receiving this intent, the Android system meticulously scans the device for apps that match these criteria and presents those options to the user. This seamless interaction showcases the power of `<queries>` in facilitating app-to-app communication and delivering a smooth user experience.

### Sub-elements and their attributes of `<queries>`

`<package>` — When our app requires direct communication with a specific, known app, this sub-element is used to provide a clear path for establishing this connection. It allows us to explicitly declare the package name of the target app, ensuring seamless interaction.

* android:name : This attribute designates the package name of the target app, ensuring precise targeting.

Imagine our app needs to launch a specific social media app to share content. To achieve this, we include the following code in the manifest file of the app:

```xml
<queries>
    <package android:name="com.example.socialapp" />
</queries>
```

`<intent>` — When our app wants to engage with other apps based on their capabilities rather than their specific identities, this sub-element is used to serving as a bridge, it allows us to do intent requests that articulate the desired actions or data types, delegating the task of finding compatible apps to the Android system.

For example, as shown in the “FileExplorer” example above.

`<provider>` — it grants the app the ability to forge connections with content providers exposed by other apps.

* android:authorities : The unique identifier for the content provider we wish to access. This attribute typically consist of the package name of the app that houses the provider, ensuring precise targeting.

Imagine our app needs to access contacts stored in the device’s default contacts app. To establish this connection, we include the following code in the app’s manifest file:

```xml
<queries>
    <provider android:authorities="com.android.contacts" />
</queries>
```

By declaring this authority, we enable your app to interact with the contacts provider, retrieving and potentially modifying contact data as needed, all while respecting user privacy and system security.

_Note_: Due to the security restrictions regarding package visibility in Android 11 (API 30), it is required for developers to explicitly declare the queries if the app needs to interact with other apps or with their entities.

## Why should I choose native android development with Kotlin over cross platforms like Flutter and React native?

Choosing between native Android development with Kotlin and cross-platform frameworks like Flutter or React Native depends on your project's specific needs and priorities. Here's a breakdown of the advantages of native development that might make it the right choice for you:

1. Performance:
Native Advantage: Kotlin compiles directly to native machine code, resulting in potentially better performance, especially for computationally intensive tasks or complex UI interactions.
Cross-Platform Trade-off: Cross-platform frameworks often introduce an additional layer of abstraction, which can sometimes lead to performance overhead, particularly in scenarios requiring high responsiveness or smooth animations.
2. Access to Latest Features:
Native Advantage: With native development, you have immediate access to the latest Android APIs and features as soon as they are released. This allows you to leverage the full potential of the platform and provide the most up-to-date experience for your users.
Cross-Platform Lag: Cross-platform frameworks might have a delay in supporting the newest Android features, as they need to adapt their frameworks to accommodate the changes.
3. Seamless Integration:
Native Advantage: Kotlin and the Android SDK are designed to work together seamlessly, providing a smooth development experience and access to all the native components and functionalities.
Cross-Platform Challenges: Cross-platform frameworks might have limitations or require workarounds to integrate with certain native features or third-party libraries.
4. Tooling and Ecosystem:
Native Advantage: Android Studio, the official IDE for Android development, offers excellent support for Kotlin, including powerful debugging, profiling, and code analysis tools. The Android ecosystem is vast and mature, with a wealth of resources, libraries, and community support.
Cross-Platform Variations: While cross-platform frameworks have their own tooling and ecosystems, they might not be as comprehensive or well-established as the native Android ecosystem.
5. Long-Term Maintainability:
Native Advantage: Native Android development with Kotlin follows established patterns and best practices, making it easier to maintain and update your app over time, even as the Android platform evolves.
Cross-Platform Evolution: Cross-platform frameworks can be subject to more rapid changes and updates, potentially requiring more effort to keep your app compatible and up-to-date.
When to Consider Cross-Platform:
Faster Development: If you need to build an app for multiple platforms (Android, iOS, web) with a shorter development time, cross-platform frameworks can be a good option.
Code Reusability: Cross-platform allows you to share a significant portion of your codebase across platforms, reducing development effort.
Smaller Teams: If you have a smaller team and need to target multiple platforms, cross-platform can be more resource-efficient.
Ultimately, the best choice depends on your project's specific needs. Consider factors like performance requirements, the importance of using the latest Android features, the complexity of your app, and your team's expertise when making your decision.

## I have not published any app on playstore in my journey. If the I was asked the reason, what should I answer them?

It's perfectly fine to not have published an app on the Play Store within your first two years of learning Android development. Many developers take time to hone their skills, experiment with different projects, and build a solid foundation before releasing their first app.

If you're asked about this in an interview, here are some ways to frame your response positively:

1. Focus on Skill Development: "During my initial years, I prioritized building a strong foundation in Android development and Kotlin. I focused on learning core concepts, exploring different architectural patterns, and experimenting with various libraries and frameworks. I believe this solid foundation will enable me to create high-quality, maintainable apps in the future."
2. Highlight Personal Projects: "While I haven't published on the Play Store yet, I've been actively working on personal projects to apply my knowledge and gain practical experience. For example, I developed [mention a specific project] which helped me learn [mention specific skills or technologies]. I'm now confident in my ability to bring a polished app to the Play Store."
3. Show Enthusiasm for Learning: "I'm eager to publish my first app on the Play Store, and I'm actively working towards that goal. I've been following the latest Android development trends and best practices, and I'm excited to apply my knowledge to create a successful app that users will love."
4. Demonstrate Proactiveness: "I'm currently working on [mention a current project] which I plan to publish on the Play Store in the coming months. I'm focusing on [mention specific aspects like UI/UX, performance optimization, or unique features] to ensure it's a high-quality app that meets user needs."

_Additional Tips:_

Be Honest and Confident: Don't try to fabricate a fake app or exaggerate your experience. Be upfront about your journey and highlight your genuine passion for Android development.

Show Initiative: If you haven't started a Play Store-ready project yet, consider starting one now to demonstrate your commitment.

Emphasize Your Strengths: Focus on the skills and knowledge you've gained, even if you haven't published an app yet.

Remember, interviewers are looking for potential, not just a checklist of accomplishments. By showcasing your dedication to learning, your passion for Android development, and your plans for the future, you can leave a positive impression even without a published app on the Play Store.
