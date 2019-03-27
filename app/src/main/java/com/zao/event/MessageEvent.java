package com.zao.event;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/27 10:31
 */
public class MessageEvent {
    private String messageA;
    private String messageB;
    private String index;


    public MessageEvent(String index, String messageA, String messageB) {
        this.index = index;
        this.messageA = messageA;
        this.messageB = messageB;
    }



    public String[] getStringArray() {
        return new String[]{index, messageA, messageB};
    }
}