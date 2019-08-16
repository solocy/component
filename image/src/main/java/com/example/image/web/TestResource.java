package com.example.image.web;

import com.example.image.config.img.CodeCookie;
import com.example.image.config.img.ImageCode;
import com.example.image.config.img.ImageCodeGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TestResource {

    private ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();

    /**
     * 获取验证码图片
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/image")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.createCode();
        BufferedImage image = imageCode.getImage();
        imageCode.setImage(null);
        String  ip = request.getRemoteAddr().trim();
        CodeCookie.setCookie(ip, imageCode);
        response.setContentType("image/jpeg");
        ImageIO.write(image, "jpeg", response.getOutputStream());
    }

    @GetMapping("test")
    public String test() {
        return "success";
    }
}
