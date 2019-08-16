package com.example.image.config.img;


import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 验证码信息
 *
 */
public class ImageCode  {

    private static final long serialVersionUID = 1111544715170749226L;

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
        this.image = image;
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageCode{" +
                "image=" + image +
                ", code='" + code + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}
