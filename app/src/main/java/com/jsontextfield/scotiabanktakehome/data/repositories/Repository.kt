package com.jsontextfield.scotiabanktakehome.data.repositories
interface Repository<T> {
    suspend fun getData(userId: String) : T
}
