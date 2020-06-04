package com.cmi.flipbuy.model

import com.cmi.flipbuy.fragment.DashboardFragment

data class Product(
    var ProductName: String,
    var ProductDescrip: String,
    var productPrice: String,
    var productRating: String,
    var uid: String,
    var productImage: Int
) {

    constructor() : this("", "", "", "", "", 0)


}
