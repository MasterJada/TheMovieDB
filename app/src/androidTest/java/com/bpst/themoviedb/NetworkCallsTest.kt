package com.bpst.themoviedb

import androidx.test.runner.AndroidJUnit4
import com.bpst.themoviedb.network.APIServer
import io.reactivex.rxkotlin.toObservable
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NetworkCallsTest {
    @Test
    fun getMoviesRequest(){
        APIServer.buildApi().loadChanges().call()
            .subscribe({
                Assert.assertEquals(true, it.results.size > 0)
            }, {
                throw it
            })
    }

    @Test
    fun getMoviesFromRequest(){
        APIServer.buildApi().loadMovieInfo(422803)
            .call().subscribe {
                assertEquals(true, it != null)
            }
    }

}