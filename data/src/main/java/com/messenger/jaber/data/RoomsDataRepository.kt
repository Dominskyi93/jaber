package com.messenger.jaber.data

import com.elveum.container.ListContainerFlow
import com.messenger.jaber.data.rooms.entities.RoomDataEntity

interface RoomsDataRepository {
    fun getRooms(): ListContainerFlow<RoomDataEntity>
}