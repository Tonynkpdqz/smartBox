package com.smartBox.action;


import com.smartBox.domain.User;
import com.smartBox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

@org.springframework.stereotype.Controller
@RequestMapping("/UserAction")
@SessionAttributes("user")
public class Controller {
    @Autowired
    private UserService service;

    public void setService(UserService service) {

        this.service = service;

    }

    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                           @RequestParam("authorities") String auths,
                         HttpServletResponse response) throws IOException {
        System.out.println("first");
        PrintWriter out = response.getWriter();
        String result;
        if (username.isEmpty() || password.isEmpty()){
            result = "username or password should not be null!";
            return "redirect:localhost:9090";
        } else {
            boolean isOK = service.register(username, password,auths);
            System.out.println("done!!!");
            if (isOK){
                result = "ok";
                System.out.println("done this");
                return "redirect:localhost:9090";
            } else {
                result = "username has been registered";
                return "redirect:localhost:9090";
            }

        }

    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      ModelMap model, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String result;
        User user = service.getUser(username);
        if (user == null){
            result = "user is not exist";
            return "redirect:localhost:9090";
        } else {
            if (!user.getPassword().equals(password)){
                result = "password is wrong!";
                return "redirect:localhost:9090";
            } else {
                result = "ok";
                model.addAttribute("user",user);
                System.out.println(user);
                return "home";
            }
        }
    }



    //不使用此接口
    @RequestMapping("/auth")
    public void getAuth(@RequestParam("authorities") String auths, ModelMap modelMap){
        User user = (User) modelMap.get("user");
        System.out.println(user);
        /*StringBuilder authrs = new StringBuilder();
        for (String s:auths){
            authrs.append(s).append(",");
        }*/
        user.setAuthorities(auths);
        service.addauth(auths,user.getUsername());
        modelMap.addAttribute("user",user);
    }


    @ModelAttribute("user")
    public User getUser(){
        User user = new User();
        return user;
    }

    static String authss = "no";
    @RequestMapping("/authority")
    @ResponseBody
    public String open(@RequestParam("authority") String auth,
                     ModelMap modelMap,HttpServletResponse response) throws IOException {
        //PrintWriter out = response.getWriter();
        String result;
        User user = (User) modelMap.get("user");
        System.out.println(user);
        String authorities = user.getAuthorities();
        if (authorities.contains(auth.toUpperCase())){
            result = user.getPassword();
            System.out.println("stop!!!!!");
            //这里要给箱子发送开锁指令，即发送result
            Socket socket = new Socket("s.ganhailin.cn",666);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            writer.write(auth.toUpperCase());
            writer.flush();
            socket.shutdownOutput();
            outputStream.close();
            writer.close();
            socket.close();
            System.out.println("keyinadaoaaaa" + result);
            //out.write("1");
            //authss = result;
            return "1";
        } else {
            result = "You don`t have authority to open the box";
            System.out.println("buxingaaa" + result);
            //out.write("1");
            return "0";
        }
    }

    @RequestMapping(value = "/users",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public User getuser(HttpServletRequest request){
        User user = new User();
        user.setId(122);
        user.setPassword("123");
        user.setUsername("xiaoming");
        return user;
    }

}
