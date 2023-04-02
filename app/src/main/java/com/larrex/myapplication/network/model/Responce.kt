package com.larrex.myapplication.network.model

class Responce(
    var status: Status, var responce: List<IgboApiResponse>,
    var singleWordResponse: IgboSingleWordApiResponse,
    var message:String) {
}