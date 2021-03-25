package com.rodriguez.giomar.el_meneo.api

import com.apollographql.apollo.ApolloClient

object MyApolloClient {
    val apolloClient: ApolloClient by lazy {
        ApolloClient.builder()
            .serverUrl("http://165.22.9.67/v1/graphql")
            .build()
    }
}