package club.banyuan.dao;

import club.banyuan.entity.Auction;

import java.util.List;

public interface AuctionDao {
    public List<Auction> selectByProductId(Integer id);
    public Auction getProductHighPrice(Integer id);
    public int add(Auction auction);
}
