package com.cmi.flipbuy.model

data class Product(val ProductName:String,val ProductDescrip:String,val productPrice:String,val productRating:String,val uid:String,val productImage:Int) {
    constructor():this("","","","","",0)
}