Weather App v1.0
-------------------------
###### Author: Anto Stephen ######

Introduction:
-------------
This Android App has been developed as part of the Assignment. 


Overview:
---------------
1. Implemented the App using Jet Pack Compose & the MVVM Architecture Android Clean Architeture
2. This weather App has 3 main screens:
   * Home Screen:
     
     <img width="333" alt="screen_1" src="https://github.com/antostephen/WeatherApp/assets/172839891/1499dfc7-e603-4d62-b68c-3096605b3ee3">
     <img width="334" alt="screen_2" src="https://github.com/antostephen/WeatherApp/assets/172839891/0276d965-4242-4dc1-9fdd-b0b617a2ea85">
     
   * Search Screen
     
     <img width="324" alt="screen_4" src="https://github.com/antostephen/WeatherApp/assets/172839891/a6dd4e2a-f7a1-40ed-a64c-86dc06c65079">

   * Add Screen:
     
     <img width="330" alt="screen_3" src="https://github.com/antostephen/WeatherApp/assets/172839891/62437ac3-9e44-49fa-ab2a-13f9c5eecdd9">
     
   * Alert Screen

4. Home Screen features:
   * Search and add a place
   * Display the Tempature
   * Weather Details
   * Hourly Weather Forecast
   * Next 3 days Weather Forecast  

Unit Testing:
----------

Here each test method focuses on testing a specific functionality or behavior of the WeatherRepository class, such as handling different types of exceptions, interacting with the ApiService, and updating shared preferences.

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



