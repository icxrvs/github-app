package com.example.githubapp.mappers

import com.example.core.domain.License
import com.example.core.domain.Owner
import com.example.core.domain.User
import com.example.core.domain.UserRepositories
import com.example.githubapp.framework.network.response.LicenseResponse
import com.example.githubapp.framework.network.response.OwnerResponse
import com.example.githubapp.framework.network.response.UserRepositoriesResponse
import com.example.githubapp.framework.network.response.UserResponse

object UserMapper {
    fun toUserModel(userResponse: UserResponse): User {
        return User(
            login = userResponse.login,
            name = userResponse.name,
            bio = userResponse.bio,
            id = userResponse.id,
            followers =  userResponse.followers,
            following = userResponse.following,
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

    fun toListUserRepositories(responseList: List<UserRepositoriesResponse>) : List<UserRepositories>{
        return responseList.map { toUserRepositories(it) }
    }

    private fun toUserRepositories(userResponse: UserRepositoriesResponse): UserRepositories {
        return UserRepositories(
            id = userResponse.id,
            nodeId = userResponse.nodeId,
            name = userResponse.name,
            fullName = userResponse.fullName,
            private = userResponse.private,
            owner = userResponse.ownerResponse?.toOwner(),
            htmlUrl = userResponse.htmlUrl,
            description = userResponse.description,
            fork = userResponse.fork,
            url = userResponse.url,
            forksUrl = userResponse.forksUrl,
            keysUrl = userResponse.keysUrl,
            collaboratorsUrl = userResponse.collaboratorsUrl,
            teamsUrl = userResponse.teamsUrl,
            hooksUrl = userResponse.hooksUrl,
            issueEventsUrl = userResponse.issueEventsUrl,
            eventsUrl = userResponse.eventsUrl,
            assigneesUrl = userResponse.assigneesUrl,
            branchesUrl = userResponse.branchesUrl,
            tagsUrl = userResponse.tagsUrl,
            blobsUrl = userResponse.blobsUrl,
            gitTagsUrl = userResponse.gitTagsUrl,
            gitRefsUrl = userResponse.gitRefsUrl,
            treesUrl = userResponse.treesUrl,
            statusesUrl = userResponse.statusesUrl,
            languagesUrl = userResponse.languagesUrl,
            stargazersUrl = userResponse.stargazersUrl,
            contributorsUrl = userResponse.contributorsUrl,
            subscribersUrl = userResponse.subscribersUrl,
            subscriptionUrl = userResponse.subscriptionUrl,
            commitsUrl = userResponse.commitsUrl,
            gitCommitsUrl = userResponse.gitCommitsUrl,
            commentsUrl = userResponse.commentsUrl,
            issueCommentUrl = userResponse.issueCommentUrl,
            contentsUrl = userResponse.contentsUrl,
            compareUrl = userResponse.compareUrl,
            mergesUrl = userResponse.mergesUrl,
            archiveUrl = userResponse.archiveUrl,
            downloadsUrl = userResponse.downloadsUrl,
            issuesUrl = userResponse.issuesUrl,
            pullsUrl = userResponse.pullsUrl,
            milestonesUrl = userResponse.milestonesUrl,
            notificationsUrl = userResponse.notificationsUrl,
            labelsUrl = userResponse.labelsUrl,
            releasesUrl = userResponse.releasesUrl,
            deploymentsUrl = userResponse.deploymentsUrl,
            createdAt = userResponse.createdAt,
            updatedAt = userResponse.updatedAt,
            pushedAt = userResponse.pushedAt,
            gitUrl = userResponse.gitUrl,
            sshUrl = userResponse.sshUrl,
            cloneUrl = userResponse.cloneUrl,
            svnUrl = userResponse.svnUrl,
            homepage = userResponse.homepage,
            size = userResponse.size,
            stargazersCount = userResponse.stargazersCount,
            watchersCount = userResponse.watchersCount,
            language = userResponse.language,
            hasIssues = userResponse.hasIssues,
            hasProjects = userResponse.hasProjects,
            hasDownloads = userResponse.hasDownloads,
            hasWiki = userResponse.hasWiki,
            hasPages = userResponse.hasPages,
            hasDiscussions = userResponse.hasDiscussions,
            forksCount = userResponse.forksCount,
            mirrorUrl = userResponse.mirrorUrl,
            archived = userResponse.archived,
            disabled = userResponse.disabled,
            openIssuesCount = userResponse.openIssuesCount,
            license = userResponse.licenseResponse?.toLicense(),
            allowForking = userResponse.allowForking,
            isTemplate = userResponse.isTemplate,
            webCommitSignoffRequired = userResponse.webCommitSignoffRequired,
            topics = userResponse.topics,
            visibility = userResponse.visibility,
            forks = userResponse.forks,
            openIssues = userResponse.openIssues,
            watchers = userResponse.watchers,
            defaultBranch = userResponse.defaultBranch
        )
    }

    fun OwnerResponse.toOwner(): Owner {
         return Owner(
            login = this.login,
            id = this.id,
            nodeId = this.nodeId,
            avatarUrl = this.avatarUrl,
            gravatarId = this.gravatarId,
            url = this.url,
            htmlUrl = this.htmlUrl,
            followersUrl = this.followersUrl,
            followingUrl = this.followingUrl,
            gistsUrl = this.gistsUrl,
            starredUrl = this.starredUrl,
            subscriptionsUrl = this.subscriptionsUrl,
            organizationsUrl = this.organizationsUrl,
            reposUrl = this.reposUrl,
            eventsUrl = this.eventsUrl,
            receivedEventsUrl = this.receivedEventsUrl,
            type = this.type,
            siteAdmin = this.siteAdmin
        )
    }

    fun LicenseResponse.toLicense(): License {
        return License(
            key = this.key,
            name = this.name,
            spdxId = this.spdxId,
            url = this.url,
            nodeId = this.nodeId
        )
    }
}