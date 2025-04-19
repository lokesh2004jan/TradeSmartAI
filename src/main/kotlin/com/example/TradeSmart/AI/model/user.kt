package com.example.TradeSmart.AI.model

import jakarta.persistence.*

@Entity
@Table(name = "users")  // ✅ Ensure table name matches MySQL
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val username: String,


    @Column(nullable = false)
    val encryptedPassword: String
){
    constructor() : this( null,"", "")  // ✅ Default constructor
}