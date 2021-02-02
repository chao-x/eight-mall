package bjfu.eight.mall.service;


import bjfu.eight.mall.entity.po.Address;
import bjfu.eight.mall.entity.vo.AddressList;
import bjfu.eight.mall.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    public Address getAddressById(int addrid){
        return addressMapper.getByAddrid(addrid);
    }

    public AddressList setDefault(int addrid, int uid){
        Address[] addresses=addressMapper.getByUserid(uid);
        for(Address address:addresses){
            addressMapper.updateDefault(address.getId(),0);
            if(address.getId()==addrid){
                addressMapper.updateDefault(address.getId(),1);
            }
        }
        AddressList addressList=new AddressList();
        addressList.setAddresses(addressMapper.getByUserid(uid));
        return addressList;
    }

    public AddressList findaddrs(int uid){
        AddressList addressList=new AddressList();
        addressList.setAddresses(addressMapper.getByUserid(uid));
        return addressList;
    }

    public AddressList delAddr(int addrid,int uid){
        Address[] addresses=addressMapper.getByUserid(uid);
        for(Address address:addresses) {
            if (address.getId() == addrid) {
                addressMapper.updateDelstate(address.getId(), 1);
            }
        }
        AddressList addressList=new AddressList();
        addressList.setAddresses(addressMapper.getByUserid(uid));
        return addressList;
    }

    public AddressList saveAddr(Address address,int uid){
        address.setUserId(uid);
        address.setDefaultAddr(0);
        address.setDelState(0);
        addressMapper.insertAddress(address);
        AddressList addressList=new AddressList();
        addressList.setAddresses(addressMapper.getByUserid(uid));
        return addressList;
    }
}
