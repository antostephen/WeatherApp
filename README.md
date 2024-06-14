Weather App v1.0
-------------------------
###### Author: Anto Stephen ######

Introduction:
-------------
This Android App has been developed as part of the Assignment. 

[weather_app2.webm](https://github.com/antostephen/WeatherApp/assets/172839891/777da9c8-cdec-4f02-871f-55dbc3764ed6)

Overview:
---------------
This report provides an overview of a weather application developed for Android devices. The application is built using Kotlin, leverages Jetpack Compose for the UI, follows the MVVM (Model-View-ViewModel) Clean Architecture, utilizes Dependency Injection for better modularity, and employs Retrofit for network operations. Additionally, unit testing ensures the reliability and robustness of the application.

App Screenshots:
-----------------

* Homescreen 
     
    <img width="333" alt="screen_1" src="https://github.com/antostephen/WeatherApp/assets/172839891/1499dfc7-e603-4d62-b68c-3096605b3ee3">
    <img width="334" alt="screen_2" src="https://github.com/antostephen/WeatherApp/assets/172839891/0276d965-4242-4dc1-9fdd-b0b617a2ea85">
     
* Search Screen
     
     <img width="324" alt="screen_4" src="https://github.com/antostephen/WeatherApp/assets/172839891/a6dd4e2a-f7a1-40ed-a64c-86dc06c65079">

* Add Screen:
     
     <img width="330" alt="screen_3" src="https://github.com/antostephen/WeatherApp/assets/172839891/62437ac3-9e44-49fa-ab2a-13f9c5eecdd9">
     
* Alert Screen
  
Application Features:
--------------------
* **Current Weather:** Displays the current weather information for the user's location or any searched location.
* **Weather Forecast:** Provides a 7-day weather forecast with details on temperature, humidity, and weather conditions.
* **Location Search:** Allows users to search for weather information by city name.
* **Responsive UI:** Designed using Jetpack Compose for a smooth and interactive user experience across different screen sizes and orientations.

Technologies Used:
--------------------
1. **Kotlin**: The programming language used for developing the app, known for its conciseness and safety features.
3. **Jetpack Compose**: The modern toolkit for building native Android UI, which allows for a more declarative and responsive design.
4. **Dependency Injection**: Implemented using Hilt, this helps in managing dependencies efficiently and promotes a clean and modular codebase.
5. **MVVM Clean Architecture**: This architectural pattern helps in separating concerns, making the app more maintainable and testable.
6. **Retrofit**: A type-safe HTTP client for Android and Java, used for making network requests to fetch weather data from the Weather API.
7. **Kotlin Coroutines**: Used for managing background tasks and asynchronous operations in a more efficient and readable way.
8. **Flow**: A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
9. **Ramcosta Navigation Library**: Simplifies navigation within Jetpack Compose, providing a type-safe way to handle navigation.
10. **Coil**: An image loading library for Android backed by Kotlin Coroutines, used to load and display images in the app.
11. **Unit Testing**: Ensures the correctness of the code, with tests written for various components to validate functionality.
  
Architecture
------------
The application follows the MVVM Clean Architecture, which separates the code into distinct layers:

* **Presentation Layer**: Contains UI elements built with Jetpack Compose and ViewModels that interact with the UI.

* **Domain Laye**r: Includes use cases that encapsulate business logic and interact with the repository layer.

* **Data Layer**: Consists of repositories that handle data operations, fetching data from remote sources (Weather API) via Retrofit, and providing it to the domain layer.


Dependency Injection
--------------------

Hilt is used for dependency injection, providing a standardized way to manage dependencies and enabling easy testing and modularization. It helps in injecting ViewModels, repositories, and other dependencies across the app.

Network Operations
-------------------

Retrofit is employed for network operations, specifically for making API calls to the Weather API. The API client is configured with appropriate interceptors, converters, and error handling mechanisms to ensure reliable data fetching.  


Weather API
-----------

https://www.weatherapi.com/

UI / UX References:
-------------------

Figma community:
https://www.figma.com/community/file/1264522121969062318/weather-app

Unit Testing:
----------

Unit tests are written for various components of the app to ensure their functionality. Each test method focuses on testing a specific functionality or behavior of the WeatherRepository class, such as handling different types of exceptions, interacting with the ApiService, and updating shared preferences.

The tests include:

* **ViewModel Tests:** Validating the logic within ViewModels and their interaction with the UI.
* **Repository Tests:** Ensuring data fetching and manipulation logic is correct.
* **Use Case Tests:** Verifying the business logic encapsulated within use cases.

Testing is facilitated by mocking dependencies using Mockito and running asynchronous operations with the help of the Kotlin coroutines test library.

Test Methods:
----------------
1. getWeatherData returns success when api call is successful
2. getWeatherData returns error when IOException is thrown
3. getWeatherData returns error when HttpException is thrown
4. saveToSharedPrefs updates shared preferences and current location query
5. currentLocationQuery returns initial value from shared preferences

Overall, these tests cover various scenarios for the WeatherRepository class, including successful API calls, error handling for different exceptions, and updating shared preferences.

Test Reports:
--------------

Unit Test Report:
<img width="1019" alt="unitTest_reports" src="https://github.com/antostephen/WeatherApp/assets/172839891/7490774a-7f00-496a-8d32-902d441b8492">

Instrumentation Test Report:
<img width="1502" alt="Instrumentation_test_result" src="https://github.com/antostephen/WeatherApp/assets/172839891/b66b1a46-2b2e-49e2-82a8-4e78b0e5a61b">

Conclusion
------------
The weather app is a robust and user-friendly application that provides accurate weather information with a clean and responsive interface. Leveraging modern Android development practices and tools ensures the app is maintainable, scalable, and easy to test. The use of MVVM Clean Architecture, Dependency Injection, and comprehensive unit testing contributes to a high-quality codebase and overall application stability.



