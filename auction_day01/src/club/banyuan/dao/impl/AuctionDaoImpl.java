package club.banyuan.dao.impl;

import club.banyuan.dao.AuctionDao;
import club.banyuan.entity.Auction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuctionDaoImpl extends BaseDaoImpl implements AuctionDao {
    public AuctionDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Auction> selectByProductId(Integer id) throws Exception {
        String sql = "select * from auction where productId = ? order by price desc";
        ResultSet rs = executeQuery(sql, new Object[]{id});
        List<Auction> auctionList = new ArrayList<>();
        while(rs.next()){
            Auction auction = new Auction();
            auction = tableToClass(rs);
            auctionList.add(auction);
        }
        return auctionList;
    }

    @Override
    public int add(Auction auction) {
        String sql = "insert into auction values(null,?,?,?,?,?)";
        Object[] params = new Object[]{auction.getUserId(),auction.getUesrName(),auction.getProductId(),
                auction.getCreateTime(),auction.getPrice()};
        int i = executeInsert(sql, params);
        return i;
    }

    @Override
    public Auction tableToClass(ResultSet rs) throws Exception {
        Auction auction = new Auction();
        auction.setId(rs.getInt(1));
        auction.setUserId(rs.getInt(2));
        auction.setUesrName(rs.getString(3));
        auction.setProductId(rs.getInt(4));
        auction.setCreateTime(rs.getTimestamp(5));
        auction.setPrice(rs.getDouble(6));
        return auction;
    }
}
