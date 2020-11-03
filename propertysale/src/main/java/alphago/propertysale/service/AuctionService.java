package alphago.propertysale.service;

import alphago.propertysale.entity.Auction;
import alphago.propertysale.entity.AuctionStatus;
import alphago.propertysale.entity.returnVO.AuctionVO;
import alphago.propertysale.entity.returnVO.SearchVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AuctionService extends IService<Auction> {

    AuctionVO getAuctionByAid(long aid);

    AuctionVO getAuctionByProperty(long pid);

    AuctionStatus getAuctionStatus(long pid);

    void auctionCancel(long pid, long aid);

    void initHistory(long aid);

    void finishAuction(long aid);

    SearchVO getSearchVO(boolean isAuthenticated);
}
