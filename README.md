# Sales Report App  



A full-stack sales reporting application featuring a gRPC-based backend (Java / Spring Boot) and an Android client (Kotlin / Jetpack Compose).  

Designed with modern libraries, Clean Architecture using MVVC pattern for frontend/MVC pattern for backend and cross-platform communication with JWT Authentication using Protocol Buffers.



## 🧩 Project Architecture  



proto/... ← shared contract (gRPC / Protobuf)



backend/src/main/java/... ← Spring Boot gRPC server



frontend/app/src/main/java ← Android app in Kotlin + Compose



### Backend (Spring Boot + gRPC)  

- Implements gRPC services defined in `proto/salesreport.proto`  

- Java + Spring Boot ecosystem

- Model, View, Controller pattern

- Uses Spring Boot’s support for gRPC + Protobuf

- Login, Register, authentication systems using JWT

- Connects to a relational database (see [backend setup](https://github.com/Jedsam/salesReportApp/tree/master/backend/database/setup) for creating the database)  

- Provides endpoints such as:  

  - Fetch hourly sales  

  - Report new sales  

  - Aggregate by device/location  



### Frontend (Android Kotlin + Compose + gRPC)  

- Android app built with Kotlin and Jetpack Compose

- Model, View, Viewmodel pattern

- Store/Generate(login) JWT for communicating with the backend 

- Clearn architecture (Data/Domain/UI)

- Uses gRPC client generated from the same Protobuf contract  

- UI design: clean, modern, stateless components  

- Offline-capable, syncs with backend when connected  



## 🔧 Key Technologies & Libraries  

- Protocol Buffers + gRPC for schema-first API  

- Java 17 (or latest LTS) + Spring Boot  

- Kotlin for Android client  

- Android Jetpack Compose for UI  

- Modern Android libraries: (e.g., Hilt/Dagger, Kotlin Coroutines, Flow, Navigate)  

- Relational database (MariaDB/SQLite) for persistence (see [frontend database](https://github.com/Jedsam/salesReportApp/tree/master/frontend/database) schema)  



## ✅ Features  

- Real-time hourly sales reporting  

- Aggregation by device/location  

- Shared gRPC contract ensures strong typing across client/server  

- Clean architecture for both backend and Android  

- Jetpack Compose-based UI for future scalability  





## 🎞️ Demo  

A short demo video is included showing the frontend working (though no connection is present)



https://github.com/user-attachments/assets/95c7018f-0a02-4ddb-81e1-a864a41b5b64

