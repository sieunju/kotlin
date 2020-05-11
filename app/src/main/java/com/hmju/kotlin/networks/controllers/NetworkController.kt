package com.hmju.kotlin.networks.controllers

/**
 * Description: Network Controller Single Ton Class
 *
 * Created by juhongmin on 2020/05/11
 */
class NetworkController {
    private val runList : ArrayList<Runnable> = ArrayList<Runnable>()

    companion object{
        val instance = NetworkController()
    }

    fun add(run: Runnable){
        runList.add(run)
    }

    fun runNext(){
        if(runList.isNotEmpty()){
            runList[0].run()
            runList.removeAt(0)
        }
    }

    fun clear(){
        runList.clear()
    }
}