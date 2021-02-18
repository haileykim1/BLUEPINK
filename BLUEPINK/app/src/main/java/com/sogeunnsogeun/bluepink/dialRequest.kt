package com.sogeunnsogeun.bluepink

data class dialRequest(val version: String,
                       val chatbot_id: String,
                       val user_id:String,
                       val input_sentence: String,
                       val session_id: String,
                       val log_id: String,
                       val ins_id: String,
                       val intent_id: String,
                       val node_id: String,
                       val param_id: String,
                       val ref_intent_id: String,
                       val chatflow_id: String,
                       val another_result: String,
                       val result: String,
                       val parameters: String,
                       val emotions: String,
                       val evaluate_setting: String,
                       val debugCode: String,
                       val debugMsg: String,
                       val resultStatus: String) {
}