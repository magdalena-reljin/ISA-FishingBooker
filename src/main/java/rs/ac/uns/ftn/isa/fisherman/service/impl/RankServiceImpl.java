package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.enums.RankType;
import rs.ac.uns.ftn.isa.fisherman.model.Rank;
import rs.ac.uns.ftn.isa.fisherman.repository.RankRepository;
import rs.ac.uns.ftn.isa.fisherman.service.RankService;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankRepository rankRepository;
    @Override
    public List<Rank> getAll() {
        return rankRepository.findAll();
    }

    @Override
    public void update(Rank rank) {
        rankRepository.save(rank);
    }

    @Override
    public Integer getPointsByRank(Integer rank) {
        return  rankRepository.getPointsByRank(rank);
    }

    @Override
    public Integer getDiscountByRank(Integer rank) {
        return rankRepository.getDiscountByRank(rank);
    }
}
