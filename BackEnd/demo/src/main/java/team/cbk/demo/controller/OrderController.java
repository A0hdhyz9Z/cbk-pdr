package team.cbk.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.cbk.demo.pojo.RespBean;
import team.cbk.demo.pojo.User;
import team.cbk.demo.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Api(value="订单controller",tags={"订单信息接口"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/upload")
    public RespBean uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest req){
        if (file.isEmpty())
            return RespBean.error("文件为空");
        if(!file.getOriginalFilename().endsWith(".csv"))
            return RespBean.error("文件格式错误");
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        int upload = orderService.upload(file, loginUser);
        if(upload == 1){
            return RespBean.success("上传成功");
        }else{
            return RespBean.error("上传失败");
        }
    }


}
