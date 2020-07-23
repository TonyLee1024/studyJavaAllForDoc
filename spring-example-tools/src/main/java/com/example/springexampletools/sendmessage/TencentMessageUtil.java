package com.example.springexampletools.sendmessage;

import com.aliyuncs.exceptions.ClientException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;

/**
 * @ClassName: TencentMessageUtil
 * 参数及API信息:https://cloud.tencent.com/document/product/382/38778#2.-.E8.BE.93.E5.85.A5.E5.8F.82.E6.95.B0
 * 验证码标准:https://cloud.tencent.com/document/product/382/39023#.E7.89.B9.E5.AE.9A.E8.A7.84.E8.8C.83
 */
public class TencentMessageUtil {

    // 在 账号中心--访问管理 -- 访问密钥 -- API密钥管理
    final static String secretId = "";
    final static String secretKey = "";
    //短信应用ID: 短信SdkAppid
    final static String appid = "";
    //短信签名内容
    final static String sign = "";
    //模板 ID: 必须填写已审核通过的模板 ID
    final static String templateID = "";

    public static void main(String[] args) throws ClientException, InterruptedException {
        String TencentMessageResult = sendSms("", "123456");
    }

    /**
     * 传入手机号和验证码，并让腾讯服务发送，返还参数:200 或 400
     *
     * @param phoneNumber
     * @param code
     * @return
     * @throws ClientException
     */
    public static String sendSms(String phoneNumber, String code) throws ClientException {
        String result = "";
        SendSmsRequest req = new SendSmsRequest();
        req.setSmsSdkAppid(appid);
        req.setSign(sign);
        req.setTemplateID(templateID);
        //国内强制加+86
        String[] phoneNumbers = {"+86" + phoneNumber};
        req.setPhoneNumberSet(phoneNumbers);
        //模板参数
        String[] templateParams = {code};
        req.setTemplateParamSet(templateParams);

        /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
         * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */

        try {
            /* 必要步骤：
             * 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
             */
            Credential cred = new Credential(secretId, secretKey);

            /* 非必要步骤:
             * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK默认用TC3-HMAC-SHA256进行签名
             * 非必要请不要修改这个字段 */
            clientProfile.setSignMethod("HmacSHA256");
            //clientProfile.setHttpProfile(httpProfile);
            /* 实例化要请求产品(以sms为例)的client对象 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量*/

            SmsClient client = new SmsClient(cred, "", clientProfile);
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 你可以直接查询SDK源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用IDE进行开发，可以方便的跳转查阅各个接口和数据结构的文档说明 */

            SendSmsResponse res = client.SendSms(req);
            SendStatus[] responseStat = res.getSendStatusSet();
            String reponseCode = responseStat[0].getCode();

            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));

            if (reponseCode.equals("Ok")) {
                result = "200";
            } else {
                result = "400";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
