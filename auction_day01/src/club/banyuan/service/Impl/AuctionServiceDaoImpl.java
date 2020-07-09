package club.banyuan.service.Impl;

import club.banyuan.dao.AuctionDao;
import club.banyuan.dao.impl.AuctionDaoImpl;
import club.banyuan.entity.Auction;
import club.banyuan.entity.Product;
import club.banyuan.service.AuctionServiceDao;
import club.banyuan.util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class AuctionServiceDaoImpl implements AuctionServiceDao {
    @Override
    public List<Auction> getByProductId(Integer productId) throws Exception {
        AuctionDao auctionDao = new AuctionDaoImpl(JdbcUtils.getConnection());
        return auctionDao.selectByProductId(productId);
    }

    @Override
    public Auction getProductHighPrice(Integer productId) throws Exception {
        AuctionDao auctionDao = new AuctionDaoImpl(JdbcUtils.getConnection());
        return auctionDao.getProductHighPrice(productId);
    }

    @Override
    public int add(Auction auction) throws SQLException {
        AuctionDao auctionDao = new AuctionDaoImpl(JdbcUtils.getConnection());
        return auctionDao.add(auction);
    }
}
