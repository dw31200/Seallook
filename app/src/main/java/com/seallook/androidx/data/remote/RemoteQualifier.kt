package com.seallook.androidx.data.remote

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteCoroutine

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WebClientId

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CurrentUser

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BeginSignInResultQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ConnectTimeoutPolicy
