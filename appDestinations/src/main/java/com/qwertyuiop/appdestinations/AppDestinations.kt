package com.qwertyuiop.appdestinations

data object AppDestinations {

    private enum class Destinations {
        Gray, Error, White, Loading
    }

    val Gray = Destinations.Gray.name
    val Error = Destinations.Error.name
    val White = Destinations.White.name
    val Loading = Destinations.Loading.name
}