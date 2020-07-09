package club.banyuan.service;

import club.banyuan.entity.Auction;
import club.banyuan.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface AuctionServiceDao {
    public List<Auction> getByProductId(Integer productId) throws Exception;
    public Auction getProductHighPrice(Integer productId) throws  Exception;
    public int add(Auction auction)throws SQLException;
}
