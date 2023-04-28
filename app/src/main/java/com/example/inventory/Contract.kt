package com.example.inventory

interface Contract {

    interface View{
        fun showProgress()
        fun hideProgress()
    }

    interface Model{
        interface onFinishedListener{
            fun onFinished(str: String)
        }
    }

    interface Presenter{
        fun onButtonClick()
        fun onDestroy()
    }

}