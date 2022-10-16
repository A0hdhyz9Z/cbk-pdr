package team.cbk.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface AlgMapper {
    int insertAlgByOrderIdAndAlgName(@Param("orderId")Integer orderId, @Param("algName")int Integer);

    int insertAcc(@Param("id")Double id, @Param("arr1")Double arr1, @Param("arr2")Double arr2
            , @Param("arr3")Double arr3, @Param("arr4")Double arr4, @Param("arr5")Double arr5
            , @Param("arr6")Double arr6, @Param("arr7")Double arr7, @Param("arr8")Double arr8
            , @Param("arr9")Double arr9, @Param("arr10")Double arr10, @Param("orderId")Double orderid);

    int insertPre(@Param("id")Double id, @Param("arr1")Double arr1, @Param("arr2")Double arr2
            , @Param("arr3")Double arr3, @Param("arr4")Double arr4, @Param("arr5")Double arr5
            , @Param("arr6")Double arr6, @Param("arr7")Double arr7, @Param("arr8")Double arr8
            , @Param("arr9")Double arr9, @Param("arr10")Double arr10, @Param("orderId")Double orderid);

    int insertRecall(@Param("id")Double id, @Param("arr1")Double arr1, @Param("arr2")Double arr2
            , @Param("arr3")Double arr3, @Param("arr4")Double arr4, @Param("arr5")Double arr5
            , @Param("arr6")Double arr6, @Param("arr7")Double arr7, @Param("arr8")Double arr8
            , @Param("arr9")Double arr9, @Param("arr10")Double arr10, @Param("orderId")Double orderid);

    int insertF1(@Param("id")Double id, @Param("arr1")Double arr1, @Param("arr2")Double arr2
            , @Param("arr3")Double arr3, @Param("arr4")Double arr4, @Param("arr5")Double arr5
            , @Param("arr6")Double arr6, @Param("arr7")Double arr7, @Param("arr8")Double arr8
            , @Param("arr9")Double arr9, @Param("arr10")Double arr10, @Param("orderId")Double orderid);
}
