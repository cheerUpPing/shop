/**
 * 发送请求
 *
 * @param isAsync 是否异步
 * @param requestMethod 字符串 "POST" "GET"
 * @param requestUrl
 * @param requestParam json对象 {}
 * @param loginUrl session过期跳转到登录页面
 * @returns {*} 返回json
 */
function sendRequestJson(requestMethod,isAsync,requestUrl,requestParam,loginUrl) {
    var data;
    $.ajax({
        async: isAsync,
        url: requestUrl,
        type: requestMethod,
        data: requestParam,
        dataType: "text",
        success: function (result) {
            data = $.parseJSON(result);
            if (data.code == "0003"){
                $(window).attr('location',loginUrl);
            }
        },
        error: function () {
            data = $.parseJSON({"code":"_0001","msg":"网络异常","data":null});
        }
    });
    return data;
}

/**
 * 发送请求
 *
 * @param isAsync 是否异步
 * @param requestMethod 字符串 "POST" "GET"
 * @param requestUrl
 * @param requestParam json对象 {}
 * @param loginUrl session过期跳转到登录页面
 * @returns {*} 返回页面
 */
function sendRequestPage(requestMethod,isAsync,requestUrl,requestParam,loginUrl) {
    var data;
    $.ajax({
        async: isAsync,
        url: requestUrl,
        type: requestMethod,
        data: requestParam,
        dataType: "text",
        success: function (result) {
            try {
                data = $.parseJSON(result);
                if (data.code = "0003"){
                    $(window).attr('location',loginUrl);
                }
            }catch (e){
                data = result;
            }
        },
        error: function () {
            data = $.parseJSON({"code":"_0001","msg":"网络异常","data":null});
        }
    });
    return data;
}

/**
 * 发送请求
 *
 * @param isAsync 是否异步
 * @param requestUrl
 * @param formData formData对象
 * @param loginUrl session过期跳转到登录页面
 * @returns {*} 发送表单(可以包含文件) 返回json
 */
function sendFormRequestJson(isAsync,requestUrl,formData,loginUrl) {
    var data;
        $.ajax({
            url: requestUrl,
            type: 'POST',
            data: formData,
            async: isAsync,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                data = $.parseJSON(result);
                if (data.code == "0003"){
                    $(window).attr('location',loginUrl);
                }
            },
            error: function () {
                data = $.parseJSON({"code":"_0001","msg":"网络异常","data":null});
            }
        });
    return data;
}