package mr.moridi.videoplayer.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://notificator.ir/api/v1/"

    private val retrofit=  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService : ApiService = retrofit.create(ApiService::class.java)

}
