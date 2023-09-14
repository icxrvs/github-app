package com.example.githubapp.mappers

import com.example.core.domain.User
import com.example.githubapp.framework.network.response.UserResponse

object UserMapper {
    fun toUserModel(userResponse: UserResponse): User {
        return User(
            login = userResponse.login,
            id = userResponse.id,
            nodeId = userResponse.nodeId,
            avatarUrl = userResponse.avatarUrl,
            gravatarId = userResponse.gravatarId,
            url = userResponse.url,
            htmlUrl = userResponse.url,
            followersUrl = userResponse.followersUrl,
            followingUrl = userResponse.followingUrl,
            gistsUrl = userResponse.gistsUrl,
            starredUrl = userResponse.starredUrl,
            subscriptionsUrl = userResponse.subscriptionsUrl,
            organizationsUrl = userResponse.organizationsUrl,
            reposUrl = userResponse.reposUrl,
            eventsUrl = userResponse.eventsUrl,
            receivedEventsUrl = userResponse.receivedEventsUrl,
            type = userResponse.type,
            siteAdmin = userResponse.siteAdmin
        )
    }

    fun toListUserModel(responseList: List<UserResponse>): List<User> {
        return responseList.map { toUserModel(it) }
    }
}