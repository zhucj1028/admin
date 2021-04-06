package cc.valuenet.wladmin.mapper;

import cc.valuenet.wladmin.model.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuqu
 * @since 2021-03-27
 */
@Repository
public interface LogMapper extends BaseMapper<Log> {

    /**
     * 查询日志
     * @return user
     */
    List findLogs();
}
