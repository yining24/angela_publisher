package com.angela.angelapublisher

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Articles (
    var author: Author?=null,
    var title: String? = null,
    var content: String? = null,
    var createdTime: Long? = null,
    var id: String? = null,
    var tag: String? = null
)


@Parcelize
data class Author(
    var email: String? = null,
    var id: String? = null,
    var name: String? = null
): Parcelable