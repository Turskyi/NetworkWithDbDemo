package io.github.turskyi.networkwithdbdemo.data.util

import io.github.turskyi.networkwithdbdemo.common.Resource
import kotlinx.coroutines.flow.*

// "inline" makes sure that unnecessary objects will not created
/* ResultType: Type for the Resource data.
 RequestType: Type for the API response. */
inline fun <ResultType, RequestType> getNetworkBoundResource(
    // Called to get the cached data from the database.
    crossinline query: () -> Flow<ResultType>,
    // Called to get the data from the network.
    crossinline fetch: suspend () -> RequestType,
    // Called to save the result of the API response into the database
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    /* Called with the data in the database to decide whether to fetch
     potentially updated data from the network. */
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
): Flow<Resource<ResultType>> {
    return flow {
        // "first" means retrieve only one time
        val data:ResultType = query().first()

        val flow: Flow<Resource<ResultType>> = if (shouldFetch(data)) {
            emit(Resource.Loading(data))

            try {
                saveFetchResult(fetch())
                query().map { Resource.Success(it) }
            } catch (throwable: Throwable) {
                query().map { Resource.Error(throwable, it) }
            }
        } else {
            query().map { Resource.Success(it) }
        }

        emitAll(flow)
    }
}