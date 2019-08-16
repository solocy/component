package com.example.image.config.img;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CodeCookie {

    private static Map<String,ImageCode> cookie = new ConcurrentHashMap<>();

    public static ImageCode getCookie(String ip) {
        System.out.println(cookie.toString());
        return cookie.get(ip);
    }

    public static void setCookie(String ip, ImageCode imageCode) {
        cookie.put(ip,imageCode);
    }

    public static void removeCookie(String ip) {
        cookie.remove(ip);
    }
}
