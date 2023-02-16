package com.livelike.sampletestapp

import android.content.Context
import com.livelike.common.AccessTokenDelegate
import com.livelike.engagementsdk.EngagementSDK
import com.livelike.engagementsdk.LiveLikeContentSession
import com.livelike.engagementsdk.publicapis.ErrorDelegate
import com.livelike.utils.LogLevel
import com.livelike.utils.minimumLogLevel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LiveLikeWrapper {
    private lateinit var engagementSDK: EngagementSDK
    private var token: String? = null
    fun initSDK(context: Context) {
        minimumLogLevel = LogLevel.Debug
        engagementSDK = EngagementSDK(
            "8PqSNDgIVHnXuJuGte1HdvOjOqhCFE1ZCR3qhqaS",
            context,
            errorDelegate = object : ErrorDelegate() {
                override fun onError(error: String) {
                    println("error:$error")
                }
            }, accessTokenDelegate = object : AccessTokenDelegate {
                override fun getAccessToken(): String? {
                    return token
                }

                override fun storeAccessToken(accessToken: String?) {
                    println("LiveLikeWrapper.storeAccessToken:$accessToken")
                    token = accessToken
                }
            })
        GlobalScope.launch {
            engagementSDK.currentUserFlow.collect {
                println("LiveLikeWrapper.initSDK: $it")
            }
        }
    }

    fun createSession(): LiveLikeContentSession {
        return engagementSDK.createContentSession("09d93835-ee52-4757-976c-ea09d6a5798c")
    }
}