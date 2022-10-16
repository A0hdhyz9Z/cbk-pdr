package team.cbk.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.cbk.demo.pojo.Order;
import team.cbk.demo.pojo.score;

import java.util.ArrayList;

@Mapper
public interface OrderMapper {
    int selectcountTempRecordByUserName(@Param("username") String username);

    int insertTempRecordByUserName(@Param("username") String username);

    int selectTempOrderIdByUserName(@Param("username") String username);

    int updateTempRecordOrderAddressByUserName(@Param("username") String username , @Param("address") String address);

    int updateTempRecordOrderToCompleteOrderByOrder(@Param("order")Order order);

    int insertOrderByOrder(@Param("order") Order order);

    int selectOrderIdByOrder(@Param("order") Order order);

    String selectOrderDataAddress(@Param("order")Order order);

    ArrayList<Order> selectAllOrderByOrderStatus();

    int updataOrderStatus(@Param("id")int id);

    ArrayList<Order> selectAllOrdersByUserName(@Param("username")String username);

    ArrayList<score> selectScoreByOrderId(@Param("id")int id);
}
