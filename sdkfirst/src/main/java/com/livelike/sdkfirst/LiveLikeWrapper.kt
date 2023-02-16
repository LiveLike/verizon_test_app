package com.livelike.sdkfirst

import android.content.Context
import com.livelike.common.AccessTokenDelegate
import com.livelike.engagementsdk.EngagementSDK
import com.livelike.engagementsdk.publicapis.ErrorDelegate
import com.livelike.utils.LogLevel
import com.livelike.utils.minimumLogLevel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LiveLikeWrapper {
    private var token: String? = null
    fun initSDK(context: Context) {
        minimumLogLevel = LogLevel.Debug
        val engagementSDK = EngagementSDK(
            "PwYJzAlwwWcTkZ21OcR3YpfiNDoLovMQIRpkRj7U",
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
}