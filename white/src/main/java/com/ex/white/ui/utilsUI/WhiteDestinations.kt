package com.ex.white.ui.utilsUI

class WhiteDestinations {
    private enum class Destinations {
        Main, Settings, Start
    }

    companion object {
        val Main = Destinations.Main.name
        val Settings = Destinations.Settings.name
        val Start = Destinations.Start.name
    }
}