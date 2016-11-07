# Mini racer with GDX

Sandbox for trying out libgdx. Simple-prove-a-point top to down car racer game. 

Game is compiled in two versions. One is built with GWT (runs on browser), another built with kotlin and kryonet (networking) integration. 

Although networking seems to work correctly, its still missing some parts (as clients should have a reported position, or client update for a server of its input changes)

## Running locally

* To run the game, simply `./gradlew :desktop:run`. This will run the localhost server and the core game. 
* After running the game, `./gradlew :desktop_client:run` will connect to your server. Server will see the changes and add an additional car, dedicated for the client. Client will reflect the changes, though position update is still missing :)

## Running browser

* To try out game in a [browser](http://545149.s.dedikuoti.lt/racer/)

Definitely should work on chrome, didn't try others.

## Used technologies

* Libgdx
* Kotlin
* Box2d
* Kryonet

As far as I've tried, runs on all platforms.

## History

My sandbox of trying out game making with libgdx. Amazing library, would use it even more, if I would have the time and resources to do something fully. Loved every minute of it. Learned a lot of stuff. If anyone will want to use it as a reference, please do as you wish. :)

Game itself is simple enough and created by the references that are declared below. I've created such a game in the past, so was lucky enough to find a reference of similar implementation, so the only thing that was needed - just to assemble everything. 

Browser game - a bit of a drawback. The base of the game was compiled in kotlin. Wanting to compile it with GWT, hit a wall, that GWT only compiles with java. For it to work, had to decompile kotlin files and do a bit of a cleanup. This worked out, and GWT compiled successfully. 

Networking - even though its not complete as i would wish for, still i'm quite happy of the result. Proved a point that kryonet works flawlessly and can be used further. 

Also quite sad that browser implementation lacks full websockets integration :( as it does not have UDP packet sending (or i do not understand the problem correctly) which is essential to real time game such as this. 

![Racer game](imgs/racer.gif)

## References

* [Test game in java](https://github.com/signalsin/Racer)
* [Physics tutorial](http://www.iforce2d.net/b2dtut/top-down-car)
