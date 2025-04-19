package com.example.TradeSmart.AI.utils



object SdesEncryption {

    fun encrypt(plainText: String, key: String): String {
        val binaryText = plainText.toByteArray().joinToString("") { it.toString(2).padStart(8, '0') }
        return applySdes(binaryText, key) // Ensure it outputs binary
    }

    fun decrypt(cipherText: String, key: String): String {
        val decryptedBinary = applySdes(cipherText, key) // Decrypt as binary
        return binaryToString(decryptedBinary) // Convert binary back to text
    }

    private fun applySdes(binaryText: String, key: String): String {
        // Your existing S-DES logic here
        return binaryText // Modify this to ensure output is binary!
    }

    private fun binaryToString(binary: String): String {
        return binary.chunked(8)
            .map { it.toInt(2).toChar() }
            .joinToString("")
    }
}

