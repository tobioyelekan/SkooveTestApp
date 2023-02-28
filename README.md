# News Feed
## Features 🎨

- Coroutine, 
- Jetpack compose, 
- Dependency Injection using Dagger Hilt 
- Coroutines Flow
- MVVM Architecture
- SOLID Principles.

## How To Build
Pull the code on this branch, import into Android Studio, from there you can run it like a standard
android project project or run ./gradlew assembleDebug. Further notes can be found here https://developer.android.com/studio/build/building-cmdline#DebugMode

## To-do Tasks
- Song details screen
The approach I would have taken here is to pass the `id` of the song to the details screen using compose navigation arguments,
and then getting the id via savedStateHandle in the injected viewmodel of the song details screen, with the `id` fetched, 
I can then fetch the song from the repository.

-Unit test
I intend to write unit test for every testable classes and functions in isolation, by using a mock class or use Mockito to mock it's dependencies and using the Given-When-Then approach for functions

Possible unit test coverage
- Unit test AllSongsViewModel 
- Unit test SongDetailsViewModel
- Unit test LocalDatSourceImpl
- Unit test RemoteDataSourceImpl
- Integration test SongRepositoryImpl
- Unit test Dao class
