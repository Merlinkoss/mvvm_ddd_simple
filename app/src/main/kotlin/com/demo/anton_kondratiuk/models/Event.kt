package com.demo.anton_kondratiuk.models


sealed class Event<out T> {

    data class Error<out T> (val throwable: Throwable) : Event<T>()

    data class Complete<out T> (val data: T) : Event<T>()

    class Loading<out T> : Event<T>()

}