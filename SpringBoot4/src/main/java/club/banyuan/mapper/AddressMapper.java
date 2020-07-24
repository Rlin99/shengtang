package club.banyuan.mapper;


import club.banyuan.entity.Address;
import club.banyuan.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AddressMapper {
    public int addAddress(Address address);
    public List<Address> getAddressById(Integer userId);
}
