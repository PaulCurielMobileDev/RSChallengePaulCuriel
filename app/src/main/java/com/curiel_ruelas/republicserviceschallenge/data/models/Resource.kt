package com.curiel_ruelas.republicserviceschallenge.data.models


sealed class Resource<out T> {

        class  Success<out T>(val data: T): Resource<T>()

        class  Error<out T>(val msg: String, val data: T? = null): Resource<T>()

        class Loading<out T> : Resource<T>()



}
class pruebas{

        fun giveResource():Resource<DataResponse>{
                return Resource.Success(DataResponse(emptyList(), emptyList()))
        }

        fun workWithResource(){
                var theResource= giveResource()

                when(theResource){
                        is Resource.Success->{
                                theResource.data
                        }
                        else ->{
                                theResource.toString()
                        }
                }
        }

}


