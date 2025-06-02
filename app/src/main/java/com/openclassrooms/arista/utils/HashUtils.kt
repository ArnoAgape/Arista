package com.openclassrooms.arista.utils

import de.mkammerer.argon2.Argon2Factory

object HashUtils {

    fun hashPassword(password: String): String {
        val argon2 = Argon2Factory.create()
        return argon2.hash(2, 65536, 1, password.toCharArray())
    }
    fun verifyPassword(hash: String, password: String): Boolean {
        val argon2 = Argon2Factory.create()
        return argon2.verify(hash, password.toCharArray())
    }
}


