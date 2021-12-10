package com.chatmen.plugins

import com.chatmen.routes.authenticate
import com.chatmen.routes.createMemberAccount
import com.chatmen.routes.loginMember
import com.chatmen.service.MemberService
import com.chatmen.service.chat.ChatController
import com.chatmen.service.chat.ChatService
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val memberService: MemberService by inject() // User Service
    val chatService: ChatService by inject() // Chat Service
    val chatController: ChatController by inject() // Chat Controller

    val jwtIssuer = environment.config.property("jwt.domain").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()

    install(Routing) {
        // Auth Routes
        authenticate()
        createMemberAccount(memberService)
        loginMember(
            memberService = memberService,
            jwtIssuer = jwtIssuer,
            jwtAudience = jwtAudience,
            jwtSecret = jwtSecret
        )

        // Chat Service
//        getChatsForUser(chatService)
//        getMessagesForChat(chatService)
//        chatWebSocket(chatController)

        // Static Resources
        static {
            resources("static")
        }
    }
}
