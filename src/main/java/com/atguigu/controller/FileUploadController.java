package com.atguigu.controller;


import com.atguigu.entity.UserIcons;
import com.atguigu.service.UserIconsService;
import com.atguigu.util.CommParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/img")
public class FileUploadController {

    private Logger logger = LoggerFactory.getLogger(FileUploadController.class);
   @Resource
    private UserIconsService userIconsService;

    /**
     * @createtime 2017年8月20日17:15:41
     * @param request
     * @param file
     * @return 上传成功返回“success”，上传失败返回“error”
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/upload")
    public Map<String,String> upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Map<String,String> map = new HashMap<String,String>();
        System.out.println("执行upload");
        request.setCharacterEncoding("UTF-8");
        logger.info("执行图片上传");

        String user_id = request.getParameter("user_id");
        /**
         * photo_type 1--头像  2--证书  3--相册  4--
         */
        String photo_type = request.getParameter("type");

        logger.info("user_id:"+user_id+"   photo_type:"+photo_type);
        String path = null;
        String trueFileName = null;
        if(!file.isEmpty()) {
            logger.info("成功获取照片");
            String fileName = file.getOriginalFilename();

            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {
                if ("JPEG".equals(type.toUpperCase())||"GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
//                    String realPath = request.getSession().getServletContext().getRealPath("/");
//                    String realPath= CommParams.IMG_LOCATION_TEST;
                    String realPath= CommParams.IMG_LOCATION;
                    // 自定义的文件名称
                    trueFileName = String.valueOf(System.currentTimeMillis()) +(int)(1+Math.random()*100)+ "."+type;
                    //创建存储上传图片的文件
                    String pathbefore = realPath+"/"+user_id+"/"+photo_type+"/";
                    File filebefore = new File(pathbefore);
                    if(!filebefore.exists()){
                        logger.info("创建目录。。。。");
                        filebefore.mkdirs();
                    }
                    // 设置存放图片文件的路径
                    path = pathbefore + trueFileName;
                    logger.info("存放图片文件的路径:" + path);
                    file.transferTo(new File(path));
                    logger.info("文件成功上传到指定目录下");
                }else {
                    logger.info("不是我们想要的文件类型,请按要求重新上传");
                    map.put("message_flag","error");
                    map.put("message","error,不是我们想要的文件类型,请按要求重新上传");
                    return map;
                }
            }else {
                logger.info("文件类型为空");
                map.put("message_flag","error");
                map.put("message","文件类型为空");
                return map;
            }
        }else {
            logger.info("没有找到相对应的文件");
            map.put("message_flag","error");
            map.put("message","没有找到相对应的文件");
            return map;
        }
        map.put("message_flag","success");
        map.put("message","文件上传成功");
        map.put("imgUrl",path);
        //文件上传成功后将图片信息保存到数据库
        UserIcons userIcons = new UserIcons();
        userIcons.setUserId(user_id);
        userIcons.setIconName(trueFileName);
        userIcons.setIconUrl(path);
        userIcons.setType(Integer.parseInt(photo_type));
        userIcons.setUp_time(new Date());

        boolean flag = userIconsService.insertUserIcon(userIcons);
        logger.info("=-=--=-=-=-=-=-=- flag is: "+flag);
        map.put("insert_flag",""+flag);
        return map;
    }

    @ResponseBody
    @RequestMapping("/load")
    public List<UserIcons> load(@RequestParam("userid") String userid, HttpServletResponse response){
        List<UserIcons> userIconsList = userIconsService.selectByUser(userid);
        return userIconsList;
    }

    @ResponseBody
    @RequestMapping("/load1")
    public List<Map<Object,Object>> load(@RequestParam("type") Integer type, HttpServletResponse response){
        List<UserIcons> list = userIconsService.selectByType(type);
        List<Map<Object,Object>> resList=new ArrayList<>();
        for(UserIcons u :list){
            UserIcons  ui=(UserIcons)u;
            String img_url=CommParams.WEB_URL+ui.getIconUrl().replaceAll("/app/test","");
            Map<Object,Object> map=new HashMap<>();
            map.put("img_url",img_url);

//            resList.add(memberService.getMemberById(mem_id));
//            map=qryMyMemSum(mem_id);
            resList.add(map);
            logger.info(img_url);
        }
        return resList;
    }
}
