
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import CouponManager from "./components/CouponManager"

import OrderManager from "./components/OrderManager"

import PaymentManager from "./components/PaymentManager"


import Mypage from "./components/Mypage"
export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/Coupon',
                name: 'CouponManager',
                component: CouponManager
            },

            {
                path: '/Order',
                name: 'OrderManager',
                component: OrderManager
            },

            {
                path: '/Payment',
                name: 'PaymentManager',
                component: PaymentManager
            },


            {
                path: '/Mypage',
                name: 'Mypage',
                component: Mypage
            },


    ]
})
