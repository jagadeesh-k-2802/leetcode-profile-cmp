package com.jackappsdev.leetcode.data.queries

object GraphQL {
    val USER_PROFILE_QUERY = """
        query userPublicProfile(${'$'}username: String!) {
            matchedUser(username: ${'$'}username) {
                contestBadge {
                  name
                  expired
                  hoverText
                  icon
                }
                username
                githubUrl
                twitterUrl
                linkedinUrl
                profile {
                  ranking
                  userAvatar
                  realName
                  aboutMe
                  school
                  websites
                  countryName
                  company
                  jobTitle
                  skillTags
                  postViewCount
                  postViewCountDiff
                  reputation
                  reputationDiff
                  solutionCount
                  solutionCountDiff
                  categoryDiscussCount
                  categoryDiscussCountDiff
                  certificationLevel
                }
              }
            }
    """.trimIndent()
}
