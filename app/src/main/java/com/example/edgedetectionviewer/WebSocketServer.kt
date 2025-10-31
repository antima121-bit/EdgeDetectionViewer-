package com.example.edgedetectionviewer

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.io.ByteArrayOutputStream
import java.util.concurrent.ConcurrentLinkedQueue

class WebSocketServer {

    private val connections = ConcurrentLinkedQueue<DefaultWebSocketSession>()
    private var server: CIOApplicationEngine? = null

    fun start() {
        server = embeddedServer(CIO, port = 8080) {
            install(WebSockets)
            routing {
                webSocket("/") {
                    connections.add(this)
                    try {
                        for (frame in incoming) {
                            // Handle incoming messages if needed
                        }
                    } finally {
                        connections.remove(this)
                    }
                }
            }
        }.start(wait = false)
    }

    fun stop() {
        server?.stop(1000, 5000)
    }

    suspend fun broadcast(image: android.graphics.Bitmap) {
        val stream = ByteArrayOutputStream()
        image.compress(android.graphics.Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        for (session in connections) {
            session.send(Frame.Binary(true, byteArray))
        }
    }
}
