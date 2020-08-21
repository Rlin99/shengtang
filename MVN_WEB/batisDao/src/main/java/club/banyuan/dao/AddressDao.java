package club.banyuan.dao;


import club.banyuan.entity.Address;
import club.banyuan.entity.Product;

import java.util.List;

public interface AddressDao {
    public int addAddress(Address address);

    public List<Address> getAddressById(Integer userId);
}
