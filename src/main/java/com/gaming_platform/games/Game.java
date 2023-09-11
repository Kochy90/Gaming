package com.gaming_platform.games;

public enum Game {
    /*
     I am a bit concerned about this whole games package. The way as I see it is your core domain, so it should somehow handle the whole of the business logic.
 On the other hand I see this is kind of scattered between the service layer (mostly GameService) amd the game itself.
 The way I see it there is no strictly defined responsibilities about who does what.

Also there is some feeling of duplicattion, take Roulette, for example, What is it? is this ROULETTE enum or is it the Roulette class? This seems a bit confusing to me
     */
    HIGHER_OR_LOWER,
    ROULETTE
}
