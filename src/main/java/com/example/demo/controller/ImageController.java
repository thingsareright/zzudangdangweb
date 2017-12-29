package com.example.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.bean.Image;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.entity.User;
import com.example.demo.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.rmi.runtime.Log;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String setterUserImage(@RequestBody Image image) throws IOException {
        //获取图片的64位编码
        String id = image.getPhone();
        String base64 = image.getBase64();
        //将base64位编码写入图片文件
        System.out.println(base64);
        boolean flag = FileUtil.generateImage(base64, id);
        if (flag == false) {
            System.out.println("上传文件" + id + "失败");
        }

        //对后台数据库进行刷新
        User user = new User();
        user = userRepository.findTopByPhone(id);
        if (user == null)
            return "";
        user.setHead(id + ".jpg");
        userRepository.save(user);
        //返回图片的链接地址
        return id + ".jpg";
    }

}
