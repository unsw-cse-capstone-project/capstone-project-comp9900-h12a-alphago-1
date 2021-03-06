package alphago.propertysale.mapper;

import alphago.propertysale.entity.POJO.History;
import alphago.propertysale.entity.POJO.HistoryUpdater;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * @Description: Data access layer for History table in database
 */
public interface HistoryMapper extends BaseMapper<History> {

    @Update("UPDATE property_sale.history\n" +
            "SET bedroom_num = bedroom_num + #{bedroomNum}, \n" +
            "bathroom_num=bedroom_num + #{bathroomNum},\n" +
            "garage_num=garage_num + #{garageNum}, \n" +
            "lat=lat+#{lat}, \n" +
            "lng=lng+#{lng}, \n" +
            "bedroom_cnt=bedroom_cnt + 1, \n" +
            "bathroom_cnt=bathroom_cnt + 1, \n" +
            "garage_cnt= garage_cnt + 1, \n" +
            "lat_cnt= lat_cnt + 1, \n" +
            "lng_cnt=lng_cnt + 1 \n" +
            "WHERE uid=#{uid};")
    void updateByVisit(HistoryUpdater historyUpdater);
}
