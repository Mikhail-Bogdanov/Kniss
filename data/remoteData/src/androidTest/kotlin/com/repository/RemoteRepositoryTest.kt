package com.repository

import com.qwertyuiop.domaingray.entities.AuthEntity
import com.qwertyuiop.remotedata.dtos.AnswerDto
import com.qwertyuiop.remotedata.network.ServiceApi
import com.qwertyuiop.remotedata.repository.RemoteGrayRepositoryImpl
import com.qwertyuiop.remotedata.utils.GrayRemoteMapper.toAuthDto
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteRepositoryTest {

    private val serviceApi: ServiceApi = mock()

    @Test
    fun getServiceResponse_calls_api_and_returns_answer_entity() = runTest {
        val auth = AuthEntity(
            false,
            "test",
            "test"
        )
        val expectedResult = "some url"
        whenever(
            serviceApi.getAnswer(auth.toAuthDto())
        ).thenReturn(AnswerDto(expectedResult))

        val repository = RemoteGrayRepositoryImpl(serviceApi)
        val response = repository.getServiceResponse(auth)

        assertEquals(expectedResult, response.answer)
    }
}