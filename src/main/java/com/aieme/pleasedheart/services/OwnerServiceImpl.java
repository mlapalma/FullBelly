package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Owner;
import org.springframework.stereotype.Service;
import com.aieme.pleasedheart.models.dao.OwnerDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerDao ownerDao;

    @Override
    public int insert(Owner record) {
        return ownerDao.insert(record);
    }

    @Override
    public void update(Owner record) {
        ownerDao.update(record);
    }

    @Override
    public List<Owner> findAll() {
        return ownerDao.findAll();
    }

    @Override
    public Owner findById(int id) {
        return ownerDao.findById(id);
    }

    @Override
    public void delete(int id) {
        ownerDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        ownerDao.deleteAll();
    }

    @Override
    public boolean exist(Owner record) {
        return ownerDao.exist(record);
    }

}
