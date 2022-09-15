package com.jorge.paulo.jokeapp

import com.google.gson.Gson
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}
//OkHttp é projetado de tal forma que retorna a resposta em cache somente quando a Internet está disponível.
// retorna com o erro "sem internet disponível" mesmo quando os dados estão em cache e mas a internet não está disponível.
/*
addInterceptor: usado para adicionar o interceptor no nível do aplicativo.
addNetworkInterceptor: Como o nome diz, usado para adicionar o interceptor no nível da rede.
 */
val okHttpClient = OkHttpClient().newBuilder()
    .addNetworkInterceptor(CacheInterceptor()) // only if Cache-Control header is not enabled from the server
    .addInterceptor(ForceCacheInterceptor())
    .build();
/*
Depois disso, podemos usar isso okHttpClientdiretamente ou com Retrofit e funcionará conforme o esperado.

É assim que podemos armazenar em cache as respostas HTTP no Android usando o OkHttp Interceptor e o Retrofit para criar aplicativos Android offline.
 */

/*
Podemos criar um ForceCacheInterceptoradicional ao acima ( CacheInterceptor, somente se Cache-Controlo cabeçalho
não estiver habilitado no servidor).
 */
fun IsInternetAvailable() = true //todo criar ouvinte de internt

class ForceCacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!IsInternetAvailable()) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }
        return chain.proceed(builder.build());
    }
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

//var service: GitHubService = retrofit.create(GitHubService::class.java)