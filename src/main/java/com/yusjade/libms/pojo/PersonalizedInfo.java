package com.yusjade.libms.pojo;

import lombok.Data;

/**
 * tb_personalized_info
 * @author 19179
 * @date yyyy-MM-dd HH:mm:ss
 */
@Data
public class PersonalizedInfo {
    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 用户签名
     */
    private String signature;

    /**
     * 用户头像
     */
    private byte[] avatar;

    public PersonalizedInfo(Long userId, String signature) {
        this.userId = userId;
        this.signature = signature;
    }

    public PersonalizedInfo(Long userId, String signature, byte[] avator) {
        this.userId = userId;
        this.signature = signature;
        this.avatar = avator;
    }

    public PersonalizedInfo() {
        super();
    }
}