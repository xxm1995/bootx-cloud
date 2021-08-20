// 新建优惠券模板
const addCouponTemplate = {
    "checkRules": [
        {
            "addition": "1,2,3",
            "code": "checkChannel",
            "name": "渠道检查",
            "priority": 0,
            "strategyId": 1394909889247203328
        }
    ],
    "obtainRules": [
        {
            "addition": "",
            "code": "obtainMultiple",
            "name": "多张领取检查",
            "priority": 0,
            "strategyId": 1395320340615417856
        }
    ],
    "matchRules": [
        {
            "featurePoint": "2001",
            "featureType": "goodsId",
            "matchType": "eq"
        }
    ],
    "configValues": [
        {
            "strategyConfigId": 1382517531788595200,
            "value": "100"
        },
        {
            "strategyConfigId": 1382517531788595201,
            "value": "33"
        }
    ],
    "desc": "100-33优惠券",
    "name": "100-33",
    "num": 1000,
    "strategyId": 1382517531398524928,
    "timeType": 1
}
