package cc.valuenet.wladmin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wuqu
 * @since 2021-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Integer userId;

    @TableField("rid")
    private Integer rid;


}
