package com.example.TradeSmart.AI

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.example.TradeSmart.AI"]) // ✅ Ensures Spring scans all packages
class TradeSmartApplication

fun main(args: Array<String>) {
	runApplication<TradeSmartApplication>(*args)
}
