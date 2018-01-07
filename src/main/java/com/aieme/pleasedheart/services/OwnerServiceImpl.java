package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Owner;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.aieme.pleasedheart.repositories.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public int insert(Owner record) {
        return ownerRepository.insert(record);
    }

    @Override
    public void update(Owner record) {
        ownerRepository.update(record);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner findById(int id) {
        return ownerRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        ownerRepository.deleteAll();
    }

    @Override
    public boolean exist(Owner record) {
        return ownerRepository.exist(record);
    }

}
