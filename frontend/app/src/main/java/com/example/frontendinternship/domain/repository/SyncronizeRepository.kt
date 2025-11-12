package com.example.frontendinternship.domain.repository



interface SynchronizeRepository {
    suspend fun synchronize(): Boolean
}