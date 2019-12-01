[![BuddyBuild](https://dashboard.buddybuild.com/api/statusImage?appID=59da4469df6fb700013550f4&branch=master&build=latest)](https://dashboard.buddybuild.com/apps/59da4469df6fb700013550f4/build/latest?branch=master)

# K-Places
Showcase of a pure Kotlin app that serves as an example of a clean architecture and features:

- **100% Kotlin**
  - **Coroutines** for asynchrouns tasks and communication between **domain** and **data** layers
- **Android architecture components**
  - **Room** as local cache mechanism
  - **ViewModel** to expose observable streams of data to the UI and persist during configuration changes
  - **LiveData** on the presentation layer to wire UI and ViewModels
- **Clean architecture**
- **Testing**
  - **Espresso**
  - **Page Robots + Kotlin DSL**
  - **Unit tests**
  - **Mockito**
- **Dagger**
- **Retrofit + [Kotlinx.Serialization](https://github.com/Kotlin/kotlinx.serialization)**
- **Glide**
- **Android support library**
  - **Constraint Layout**

## API
The app uses [foursquare's places api](https://developer.foursquare.com/places-api) as it's remote endpoint

## Architecture
The project follows a clean architecture setup that leverages multi module support and separates the responsabilities and dependencies into different modules
- **data**: communicates the app with any external resource and also provides a cache mechanism to provide access to data when offline
- **domain**: contains the interactors that correspond to the different use cases and business logic, and acts as a bridge between the data layer and the presentation layer
- **presentation**: contains the ViewModels that are wired with the ui on the app layer. It uses use cases to get the requiered data, and then provides observable streams of data and states to the UI, who can observe and display as required.
- **app**: represents the UI layer, making use of Android framework and APIs
- **injection**: a module that defines and setup the dependency graph using Dagger 2 as the dependency injection framework
