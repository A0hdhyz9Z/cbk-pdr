package team.cbk.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import team.cbk.demo.pojo.Order;
import team.cbk.demo.pojo.RespBean;
import team.cbk.demo.pojo.User;
import team.cbk.demo.pojo.score;
import team.cbk.demo.service.OrderService;
import team.cbk.demo.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@Api(value="订单controller",tags={"订单信息接口"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value="上传文件",notes="用户必须先登陆 key为file value为文件信息 网址格式为 /order/upload")
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
    @ApiOperation(value="提交订单",notes="用户必须先登陆 " +
            "提供订单名称(orderName),订单描述(orderDescription),订单数据集选择(orderData)(订单数据集（0，1，2，3）代表四种数据集 0是JDT 1是PDE 2是Lucene 3是自定义)" +
            "订单选择的算法(orderAlg)(二进制数字abc)(决策树为a knn为b 逻辑回归为c)" +
            "网址格式为 /order/commit?orderName={订单名称}&orderDescription={订单描述}&orderData={数据集选择}&orderAlg={算法选择}")
    @PostMapping("/order/commit")
    public RespBean commitOrder(@PathParam("orderName") String orderName,
                                @PathParam("orderDescription") String orderDescription,@PathParam("orderData") Integer orderData,
                                @PathParam("orderAlg") Integer orderAlg,HttpServletRequest req){
        String orderDataAddress = null;
        switch (orderData){
            case 0:
                orderDataAddress = Constants.JDT;
                break;
            case 1:
                orderDataAddress=Constants.PDE;
                break;
            case 2:
                orderDataAddress=Constants.Lucene;
                break;
            default:
                break;
        }
        LocalDateTime orderTime =  LocalDateTime.now();
        HttpSession session = req.getSession();
        int orderOnline = 1;
        User loginUser = (User) session.getAttribute("loginUser");
        Order order = new Order(orderTime, loginUser.getUserName(), orderName, orderDescription, orderData, orderDataAddress,0, orderAlg, 0,orderOnline);
        if(orderData == 3){
            orderService.updateTempOrder(order);
        }else{
            orderService.insertNewOrder(order);
        }
        return RespBean.success("订单上传成功");
    }

    @ApiOperation(value="查询用户所有订单记录",notes="用户必须先登陆 不用传参，用户信息从session域中获取 网址格式为 /order/record")
    @PostMapping("/order/record")
    public RespBean record(HttpServletRequest req){
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        ArrayList<Order> orders = orderService.selectOrders(loginUser.getUserName());
        ArrayList<Order> ans = new ArrayList<>();
        for(Order index : orders){
           if (!index.getOrderName().equals("temp"))
               ans.add(index);
        }
        return RespBean.success("查询成功",ans);
    }
    @ApiOperation(value="用户订单中各算法指标数据的查询",notes="用户必须先登陆 需要订单id 网址格式为 /order/detail/{orderid}")
    @PostMapping("/order/detail/{orderid}")
    public RespBean detail(@PathVariable("orderid") int orderid){
        ArrayList<score> score = orderService.selectScore(orderid);
        return RespBean.success("算法对比数据查询成功",score);
    }
    //clean 1 buggy 0   logist1 knn2 decision3
    @ApiOperation(value="预测功能",notes="用户必须先登陆 需要先上传文件，将文件、订单id、预测算法(1为逻辑回归 2为knn 3为决策树)一并传入" +
            "网址格式为 /order/predict")
    @PostMapping("/order/predict")
    public RespBean Predict(@RequestParam("file")MultipartFile file,@RequestParam("orderId")int orderId,@RequestParam("alg")int alg,HttpServletRequest req){
        if (file.isEmpty())
            return RespBean.error("文件为空");
        if(!file.getOriginalFilename().endsWith(".csv"))
            return RespBean.error("文件格式错误");
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        int predict = orderService.predict(file, loginUser, orderId, alg);
        if(predict == -1){
            return RespBean.error("未知异常，预测失败");
        }else {
            return RespBean.success("预测成功 1是clean 0是buggy",predict);
        }
    }
}
