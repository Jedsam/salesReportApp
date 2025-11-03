package com.example.frontendinternship.utils

import java.nio.ByteBuffer
import java.util.UUID

fun ByteArray.toUUID(): UUID {
    val bb = ByteBuffer.wrap(this)
    val high = bb.long
    val low = bb.long
    return UUID(high, low)
}

fun UUID.toBytes(): ByteArray {
    val bb = ByteBuffer.wrap(ByteArray(16))
    bb.putLong(this.mostSignificantBits)
    bb.putLong(this.leastSignificantBits)
    return bb.array()
}
