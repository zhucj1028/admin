package cc.valuenet.wladmin.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author wuqu
 * @since 2021-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 操作人名称
     */
    @TableField("name")
    private String name;

    /**
     * 操作内容
     */
    @TableField("operation")
    private String operation;

    /**
     * 操作时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;


}
