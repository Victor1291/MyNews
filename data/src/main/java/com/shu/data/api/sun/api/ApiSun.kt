package  com.shu.data.api.sun.api

import com.shu.data.api.sun.model.SunriseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSun {

    @GET("/json")
    suspend fun getSunrise(
        @Query("lat") lat: Float ,
        @Query("lng") lng : Float ,
        @Query("date") date : String = "today", // 2024-01-09
    ): SunriseDto

}