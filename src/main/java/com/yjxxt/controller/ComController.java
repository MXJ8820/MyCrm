package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.Com;
import com.yjxxt.query.ComQuery;
import com.yjxxt.service.ComService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("com")
public class ComController extends BaseController {

    @Resource
    private ComService comService;

    @Resource
    private HttpSession httpSession;

    @RequestMapping("toComPage")
    public String toCommPage(){

        return "com/com";
    }

    @RequestMapping("listShow")
    @ResponseBody
    public Map<String,Object> listShow(ComQuery comQuery){
        return comService.queryAllComs(comQuery);
    }

    @RequestMapping("addOrUpdateComPage")
    public String addOrUpdateComPage(Integer id, HttpServletRequest request){

        if (id != null){
            Com com = comService.selectByPrimaryKey(id);

            request.setAttribute("com",com);

        }

        return "com/add_update";
    }

    @RequestMapping("queryComState")
    @ResponseBody
    public Integer queryComState(Integer id){
        return comService.queryComState(id);
    }

    /**
     * 上传
     * @param file
    @RequestMapping("upload")
    @ResponseBody
    public ResultInfo upload(MultipartFile file, Model model){

        if (file.isEmpty()) {
            System.out.println("文件为空空");
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setMsg("文件为空");
            resultInfo.setCode(300);
            return resultInfo;
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String filePath = "E:\\yjxxt54\\daima\\CRMzhenghe\\crmzhengheceshi\\crm1\\src\\main\\resources\\static\\images\\com\\"; // 上传后的路径

        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpSession.setAttribute("imgAddr",fileName);

        return success("上传成功",fileName);
    }

*/
    @RequestMapping("upload")
    @ResponseBody
    public ResultInfo upload(MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty() ){
            try {
                // 获取项目的所在的路径 （绝对路径）
                String rootPath = request.getServletContext().getRealPath("/");

                System.out.println(rootPath);
                File uploadFile = new File(rootPath +File.separator+ "images");
                if (!uploadFile.exists()) {
                    // 新建目录
                    uploadFile.mkdir();
                }

                // 获取上传文件的原文件名
                String originalName = file.getOriginalFilename();

                // 获取上传的文件的后缀
                String suffix = originalName.substring(originalName.lastIndexOf("."));

                // 写文件到服务器
                String fileName = System.currentTimeMillis()+suffix;
                System.out.println(fileName);
                File severFile = new File(uploadFile,fileName);

                // 上传文件 (转存文件到指定目录)
                file.transferTo(severFile);

                request.getSession().setAttribute("imgAddr",fileName);
                return success("上传成功",fileName);
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg("文件为空");
        resultInfo.setCode(300);
        return resultInfo;

    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addCom(Com com){
        com.setImgAddr((String) httpSession.getAttribute("imgAddr"));
        comService.addCom(com);
        return success("添加成功");
    }


    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCom(Com com){
        com.setImgAddr((String) httpSession.getAttribute("imgAddr"));
        comService.updateCom(com);
        return success("修改成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCom(Integer[] ids){

        comService.deleteCom(ids);

        return success("删除成功");
    }
}
