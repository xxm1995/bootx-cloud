
//自动查询价格
const checkAutoOrderPrice ={
    "addition": "",
    "businessId": 0,
    "code": "6688",
    "buyerId": 0,
    "channelId": 1,
    "details": [
        {
            "addition": "",
            "categoryId": 0,
            "goodsId": 101,
            "goodsPrice": 1000,
            "goodsTitle": "商品1",
            "num": 1,
            "skuId": 1001
        },
        {
            "addition": "",
            "categoryId": 0,
            "goodsId": 101,
            "goodsPrice": 1002,
            "goodsTitle": "商品2",
            "num": 1,
            "skuId": 1002
        },{
            "addition": "",
            "categoryId": 0,
            "goodsId": 0,
            "goodsPrice": 1003,
            "goodsTitle": "商品3",
            "num": 1,
            "skuId": 1003
        }
    ],
    "deviceId": "",
    "source": "",
    "type": 0
}

//手动计算价格 带优惠券
const checkChooseOrderPrice = {
    "addition": "",
    "code": "12346",
    "businessId": 0,
    "userId": 1001,
    "channelId": 1,
    "couponIds": [
        // 1328979905706348544,
        1328979922198351872
    ],
    "details": [
        {
            "addition": "",
            "categoryId": 0,
            "goodsId": 101,
            "goodsPrice": 1000,
            "goodsTitle": "商品A",
            "num": 1,
            "skuId": 1001,
            "strategyRegisterIds": [
                1320265025499320320
            ]
        },
        {
            "addition": "",
            "categoryId": 0,
            "goodsId": 101,
            "goodsPrice": 1500,
            "goodsTitle": "商品B",
            "num": 1,
            "skuId": 1002,
            "strategyRegisterIds": [
                1320265025499320320,
                1320277318022877184
            ]
        },
        {
            "addition": "",
            "categoryId": 0,
            "goodsId": 0,
            "goodsPrice": 333,
            "goodsTitle": "商品C",
            "num": 1,
            "skuId": 1003,
            "strategyRegisterIds": [
                1320276824919527424
            ]
        }
    ],
    "deviceId": "11",
    "source": "22"
}
