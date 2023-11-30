package com.qwertyuiop.remotedata.repository

import com.qwertyuiop.domaingray.entities.AuthEntity
import com.qwertyuiop.domaingray.repositories.remote.RemoteGrayRepository
import com.qwertyuiop.remotedata.network.ServiceApi
import com.qwertyuiop.remotedata.utils.GrayRemoteMapper.toAnswerEntity
import com.qwertyuiop.remotedata.utils.GrayRemoteMapper.toAuthDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteGrayRepositoryImpl(
    private val serviceApi: ServiceApi
) : RemoteGrayRepository {

    override suspend fun getServiceResponse(
        authEntity: AuthEntity
    ) = withContext(Dispatchers.IO) {
        serviceApi.getAnswer(authEntity.toAuthDto()).toAnswerEntity()
    }
}