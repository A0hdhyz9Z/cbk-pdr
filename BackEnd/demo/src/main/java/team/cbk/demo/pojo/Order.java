package team.cbk.demo.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Order实体类", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单提交时间")
    private LocalDateTime orderTime;

    @ApiModelProperty(value = "提交订单的用户名")
    private String userName;

    @ApiModelProperty(value = "订单名称")
    private String orderName;

    @ApiModelProperty(value = "订单描述")
    private String orderDescription;

    @ApiModelProperty(value = "订单数据集（0，1，2，3）代表四种数据集 3是自定义")
    private Integer orderData;

    @ApiModelProperty(value = "自定义数据集存放位置")
    private String orderDataAddress;

    @ApiModelProperty(value = "订单状态（0 处理中 1已完成）")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单选择的算法（二进制）")
    private Integer orderAlg;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "订单是否离线训练")
    private Integer orderOnline;
}
