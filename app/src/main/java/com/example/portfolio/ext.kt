package com.example.portfolio

/**
 * @Author: Temur
 * @Date: 01/08/2022
 */


fun interface OnClick<T> {
    fun onClick(data: T?)
}