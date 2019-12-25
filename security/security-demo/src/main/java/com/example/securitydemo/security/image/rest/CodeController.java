package com.example.securitydemo.security.image.rest;

import com.example.securitydemo.security.image.ImageCode;
import com.example.securitydemo.security.image.ImageCodeGenerator;
import com.example.securitydemo.security.image.service.CodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

@Api(tags = "系统：验证码")
@RestController
@RequestMapping("/api/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping
    @ApiOperation("获取验证码")
    public void getCode(HttpServletResponse response) throws IOException {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        ImageCode imageCode = imageCodeGenerator.createCode();
        BufferedImage image = imageCode.getImage();
        String uuid = UUID.randomUUID().toString();
        codeService.saveCode(uuid,imageCode.getCode());
        response.setContentType("image/jpeg");
        response.setHeader("imageUUID",uuid);
        ImageIO.write(image, "jpeg", response.getOutputStream());
    }
}
