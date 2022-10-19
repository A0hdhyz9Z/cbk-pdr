package team.cbk.demo.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Alg实体类", description="")
public class Alg implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "订单编号")
    private Integer orderId;
    @ApiModelProperty(value = "算法名(用整数标识,1代表第一种算法，2代表第二种，以此类推)")
    private Integer algName;
    @ApiModelProperty(value = "auc值")
    private Integer auc;
    @ApiModelProperty(value = "roc存储位置")
    private String rocAdress;
    @ApiModelProperty(value = "f1分数")
    private Integer f1Score;
    @ApiModelProperty(value = "精度")
    private Integer precission;
    @ApiModelProperty(value = "召回率")
    private Integer recall;
    @ApiModelProperty(value = "准确率")
    private Integer accuracy;
    @ApiModelProperty(value = "模型存放地址")
    private String algAddress;
}
