# Sales Report App  



A full-stack sales reporting application featuring a gRPC-based backend (Java / Spring Boot) and an Android client (Kotlin / Jetpack Compose).  

Designed with modern libraries, Clean Architecture using MVVC pattern for frontend/MVC pattern for backend and cross-platform communication with JWT Authentication using Protocol Buffers.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![gRPC](https://img.shields.io/badge/gRPC-4285F4?style=for-the-badge&logo=grpc&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)



## ✅ Core Features

* **Real-time Reporting:** Real-time hourly transaction reporting.
* **Data Aggregation:** View sales aggregated by device or location.
* **Secure Authentication:** Full Login/Register flow using **JWT** for secure API communication.
* **Cross-Platform Contract:** A single `.proto` file defines the API contract, ensuring type safety between the backend and Android client.
* **Clean architecture:** Modern architecture for both backend and Android frontend
* **Offline-First Android App:** The Android client caches data and syncs with the backend when connected.
* **Modern UI:** A clean, stateless UI built entirely with Jetpack Compose.

---
## 🎞️ Demo

A short demo video is included showing the frontend working (though no connection is present)
<br>
<video src="https://github.com/user-attachments/assets/95c7018f-0a02-4ddb-81e1-a864a41b5b64" width="300"></video>

---
## 🛠️ Technology Stack

| Category | Technology |
| :--- | :--- |
| 🛰️ **Shared Contract** | gRPC, Protocol Buffers (Protobuf) |
| 🖥️ **Backend** | Java 17+, Spring Boot, Spring Security (for JWT), gRPC-Spring-Boot-Starter |
| 📱 **Frontend** | Kotlin, Android SDK, Jetpack Compose, Coroutines & Flow, Hilt (DI), gRPC-Kotlin |
| 🛢️ **Database** | **Backend:** Relational DB (MariaDB) <br> **Frontend:** SQLite (via Room) for offline cache |

---

## 🧩 Project Architecture  



    ├── proto/              # 🛰️ Shared gRPC & Protobuf Contract
    │   └── salesreport.proto
    ├── backend/            # 🖥️ Spring Boot gRPC Server
    │   └── src/main/java
    └── frontend/           # 📱 Android Client
        └── app/src/main/java



### 🖥️ Backend (Spring Boot & gRPC Server)

The backend is a robust gRPC server built with Spring Boot. It implements the services defined in `salesreport.proto`.

* **Pattern:** Follows a classic Model-View-Controller (MVC)-style layering.
* **Authentication:** Implements JWT-based authentication. A gRPC interceptor validates the JWT on protected endpoints.
* **Services:** Provides gRPC endpoints for:
    * User registration and login (generating JWTs).
    * Reporting new transaction events.
    * Querying user information.
* **Persistence:** Connects to a relational database to persist user and sales data. (See [Backend Database Setup](https://github.com/Jedsam/salesReportApp/tree/master/backend/database/setup)).

### 📱 Frontend (Android, Compose & gRPC Client)

The Android app is a modern, offline-capable client built with 100% Kotlin and Jetpack Compose.

* **Architecture:** Implements **Clean Architecture** (Data / Domain / UI layers).
* **UI Pattern:** Uses the **Model-View-ViewModel (MVVM)** pattern.
* **UI:** Built entirely with Jetpack Compose, using stateless components and state hoisting.
* **Concurrency:** Uses Kotlin Coroutines and Flow for all asynchronous operations (API calls, database access).
* **Networking:** Uses a generated gRPC-Kotlin client to communicate with the backend, securely attaching the JWT to requests.
* **Authentication:** Stores/Generates(login) JWT for communicating with the backend 
* **Offline Storage:** Uses a local **SQLite** database (see [Frontend Database Schema](https://github.com/Jedsam/salesReportApp/tree/master/frontend/database)) to cache data, allowing the app to function offline.


