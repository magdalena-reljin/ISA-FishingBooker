package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.uns.ftn.isa.fisherman.model.Evaluation;
import rs.ac.uns.ftn.isa.fisherman.repository.EvaluationRepository;
import rs.ac.uns.ftn.isa.fisherman.service.EvaluationService;

import java.util.List;

public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Override
    public List<Evaluation> getAll() {
        return evaluationRepository.getAll();
    }
}
