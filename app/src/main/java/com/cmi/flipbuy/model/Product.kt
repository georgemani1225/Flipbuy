package com.cmi.flipbuy.model

class Product{
    var productName: String? = null
    var productPrice: String? = null
    var productImage: Int? = 0
    constructor(ProductName: String?, ProductPrice: String?, ProductImage: Int?){
        this.productName = ProductName
        this.productImage = ProductImage
        this.productPrice = ProductPrice
    }
}




