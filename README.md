# codingTest
A sample e-commerce mobile application having home screen with dynamic product widgets - a carousel, products grid and a banner.
Used https://www.mockable.io/a/ for the mock APIs.

Each Feature/Modules are separated as different packages. In each Package we can see following classes:

- **`**Backend.kt:`**
    - Retrofit Interface class for backend API.
    - API path will be defined here.
- **`**Fetcher.kt:`**
    - Fetcher responsible for initializing the retrofit client and JSON converter.
    - It hit the backend API and get the JSON response and convert to `Core` model
- **`**Repository.kt:`**
    - Repository is responsible for composing the backend fetcher and convert it into ViewState in background thread.
    - It helps to manage/store the data locally as well as remotely.
    - Handle creating different states like loading, idle and errors.
- **`**ViewModel.kt:`**
    - Responsible to present the view in mobile.
    - `Activity` communicate with `ViewModel` to initiate all the process above.
    - ViewModel would be activity lifecycle aware.
