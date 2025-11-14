# Sales Report App  



A full-stack sales reporting application featuring a gRPC-based backend (Java / Spring Boot) and an Android client (Kotlin / Jetpack Compose).  

Designed with modern libraries, Clean Architecture using MVVC pattern for frontend/MVC pattern for backend and cross-platform communication with JWT Authentication using Protocol Buffers.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![gRPC](https://img.shields.io/badge/gRPC-4285F4?style=for-the-badge&logo=grpc&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

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



### Frontend (Android Kotlin + Compose + gRPC)  

- Android app built with Kotlin and Jetpack Compose

- Model, View, Viewmodel pattern

- Store/Generate(login) JWT for communicating with the backend 

- Clearn architecture (Data/Domain/UI)

- Uses gRPC client generated from the same Protobuf contract  

- UI design: clean, modern, stateless components  

- Offline-capable, syncs with backend when connected

- Stores data in local database SQLite (see [frontend database](https://github.com/Jedsam/salesReportApp/tree/master/frontend/database) for creating the database)



## 🔧 Key Technologies & Libraries  

- Protocol Buffers + gRPC for schema-first API  

- Java 17 (or latest LTS) + Spring Boot  

- Kotlin for Android client  

- Android Jetpack Compose for UI  

- Modern Android libraries: (e.g., Hilt/Dagger, Kotlin Coroutines, Flow, Navigate)  

- Relational database (MariaDB/SQLite) for persistence



## ✅ Features  

- Real-time hourly sales reporting  

- Aggregation by device/location  

- Shared gRPC contract ensures strong typing across client/server  

- Clean architecture for both backend and Android  

- Jetpack Compose-based UI for future scalability  


