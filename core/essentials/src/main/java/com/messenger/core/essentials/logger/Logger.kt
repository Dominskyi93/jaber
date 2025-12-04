package com.messenger.core.essentials.logger

interface Logger {
    fun d(message: String)
    fun e(exception: Exception, message: String = "Error!")

    companion object : Logger {
        private var instance: Logger = DefaultLogger

        override fun e(exception: Exception, message: String) {
            println("ERROR: $message")
            exception.printStackTrace()
        }

        override fun d(message: String) {
            println("DEBUG: $message")
        }

        fun set(logger: Logger) {
            this.instance = logger
        }

        fun reset() {
            this.instance = DefaultLogger
        }
    }
}