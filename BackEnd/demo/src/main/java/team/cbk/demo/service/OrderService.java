package team.cbk.demo.service;

import org.springframework.web.multipart.MultipartFile;
import team.cbk.demo.pojo.Order;
import team.cbk.demo.pojo.User;
import team.cbk.demo.pojo.score;

import java.util.ArrayList;

public interface OrderService {
    int upload(MultipartFile file, User user);

    int insertNewOrder(Order order);

    int updateTempOrder(Order order);

    ArrayList<Order> selectOrders(String username);

    ArrayList<score> selectScore(int orderid);

    int predict(MultipartFile file, User user,int orderId,int Alg);


}
