package club.banyuan.dao;

import club.banyuan.entity.Auction;

import java.sql.SQLException;
import java.util.List;

public interface AuctionDao extends IBaseDao{
    public List<Auction> selectByProductId(Integer id) throws Exception;
    public Auction getProductHighPrice(Integer id )throws  Exception;
    public int add(Auction auction);
}
