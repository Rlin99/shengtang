package club.banyuan.dao.impl;

import club.banyuan.dao.AddressDao;
import club.banyuan.entity.Address;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImlp extends BaseDaoImpl implements AddressDao {

    public AddressDaoImlp(Connection connection) {
        super(connection);
    }

    @Override
    public Address tableToClass(ResultSet rs) throws Exception {
        Address address = new Address();
        address.setId(rs.getInt(1));
        address.setUserId(rs.getInt(2));
        address.setAddress(rs.getString(3));
        address.setCreateTime(rs.getDate(4));
        address.setIsDefault(rs.getInt(5));
        address.setRemark(rs.getString(6));
        return address;
    }

    @Override
    public int add(Address address) {
        String sql = "insert into user_address values(null,?,?,?,?,?)";
        Object[] params = new Object[]{address.getUserId(),address.getAddress(),address.getCreateTime(),
                address.getIsDefault(),address.getRemark()};
        int i= executeInsert(sql,params);
        return i;
    }

    @Override
    public List<Address> getAddressById(Integer userId) throws Exception {
        String sql = "select * from user_address where userId = ? ";
        Object[] params = new Object[]{userId};
        ResultSet rs = executeQuery(sql,params);
        List<Address> addressList = new ArrayList<>();
        while(rs.next()){
            Address address = tableToClass(rs);
            addressList.add(address);
        }
        return addressList;
    }
}
