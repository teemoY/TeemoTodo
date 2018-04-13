package com.teemo.network.entity

import java.util.*


/**
 * Created by Teemo on 2018/4/12.
 * @author teemo
 */
class User {


    private var id: Long = 0
    private var account: String? = null
    private var password: String? = null
    private var gender: Byte = 0
    private var birthday: Date? = null
    private var createTime: Date? = null


    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getAccount(): String? {
        return account
    }

    fun setAccount(account: String) {
        this.account = account
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getGender(): Byte {
        return gender
    }

    fun setGender(gender: Byte) {
        this.gender = gender
    }

    fun getBirthday(): Date? {
        return birthday
    }

    fun setBirthday(birthday: Date) {
        this.birthday = birthday
    }

    fun getCreateTime(): Date? {
        return createTime
    }

    fun setCreateTime(createTime: Date) {
        this.createTime = createTime
    }

    override fun toString(): String {
        return "User{id=$id, account='$account\', password='$password', gender=$gender, birthday=$birthday, createTime=$createTime}"
    }

}