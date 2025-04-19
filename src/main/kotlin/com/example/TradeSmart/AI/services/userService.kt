package com.example.TradeSmart.AI.services

import com.example.TradeSmart.AI.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service // ✅ This is required
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()

    fun registerUser(username: String, password: String): String {
        val encryptedPassword = passwordEncoder.encode(password) // ✅ Encrypt password
        val user = com.example.TradeSmart.AI.model.User(username = username, encryptedPassword = encryptedPassword)
        userRepository.save(user)
        return "User Registered Successfully!"
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found")

        return org.springframework.security.core.userdetails.User.withUsername(user.username)
            .password(user.encryptedPassword)
            .roles("USER")
            .build()
    }
}
