package com.yunerp.server3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by DIY on 2016/11/9.
 */
@Controller
public class IndexController {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    //    @RequestMapping(value = "")
//    public String toIndex(){
//        return "aa.html";
//    }
    @RequestMapping(value = "")
    public String toHello(Model model) {
        model.addAttribute("name", "你好");
        return "hello";
    }

    @RequestMapping(value = "setHtml")
    @ResponseBody
    public String setHtml(HttpSession session, HttpServletRequest request) {
        String a = request.getContextPath();
        String ab = request.getServletPath();

//        String path = session.getServletContext().getRealPath("/")+"WEB-INF/static/aa.html";
        String path = this.getClass().getResource("/").getPath() + "static/aa.html";
        String strHtml = getHtmlToString(path);
        session.setAttribute("aa", strHtml);
        return "成功";
    }

    @RequestMapping(value = "getHtml")

    public void getHtml(HttpSession session, HttpServletResponse response) {


//        return (String) session.getAttribute("aa");
        response.setContentType("");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(session.getAttribute("aa"));
//        out.println("<html><head></head><body>1111</body></html>");
    }


    public String getHtmlToString(String path) {
        String str = "";
        File myFile = new File(path);

        if (!myFile.exists()) {
            return null;
        }
        try {
//            BufferedReader in = new BufferedReader(new FileReader(myFile));
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(myFile),"utf-8"));
            String strTemp;
            while ((strTemp = in.readLine()) != null) {
                str +=strTemp;
                System.out.println(str);
            }
            in.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

        return str;
    }


//    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveFilename),"GB2312")));
}
