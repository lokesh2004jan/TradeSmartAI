package com.example.TradeSmart.AI.controllers


import com.example.TradeSmart.AI.services.UserService
import jakarta.servlet.http.HttpSession

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService,
    private val authenticationManager: AuthenticationManager
) {

    data class RegisterRequest(val username: String, val password: String)
    data class LoginRequest(val username: String, val password: String)

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<String> {
        val response = userService.registerUser(request.username, request.password)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest, session: HttpSession): ResponseEntity<String> {
        return try {
            // ✅ Authenticate user using Spring Security
            val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request.username, request.password)
            )

            // ✅ Set authentication in SecurityContext
            SecurityContextHolder.getContext().authentication = authentication

            // ✅ Store user in session
            session.setAttribute("USER", authentication.principal)

            ResponseEntity.ok("Login Successful! Session created.")
        } catch (e: Exception) {
            ResponseEntity.status(401).body("Invalid Credentials")
        }
    }

    @PostMapping("/logout")
    fun logout(session: HttpSession): ResponseEntity<String> {
        session.invalidate() // ✅ Destroy session on logout
        return ResponseEntity.ok("Logged out successfully!")
    }
}
