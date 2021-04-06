package cc.valuenet.wladmin.service.impl;

import cc.valuenet.wladmin.model.Log;
import cc.valuenet.wladmin.mapper.LogMapper;
import cc.valuenet.wladmin.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqu
 * @since 2021-03-27
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
