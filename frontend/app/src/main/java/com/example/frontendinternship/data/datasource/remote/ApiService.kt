package com.example.frontendinternship.data.datasource.remote

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/xml")
    @POST("receive")
    suspend fun sendXmlReport(
        @Body xmlBody: RequestBody
    ): Response<Unit>

    //@GET("movie/now_playing")
    //suspend fun nowPlayingMovies(
    //@Query("page") page: Int,
    //@Query("with_genres") genreId: String?
    //): BaseModel<MovieItem>
}