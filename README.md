# Clue Assignment App

Welcome to the Cue Assignment App. The project aims to demonstrate my Android Engineering skills. The app offers an add button that retrieves a random photo from the Unsplash API. It displays a two column list with the images. Upon click, details of the image like the location it was taken in, a description of it and the number of like sit received are displayed.


| Author |  |
|--|--|
| Mina Kamel | Android Engineer  |

## Table of Contents
- [Clue Assignment App](#clue-assignment-app)
    - [Demo](#demo)
    - [To try the app](#to-try-the-app)
	    - [Adding Photos](#adding-photos)
	    - [Display of Photos](#display-of-photos)
	    - [Details](#details)
	    - [Error State](#error-state)
    - [Architecture](#architecture)
    - [Decisions and tradeoffs](#decisions-and-tradeoffs)
	    - [Modernisation of the codebase](#modernisation-of-the-codebase)
        - [Modularisation](#modularisation)
        - [Naming Conventions](#naming-conventions)
            - [Renaming](#renaming) 
            - [New Classes](#new-classes)
        - [Local Storage](#local-storage)
        - [Deserialisation and flattening data](#deserialisation-and-flattening-data)
        - [Client id Interception and security](#client-id-interception-and-security)
        - [Dependency injection](#dependency-injection)
        - [Testing](#testing)
            - [Unit Testing](#unit-testing)
            - [UI Testing](#ui-testing)
        - [Error and Progress](#error-and-progress)
     - [Functionalities](#functionalities)
	     - [Functionality 1: Add Photo](#functionality-1-add-photo)
		    -  [Presentation](#presentation)
		    -  [Domain](#domain)
		    - [Data](#data)
		  - [Functionality 2: Display of photos](#functionality-2-display-of-photos)
			  -  [Presentation](#presentation)
			  -  [Domain](#domain)
			  - [Data](#data)
		  - [Functionality 3: Details](#functionality-3-details)
			  -  [Presentation](#presentation)
			  -  [Domain](#domain)
			  - [Data](#data)
# Demo
<p align="center">
<img src="https://github.com/clue-external/clue-android-challenge-minafkamel/blob/feature/demo.gif" alt="Demo" width="420" height="840">

# To try the app
- Switch to `feature` branch and pull latest
- Add `CLIENT_ID` in `local.properties`
- Run the app

### Adding Photos
- Press "Add Photos" a few times to some photos (Note: a delay has been added to show the loading state)

### Display of photos
- Kill the app
- Open it again and notice the photos added

### Details
- Press on a photo
- Notice the dialog with description, location and number of likes of the photo

### Error state
- Turn on the Airplane mode
- Press "Add Photos"
- Notice the error toast

# Architecture

The app is built with the concepts of [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) in mind however optimised for Android. Clean Architecture has 3 main layers (presentation, domain and data) that will be explained below along with their classes. The app uses MVVM, Compose, Coroutines, Kotlin, Hilt, Retrofit and Room to achieve its goal.


# Decisions and tradeoffs 

A few important decisions were taken in order to deliver a viable solution in an adequate time. These are:

## Modernisation of the codebase:
Since the initial code is fairly old (3 years old) with little modernisation, I decided to update it to the latest state of the art technologies. The following has been done:
- Added a Version catalog to organise dependencies via a .toml file
- The `MainActivity` was converted to compose by extending `ComponentActivity `and removing the xml files: `activity_main.xml` and `item_photo.xml`
- Gradle files were converted to Kotlin DSL

## Modularisation
In order to demonstrate modularisation, multiple modules and submodules were added.
The main feature of the app lies in a module by itself called :photolist. This module has 3 submodules:
- :photolist:data
- :photolist:domain
- :photolist:presentation

In addition, there's a :common module for common classes. This module has a :common:ui module for common views and :common:domain module for common domain classes.

## Naming Conventions 

### Renaming
Existing classes were renamed to be in line with other classes in terms of conventions. 
For example: `PhotoApiService` became `RemoteDataSource`.

### New classes
For newly added classes, certain suffixes were added to help understanding the nature of these classes. For e.g:
- In the data layer: Since the class responsible for the API call has been called `RemoteDataSource`, the class responsible for the Room operations (DAO) is called `LocalDataSource`
- In the domain layer: use cases have the a `*UseCase` suffix

## Local Storage
There are multiple ways to store the data. In this sample I opted for Room since the data object is complex and there are queries needed to be done to get one stored object. Other approaches can be present in the app to satisfy different needs:
- A simple generic `Cache` mechanism to store simple data in a session
- `DataStore` to store data that persist when an app is closes
- `Room` to store more complex data the require querying or in case of relational data objects

## Deserialisation and flattening data
The `PhotoDeserializer` is used to flatten the data retrieved from the API. This helps in unifying the data object stored in Room.

Given that the API [response](https://unsplash.com/documentation#get-a-random-photo) contains objects like `urls.regular` and `location.name`, there were 3 options:
- Creating two sets of `Photo` class one for the API and one for Room: This approach causes redundancy and requires maintaining two versions of the same class.
- Using `TypeConverter` provided by Room to flatten the object in order to store it in room: This approach means extra fields should be added to the class (flattened `locationName` to satisfy Room in addition to unflattened `location.Name` to satisfy the API). This can cause confusion as the flattened fields will be null when the API is called. There would also be a redundancy problem.
- Using a `JsonDeserializer` to flatten the object as it's received from the BE into a flat Photo which contains just `locationName` and `urlRegular` besides other fields. This approach is the optimal one because it means the the `Photo` class can be used as it is for Room and the API call.

## Client id Interception and security
The `ClientIdInterceptor` attaches the `id` given by the API to the header of the API call. It is per norms hooked to the `OkHttpClient` setup in the DI setup. For security reasons, the client id is kept in `local.properties`. Before running the app, a `CLIENT_ID=` field must be added in the file.

## Dependency injection
`Hilt` was used to inject dependencies into different classes. For e.g use cases in view models and data sources in repositories. Additionally, assisted injection was used to pass the id of the clicked photo to the `DetailsViewModel` instead of adding a `setId` method. This way the functionality of the view model can run on `init{}`.

## Testing
The tests are organised into Arrange/Act/Assert blocks to be read and understood easily.

 ### Unit Testing
Some unit tests were written to test the to important parts of the code. Ideally all testable code should have unit tests before a PR is submitted. These tested classes are:
- `GetPhotosUseCaseTest`
- `PhotosViewModelTest`

### UI Testing
A UI Test has been written to test the Add Photo functionality. Ideally, the critical path of the app should be automated.
- `AddPhotoKtTest`

## Error and Progress
In module `:common:ui`, `CommonError` and `CommonProgress` composable were introduced to be reused in different features. Different functionalities have their own `Error` and `Progress` composables that use the common ones. They were taken out to separate files to achieve the following:
- Remove the clutter from their contain classes: for example in `photos.add.Error` the `photosViewModel` is injected and `photosViewModel.addPhotoUiState.collectAsState()` is collected and handled instead of doing so in the `FloatingButton` class.
- At the same time, the common code for showing the Toast message was kept in `CommonError`

The same applies for showing progress.
 
# Functionalities 

## Functionality 1: Add Photo

When the app is launched, a "Add Photo" floating button is shown. When clicked, a random photo is retrieved and is displayed. Additionally, the photo is stored. If there are photos that already stored, they will be displayed in the grid

### Presentation

The `FloatingButton` contains the implementation of the "Add Photo". It has a `FloatingActionButton` that contains an `Icon` and a `Text`.

When clicked, it calls `photosViewModel.addPhotoClicked`, which changes the `addRandomPhotoUseCase` to loading then invokes `addRandomPhotoUseCase`. The use case will  only return a success or an error but not actually a photo. The way the grid is updated will be explained later.

### Domain
The `AddRandomPhotoUseCase` calls `repository.getRandomPhoto()`. The result is wrapped in `Resource.Success` or `Success.Error`.

### Data
In the repository, `getRandomPhoto()` does two things:
 - Calls`remoteDataSource.getPhoto()` to retrieve a `Photo` from the BE.
- Adds the photo to a Room Database via `localDataSource.add(photo)`


## Functionality 2: Display of photos

When the app launches, the photos grid is loaded with the photos that have been saved. If there's nothing saved, the grid is shown empty until the user adds a photo.

### Presentation

In `Photos`, there's a `LazyVerticalGrid` that displays the photos in two columns. When the `PhotosViewModel` is initialised, the `getPhotosUseCase` collects the photos and sets `loadStoredUrlsUiState` accordingly. When set, the photos are shown in the `LazyVerticalGrid` which collects then via `photosViewModel.loadStoredUrlsUiState.collectAsState()`

### Domain
The `GetPhotosUseCase` makes use of the `repository.photosFlow` exposed by the data layer. It maps the information needed (which are the `id` for getting the details later upon click and and the `regularUrl` to show the photo)  in `PhotoInfo`

### Data
`repository.photosFlow` is a hot flow that exposes a flow from the database via `localDataSource.getAllPhotos()`. Whenever a new photo is added, the flow will emit it and anyone subscribed to it can collect the value. This is how the UI is updated here.

## Functionality 3: Details

Other details such as (location, description and the number of likes) are stored in the database. When the user clicks on a photo, a dialog is shown displaying them.

### Presentation
`Dialog` will show a `AlertDialog` with the details of the photo. It sits in `Photos`. When `AsyncImage` is clicked the `clickedId` which is a `remember` is set. It is passed to the `Dialog` which recomposes based on the value i.e `if(clickedId.value.isNotEmpty())`

If this is the case, the `clickedId` is passed to the `DetailsViewModel` via assisted injection. When initialised, it invokes `getDetailsUseCase` which 

### Domain
The `GetDetailsUseCase` gets the details of the photo given an `id`. It calls `repository.getPhotoById(id)`. The needed info are wrapped in `DetailsInfo` then wrapped in `Resource.Success` if successful. It does some check on the nullability of the fields and defaults to an empty string.

### Data
In the `Repository`, the method `getPhotoById` calls `localDataSource.getPhotoById(id)` which queries the database and gets the info.

